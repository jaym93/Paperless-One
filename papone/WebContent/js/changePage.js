$(document).ready(function(){
function findLinks() {
	    var links = $('.find-tickets');
	    links.html(replaceURLWithHTMLLinks(links.html()));
	}

	function replaceURLWithHTMLLinks(text) {
		
	    var exp = /((IM|FR|PM|C)\d\d\d\d\d\d\d\d)/ig;
	    if(text!=undefined)
	        return text.replace(exp, "<a class='link' style='cursor:pointer;' onclick='showModal(\"$1\");'>$1</a>");
	};

	
    /*
     * Top Search
     */

	  $.ajax({

		        type : 'POST',
		          url : 'http://localhost:8080/papone/Exhibit',		          
		          contentType: 'application/x-www-form-urlencoded',
		          dataType: 'json',
		           success : function(response) {
		        	   
		        	   setData(response);
		        	  
		        }
		    }

		    );
	 
	  
	 
	
	  function setData(result){
		 
		 fillTable(result);
		  $('#data-table-basic').DataTable({
              "ordering": true,
              "paging": true,
              "info": true,
              "fnDrawCallback":findLinks(),
              "fnRowCallback": function( nRow, aData ) {
            	  var $nRow = $(nRow); // cache the row wrapped up in jQuery
            	  if (aData[6] == "2 - High") {
            	    $nRow.css({"background-color":"#ffffcc"})
            	  }
              	if (aData[3] == "3 - Critical" ||aData[3] == "2 - High" ) {
            	    $nRow.css({"background-color":"#ffdddd"})
            	  }
            	  return nRow
            	},
              "responsive": true,
              "autoWidth": true,
              "orderMulti": true,
              "pagingType": "full_numbers",
              "dom": "CRTlfrtip",
              "tableTools": {
                  "sSwfPath": "/papone/vendors/datatable/TableTools/swf/copy_csv_xls_pdf.swf",
                  "sExtends": "collection",
                  "sButtonText": "Save",
                  "aButtons": [{"sExtends":"copy", "sButtonText": "Copy","oSelectorOpts": {filter: 'applied'}},{"sExtends":"csv","oSelectorOpts": {filter: 'applied'}}, {"sExtends":"pdf","oSelectorOpts": {filter: 'applied'}}]
              }
          });		 
	  }
	  
	  function fillTable(result){
		  var data = result.calPresentMonthArray;
		  var r = new Array(), j = -1;
		  for (var key=0, size=data.length; key<size; key++){
		      r[++j] ='<tr><td class="f-500">';
		      r[++j] = data[key].title;
		      r[++j] = '</td><td class="f-500">';
		      r[++j] = data[key].textBody;
		      r[++j] = '</td><td class="f-500">';
		      r[++j] = data[key].changeType;
		      r[++j] = '</td><td class="f-500">';
		      r[++j] = data[key].start;
		      r[++j] = '</td><td class="f-500">';
		      r[++j] = data[key].end;
		      r[++j] = '</td></tr>';
		    
		      
		  }
		  $('#change-body').html(r.join('')); 
		  
		  if($('#calendar-widget-new')[0]) {
		        (function () {
		            var today = new Date();
		            var dd = today.getDate();
		            var mm = today.getMonth() + 1;
		            var yyyy = today.getFullYear();
		            today = yyyy + "-" + mm + "-" + dd;
		            $('#calendar-widget-new').fullCalendar({
				        contentHeight: 'auto',
				        theme: true,
		                header: {
		                    right: 'nextYear',
		                    center: 'prev, title, next',
		                    left: 'prevYear'
		                },
		                eventMouseover: function (event, jsEvent, view) {
		                    $this = $(this);
		                    $this.popover({ html: true, title:event.textBody, placement: 'top',content:'Start: '+ (event.start ? new Date(event.start).toString().substr(4,17) : "--") + '<br> End:'+ (event.end ? new Date(event.end).toString().substr(4,17) : " -- ") + ' <br> Category: ' + (event.className=='bgm-red'?"Emergency":(event.className=='bgm-blue'?"Expedited":"Normal")),container: 'body' }).popover('show');
		                    return true;
		                },
		                eventMouseout: function (event, jsEvent, view) {
		                    $this = $(this);
		                    $this.popover().popover('hide');
		                    return true;
		                },
		               // defaultDate: today,
		                editable: false,
		                events: result.calPresentMonthArray

		            });
		        })();
		    }
	  }
});