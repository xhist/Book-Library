import { Injectable } from '@angular/core';
import {BehaviorSubject, flatMap, switchMap, take, tap} from "rxjs";
import {List, Note, Notes} from "./list";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class NoteService {

  notes$: BehaviorSubject<Note[]> = new BehaviorSubject<Note[]>([]);

  constructor(private httpClient: HttpClient) {}

  getAllNotes() {
    return this.httpClient.get<Note[]>(environment.apiBackendEndpoint + 'notes')
      .pipe(
        tap((notes) => {
          this.notes$.next(notes);
        })
      );
  }

  getAllNotesAsObservable() {
    return this.notes$.asObservable();
  }

  getNotesByList(list: string) {
    return this.httpClient.get<Note[]>(environment.apiBackendEndpoint + 'notes/list/' + list);
  }

  updateNote(note: Note) {
    return this.httpClient.put(environment.apiBackendEndpoint + 'notes', note)
      .pipe(
        flatMap(() => this.getAllNotes())
      ).toPromise();
  }

  deleteNote(note: Note) {
    return this.httpClient.delete(environment.apiBackendEndpoint + 'notes/book/' + note.isbn + '/list/' + note.listName)
      .pipe(
        flatMap(() => this.getAllNotes())
      ).toPromise();
  }

  deleteNotes(notes: Note[]) {
    const httpOptions = {
      headers: new HttpHeaders({'Content-Type': 'application/json'}), body: notes
    }
    return this.httpClient.delete(environment.apiBackendEndpoint + 'notes/multiple', httpOptions).pipe(
      flatMap(() => this.getAllNotes())
    ).toPromise();
  }
}
