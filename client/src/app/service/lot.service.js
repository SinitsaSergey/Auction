"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
require("rxjs/add/operator/toPromise");
var LOT_PATH = 'api/lot';
var LotService = (function () {
    function LotService(http) {
        this.http = http;
    }
    LotService.prototype.insert = function (lot) {
        return this.http.post(LOT_PATH, lot)
            .toPromise()
            .then(function (response) { return response.json(); });
    };
    LotService = __decorate([
        core_1.Injectable()
    ], LotService);
    return LotService;
}());
exports.LotService = LotService;
