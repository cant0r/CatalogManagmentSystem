import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

@Component({
  selector: 'app-container-type-view',
  templateUrl: './container-type-view.component.html',
  styleUrls: ['./container-type-view.component.css']
})
export class ContainerTypeViewComponent implements OnInit {

  badResponse = false;
  saveSuccess = false;

  name: string = "";
  description: string = "";

  page: number = 0;
  entry: number = 5;

  headers: HttpHeaders;

  containerTypes: ContainerType[];
  containerTypeAPI: string;

  constructor(private router: Router,
    private httpClient: HttpClient) {

    this.containerTypeAPI = "http://fema:8080/cms/api/types";

    this.headers = new HttpHeaders({
      'Authorization': 'Basic ' + sessionStorage.getItem('token')
    });


  }

  ngOnInit(): void {
    this.getContainerTypes(0);
  }
  getContainerTypes(page: number) {
    var params = new HttpParams()
      .set("pageNumber", page.toString())
      .set("entries", this.entry.toString());
    this.httpClient.get<ContainerType[]>(this.containerTypeAPI + "/all",
      { params: params, headers: this.headers })
      .subscribe((response) => {
        this.containerTypes = response;
      });
  }

  onNewClick() {
    this.containerTypes.push(new ContainerType());
  }

  onSaveClick() {
    for (let item of this.containerTypes) {
      if (item.name != "")
        this.httpClient.post<ContainerType>(this.containerTypeAPI, item, { headers: this.headers})
          .pipe(catchError((err): Observable<ContainerType[]> => {
            this.badResponse = true;
            return null;
          }))
          .subscribe((response) => {
            console.log(response);
            if (!this.badResponse) {
              this.saveSuccess = true;
              setTimeout(() => {
                this.saveSuccess = false;
              }, 1500);
            }
          })
      else
        this.badResponse = true;
    };


  }

  onDangerClick(id: number) {
    this.httpClient.delete(this.containerTypeAPI + "/" + this.containerTypes[id].name, { headers: this.headers})
      .pipe(catchError((err): Observable<ContainerType[]> => {
        this.badResponse = true;
        return null;
      }))
      .subscribe((response) => {
        console.log(response);

        if (!this.badResponse)
          this.containerTypes.splice(id % this.containerTypes.length, 1);
      });
  }
  onSearchClick() {
    var ps = new HttpParams();
    ps = ps.append("n", this.name);
    ps = ps.append("d", this.description);
    ps = ps.append("pageNumber", this.page.toString());
    ps = ps.append("entries", this.entry.toString());

    this.httpClient.get<ContainerType[]>(this.containerTypeAPI + "/all-params", { params: ps, headers: this.headers })
      .subscribe((response) => {
        this.containerTypes = response;
      });
  }

  onPreviousClick() {
    this.page -= 1;

    if (this.page < 0)
      this.page = 0;

    this.getContainerTypes(this.page);
    if (this.containerTypes.length == 0) {
      this.page += 1;
      this.getContainerTypes(this.page);
    }
  }

  onNextClick() {
    this.page += 1;

    this.getContainerTypes(this.page);
    if (this.containerTypes.length == 0) {
      this.page = 0;
      this.getContainerTypes(this.page);
    }
  }

  logOut() {
    sessionStorage.setItem("token", "");
  }
}

export class ContainerType {
  name: string;
  description: string


  constructor() {
    this.name = ""
    this.description = ""
  }

}
