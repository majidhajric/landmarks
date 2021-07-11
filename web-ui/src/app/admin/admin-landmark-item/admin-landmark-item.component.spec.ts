import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminLandmarkItemComponent } from './admin-landmark-item.component';

describe('AdminLandmarkItemComponent', () => {
  let component: AdminLandmarkItemComponent;
  let fixture: ComponentFixture<AdminLandmarkItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminLandmarkItemComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminLandmarkItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
