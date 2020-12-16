import { HttpClient, HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-container-type-view',
  templateUrl: './container-type-view.component.html',
  styleUrls: ['./container-type-view.component.css']
})
export class ContainerTypeViewComponent implements OnInit {

  name:string;
  description:string;

  page:number;
  entry:number = 5;


  containerTypes: ContainerType[];
  containerTypeAPI: string;

  constructor(private router: Router,
              private httpClient: HttpClient) {
        this.containerTypeAPI = "http://localhost:8080/api/types"}

  ngOnInit(): void {
    this.getContainerTypes(0);
  }
  getContainerTypes(page: number) {
    var params = new HttpParams()
                      .set("pageNumber", page.toString())
                      .set("entries", this.entry.toString());
     this.httpClient.get<ContainerType[]>(this.containerTypeAPI + "/all",
      {params : params}).subscribe( (response) => {
        this.containerTypes = response;
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

export class ContainerType {
  name: string;
  description: string


  constructor() {
    this.name = ""
    this.description = ""
  }

}
