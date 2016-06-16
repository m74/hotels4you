(function() {
	function begin() {
		$.ajax({
		  url : contextPath + '/ru.com.m74.booking4u.booking.service.Reservations.do',
		  success : function(resp) {

			  var div = $('#main');
			  div.html('');

			  if (resp.list) {
				  div.append('<div class="page-header"><h3>1. Имена постояльцев</h3></div>');
				  div.append('<p>Укажите имена постояльцев, которые будут проживать в забронированных Вами номерах.</p>');
				  div.append('<hr/>');
				  div.append('<button class="btn btn-default" id="clear"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span> Удалить все</button>');
				  div.append('<form><div id="mybooking"></div></form>');
				  div
				      .append('<p style="text-align: right; margin-top: 20px;">'
				          + '<button id="next" class="btn btn-primary">Подолжить <span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span></button>'
				          + '</p>');

				  $('#clear').on('click', function() {
					  $.ajax({
					    url : contextPath + '/ru.com.m74.booking4u.booking.service.Clear.do',
					    success : function(arr) {
						    $('span.badge').html('');
						    begin();
					    }
					  });
				  });

				  $('#next').on('click', function() {
					  if ($('input').valid()) {
						  div.addClass('bounceOutLeft animated');
					  }
				  });

				  $.each(resp.list, function(i, o) {
					  var html = '';
					  html += '<div class="row" style="padding: 10px; border-bottom: 1px solid #eee;">';
					  html += '<div class="col-sm-2"><img class="img-responsive" src="' + o.icon + '"></div>';
					  html += '<div class="col-sm-8">';
					  html += '<h3 style="margin-top: 0;">' + o.title + '</h3>';
					  html += '<input required name="guestnames_' + i + '" class="form-control" placeholder="ФИО постояльцев">';
					  html += '</div>';
					  html += '<div class="col-sm-2" style="text-align: right;"><a href="#" class="btn btn-default"> Удалить</button></a>';
					  html += '</div>';
					  $('#mybooking').append(html);
				  });
			  } else {
				  div.append('<p class="lead">Отсутствуют забронированные номера</p>');
			  }
		  }
		});
	}

	begin();
})();

// glyphicon-menu-right
