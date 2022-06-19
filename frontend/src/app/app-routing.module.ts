import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from "@angular/router";
import {LoginComponent} from "./pages/login/login.component";
import {SignUpComponent} from "./pages/sign-up/sign-up.component";
import {ProfileComponent} from "./pages/profile/profile.component";
import {CabinetComponent} from "./pages/cabinet/cabinet.component";
import {UserManagementComponent} from "./pages/user-management/user-management.component";
import {QuestionsComponent} from "./pages/questions/questions.component";
import {HomeComponent} from "./pages/home/home.component";
import {ChatComponent} from "./pages/chat/chat.component";
import {AdvertisementsComponent} from "./pages/advertisements/advertisements.component";

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'logout', component: LoginComponent },
  { path: 'signup', component: SignUpComponent },
  {
    path: 'cabinet',
    component: CabinetComponent,
    children: [
      { path: 'profile', component: ProfileComponent },
      { path: 'user-management', component: UserManagementComponent },
      { path: 'advertisements', component: AdvertisementsComponent },
    ]
  },
  { path: 'questions', component: QuestionsComponent },
  { path: 'home', component: HomeComponent },
  { path: 'chat', component: ChatComponent },
  // { path: '**', redirectTo: '/not-found', pathMatch: 'full' },
  // { path: 'not-found', component: PageNotFoundComponent },

]

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ],
})
export class AppRoutingModule {
}
