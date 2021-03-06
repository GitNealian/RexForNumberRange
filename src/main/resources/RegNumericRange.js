﻿/***
 * Regex Numeric Range Generator
 * Author: Erwin Yusrizal <erwin.yusrizal@gmail.com>
 * Version: 1.0.0
 */
var RegNumericRange = function () {
    function t(r, e, n, s) {
        return null === n && (n = {}),
            this instanceof t ? (this.minValue = r,
                this.maxValue = e,
                this.options = this.extend({}, this.defaults, n),
                void (this.data = {})) : new t(r, e, n, s)
    }

    return t.prototype.defaults = {
        MatchWholeWord: !1,
        MatchWholeLine: !1,
        MatchLeadingZero: !1,
        showProcess: !1
    },
        t.prototype.generate = function (t) {
            var r = this.minValue.toString()
                , e = this.maxValue.toString()
                , n = []
                , s = []
                , i = [];
            if (this.minValue === undefined || this.maxValue === undefined) {
                var a = "Minimum & Maximum value is required!";
                if ("function" == typeof t)
                    return t({
                        success: !1,
                        message: a
                    });
                throw new Error(a)
            }
            if (!this.isNumeric(this.minValue) || !this.isNumeric(this.maxValue)) {
                var a = "Minimum & Maximum value must numbers only!";
                if ("function" == typeof t)
                    return t({
                        success: !1,
                        message: a
                    });
                throw new Error(a)
            }
            if (parseInt(this.minValue) === parseInt(this.maxValue) || parseInt(this.minValue) > parseInt(this.maxValue)) {
                var a = "Max. value must greater than Min. value!";
                if ("function" == typeof t)
                    return t({
                        success: !1,
                        message: a
                    });
                throw new Error(a)
            }
            n = this.parseStartRange(r, e);
            for (var o in n)
                s.push(this.parseEndRange(n[o][0], n[o][1]));
            if (n = this.reformatArray(s),
                this.options.showProcess)
                for (i[i.length] = {
                    title: "Parse Into Range:",
                    steps: []
                },
                         o = 0; o < n.length; o++)
                    i[i.length - 1].steps.push(n[o].join(" - "));
            var h = this.parseIntoRegex(n);
            if (this.options.showProcess)
                for (i[i.length] = {
                    title: "Parse Into Block Regex:",
                    steps: []
                },
                         o = 0; o < h.length; o++)
                    i[i.length - 1].steps.push(h[o]);
            var u = this.parseIntoPattern(h);
            return this.options.showProcess && (i[i.length] = {
                title: "Combining Into Regex Pattern:",
                steps: [u]
            }),
                this.setProcess({
                    pattern: u,
                    process: i
                }),
                "function" == typeof t ? t({
                    success: !0,
                    data: this.data
                }) : this.data
        }
        ,
        t.prototype.setProcess = function (t) {
            this.data = this.extend({}, this.data, t)
        }
        ,
        t.prototype.parseStartRange = function (t, r) {
            return t = parseInt(t),
                r = parseInt(r),
                t.toString().length === r.toString().length ? [[t.toString(), r.toString()]] : (breakPoint = Math.pow(10, t.toString().length) - 1,
                    [[t.toString(), breakPoint.toString()]].concat(this.parseStartRange(breakPoint + 1, r.toString())))
        }
        ,
        t.prototype.parseEndRange = function (t, r) {
            if (1 === t.length)
                return [t, r];
            if (Array(t.length + 1).join("0") === "0" + t.substr(1)) {
                if (Array(r.length + 1).join("0") === "9" + r.substr(1))
                    return [t, r];
                if (t.substr(0, 1) < r.substr(0, 1)) {
                    var e = parseInt(r.substr(0, 1) + Array(r.substr(1).length + 1).join("0")) - 1
                        , n = this.strBreakPoint(e)
                        , s = this.strBreakPoint(e + 1);
                    return [t, n].concat(this.parseEndRange(s, r))
                }
            }
            if (Array(r.length + 1).join("9") === "9" + r.substr(1) && t.substr(0, 1) < r.substr(0, 1)) {
                var i = (parseInt(t.substr(0, 1)) + 1).toString()
                    , e = parseInt(i + Array(r.substr(1).length + 1).join("0")) - 1
                    , n = this.strBreakPoint(e)
                    , s = this.strBreakPoint(e + 1);
                return this.parseEndRange(t, n).concat([s, r])
            }
            if (t.substr(0, 1) < r.substr(0, 1)) {
                var i = (parseInt(t.substr(0, 1)) + 1).toString()
                    , e = parseInt(i + Array(r.substr(1).length + 1).join("0")) - 1
                    , n = this.strBreakPoint(e)
                    , s = this.strBreakPoint(e + 1);
                return this.parseEndRange(t, n).concat(this.parseEndRange(s, r))
            }
            for (var a = t.substr(0, 1), o = this.parseEndRange(t.substr(1), r.substr(1)), h = [], u = 0; u < o.length; u++)
                h.push(a + o[u]);
            return h
        }
        ,
        t.prototype.parseIntoRegex = function (t) {
            if (!this.isArray(t))
                throw new Error("Argument needs to be an array!");
            var r = [];
            for (i = 0; i < t.length; i++) {
                var e = t[i][0].split("")
                    , n = t[i][1].split("")
                    , s = ""
                    , o = 0
                    , h = "";
                for (a = 0; a < e.length; a++)
                    e[a] === n[a] ? h += e[a] : parseInt(e[a]) + 1 === parseInt(n[a]) ? h += "[" + e[a] + n[a] + "]" : (s === e[a] + n[a] ? o++ : s = e[a] + n[a],
                        a === e.length - 1 ? h += o > 0 ? "{" + (o + 1) + "}" : "[" + e[a] + "-" + n[a] + "]" : 0 === o && (h += "[" + e[a] + "-" + n[a] + "]"));
                r.push(h)
            }
            return r
        }
        ,
        t.prototype.parseIntoPattern = function (t) {
            if (this.isArray(t)) {
                var r = t.join("|");
                return this.options.MatchWholeLine && this.options.MatchLeadingZero ? "^0*(" + r + ")$" : this.options.MatchLeadingZero ? "0*(" + r + ")" : this.options.MatchWholeLine ? "^(" + r + ")$" : this.options.MatchWholeWord ? "\\b(" + r + ")\\b" : "(" + r + ")"
            }
            var r = "[" + t + "]";
            return this.options.MatchWholeLine && this.options.MatchLeadingZero ? "^0*(" + r + ")$" : this.options.MatchLeadingZero ? "0*(" + r + ")" : this.options.MatchWholeLine ? "^(" + r + ")$" : this.options.MatchWholeWord ? "\\b(" + r + ")\\b" : "(" + r + ")"
        }
        ,
        t.prototype.reformatArray = function (t) {
            for (arrReturn = [],
                     i = 0; i < t.length; i++)
                for (page = t[i].length / 2,
                         a = 0; a < page; a++)
                    left = 2 * a,
                        right = 2 * a + 2,
                        arrReturn.push(t[i].slice(left, right));
            return arrReturn
        }
        ,
        t.prototype.fixPair = function (t) {
            var r = t[0]
                , e = t[1];
            return this.rjust(r, e.length, "0")
        }
        ,
        t.prototype.strBreakPoint = function (t) {
            return this.fixPair([t.toString(), (t + 1).toString()])
        }
        ,
        t.prototype.rjust = function (t, r, e) {
            return e = e || " ",
                e = e.substr(0, 1),
                t.length < r ? e.repeat(r - t.length) + t : t
        }
        ,
        t.prototype.extend = function () {
            var t, r, e, n, s, i, a;
            for (i = arguments[0],
                     t = 2 <= arguments.length ? [].slice.call(arguments, 1) : [],
                     r = 0,
                     n = t.length; n > r; r++) {
                s = t[r];
                for (e in s)
                    a = s[e],
                    null != a && (i[e] = a)
            }
            return i
        }
        ,
        t.prototype.append = function (t, r) {
            return document.querySelector(t) && document.querySelector(t).insertAdjacentHTML("beforeend", r),
                this
        }
        ,
        t.prototype.isArray = Array.isArray || function (t) {
            return "[object Array]" === Object.prototype.toString.call(t)
        }
        ,
        t.prototype.isNumeric = function (t) {
            return !isNaN(parseFloat(t)) && isFinite(t)
        }
        ,
        t
}();
var a = 0;