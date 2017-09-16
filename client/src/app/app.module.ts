import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { RegistrationComponent } from './registration/registration.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { NotFoundComponent } from './not-found/not-found.component';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {RoutingModule} from './module/routing.module';
import {HttpClient, HttpClientModule} from '@angular/common/http';
import {TranslateLoader, TranslateModule} from "@ngx-translate/core";
import {TranslateHttpLoader} from "@ngx-translate/http-loader";
import { AdminComponent } from './admin/admin.component';
import { AuctionComponent } from './auction/auction.component';
import { LotComponent } from './lot/lot.component';
import { TradingDayComponent } from './trading-day/trading-day.component';
import { UserComponent } from './user/user.component';
import { AccountComponent } from './account/account.component';
import {LotService} from "./service/lot.service";

@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent,
    LoginComponent,
    HomeComponent,
    NotFoundComponent,
    AdminComponent,
    AuctionComponent,
    LotComponent,
    TradingDayComponent,
    UserComponent,
    AccountComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    RoutingModule,
    HttpClientModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    })
  ],
  providers: [
    {provide: 'lotService', useClass: LotService}
    ],
  bootstrap: [AppComponent]
})
export class AppModule { }

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http, 'assets/i18n/', '.json');
}
