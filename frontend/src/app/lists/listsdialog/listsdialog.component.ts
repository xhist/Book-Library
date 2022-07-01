import { Component, OnInit } from '@angular/core';
import {ListService} from "../../services/list.service";
import {MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-listsdialog',
  templateUrl: './listsdialog.component.html',
  styleUrls: ['./listsdialog.component.css']
})
export class ListsdialogComponent implements OnInit {
  currentName: string = "";

  constructor(private listService: ListService, public dialogRef: MatDialogRef<ListsdialogComponent>) { }

  ngOnInit(): void {
  }

  createList() {
    this.listService.addList({name: this.currentName}).then(
      (success) => {
        this.dialogRef.close();
      },
      (error) => {
        console.log(error.error.message);
      }
    )
  }

}
