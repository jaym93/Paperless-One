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



		var ci = localStorage.getItem('ci');	
		
	  $.ajax({

		        type : 'POST',
		          url : 'http://localhost:8080/papone/ApplicationService',	
		          contentType: "application/x-www-form-urlencoded; charset=UTF-8;",
		          dataType: "json",
		          data: {
		              ci: ci
		              
		          },
		          success : function(response) {
		        	  alert(response);
		        	  drawGraph();
		        	  
		        	  
		        }
		   });
	  
	 

	function drawGraph(){
		$(function () {
            $('#pr-graph-container').highcharts({
                title: {
                    text: 'Monthly snapshot',
                    x: -20 //center
                },
                subtitle: {
                    text: 'Click-and-drag chart area to zoom in, click \'Reset zoom\' button to reset'
                },
                chart: {
                    zoomType: 'x'
                },
                xAxis: {
                    categories: ['Pavan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
                        'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
                },
                yAxis: {
                    title: {
                        text: 'Number of PRs'
                    },
                    plotLines: [{
                        value: 0,
                        width: 1,
                        color: '#808080'
                    }]
                },
                tooltip: {
                    valueSuffix: 'tickets'
                },
                legend: {
                    layout: 'vertical',
                    align: 'right',
                    verticalAlign: 'middle',
                    borderWidth: 0
                },
                series: [{
                    name: 'Open PRs',
                    color: '#FF9800',
                    data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]
                }, {
                    name: 'Closed PRs',
                    color: '#4CAF50',
                    data: [-0.2, 0.8, 5.7, 11.3, 17.0, 22.0, 24.8, 24.1, 20.1, 14.1, 8.6, 2.5]
                }]
            });
        });
	}
	
	  
	  
	 
	 
	  function setData(result){
		 
		

	  function showModalData(arr,result){
		  
		 var modal =null;
		  switch(arr){
		  
		  case 1: modal = result.problemDetailMinor;break;
		  case 2: modal = result.problemDetailMajor; break;
		  case 3: modal = result.problemDetailCritical; break;
		  case 4: modal = result.ticketDetailMajor; break;
		  case 5: modal = result.ticketDetailMinor; break;
		  case 6: modal = result.ticketDetailCritical; break;
		  }
		  	  
		  
	
		  var m = new Array(), j = -1;
		  for (var key=0, size=modal.length; key<size; key++){
		      m[++j] ='<tr><td class="f-500 c-black">';
		      m[++j] = modal[key].id;
		      m[++j] = '</td><td class="f-500 c-black">';
		      m[++j] = modal[key].title;
		      m[++j] = '</td><td class="f-500 c-black">';
		      m[++j] = modal[key].appName;
		      m[++j] = '</td><td class="f-500 c-black">';
		      m[++j] = modal[key].specialist;
		      m[++j] = '</td><td class="f-500 c-black">';
		      m[++j] = modal[key].status;
		      m[++j] = '</td></tr>';
		      
		      
		  }
		  $('#tickets-modal').html(m.join('')); 
		 $("#app-modal").modal('show');
		 
	  }
	  
	  function modalDetails(arr,result){
		 
			  var m = new Array(), j = -1;
			  for (var key=0, size=result.length; key<size; key++){
			      m[++j] ='<tr><td class="f-500 c-black">';
			      m[++j] = result[key].id;
			      m[++j] = '</td><td class="f-500 c-black">';
			      m[++j] = result[key].title;
			      m[++j] = '</td><td class="f-500 c-black">';
			      m[++j] = result[key].status;
			      m[++j] = '</td><td class="f-500 c-black">';
			      m[++j] = result[key].specialist;
			      m[++j] = '</td><td class="f-500 c-black">';
			      m[++j] = result[key].requestDate;
			      m[++j] = '</td><td class="f-500 c-black">';
			      m[++j] = result[key].targetDate;
			      m[++j] = '</td></tr>';
			      
			      
			  }
			  $('#tickets-modal-fr').html(m.join('')); 
			 $("#fr-modal").modal('show');
			 
		  }
	  
	
	  }
});