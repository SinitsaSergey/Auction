import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TradingDayComponent } from './trading-day.component';

describe('TradingDayComponent', () => {
  let component: TradingDayComponent;
  let fixture: ComponentFixture<TradingDayComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TradingDayComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TradingDayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
