import { Injectable } from '@angular/core';
import {BehaviorSubject, flatMap, tap} from "rxjs";
import {List} from "./list";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class ListService {
  lists$: BehaviorSubject<List[]> = new BehaviorSubject<List[]>([]);

  constructor(private httpClient: HttpClient) {}

  getAllLists() {
    return this.httpClient.get<List[]>(environment.apiBackendEndpoint + 'lists')
      .pipe(
        tap((lists) => {
          this.lists$.next(lists);
        })
      );
  }

  getAllListsAsObservable() {
    return this.lists$.asObservable();
  }

  deleteList(name: string) {
    return this.httpClient.delete(environment.apiBackendEndpoint + 'lists/name/' + name)
      .pipe(
        flatMap(() => this.getAllLists())
      ).toPromise();
  }

  addList(list: List) {
    return this.httpClient.post(environment.apiBackendEndpoint + 'lists', list)
      .pipe(
        flatMap(() => this.getAllLists())
      ).toPromise();
  }
}
