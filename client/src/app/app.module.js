"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
var platform_browser_1 = require("@angular/platform-browser");
var core_1 = require("@angular/core");
var app_component_1 = require("./app.component");
var registration_component_1 = require("./registration/registration.component");
var login_component_1 = require("./login/login.component");
var home_component_1 = require("./home/home.component");
var not_found_component_1 = require("./not-found/not-found.component");
var forms_1 = require("@angular/forms");
var http_1 = require("@angular/http");
var routing_module_1 = require("./module/routing.module");
var http_2 = require("@angular/common/http");
var core_2 = require("@ngx-translate/core");
var http_loader_1 = require("@ngx-translate/http-loader");
var admin_component_1 = require("./admin/admin.component");
var auction_component_1 = require("./auction/auction.component");
var lot_component_1 = require("./lot/lot.component");
var trading_day_component_1 = require("./trading-day/trading-day.component");
var user_component_1 = require("./user/user.component");
var account_component_1 = require("./account/account.component");
var lot_service_1 = require("./service/lot.service");
var AppModule = (function () {
    function AppModule() {
    }
    AppModule = __decorate([
        core_1.NgModule({
            declarations: [
                app_component_1.AppComponent,
                registration_component_1.RegistrationComponent,
                login_component_1.LoginComponent,
                home_component_1.HomeComponent,
                not_found_component_1.NotFoundComponent,
                admin_component_1.AdminComponent,
                auction_component_1.AuctionComponent,
                lot_component_1.LotComponent,
                trading_day_component_1.TradingDayComponent,
                user_component_1.UserComponent,
                account_component_1.AccountComponent
            ],
            imports: [
                platform_browser_1.BrowserModule,
                forms_1.FormsModule,
                http_1.HttpModule,
                routing_module_1.RoutingModule,
                http_2.HttpClientModule,
                core_2.TranslateModule.forRoot({
                    loader: {
                        provide: core_2.TranslateLoader,
                        useFactory: HttpLoaderFactory,
                        deps: [http_2.HttpClient]
                    }
                })
            ],
            providers: [
                { provide: 'lotService', useClass: lot_service_1.LotService }
            ],
            bootstrap: [app_component_1.AppComponent]
        })
    ], AppModule);
    return AppModule;
}());
exports.AppModule = AppModule;
function HttpLoaderFactory(http) {
    return new http_loader_1.TranslateHttpLoader(http, 'assets/i18n/', '.json');
}
exports.HttpLoaderFactory = HttpLoaderFactory;
