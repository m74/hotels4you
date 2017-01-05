PNotify.prototype.options.icon = false;
PNotify.prototype.options.stack.context = $('#messages');
PNotify.prototype.options.styling = "bootstrap3";
PNotify.prototype.options.animate.animate = true;
PNotify.prototype.options.animate.in_class = "bounceIn";
PNotify.prototype.options.animate.out_class = "bounceOut";

function msg(c) {
    new PNotify(c);
}


Array.prototype.contains = function (item) {
    return this.indexOf(item) != -1;
};

String.prototype.contains = function (item) {
    return this.indexOf(item) != -1;
};


// find contextPath
var contextPath =
    (function (arr) {
        var href = "/js/site.js";
        for (var i = 0; i < arr.length; i++) {
            var el = arr[i];
            if (el.src.contains(href))return el.src.substring(0, el.src.length - href.length);
        }
    })(document.getElementsByTagName('script'));

console.log('contextPath: ', contextPath);