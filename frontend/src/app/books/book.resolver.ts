import { Injectable } from '@angular/core';
import {
  Router, Resolve,
  RouterStateSnapshot,
  ActivatedRouteSnapshot
} from '@angular/router';
import {BookService} from "../services/book.service";
import {forkJoin, Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class BookResolver implements Resolve<any> {

  constructor(private bookService: BookService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    return new Promise((resolve, reject) => {
      forkJoin([
        this.bookService.getAllBooks()
      ])
        .subscribe((data: any) => {
          return resolve(true);
        });
    });
  }
}
