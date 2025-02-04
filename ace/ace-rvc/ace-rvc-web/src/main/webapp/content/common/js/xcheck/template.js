/*!art-template - Template Engine | http://aui.github.com/artTemplate/*/
!function () {
    function a(a) {
        return a.replace(t, "").replace(u, ",").replace(v, "").replace(w, "").replace(x, "").split(/^$|,+/)
    }

    function b(a) {
        return "'" + a.replace(/('|\\)/g, "\\$1").replace(/\r/g, "\\r").replace(/\n/g, "\\n") + "'"
    }

    function c(c, d) {
        function e(a) {
            return m += a.split(/\n/).length - 1, k && (a = a.replace(/\s+/g, " ").replace(/<!--.*?-->/g, "")), a && (a = s[1] + b(a) + s[2] + "\n"), a
        }

        function f(b) {
            var c = m;
            if (j ? b = j(b, d) : g && (b = b.replace(/\n/g, function () {
                        return m++, "$line=" + m + ";"
                    })), 0 === b.indexOf("=")) {
                var e = l && !/^=[=#]/.test(b);
                if (b = b.replace(/^=[=#]?|[\s;]*$/g, ""), e) {
                    var f = b.replace(/\s*\([^\)]+\)/, "");
                    n[f] || /^(include|print)$/.test(f) || (b = "$escape(" + b + ")")
                } else b = "$string(" + b + ")";
                b = s[1] + b + s[2]
            }
            return g && (b = "$line=" + c + ";" + b), r(a(b), function (a) {
                if (a && !p[a]) {
                    var b;
                    b = "print" === a ? u : "include" === a ? v : n[a] ? "$utils." + a : o[a] ? "$helpers." + a : "$data." + a, w += a + "=" + b + ",", p[a] = !0
                }
            }), b + "\n"
        }

        var g = d.debug, h = d.openTag, i = d.closeTag, j = d.parser, k = d.compress, l = d.escape, m = 1,
            p = {$data: 1, $filename: 1, $utils: 1, $helpers: 1, $out: 1, $line: 1}, q = "".trim,
            s = q ? ["$out='';", "$out+=", ";", "$out"] : ["$out=[];", "$out.push(", ");", "$out.join('')"],
            t = q ? "$out+=text;return $out;" : "$out.push(text);",
            u = "function(){var text=''.concat.apply('',arguments);" + t + "}",
            v = "function(filename,data){data=data||$data;var text=$utils.$include(filename,data,$filename);" + t + "}",
            w = "'use strict';var $utils=this,$helpers=$utils.$helpers," + (g ? "$line=0," : ""), x = s[0],
            y = "return new String(" + s[3] + ");";
        r(c.split(h), function (a) {
            a = a.split(i);
            var b = a[0], c = a[1];
            1 === a.length ? x += e(b) : (x += f(b), c && (x += e(c)))
        });
        var z = w + x + y;
        g && (z = "try{" + z + "}catch(e){throw {filename:$filename,name:'Render Error',message:e.message,line:$line,source:" + b(c) + ".split(/\\n/)[$line-1].replace(/^\\s+/,'')};}");
        try {
            var A = new Function("$data", "$filename", z);
            return A.prototype = n, A
        } catch (B) {
            throw B.temp = "function anonymous($data,$filename) {" + z + "}", B
        }
    }

    var d = function (a, b) {
        return "string" == typeof b ? q(b, {filename: a}) : g(a, b)
    };
    d.version = "3.0.0", d.config = function (a, b) {
        e[a] = b
    };
    var e = d.defaults = {openTag: "<%", closeTag: "%>", escape: !0, cache: !0, compress: !1, parser: null},
        f = d.cache = {};
    d.render = function (a, b) {
        return q(a, b)
    };
    var g = d.renderFile = function (a, b) {
        var c = d.get(a) || p({filename: a, name: "Render Error", message: "Template not found"});
        return b ? c(b) : c
    };
    d.get = function (a) {
        var b;
        if (f[a]) b = f[a]; else if ("object" == typeof document) {
            var c = document.getElementById(a);
            if (c) {
                var d = (c.value || c.innerHTML).replace(/^\s*|\s*$/g, "");
                b = q(d, {filename: a})
            }
        }
        return b
    };
    var h = function (a, b) {
        return "string" != typeof a && (b = typeof a, "number" === b ? a += "" : a = "function" === b ? h(a.call(a)) : ""), a
    }, i = {"<": "&#60;", ">": "&#62;", '"': "&#34;", "'": "&#39;", "&": "&#38;"}, j = function (a) {
        return i[a]
    }, k = function (a) {
        return h(a).replace(/&(?![\w#]+;)|[<>"']/g, j)
    }, l = Array.isArray || function (a) {
            return "[object Array]" === {}.toString.call(a)
        }, m = function (a, b) {
        var c, d;
        if (l(a)) for (c = 0, d = a.length; d > c; c++) b.call(a, a[c], c, a); else for (c in a) b.call(a, a[c], c)
    }, n = d.utils = {$helpers: {}, $include: g, $string: h, $escape: k, $each: m};
    d.helper = function (a, b) {
        o[a] = b
    };
    var o = d.helpers = n.$helpers;
    d.onerror = function (a) {
        var b = "Template Error\n\n";
        for (var c in a) b += "<" + c + ">\n" + a[c] + "\n\n";
        "object" == typeof console && console.error(b)
    };
    var p = function (a) {
            return d.onerror(a), function () {
                return "{Template Error}"
            }
        }, q = d.compile = function (a, b) {
            function d(c) {
                try {
                    return new i(c, h) + ""
                } catch (d) {
                    return b.debug ? p(d)() : (b.debug = !0, q(a, b)(c))
                }
            }

            b = b || {};
            for (var g in e) void 0 === b[g] && (b[g] = e[g]);
            var h = b.filename;
            try {
                var i = c(a, b)
            } catch (j) {
                return j.filename = h || "anonymous", j.name = "Syntax Error", p(j)
            }
            return d.prototype = i.prototype, d.toString = function () {
                return i.toString()
            }, h && b.cache && (f[h] = d), d
        }, r = n.$each,
        s = "break,case,catch,continue,debugger,default,delete,do,else,false,finally,for,function,if,in,instanceof,new,null,return,switch,this,throw,true,try,typeof,var,void,while,with,abstract,boolean,byte,char,class,const,double,enum,export,extends,final,float,goto,implements,import,int,interface,long,native,package,private,protected,public,short,static,super,synchronized,throws,transient,volatile,arguments,let,yield,undefined",
        t = /\/\*[\w\W]*?\*\/|\/\/[^\n]*\n|\/\/[^\n]*$|"(?:[^"\\]|\\[\w\W])*"|'(?:[^'\\]|\\[\w\W])*'|\s*\.\s*[$\w\.]+/g,
        u = /[^\w$]+/g, v = new RegExp(["\\b" + s.replace(/,/g, "\\b|\\b") + "\\b"].join("|"), "g"),
        w = /^\d[^,]*|,\d[^,]*/g, x = /^,+|,+$/g;
    e.openTag = "{{", e.closeTag = "}}";
    var y = function (a, b) {
        var c = b.split(":"), d = c.shift(), e = c.join(":") || "";
        return e && (e = ", " + e), "$helpers." + d + "(" + a + e + ")"
    };
    e.parser = function (a, b) {
        a = a.replace(/^\s/, "");
        var c = a.split(" "), e = c.shift(), f = c.join(" ");
        switch (e) {
            case "if":
                a = "if(" + f + "){";
                break;
            case "else":
                c = "if" === c.shift() ? " if(" + c.join(" ") + ")" : "", a = "}else" + c + "{";
                break;
            case "/if":
                a = "}";
                break;
            case "each":
                var g = c[0] || "$data", h = c[1] || "as", i = c[2] || "$value", j = c[3] || "$index", k = i + "," + j;
                "as" !== h && (g = "[]"), a = "$each(" + g + ",function(" + k + "){";
                break;
            case "/each":
                a = "});";
                break;
            case "echo":
                a = "print(" + f + ");";
                break;
            case "print":
            case "include":
                a = e + "(" + c.join(",") + ");";
                break;
            default:
                if (-1 !== f.indexOf("|")) {
                    var l = b.escape;
                    0 === a.indexOf("#") && (a = a.substr(1), l = !1);
                    for (var m = 0, n = a.split("|"), o = n.length, p = l ? "$escape" : "$string",
                             q = p + "(" + n[m++] + ")"; o > m; m++) q = y(q, n[m]);
                    a = "=#" + q
                } else a = d.helpers[e] ? "=#" + e + "(" + c.join(",") + ");" : "=" + a
        }
        return a
    }, "function" == typeof define ? define(function () {
        return d
    }) : "undefined" != typeof exports ? module.exports = d : this.template = d
}();

/**
 * �����ڽ��и�ʽ����
 * @param date Ҫ��ʽ��������
 * @param format ���и�ʽ����ģʽ�ַ���
 *     ֧�ֵ�ģʽ��ĸ�У�
 *     y:��,
 *     M:���е��·�(1-12),
 *     d:�·��е���(1-31),
 *     h:Сʱ(0-23),
 *     m:��(0-59),
 *     s:��(0-59),
 *     S:����(0-999),
 *     q:����(1-4)
 * @return String
 * @author yanis.wang
 * @see    http://yaniswang.com/frontend/2013/02/16/dateformat-performance/
 */
template.helper('dateFormat', function (date, format) {
    if (typeof date === "string") {
        var mts = date.match(/(\/Date\((\d+)\)\/)/);
        if (mts && mts.length >= 3) {
            date = parseInt(mts[2]);
        }
    }
    date = new Date(date);
    if (!date || date.toUTCString() == "Invalid Date") {
        return "";
    }

    var map = {
        "M": date.getMonth() + 1, //�·�
        "d": date.getDate(), //��
        "h": date.getHours(), //Сʱ
        "m": date.getMinutes(), //��
        "s": date.getSeconds(), //��
        "q": Math.floor((date.getMonth() + 3) / 3), //����
        "S": date.getMilliseconds() //����
    };
    format = format.replace(/([yMdhmsqS])+/g, function (all, t) {
        var v = map[t];
        if (v !== undefined) {
            if (all.length > 1) {
                v = '0' + v;
                v = v.substr(v.length - 2);
            }
            return v;
        }
        else if (t === 'y') {
            return (date.getFullYear() + '').substr(4 - all.length);
        }
        return all;
    });
    return format;
});