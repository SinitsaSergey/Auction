import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AuctionCurrentComponent } from './auction-current.component';

describe('AuctionCurrentComponent', () => {
  let component: AuctionCurrentComponent;
  let fixture: ComponentFixture<AuctionCurrentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AuctionCurrentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AuctionCurrentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
