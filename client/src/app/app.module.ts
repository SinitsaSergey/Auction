import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {RegistrationComponent} from './registration/registration.component';
import {LoginComponent} from './login/login.component';
import {HomeComponent} from './home/home.component';
import {NotFoundComponent} from './not-found/not-found.component';
import {FormsModule} from '@angular/forms';
import {RoutingModule} from './module/routing.module';
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from '@angular/common/http';
import {TranslateLoader, TranslateModule} from '@ngx-translate/core';
import {TranslateHttpLoader} from '@ngx-translate/http-loader';
import {AdminComponent} from './admin/admin.component';
import {AuctionComponent} from './auction/auction.component';
import {LotComponent} from './lot/lot.component';
import {TradingDayComponent} from './trading-day/trading-day.component';
import {UserComponent} from './user/user.component';
import {AccountComponent} from './account/account.component';
import {LotService} from './service/lot.service';
import {AuthenticationService} from './service/authentication.service';
import {AuthenticationGuard} from './security/authentication.guard';
import {UserService} from './service/user.service';
import {AuctionService} from './service/auction.service';
import {AdminService} from './service/admin.service';
import {ManagerComponent} from './manager/manager.component';
import {ManagerService} from './service/manager.service';
import {JWTInterceptor} from './service/JWTInterceptor';
import {AuctionDetailsComponent} from './auction-details/auction-details.component';
import { AuctionCurrentComponent } from './auction-current/auction-current.component';

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
    AccountComponent,
    ManagerComponent,
    AuctionDetailsComponent,
    AuctionCurrentComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
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
    {provide: HTTP_INTERCEPTORS, useClass: JWTInterceptor, multi: true },
    {provide: 'lotService', useClass: LotService},
    {provide: 'userService', useClass: UserService},
    {provide: 'auctionService', useClass: AuctionService},
    {provide: 'authenticationService', useClass: AuthenticationService},
    {provide: 'auctionService', useClass: AuctionService},
    {provide: 'adminService', useClass: AdminService},
    {provide: 'managerService', useClass: ManagerService},
    AuthenticationGuard
    ],
  bootstrap: [AppComponent]
})
export class AppModule { }

export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http, 'assets/i18n/', '.json');
}
