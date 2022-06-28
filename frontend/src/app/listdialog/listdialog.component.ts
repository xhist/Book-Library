import {Component, Inject, OnInit} from '@angular/core';
import {ListService} from "../services/list.service";
import {List, Note, Notes} from "../services/list";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Isbn} from "../services/book";
import {environment} from "../../environments/environment";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";


@Component({
  selector: 'app-listdialog',
  templateUrl: './listdialog.component.html',
  styleUrls: ['./listdialog.component.css']
})
export class ListdialogComponent implements OnInit {
  lists: string[] = [];
  changingLists: string[] = [];
  notes: Notes = {noteDtos: []};

  constructor(private listService: ListService, @Inject(MAT_DIALOG_DATA) public data: Isbn[],
              private http: HttpClient, public dialogRef: MatDialogRef<ListdialogComponent>) { }

  ngOnInit(): void {
    const listsObservable = this.listService.getAllListsAsObservable();
    listsObservable.subscribe((lists: List[]) => {
      lists.forEach((current, index) => {
        this.lists.push(current.name);
      })
    });

    this.changingLists = this.lists;
  }

  createNotes() : void {
    if(this.changingLists.length > 0) {
      var arrayNotes: Note[] = [];
      this.changingLists.forEach((currentList, indexList) => {
        this.data.forEach((currentIsbn, indexIsbn) => {
          var note: Note = {description: "", isbn: currentIsbn.isbn, listName: currentList};
          arrayNotes.push(note);
        })
      });
      var notes: Notes = {noteDtos: arrayNotes};
      this.http.post<Notes>(environment.apiBackendEndpoint + 'notes/multiple', notes)
        .subscribe(
          (response) => {
            console.log("success");
          },
          (error) => {
            console.error(error.error.message);
          }
          );
      this.dialogRef.close();
    }
  }

}
