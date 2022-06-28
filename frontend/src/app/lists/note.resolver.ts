import { Injectable } from '@angular/core';
import {
  Router, Resolve,
  RouterStateSnapshot,
  ActivatedRouteSnapshot
} from '@angular/router';
import {forkJoin, Observable, of} from 'rxjs';
import {ListService} from "../services/list.service";
import {NoteService} from "../services/note.service";

@Injectable({
  providedIn: 'root'
})
export class NoteResolver implements Resolve<any> {
  constructor(private noteService: NoteService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    return new Promise((resolve, reject) => {
      forkJoin([
        this.noteService.getAllNotes()
      ])
        .subscribe((data: any) => {
          return resolve(true);
        });
    });
  }
}
