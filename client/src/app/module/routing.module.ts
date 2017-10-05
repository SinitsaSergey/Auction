import {NgModule} from '@angular/core';
import {RegistrationComponent} from '../registration/registration.component';
import {LoginComponent} from '../login/login.component';
import {HomeComponent} from '../home/home.component';
import {NotFoundComponent} from '../not-found/not-found.component';
import {AdminComponent} from '../admin/admin.component';
import {AuctionComponent} from '../auction/auction.component';
import {LotComponent} from '../lot/lot.component';
import {ManagerComponent} from '../manager/manager.component';
import {RouterModule, Routes} from '@angular/router';
import {AuctionDetailsComponent} from "../auction-details/auction-details.component";
import {AuctionCurrentComponent} from "../auction-current/auction-current.component";
import {AuthenticationGuard} from "../security/authentication.guard";
import {SpecificationComponent} from "../specification/specification.component";
import {UserComponent} from "../user/user.component";
import {LotDetailsComponent} from "../lot-details/lot-details.component";

const appRoutes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'admin', component: AdminComponent, canActivate: [AuthenticationGuard],
    data: {roles: ['ROLE_ADMIN']}},
  {path: 'manager', component: ManagerComponent, canActivate: [AuthenticationGuard],
    data: {roles: ['ROLE_MANAGER']}},
  {path: 'registration', component: RegistrationComponent},
  {path: 'login', component: LoginComponent},
  {path: 'specification', component: SpecificationComponent},
  {path: 'auction/details/:id', component: AuctionDetailsComponent, canActivate: [AuthenticationGuard],
    data: {roles: ['ROLE_MANAGER']}},
  {path: 'auction/current/:id', component: AuctionCurrentComponent, canActivate: [AuthenticationGuard],
    data: {roles: ['ROLE_USER']}},
  {path: 'auction/:date', component: AuctionComponent, canActivate: [AuthenticationGuard],
    data: {roles: ['ROLE_MANAGER']}},
  {path: 'user', component: UserComponent, canActivate: [AuthenticationGuard],
    data: {roles: ['ROLE_USER']}},
  {path: 'lot', component: LotComponent, canActivate: [AuthenticationGuard],
    data: {roles: ['ROLE_USER']}},
  {path: 'lot/details/:id', component: LotDetailsComponent},
  {path: '**', component: NotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes, {useHash: true})],
  exports: [RouterModule],
  providers: []
})

export class RoutingModule {
}
