var Bootstrap = {
  charset : 'utf-8',
  script : function(c) {
	  if (typeof (c) == 'string')
		  c = {
			  src : c
		  };
	  document.write('<script');
	  if (c.src) {
		  document.write(' src="');
		  document.write(this.url(c.src));
		  document.write('"');
	  }
	  document.write(' type="');
	  document.write(c.type || 'text/javascript');
	  document.write('"');
	  document.write(' charset="' + (c.charset || this.charset) + '"');
	  document.write('>');
	  if (c.code)
		  document.write(c.code);
	  document.write('</script>');
	  console.log("script: ", c.src);
  },
  css : function(src) {
	  document.write('<link rel="stylesheet" type="text/css" media="all" href="' + this.url(src) + '" />');
	  console.log("css: ", src);
  },
  url : function(uri) {
	  if (!uri.match(/^(http|https):\/\//)) {
		  uri = uri.replace(/^\.\//, '');
		  // if (this.contextName != '/')
		  uri = uri.replace(/^\//, this.contextPath + '/');
	  }
	  return uri;
  },
  load : function(arr) {
	  for (var i = 0; i < arr.length; i++) {
		  var rc = arr[i];
		  if (rc.match(/\.js$/))
			  this.script(rc);
		  if (rc.match(/\.css$/))
			  this.css(rc);
	  }
  }
};
// init contextPath
{
	var arr = (function(re) {
		re = new RegExp('^.*' + re + '.*$');
		var scriptTags = document.getElementsByTagName("script");
		for (var i = 0; i < scriptTags.length; i++) {
			var src = scriptTags[i].src;

			if (src) {
				// если указан относительный путь к скрипту (для IE)
				if (!src.match(/^https?:\/\//))
					src = (src.match(/^\//) ? this.serverPath : base) + src;
				if (src.match(re))
					return src;
			}
		}
		return null;
	})("bootstrap.js").split('?');

	Bootstrap.params = (function(query) {
		var params = {};
		var seg = query.replace(/^\?/, '').split('&');
		for (var i = 0; i < seg.length; i++) {
			if (!seg[i])
				continue;
			var s = seg[i].split('=');
			params[s[0]] = s[1] ? s[1] : true;
		}
		return params;
	})(arr[1] || '');

	Bootstrap.contextPath = arr[0].replace(/\/bootstrap.js$/, '').replace(location.origin, '');
}

Bootstrap.load([ 'ext/build/ext.js',
    'ext/build/classic/theme-${theme}/theme-${theme}${prefix}.js', 'ext/build/classic/theme-${theme}/resources/theme-${theme}-all${prefix}.css',
    'ext/build/classic/locale/locale-ru${prefix}.js',
]);
