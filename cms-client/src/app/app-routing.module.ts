import { EntryViewComponent } from './entry-view/entry-view.component';
import { ContainerViewComponent } from './container-view/container-view.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ContainerTypeViewComponent } from './container-type-view/container-type-view.component';
import { MediumViewComponent } from './medium-view/medium-view.component';

const routes: Routes = [
  {
    path: 'login',
    component: LoginFormComponent
  },
  {
    path: 'containers',
    component: ContainerViewComponent
  },
  {
    path: 'container-types',
    component: ContainerTypeViewComponent
  },
  {
    path: 'mediums',
    component: MediumViewComponent
  },
  {
    path: 'entries',
    component: EntryViewComponent
  },
  {
    path: '**',
    component: ContainerViewComponent
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
