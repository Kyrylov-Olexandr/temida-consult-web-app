import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';

import {RouterModule} from "@angular/router";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {CookieService} from "ngx-cookie-service";
import { NavComponent } from './parts/nav/nav.component';
import {LoginComponent} from "./pages/login/login.component";
import {SignUpComponent} from "./pages/sign-up/sign-up.component";
import {FormsModule} from "@angular/forms";
import { ProfileComponent } from './pages/profile/profile.component';
import {JwtInterceptor} from "./_interseptors/jwt-interceptor.service";
import { CabinetComponent } from './pages/cabinet/cabinet.component';
import { UserManagementComponent } from './pages/user-management/user-management.component';
import { MdbAccordionModule } from 'mdb-angular-ui-kit/accordion';
import { MdbCarouselModule } from 'mdb-angular-ui-kit/carousel';
import { MdbCheckboxModule } from 'mdb-angular-ui-kit/checkbox';
import { MdbCollapseModule } from 'mdb-angular-ui-kit/collapse';
import { MdbDropdownModule } from 'mdb-angular-ui-kit/dropdown';
import { MdbFormsModule } from 'mdb-angular-ui-kit/forms';
import {MdbModalModule, MdbModalRef, MdbModalService} from 'mdb-angular-ui-kit/modal';
import { MdbPopoverModule } from 'mdb-angular-ui-kit/popover';
import { MdbRadioModule } from 'mdb-angular-ui-kit/radio';
import { MdbRangeModule } from 'mdb-angular-ui-kit/range';
import { MdbRippleModule } from 'mdb-angular-ui-kit/ripple';
import { MdbScrollspyModule } from 'mdb-angular-ui-kit/scrollspy';
import { MdbTabsModule } from 'mdb-angular-ui-kit/tabs';
import { MdbTooltipModule } from 'mdb-angular-ui-kit/tooltip';
import { MdbValidationModule } from 'mdb-angular-ui-kit/validation';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { UserDetailsModalComponent } from './pages/modals/user-details-modal/user-details-modal.component';
import { QuestionsComponent } from './pages/questions/questions.component';
import { HomeComponent } from './pages/home/home.component';
import { FooterComponent } from './parts/footer/footer.component';
import { AdvertisementsComponent } from './pages/advertisements/advertisements.component';
import { ChatComponent } from './pages/chat/chat.component';
import {AdvConfigModalComponent} from "./pages/advertisements/adv-config-modal/adv-config-modal.component";



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignUpComponent,
    NavComponent,
    ProfileComponent,
    CabinetComponent,
    UserManagementComponent,
    UserDetailsModalComponent,
    QuestionsComponent,
    HomeComponent,
    FooterComponent,
    AdvertisementsComponent,
    AdvConfigModalComponent,
    ChatComponent,

  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        RouterModule,
        HttpClientModule,
        FormsModule,
        MdbAccordionModule,
        MdbCarouselModule,
        MdbCheckboxModule,
        MdbCollapseModule,
        MdbDropdownModule,
        MdbFormsModule,
        MdbModalModule,
        MdbPopoverModule,
        MdbRadioModule,
        MdbRangeModule,
        MdbRippleModule,
        MdbScrollspyModule,
        MdbTabsModule,
        MdbTooltipModule,
        MdbValidationModule,
        BrowserAnimationsModule,

    ],
  providers:[
    CookieService,
    {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true},
    MdbModalService,
    // {provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true}
  ],
    // [CookieService,
    // {provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true},
    // {provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true}],
  bootstrap: [AppComponent],
})
export class AppModule { }
