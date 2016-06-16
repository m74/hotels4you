$('#regionsearch-query')
    .typeahead(
        {
          minLength : 1,
          maxItem : 10,
          order : "asc",
          hint : true,
          href : '{{group}}/{{id}}/',
          // searchOnFocus : true,
          dynamic : true,
          delay : 500,
          source : {
            region : {
              display : 'title',
              template : '{{title}} ({{count}})',
              url : [ {
                type : 'POST',
                data : {
	                query : '{{query}}'
                },
                url : 'ru.com.m74.booking4u.catalog.service.RegionSearch.do'
              } ]
            },
            hotel : {
              display : 'title',
              template : '<div class="row"><img src="{{icon}}" height="32" style="float: left; margin-right: 10px;"><div>{{title}} {{rating}}, {{region}}</div><small>{{type}}</small></div>',
              url : [ {
                type : 'POST',
                data : {
	                query : '{{query}}'
                },
                url : 'ru.com.m74.booking4u.catalog.service.HotelSearch.do'
              } ]
            }
          },
          callback : {
	          onSubmit : function(n, f, item, e) {
		          console.log("args:", item);
		          e.stopPropagation();
		          return false;
	          }
          }
        });

$('#regionsearch-query').focus();