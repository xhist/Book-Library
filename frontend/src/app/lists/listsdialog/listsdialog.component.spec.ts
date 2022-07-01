import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListsdialogComponent } from './listsdialog.component';

describe('ListsdialogComponent', () => {
  let component: ListsdialogComponent;
  let fixture: ComponentFixture<ListsdialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListsdialogComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListsdialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
