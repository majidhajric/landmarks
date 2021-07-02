import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateUpdateLandmarkComponent } from './create-update-landmark.component';

describe('CreateUpdateLandmarkComponent', () => {
  let component: CreateUpdateLandmarkComponent;
  let fixture: ComponentFixture<CreateUpdateLandmarkComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateUpdateLandmarkComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateUpdateLandmarkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
