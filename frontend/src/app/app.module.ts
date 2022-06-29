import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BooksComponent } from './books/books.component';
import { ListComponent } from './list/list.component';
import { MaterialModule } from './material/material.module';
import {HttpClientModule} from "@angular/common/http";
import { PluckPipe } from './books/pluck.pipe';
import { ListsComponent } from './lists/lists.component';
import {ListdialogComponent} from './listdialog/listdialog.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { SelectListComponent } from './listdialog/select-list/select-list.component';
import { NotesCountPipe } from './lists/notes-count.pipe';
import { EmptyComponent } from './lists/empty/empty.component';
import { MyautofocusDirective } from './directives/myautofocus.directive';

@NgModule({
  declarations: [
    AppComponent,
    BooksComponent,
    ListComponent,
    PluckPipe,
    ListsComponent,
    ListdialogComponent,
    SelectListComponent,
    NotesCountPipe,
    EmptyComponent,
    MyautofocusDirective
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
