"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __param = (this && this.__param) || function (paramIndex, decorator) {
    return function (target, key) { decorator(target, key, paramIndex); }
};
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var date_utils_1 = require("../utils/date-utils");
var ManagerComponent = (function () {
    function ManagerComponent(managerService, authenticationService) {
        this.managerService = managerService;
        this.authenticationService = authenticationService;
        this.convertDate = date_utils_1.DateUtils.convertToModel;
        this.getDate = date_utils_1.DateUtils.getDate;
        this.manager = authenticationService.currentUser;
    }
    ManagerComponent.prototype.ngOnInit = function () {
        this.getMyDays();
    };
    ManagerComponent.prototype.getMyDays = function () {
        var _this = this;
        this.managerService.getMyDays()
            .then(function (tradingDays) { return _this.tradingDays = tradingDays; });
    };
    ManagerComponent = __decorate([
        core_1.Component({
            selector: 'app-manager',
            templateUrl: './manager.component.html',
            styleUrls: ['./manager.component.css']
        }),
        __param(0, core_1.Inject('managerService')),
        __param(1, core_1.Inject('authenticationService'))
    ], ManagerComponent);
    return ManagerComponent;
}());
exports.ManagerComponent = ManagerComponent;
