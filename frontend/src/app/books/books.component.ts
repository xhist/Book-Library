import {AfterViewInit, ViewChild, Component, OnInit, Output, EventEmitter, Input} from '@angular/core';
import {MatPaginator} from "@angular/material/paginator";
import {MatSort} from "@angular/material/sort";
import {MatTableDataSource} from "@angular/material/table";
import {Book, Isbn} from "../services/book";
import {BookService} from "../services/book.service";
import {SelectionModel} from "@angular/cdk/collections";
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {List, Notes} from "../services/list";
import {Note} from "../services/list";
import {ListdialogComponent} from "../listdialog/listdialog.component";
import {MatDialog} from "@angular/material/dialog";
import {Observable} from "rxjs";

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
  //@ts-ignore
  booksObservable$: Observable<Book[]>;

  constructor(private bookService: BookService, private http : HttpClient, public dialog: MatDialog) {}

  ngOnInit(): void {
    this.booksObservable$ = this.bookService.getAllBooksAsObservable();
    this.booksObservable$.subscribe((booksData: Book[]) => {
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

  extractSelectedBooks(): Isbn[] {
    var arrayIsbn: Isbn[] = [];
    if(this.selection.selected.length > 0) {
      this.selection.selected.forEach((current, index) => {
        var isbn: Isbn = {isbn: current.isbn};
        arrayIsbn.push(isbn);
      });
    }

    return arrayIsbn;
  }

  openDialog(): void {
    this.dialog.open(ListdialogComponent, {
      data: this.extractSelectedBooks()
    });
  }
}
