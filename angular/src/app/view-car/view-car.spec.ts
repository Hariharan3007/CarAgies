import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewCar } from './view-car';

describe('ViewCar', () => {
  let component: ViewCar;
  let fixture: ComponentFixture<ViewCar>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ViewCar]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewCar);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
