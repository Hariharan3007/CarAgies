import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewMyCar } from './view-my-car';

describe('ViewMyCar', () => {
  let component: ViewMyCar;
  let fixture: ComponentFixture<ViewMyCar>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ViewMyCar]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewMyCar);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
