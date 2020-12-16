import { ContainerType } from './../container-type-view/container-type-view.component';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-container-view',
  templateUrl: './container-view.component.html',
  styleUrls: ['./container-view.component.css']
})
export class ContainerViewComponent implements OnInit {

  name: string;
  capacity: string;
  location: string;
  type: string;


  page:number;
  entry:number = 5;

  containerAPI: string;
  containers: Container[];

  constructor(private router: Router,
              private httpClient: HttpClient) { 
                  this.containerAPI = "http://localhost:8080/api/containers"
              }

  ngOnInit(): void {
    this.getContainerTypes(0);
  }

  getContainerTypes(page: number) {
    var params = new HttpParams()
                      .set("pageNumber", page.toString())
                      .set("entries", this.entry.toString());
     this.httpClient.get<Container[]>(this.containerAPI + "/all",
      {params : params}).subscribe( (response) => {
        this.containers = response
      });
  }

  onNewClick() {

  }

  onSaveClick() {

  }

  onDangerClick() {

  }
  onSearchClick() {

  }

  onPreviousClick() {

  }

  onNextClick() {

  }

}

export class Container {
  id: number;
  capacity: number;
  location: string;
  name: string;

  type: ContainerType;
  
}


