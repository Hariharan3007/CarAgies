import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ServicerDashboard } from './servicer-dashboard';

describe('ServicerDashboard', () => {
  let component: ServicerDashboard;
  let fixture: ComponentFixture<ServicerDashboard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ServicerDashboard]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ServicerDashboard);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
