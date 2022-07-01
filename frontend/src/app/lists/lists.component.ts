import { Component, OnInit } from '@angular/core';
import {List, ListNote, Note} from "../services/list";
import {ListService} from "../services/list.service";
import {NoteService} from "../services/note.service";
import {BookService} from "../services/book.service";
import {BehaviorSubject, combineLatest, forkJoin} from "rxjs";
import {Book} from "../services/book";

@Component({
  selector: 'app-lists',
  templateUrl: './lists.component.html',
  styleUrls: ['./lists.component.css']
})
export class ListsComponent implements OnInit {
  lists$: List[] = [];
  notes$: Note[] = [];
  listNotesChange$: BehaviorSubject<ListNote[]> = new BehaviorSubject<ListNote[]>([]);
  rippleColorList: string = "rgba(185, 173, 179, 0.37)";
  selectedList: string = "";
  currentNotes: ListNote[] = [];

  constructor(private listService: ListService, private noteService: NoteService, private bookService: BookService) { }

  setListNotes(lists: List[], notes: Note[], books: Book[]) {
    var notesList: ListNote[] = [];
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
        notesList.push(listWithNotes);
      })
    })
    this.listNotesChange$.next(notesList);
  }

  ngOnInit(): void {

    let lists = this.listService.getAllListsAsObservable();
    let notes = this.noteService.getAllNotesAsObservable();
    let books = this.bookService.getAllBooksAsObservable();

    combineLatest([lists, notes, books]).subscribe(results => {
        this.lists$ = results[0];
        this.notes$ = results[1];
        this.setListNotes(results[0], results[1], results[2]);
    })
  }

  getNotesByList(list: string): ListNote[] {
    return this.listNotesChange$.value.filter(value => value.name == list);
  }

  hasNotes(list:string) : boolean {
    return this.getNotesByList(list).length > 0;
  }

  updateCurrentNotes() {
    this.listNotesChange$.asObservable().subscribe((value) => {
      this.currentNotes = value.filter(list => list.name == this.selectedList);
    })
  }

  onNotesDelete($event: Note[]) {
    this.noteService.deleteNotes($event).then();
  }


}
