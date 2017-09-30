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
var AuctionCurrentComponent = (function () {
    function AuctionCurrentComponent(router, auctionService) {
        this.router = router;
        this.auctionService = auctionService;
    }
    AuctionCurrentComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.sub = this.router.params.subscribe(function (params) {
            _this.id = params['id'];
            _this.auctionService.getById(_this.id)
                .then(function (auction) {
                _this.currentAuction = auction;
            });
        });
        this.refreshInterval = setInterval(function () { return _this.getCurrentPrice(); }, 1000);
    };
    AuctionCurrentComponent.prototype.getRestTime = function () {
        return this.restTime = (this.getFinishTime().getTime() - new Date().getTime()) / 1000;
    };
    AuctionCurrentComponent.prototype.getCurrentPrice = function () {
        var _this = this;
        this.auctionService.getCurrentPrice(this.currentAuction.id)
            .then(function (price) { return _this.currentPrice = price; });
    };
    AuctionCurrentComponent.prototype.placeBid = function () {
        var _this = this;
        this.auctionService.placeBid(this.currentAuction.id)
            .then(function (price) { return _this.currentPrice = price; });
    };
    AuctionCurrentComponent.prototype.getFinishTime = function () {
        return new Date(+this.currentAuction.startTime + 600000);
    };
    AuctionCurrentComponent.prototype.getDate = function (date) {
        return new Date(date);
    };
    AuctionCurrentComponent.prototype.timeToString = function (time) {
        var minutes = (time / 60).toFixed(0);
        var seconds = (time % 60).toFixed(0);
        return (+minutes > 9 ? minutes : '0' + minutes) + ':' + (+seconds > 9 ? seconds : '0' + seconds);
    };
    AuctionCurrentComponent = __decorate([
        core_1.Component({
            selector: 'app-auction-current',
            templateUrl: './auction-current.component.html',
            styleUrls: ['./auction-current.component.css']
        }),
        __param(1, core_1.Inject('auctionService'))
    ], AuctionCurrentComponent);
    return AuctionCurrentComponent;
}());
exports.AuctionCurrentComponent = AuctionCurrentComponent;
