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
		          url : 'http://localhost:8080/papone/FRService',		          
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
		  drawGraph(result);
	  }
	  
	  function drawGraph(result){
		  $(function () {
              $('#pr-graph-container').highcharts({
                  title: {
                      text: 'Month-wise FR snapshot',
                      x: -20 //center
                  },
                  subtitle: {
                      text: 'Click-and-drag chart area to zoom in, click \'Reset zoom\' button to reset'
                  },
                  chart: {
                      zoomType: 'x'
                  },
                  xAxis: {
                      categories: result.month.reverse()
                  },
                  yAxis: {
                      title: {
                          text: 'Number of FRs'
                      },
                      plotLines: [{
                          value: 0,
                          width: 1,
                          color: '#808080'
                      }]
                  },
                  tooltip: {
                      valueSuffix: ''
                  },
                  legend: {
                      layout: 'vertical',
                      align: 'right',
                      verticalAlign: 'middle',
                      borderWidth: 0
                  },
                  series: [{
                      name: 'FR Count',
                      color: '#CC33FF',
                      data: result.frCount.reverse()
                  }]
              });
          });
	  }
	  
	  function fillTable(result){
		  var data = result.incidentList;
		  var r = new Array(), j = -1;
		  for (var key=0, size=data.length; key<size; key++){
		      r[++j] ='<tr><td class="f-500">';
		      r[++j] = data[key].id;
		      r[++j] = '</td><td class="f-500">';
		      r[++j] = data[key].title;
		      r[++j] = '</td><td class="f-500">';
		      r[++j] = data[key].ci;
		      r[++j] = '</td><td class="f-500">';
		      r[++j] = data[key].specialist;
		      r[++j] = '</td><td class="f-500">';
		      r[++j] = data[key].requestDate;
		      r[++j] = '</td><td class="f-500">';
		      r[++j] = data[key].targetDate;
		      r[++j] = '</td><td class="f-500">';
		      r[++j] = data[key].status;
		      r[++j] = '</td></tr>';
		    
		      
		  }
		  $('#fr-body').html(r.join('')); 
	  }
});