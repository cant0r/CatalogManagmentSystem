import { Container } from './../container-view/container-view.component';
import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-medium-view',
  templateUrl: './medium-view.component.html',
  styleUrls: ['./medium-view.component.css']
})
export class MediumViewComponent implements OnInit {

  page:number = 0;
  entry:number = 5;

  capacity: number;
  name: string;
  container_id: number;

  containerAPI: string;
  mediums: Medium[];

  constructor(private router: Router,
              private httpClient: HttpClient) { 
                  this.containerAPI = "http://localhost:8080/api/mediums"
              }

  ngOnInit(): void {
    this.getMediums(0);
  }

  getMediums(page: number) {
    var params = new HttpParams()
        .set("pageNumber", page.toString())
        .set("entries", this.entry.toString());
    this.httpClient.get<Medium[]>(this.containerAPI + "/all",
    {params : params}).subscribe( (response) => {
    this.mediums = response
});
  }

  onNewClick() {
    var m = new Medium();
    m.container = new Container();
    this.mediums.push(m);
  }

  onSaveClick() {
    for(let item of this.mediums) {
      this.httpClient.post<Medium>(this.containerAPI, item).subscribe((response) => {
        console.log(response);
      })
    };
  }

  onDangerClick(id: number) {
    this.httpClient.delete(this.containerAPI + "/" + this.mediums[id].id)
    .subscribe((response) => {
      console.log(response);
    });
    this.mediums.splice(id % this.mediums.length, 1);
  }
  onSearchClick() {
    var ps = new HttpParams();
    ps = ps.append("n",this.name);
    ps = ps.append("c", this.capacity.toString());
    ps = ps.append("pageNumber", this.page.toString());
    ps = ps.append("entries", this.entry.toString());

    this.httpClient.get<Medium[]>(this.containerAPI + "/all-params", {params : ps})
      .subscribe((response) => {
        this.mediums = response;
        
      });
  }

  onPreviousClick() {
    this.page -= 1;

    if(this.page < 0)
      this.page = 0;

    this.getMediums(this.page);
    if(this.mediums.length == 0)
    {
      this.page += 1;
      this.getMediums(this.page);
    }
  }

  onNextClick() {
    this.page += 1;

    this.getMediums(this.page);
    if(this.mediums.length == 0)
    {
      this.page = 0;
      this.getMediums(this.page);
    }
  }

}

export class Medium {
  id: number;
  capacity: number;
  name: string;
  container: Container;
}
