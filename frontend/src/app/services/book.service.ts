import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Book} from "./book";
import {environment} from "../../environments/environment";
import {BehaviorSubject, flatMap, tap} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class BookService {

  books$: BehaviorSubject<Book[]> = new BehaviorSubject<Book[]>([]);

  constructor(private httpClient: HttpClient) { }

  getAllBooks() {
    return this.httpClient.get<Book[]>(environment.apiBackendEndpoint + 'books')
      .pipe(
        tap((books) => {
          this.books$.next(books);
        })
      );
  }

  getAllBooksAsObservable() {
    return this.books$.asObservable();
  }
}
