import { Pipe, PipeTransform } from '@angular/core';
import {NoteService} from "../services/note.service";
import {BehaviorSubject, Observable} from "rxjs";
import {ListNote} from "../services/list";

@Pipe({
  name: 'notesCount'
})
export class NotesCountPipe implements PipeTransform {

  constructor(private noteService: NoteService) {
  }

  transform(input: string, list: ListNote[]): any {
    var countNotes = list.filter((list)=> {
      return list.name == input;
    })
    return countNotes.length;
  }

}
