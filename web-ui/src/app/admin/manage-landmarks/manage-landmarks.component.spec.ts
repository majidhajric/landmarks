import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageLandmarksComponent } from './manage-landmarks.component';

describe('ManageLandmarksComponent', () => {
  let component: ManageLandmarksComponent;
  let fixture: ComponentFixture<ManageLandmarksComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManageLandmarksComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageLandmarksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
