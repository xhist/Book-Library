import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-listdialog',
  templateUrl: './listdialog.component.html',
  styleUrls: ['./listdialog.component.css']
})
export class ListdialogComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
import {FormControl} from '@angular/forms';

/** @title Select with multiple selection */
@Component({
  selector: 'select-multiple-lists',
  templateUrl: './listdialog.component.html',
})
export class SelectMultipleExample {
  toppings = new FormControl('');
  toppingList: string[] = ['Extra cheese', 'Mushroom', 'Onion', 'Pepperoni', 'Sausage', 'Tomato'];
}
