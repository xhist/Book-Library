import {AfterViewInit, Component, EventEmitter, Input, OnChanges, OnInit, Output, ViewChild} from '@angular/core';
import {List, ListNote, Note} from "../services/list";
import {MatTableDataSource} from "@angular/material/table";
import {SelectionModel} from "@angular/cdk/collections";
import {NoteService} from "../services/note.service";
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";

export interface EditableCell {
  listname: string;
  isbn: string;
}

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnChanges, AfterViewInit {

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  displayedColumns: string[] = ['select', 'isbn', 'title', 'description', 'edit'];
  // @ts-ignore
  dataSource$: MatTableDataSource<ListNote> = new MatTableDataSource<ListNote>();
  selection = new SelectionModel<ListNote>(true, []);
  edited: EditableCell = {listname: '', isbn: ''};

  @Input()
  listName: string = "";

  @Input()
  notesList: ListNote[] = [];

  @Output()
  onDelete: EventEmitter<Note[]> = new EventEmitter();

  constructor(private noteService: NoteService) { }

  ngOnInit(): void {
  }

  ngOnChanges(): void {
    this.dataSource$ = new MatTableDataSource<ListNote>(this.notesList);
    // @ts-ignore
    this.dataSource$.paginator = this.paginator;
    // @ts-ignore
    this.dataSource$.sort = this.sort;
    this.edited = {listname:'', isbn: ''};
  }

  ngAfterViewInit() {
    // @ts-ignore
    this.dataSource$.paginator = this.paginator;
    // @ts-ignore
    this.dataSource$.sort = this.sort;
  }

  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.dataSource$.data.length;
    return numSelected === numRows;
  }

  /** Selects all rows if they are not all selected; otherwise clear selection. */
  masterToggle() {
    if (this.isAllSelected()) {
      this.selection.clear();
      return;
    }

    this.selection.select(...this.dataSource$.data);
  }

  /** The label for the checkbox on the passed row */
  checkboxLabel(row?: ListNote): string {
    if (!row) {
      return `${this.isAllSelected() ? 'deselect' : 'select'} all`;
    }
    return `${this.selection.isSelected(row) ? 'deselect' : 'select'}`;
  }

  setEditable(note: ListNote): void {
    this.edited = {listname: note.name, isbn:note.isbn}
  }

  isEditable(note: ListNote) {
    return note.name == this.edited.listname && note.isbn == this.edited.isbn;
  }

  editNote(note: ListNote) {
    var currentNote: Note = {listName: note.name, isbn: note.isbn, description:note.note};
    this.noteService.updateNote(currentNote).then();
    this.edited = {listname: '', isbn: ''};
  }

  extractSelectedNotes(): Note[] {
    var selectedNotes: Note[] = [];
    if(this.selection.selected.length > 0) {
      this.selection.selected.forEach((current, index) => {
        var note = {description: current.note, isbn: current.isbn, listName: current.name};
        selectedNotes.push(note);
      })
    }

    return selectedNotes;
  }

  deleteNotes(){
    this.onDelete.emit(this.extractSelectedNotes());
    this.selection.clear();
  }

}
