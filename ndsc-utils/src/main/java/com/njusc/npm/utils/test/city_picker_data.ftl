
(function (factory) {
    if (typeof define === 'function' && define.amd) {
        define('ChineseDistricts', [], factory);
    } else {
        factory();
    }
})(function () {
    var ChineseDistricts = ${ChineseDistricts};
    if (typeof window !== 'undefined') {
        window.ChineseDistricts = ChineseDistricts;
    }
    return ChineseDistricts;
});
