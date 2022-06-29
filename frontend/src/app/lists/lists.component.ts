import { Component, OnInit } from '@angular/core';
import {List, ListNote, Note} from "../services/list";
import {ListService} from "../services/list.service";
import {NoteService} from "../services/note.service";
import {BookService} from "../services/book.service";
import {forkJoin, Observable} from "rxjs";
import {Book} from "../services/book";

@Component({
  selector: 'app-lists',
  templateUrl: './lists.component.html',
  styleUrls: ['./lists.component.css']
})
export class ListsComponent implements OnInit {
  lists$: List[] = [];
  notes$: Note[] = [];
  listNotes$: ListNote[] = [];
  rippleColorList: string = "rgba(185, 173, 179, 0.37)";
  selectedList: string = "";
  currentNotes: ListNote[] = [];

  constructor(private listService: ListService, private noteService: NoteService, private bookService: BookService) { }

  setListNotes(lists: List[], notes: Note[], books: Book[]) {
    lists.forEach((current, index) => {
      notes.filter((note, indexNote) => {
        return note.listName == current.name;
      }).forEach((currentNote, indexCurrentNote) => {
        var bookTitle: string = "";
        // @ts-ignore
        bookTitle = books.find((value) => {
          return value.isbn == currentNote.isbn;
        }).title;
        var listWithNotes: ListNote = {name: current.name, isbn: currentNote.isbn, title: bookTitle, note: currentNote.description};
        this.listNotes$.push(listWithNotes);
      })
    })
  }

  ngOnInit(): void {

    let lists = this.listService.getAllLists();
    let notes = this.noteService.getAllNotes();
    let books = this.bookService.getAllBooks();

    forkJoin([lists, notes, books]).subscribe(results => {
        this.lists$ = results[0];
        this.notes$ = results[1];
        this.setListNotes(results[0], results[1], results[2]);
    })
  }

  getNotesByList(list: string): ListNote[] {
    return this.listNotes$.filter(value => value.name == list);
  }

  hasNotes(list:string) : boolean {
    return this.getNotesByList(list).length > 0;
  }

  updateCurrentNotes() {
    this.currentNotes = this.getNotesByList(this.selectedList);
  }

}
