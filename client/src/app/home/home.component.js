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
var HomeComponent = (function () {
    function HomeComponent(auctionService) {
        this.auctionService = auctionService;
        this.convertStartTime = date_utils_1.DateUtils.getStartTime;
        this.getFinishTime = date_utils_1.DateUtils.getFinishTime;
        this.convertToModel = date_utils_1.DateUtils.convertDateToModel;
    }
    HomeComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.tradingDate = new Date();
        this.getAuctionsForDay();
        this.refreshInterval = setInterval(function () { _this.getCurrentAuction(); }, 1000);
    };
    HomeComponent.prototype.ngOnDestroy = function () {
        clearInterval(this.refreshInterval);
    };
    HomeComponent.prototype.getAuctionsForDay = function () {
        var _this = this;
        this.auctionService.getAllForDay(this.convertToModel(this.tradingDate))
            .then(function (auctions) { return _this.auctions = auctions; });
    };
    HomeComponent.prototype.getNow = function () {
        return new Date();
    };
    HomeComponent.prototype.isActual = function (auction) {
        return auction.startTime > this.getNow() && auction.lot.status.status === 'onsale';
    };
    HomeComponent.prototype.getCurrentAuction = function () {
        for (var _i = 0, _a = this.auctions; _i < _a.length; _i++) {
            var auction = _a[_i];
            if (auction.startTime < this.getNow() && auction.lot.status.status === 'onsale') {
                this.currentAuction = auction;
            }
        }
    };
    HomeComponent = __decorate([
        core_1.Component({
            selector: 'app-home',
            templateUrl: './home.component.html',
            styleUrls: ['./home.component.css']
        }),
        __param(0, core_1.Inject('auctionService'))
    ], HomeComponent);
    return HomeComponent;
}());
exports.HomeComponent = HomeComponent;
