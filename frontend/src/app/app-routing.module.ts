import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {BooksComponent} from "./books/books.component";
import {ListComponent} from "./list/list.component";
import {BookResolver} from "./books/book.resolver";

const routes: Routes = [
  {path: '', redirectTo: 'books', pathMatch: 'full'},
  {
    path: 'books', component: BooksComponent,
    resolve: {
      booksSuccess: BookResolver
    }
  },
  {
    path: 'list', component: ListComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
