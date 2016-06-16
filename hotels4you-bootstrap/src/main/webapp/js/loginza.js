(function($) {
	$.fn.loginza = function(o) {
		var url = location.href.split('/').slice(0, 4).join('/') + '/loginza';
		console.log('loginza.url: ', url);

		this.html('<iframe src="http://loginza.ru/api/widget?overlay=loginza&token_url=' + url + '&lang=' + o.lang + '&providers_set=' + o.providers.join(',')
		    + '" style="width: 100%; min-height: 200px;" scrolling="no" frameborder="no"></iframe>');
	};
})(jQuery);

$('#loginza').loginza({
  lang : "ru",
  providers : [ 'vkontakte', 'facebook', 'google', 'yandex', 'twitter', 'odnoklassniki' ]
});