import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormControl} from '@angular/forms';

/** @title Select with multiple selection */
@Component({
  selector: 'select-list',
  templateUrl: './select-list.component.html',
  styleUrls: ['./select-list.component.css']
})
export class SelectListComponent implements OnInit{
  lists = new FormControl('');

  @Input()
  listsList: string[] = [];
  @Input()
  initialList: string[] = [];

  @Output()
  initialListChange = new EventEmitter<string[]>();

  constructor() { }

  ngOnInit(): void {
  }

  onChange(value: string[]) {
    this.initialList = value;
    this.initialListChange.emit(this.initialList);
  }

}
