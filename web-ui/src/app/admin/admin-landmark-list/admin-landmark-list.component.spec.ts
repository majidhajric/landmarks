import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminLandmarkListComponent } from './admin-landmark-list.component';

describe('AdminLandmarkListComponent', () => {
  let component: AdminLandmarkListComponent;
  let fixture: ComponentFixture<AdminLandmarkListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminLandmarkListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminLandmarkListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
