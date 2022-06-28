import { Component, OnInit } from '@angular/core';
import {List, ListNote, Note} from "../services/list";
import {ListService} from "../services/list.service";
import {NoteService} from "../services/note.service";

@Component({
  selector: 'app-lists',
  templateUrl: './lists.component.html',
  styleUrls: ['./lists.component.css']
})
export class ListsComponent implements OnInit {
  lists$: List[] = [];
  notes$: Note[] = [];
  listNotes$: ListNote[] = [];

  constructor(private listService: ListService, private noteService: NoteService) { }

  ngOnInit(): void {
    this.listService.getAllListsAsObservable().subscribe((lists) => {
      this.lists$ = lists;
    });
    this.noteService.getAllNotesAsObservable().subscribe((notes) => {
      this.notes$ = notes;
    })

  }

}
