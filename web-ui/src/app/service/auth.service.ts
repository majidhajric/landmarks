import {Injectable, OnDestroy} from '@angular/core';
import {AuthConfig, JwksValidationHandler, NullValidationHandler, OAuthEvent, OAuthService} from 'angular-oauth2-oidc';
import {BehaviorSubject, Observable, Subject, Subscription} from 'rxjs';
import {Router} from '@angular/router';
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AuthService implements OnDestroy {


  constructor(private oauthService: OAuthService, private router: Router) {
    this.configure();
  }

  private isAuthenticatedSubject: Subject<boolean> = new BehaviorSubject<boolean>(false);
  private authEventSubscription: Subscription;

  private readonly authConfig: AuthConfig = {
    issuer: environment.authServer,
    redirectUri: window.location.origin,
    clientId: environment.authClientId,
    scope: 'openid profile email offline_access landmarks_manage',
    responseType: 'code',
    disableAtHashCheck: true,
    showDebugInformation: !environment.production,
    requireHttps: false
  };

  ngOnDestroy(): void {
    if (this.authEventSubscription) {
      this.authEventSubscription.unsubscribe();
    }
  }

  private configure(): void {
    this.oauthService.configure(this.authConfig);
    this.oauthService.tokenValidationHandler = new NullValidationHandler();
    this.authEventSubscription = this.oauthService.events.subscribe((e: OAuthEvent) => this.OAuthEventHandler(e));
    if (this.authConfig.issuer) {
      this.oauthService.loadDiscoveryDocumentAndTryLogin()
        .catch((error) => {
          console.log(error.message);
        }).then(v => {
        if (this.oauthService.hasValidAccessToken() && this.oauthService.hasValidIdToken()) {
          this.oauthService.refreshToken();
        }
        this.oauthService.setupAutomaticSilentRefresh();
      });
    }
  }

  private OAuthEventHandler(event: OAuthEvent): void {
    switch (event.type) {
      case 'token_received':
        this.isAuthenticatedSubject.next(true);
        this.router.navigate(['/admin']);
        break;
      case 'session_terminated':
      case 'session_error':
        this.oauthService.logOut();
        break;
      case 'logout':
        this.isAuthenticatedSubject.next(false);
        this.router.navigate(['/']);
        break;
      default:
        break;
    }
  }

  public logIn(): void {
    this.oauthService.initLoginFlow();
  }

  public logOut(): void {
    this.oauthService.logOut();
  }

  public isAuthenticated(): Observable<boolean> {
    return this.isAuthenticatedSubject.asObservable();
  }
}
