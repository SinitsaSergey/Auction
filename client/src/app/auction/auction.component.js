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
var MAX_AUCTIONS = 2;
var DURATION = 60000;
var AuctionComponent = (function () {
    function AuctionComponent(router, auctionService, lotService, adminService) {
        this.router = router;
        this.auctionService = auctionService;
        this.lotService = lotService;
        this.adminService = adminService;
        this.auctions = [];
        this.queueAuctions = [];
        this.convertStartTime = date_utils_1.DateUtils.getStartTime;
        this.getFinishTime = date_utils_1.DateUtils.getFinishTime;
        this.setTimeZone = date_utils_1.DateUtils.setTimeZone;
    }
    AuctionComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.sub = this.router.params.subscribe(function (params) {
            _this.date = params['date'];
            _this.getAuctionsForDay();
            _this.getFreeLots();
            _this.getTradingDay();
        });
    };
    AuctionComponent.prototype.getAuctionsForDay = function () {
        var _this = this;
        this.auctionService.getAllForDay(this.date)
            .then(function (auctions) { return _this.auctions = auctions; });
    };
    /*dayIsFull(): boolean {
      return AuctionService.dayIsFull(this.auctions);
    }*/
    AuctionComponent.prototype.dayIsFull = function () {
        return (this.auctions.length >= MAX_AUCTIONS);
    };
    AuctionComponent.prototype.getFreeLots = function () {
        var _this = this;
        this.lotService.getByStatus('registered')
            .then(function (lots) { return _this.regLots = lots; });
        this.lotService.getByStatus('free')
            .then(function (lots) { return _this.freeLots = lots; });
    };
    AuctionComponent.prototype.getLots = function () {
        if (this.dayIsFull()) {
            return this.regLots;
        }
        else {
            return this.freeLots;
        }
    };
    AuctionComponent.prototype.getDate = function (date) {
        return new Date(date);
    };
    AuctionComponent.prototype.getTradingDay = function () {
        var _this = this;
        this.adminService.getTradingDay(this.date)
            .then(function (tradingDay) { return _this.tradingDay = tradingDay; });
    };
    AuctionComponent.prototype.convertToDate = function () {
        this.newAuction.startTime = new Date();
        var dateArray = this.date.split('-', 3);
        var timeArray = this.startTimeString.split(':', 2);
        this.newAuction.startTime.setUTCFullYear(+dateArray[0], +dateArray[1] - 1, +dateArray[2]);
        this.newAuction.startTime.setUTCHours(+timeArray[0], +timeArray[1], 0, 0);
    };
    AuctionComponent.prototype.insert = function () {
        var _this = this;
        this.newAuction.lot = this.selectedLot;
        this.newAuction.tradingDay = this.tradingDay;
        this.newAuction.currentBid = 0;
        this.auctionService.insert(this.newAuction, this.dayIsFull())
            .then(function () { return _this.getAuctionsForDay(); });
    };
    AuctionComponent.prototype.remove = function (id) {
        var _this = this;
        this.auctionService.remove(id)
            .then(function () { return _this.getAuctionsForDay(); });
    };
    AuctionComponent = __decorate([
        core_1.Component({
            selector: 'app-auction',
            templateUrl: './auction.component.html',
            styleUrls: ['./auction.component.css']
        }),
        __param(1, core_1.Inject('auctionService')),
        __param(2, core_1.Inject('lotService')),
        __param(3, core_1.Inject('adminService'))
    ], AuctionComponent);
    return AuctionComponent;
}());
exports.AuctionComponent = AuctionComponent;
