import {AfterViewInit, ViewChild, Component, OnInit, Output, EventEmitter, Input} from '@angular/core';
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {MatTableDataSource} from "@angular/material/table";
import {Book} from "../services/book";
import {BookService} from "../services/book.service";
import {SelectionModel} from "@angular/cdk/collections";
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {List} from "../list/list";
import {Note} from "../list/list";

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.css']
})
export class BooksComponent implements AfterViewInit{
  displayedColumns: string[] = ['select', 'isbn', 'title', 'author', 'genres'];
  // @ts-ignore
  dataSource$: MatTableDataSource<Book>;
  selection = new SelectionModel<Book>(true, []);

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  @Input()
  //@ts-ignore
  books: Book[] = [];

  constructor(private bookService: BookService, private http : HttpClient) {}

  ngOnInit(): void {
    const booksObservable = this.bookService.getAllBooksAsObservable();
    booksObservable.subscribe((booksData: Book[]) => {
      this.books = booksData;
    });
    this.dataSource$ = new MatTableDataSource<Book>(this.books);
    this.dataSource$.paginator = this.paginator;
    this.dataSource$.sort = this.sort;
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
  checkboxLabel(row?: Book): string {
    if (!row) {
      return `${this.isAllSelected() ? 'deselect' : 'select'} all`;
    }
    return `${this.selection.isSelected(row) ? 'deselect' : 'select'}`;
  }

  addToList(list : string) {
    if(this.selection.selected.length > 0)
    {
      var arrayNotes:Note[] = [];
      this.selection.selected.forEach((current, index) => {
        var note:Note = {description: "", isbn: current.isbn, listName: list.toString() };
        arrayNotes.push(note);
      });
      var listrepr: List = {name: list?.toString(), notes: arrayNotes};
      this.http.post<List>(environment.apiBackendEndpoint + 'lists', listrepr);
    }
  }
}
