$('#pricerange').ionRangeSlider({
    type: 'double',
    min: 1000,
    max: 20000,
    step: 1000,
    prefix: 'р.',
    grid_snap: true,
    grid: true
});

$('.datepicker').datepicker({
    language: 'ru',
    autoclose: true,
    startDate: new Date(),
    format: 'dd.mm.yy'
});

(function () {
    var params = {
        'region': $('input[name=region]').val()
    };

    function loadContent() {
        $(document.body).addClass("loading");
        params[this.name] = this.value;

        $.ajax({
            method: 'post',
            url: contextPath + '/ru.com.m74.booking4u.catalog.service.RegionHotels.do',
            data: params,
            success: function (arr) {
                var div = $('#hotelslist');
                div.html('');
                $.each(arr, function (i, o) {
                    var str = '';
                    str += '<div class="row hotel">';
                    str += '<div class="col col-md-3">';
                    str += '<img src="' + o.icon + '" class="img-responsive img-thumbnail" />';
                    str += '</div>';
                    str += '<div class="col col-md-6">';
                    str += '<h3>' + o.title + '</h3>';
                    str += '<p class="description">';
                    str += '<small>' + o.annotation + '</small>';
                    str += '</p>';
                    str += '</div>';
                    str += '<div class="col col-md-3">';
                    str += '<p class="price">' + o.price + '</p>';
                    str += '<p class="buttons">';
                    str += '<a href="' + contextPath + '/hotel/' + o.id + '/" class="btn btn-default" role="button">';
                    str += 'Просмотр';
                    str += '</a>';
                    str += '</p>';
                    str += '</div>';
                    str += '</div>';
                    str += '<hr />';
                    div.append(str);
                });
                $(document.body).removeClass("loading");
            }
        });
    }

    $('input.datepicker').on('change', function (e) {
        loadContent.call(this);
    });

    $('input#pricerange').on('change', function (e) {
        if (this.tid)
            clearTimeout(this.tid);
        var me = this;
        this.tid = setTimeout(function () {
            loadContent.call(me);
        }, 1000);
    });

    $('input#filter').on('keyup', function (e) {
        if (e.keyCode == 27)
            this.value = '';
        if (this.tid)
            clearTimeout(this.tid);
        var me = this;
        this.tid = setTimeout(function () {
            loadContent.call(me);
        }, 1000);
    });
})();
