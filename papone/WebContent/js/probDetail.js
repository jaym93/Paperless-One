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
		          url : 'http://localhost:8080/papone/PrDetailService',	
		          contentType: "application/x-www-form-urlencoded; charset=UTF-8;",
		          dataType: "json",
		          data: {
		              ci: ci
		              
		          },
		          success : function(response) {
		        	  
		        	  drawGraphPrDetail(response);
		        	  
		        	  
		        }
		   });
	  $.ajax({

	        type : 'POST',
	          url : 'http://localhost:8080/papone/PrService',	
	          contentType: "application/x-www-form-urlencoded; charset=UTF-8;",
	          dataType: "json",
	          data: {
	              ci: ci
	              
	          },
	          success : function(response) {
	        	  
	        	  drawGraphPr(response);
	        	  
	        	  
	        }
	   });
	  $.ajax({

	        type : 'POST',
	          url : 'http://localhost:8080/papone/ApplicationService',	
	          contentType: "application/x-www-form-urlencoded; charset=UTF-8;",
	          dataType: "json",
	          data: {
	              ci: ci
	              
	          },
	          success : function(response) {
	        	  
	        	  drawGraphSeverity(response);
	        	  
	        	  
	        }
	   });
	 

	function drawGraph(response){
		
	}
	
	  
	  

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
	  
	
	  
});