import { Injectable } from '@angular/core';
import {
  Router, Resolve,
  RouterStateSnapshot,
  ActivatedRouteSnapshot
} from '@angular/router';
import {forkJoin, Observable} from "rxjs";
import {ListService} from "../services/list.service";

@Injectable({
  providedIn: 'root'
})
export class ListResolver implements Resolve<any> {

  constructor(private listService: ListService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    return new Promise((resolve, reject) => {
      forkJoin([
        this.listService.getAllLists()
      ])
        .subscribe((data: any) => {
          return resolve(true);
        });
    });
  }
}
