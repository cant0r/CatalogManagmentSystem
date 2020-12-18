import { HttpClient, HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Medium, MediumViewComponent } from '../medium-view/medium-view.component';

@Component({
  selector: 'app-entry-view',
  templateUrl: './entry-view.component.html',
  styleUrls: ['./entry-view.component.css']
})
export class EntryViewComponent implements OnInit {
  badResponse = false;
  saveSuccess = false;

  page:number = 0;
  entry:number = 5;

  name: string;
  size: number;
  medium_id: number;

  containerAPI: string;
  entries: Entry[];

  constructor(private router: Router,
              private httpClient: HttpClient) { 
                  this.containerAPI = "http://localhost:8080/api/entries"
              }

  ngOnInit(): void {
    this.getEntries(0);
  }

  getEntries(page: number) {
    var params = new HttpParams()
        .set("pageNumber", page.toString())
        .set("entries", this.entry.toString());
    this.httpClient.get<Entry[]>(this.containerAPI + "/all",
    {params : params}).subscribe( (response) => {
    this.entries = response
});
  }

  onNewClick() {
    var e = new Entry();
    e.medium = new Medium();
    this.entries.push(e)
  }

  onSaveClick() {
    for(let item of this.entries) {
      this.httpClient.post<Entry>(this.containerAPI, item)
        .pipe(catchError(
          (err): Observable<Entry[]> => {
              this.badResponse = true;
              return null;
          }
        ))
        .subscribe((response) => {
          console.log(response);
        })
    };
    this.saveSuccess = true;
    setTimeout(() => {
      this.saveSuccess = false;
    }, 1500);
  }

  onDangerClick(id: number) {
    this.httpClient.delete(this.containerAPI + "/" + this.entries[id].id)
      .subscribe((response) => {
        console.log(response);
      });
    this.entries.splice(id % this.entries.length, 1);
  }
  onSearchClick() {
    var ps = new HttpParams();
    ps = ps.append("n",this.name);
    ps = ps.append("s", this.size.toString());
    ps = ps.append("pageNumber", this.page.toString());
    ps = ps.append("entries", this.entry.toString());

    this.httpClient.get<Entry[]>(this.containerAPI + "/all-params", {params : ps})
      .subscribe((response) => {
        this.entries = response;
        
      });
  }

  onPreviousClick() {
    this.page -= 1;

    if(this.page < 0)
      this.page = 0;

    this.getEntries(this.page);
    if(this.entries.length == 0)
    {
      this.page += 1;
      this.getEntries(this.page);
    }
  }

  onNextClick() {
    this.page += 1;

    this.getEntries(this.page);
    if(this.entries.length == 0)
    {
      this.page = 0;
      this.getEntries(this.page);
    }
  }
}
export class Entry {
  id: number;
  name: string;
  size: number;
  medium: Medium;
}