"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var AUCTION_DURATION = 600000;
var DateUtils = (function () {
    function DateUtils() {
    }
    DateUtils.getFinishTime = function (auction) {
        return new Date(+auction.startTime + AUCTION_DURATION);
    };
    DateUtils.getStartTime = function (auction) {
        return new Date(auction.startTime).toLocaleTimeString();
    };
    DateUtils.getDate = function (day) {
        return new Date(day.stringDate);
    };
    DateUtils.convertDateToModel = function (date) {
        return date.getUTCFullYear() +
            '-' + (date.getUTCMonth() + 1) +
            '-' + (date.getUTCDate());
    };
    DateUtils.convertToModel = function (date) {
        return date.getUTCFullYear() +
            '-' + (date.getUTCMonth() + 1) +
            '-' + (date.getUTCDate() + 1);
    };
    DateUtils.timeToString = function (date) {
        var time = new Date(date);
        var hours = time.getHours();
        var minutes = time.getMinutes();
        return (hours > 9 ? hours : '0' + hours) + ':' + (minutes > 9 ? minutes : '0' + minutes);
    };
    DateUtils.setTimeZone = function (hours) {
        return hours + 3;
    };
    return DateUtils;
}());
exports.DateUtils = DateUtils;
