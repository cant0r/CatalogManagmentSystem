import { ContainerType } from './../container-type-view/container-type-view.component';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { catchError } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-container-view',
  templateUrl: './container-view.component.html',
  styleUrls: ['./container-view.component.css']
})
export class ContainerViewComponent implements OnInit {

  badResponse = false;
  saveSuccess = false;

  name: string = "";
  capacity: number = 10000;
  location: string = "";
  type: string = "";

  page: number = 0;
  entry: number = 5;

  headers: HttpHeaders;

  containerAPI: string;
  containers: Container[];
  containersToUpdate: Set<number>;

  constructor(private router: Router,
    private httpClient: HttpClient) {
    this.containerAPI = "http://localhost:8080/api/containers";
    this.headers = new HttpHeaders({
      'Authorization': 'Basic ' + sessionStorage.getItem('token')
    });
    this.containersToUpdate = new Set<number>();

  }

  ngOnInit(): void {
    this.getContainerTypes(0);
  }

  getContainerTypes(page: number) {
    var params = new HttpParams()
      .set("pageNumber", page.toString())
      .set("entries", this.entry.toString());
    this.httpClient.get<Container[]>(this.containerAPI + "/all",
      { params: params, headers: this.headers })
      .pipe(catchError((e): Observable<Container[]> => {
        this.router.navigateByUrl("/login");
        return null;
      }))
      .subscribe((response) => {
        this.containers = response
      });
  }

  onNewClick() {
    var c = new Container();
    c.type = new ContainerType();
    this.containers.push(c);
    this.containersToUpdate.add(this.containers.length-1);
  }

  onSaveClick() {
    for (let id of this.containersToUpdate) {
      this.httpClient.post<Container>(this.containerAPI, this.containers[id], { headers: this.headers})
        .pipe(catchError(
          (err): Observable<Container[]> => {
            this.badResponse = true;
            return null;
          }
        ))
        .subscribe((response) => {
          console.log(response);
          if (!this.badResponse) {
            this.saveSuccess = true;
            setTimeout(() => {
              this.saveSuccess = false;
            }, 1500);
          }
        })
    };
    this.containersToUpdate.clear();
  }

  onDangerClick(id: number) {
    this.httpClient.delete(this.containerAPI + "/" + this.containers[id].id, { headers: this.headers})
      .pipe(catchError((err): Observable<Container[]> => {
        this.badResponse = true;
        return null;
      }))
      .subscribe((response) => {
        console.log(response);
        if (!this.badResponse)
          this.containers.splice(id % this.containers.length, 1);
      });
  }
  onSearchClick() {
    var ps = new HttpParams();
    ps = ps.append("n", this.name);
    ps = ps.append("l", this.location);
    ps = ps.append("c", this.capacity.toString());
    ps = ps.append("t", this.type);
    ps = ps.append("pageNumber", this.page.toString());
    ps = ps.append("entries", this.entry.toString());

    this.httpClient.get<Container[]>(this.containerAPI + "/all-params", { params: ps, headers: this.headers })
      .pipe(catchError(
        (err): Observable<Container[]> => {
          this.badResponse = true;
          return null;
        }
      ))
      .subscribe((response) => {
        this.containers = response;
      });
  }

  onPreviousClick() {
    this.page -= 1;

    if (this.page < 0)
      this.page = 0;

    this.getContainerTypes(this.page);
    if (this.containers.length == 0) {
      this.page += 1;
      this.getContainerTypes(this.page);
    }

  }

  onNextClick() {
    this.page += 1;

    this.getContainerTypes(this.page);
    if (this.containers.length == 0) {
      this.page = 0;
      this.getContainerTypes(this.page);
    }
  }
  logOut() {
    sessionStorage.removeItem("token");
  } 
  onUpdate(id: number){
    this.containersToUpdate.add(id);
  }

}

export class Container {
  id: number;
  capacity: number;
  location: string;
  name: string;

  type: ContainerType;

}


