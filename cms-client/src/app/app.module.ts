import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { ContainerTypeViewComponent } from './container-type-view/container-type-view.component';
import { ContainerViewComponent } from './container-view/container-view.component';
import { MediumViewComponent } from './medium-view/medium-view.component';
import { EntryViewComponent } from './entry-view/entry-view.component';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    LoginFormComponent,
    ContainerTypeViewComponent,
    ContainerViewComponent,
    MediumViewComponent,
    EntryViewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
