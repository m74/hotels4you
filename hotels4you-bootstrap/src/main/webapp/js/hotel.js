$('.datepicker').datepicker({
  language : 'ru',
  autoclose : true,
  startDate : new Date(),
  format : 'dd.mm.yy'
});

(function() {

	$('input.datepicker').on('change', function() {
		var params = {};
		params[this.name] = this.value;
		$.ajax({
		  method : 'post',
		  url : contextPath + '/save',
		  data : params
		});
	});

	$('form').on(
	    'submit',
	    function() {
		    $.ajax({
		      method : 'post',
		      url : contextPath + '/ru.com.m74.booking4u.catalog.service.AddRoom.do',
		      data : {
		        arrival : $('input[name=arrival]').val(),
		        departure : $('input[name=departure]').val(),
		        room : this.room.value
		      },
		      success : function(r) {
			      if (r.exception) {
				      msg({
				        title : 'Ошибка',
				        type : 'error',
				        text : r.exception
				      });
			      } else {
				      msg({
				        title : 'Бронирование номера',
				        text : 'Выбранный Вами номер добавлен в раздел <a href="' + contextPath
				            + '/mybooking/">"Мое бронирование"</a>. Для завершения Вам необходимо перейти в этот раздел.' + '<hr><a href="' + contextPath
				            + '/mybooking/" class="btn btn-default">Перейти к оформлению бронирования</a>'
				      });
				      if (r.badge > 0)
					      $('span.badge').html(r.badge);
			      }
		      }
		    });
		    return false;
	    });

})();