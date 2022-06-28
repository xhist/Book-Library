import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {BooksComponent} from "./books/books.component";
import {ListComponent} from "./list/list.component";
import {BookResolver} from "./books/book.resolver";
import {ListResolver} from "./lists/list.resolver";
import {NoteResolver} from "./lists/note.resolver";

const routes: Routes = [
  {path: '', redirectTo: 'books', pathMatch: 'full'},
  {
    path: 'books', component: BooksComponent,
    resolve: {
      booksSuccess: BookResolver,
      listsSuccess: ListResolver
    }
  },
  {
    path: 'lists', component: ListComponent,
    resolve: {
      listsSuccess: ListResolver,
      notesSuccess: NoteResolver
    }
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
