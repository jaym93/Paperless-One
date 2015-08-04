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
/*	$(document).ajaxStart(function() {
		  $('#loadingmessage').show();
		  $('#main-data').hide();
		  
		}).ajaxStop(function() {
		  $('#loadingmessage').hide();
		  $('#main-data').show();
		
		});*/
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
	  $.ajax({

	        type : 'POST',
	          url : 'http://localhost:8080/papone/ExhibitStatus',		          
	          contentType: 'application/x-www-form-urlencoded',
	          dataType: 'json',
	           success : function(response) {
	        	   setDataStatus(response);
	        	  
	        }
	    }

	    );
	  
	  function setDataStatus(result){
		 setStatusClass(result);
	  }
	  
	  function setStatusClass(result){
		  $('#audit').addClass(result.auditStatus.statusClass);
		  $('#binder').addClass(result.binderStatus.statusClass);
		  $('#dashboard').addClass(result.dashboardStatus.statusClass);
		  $('#know').addClass(result.knowStatus.statusClass);
		  $('#myc').addClass(result.mycoachStatus.statusClass);
		  $('#order').addClass(result.orderStatus.statusClass);
		  $('#sea').addClass(result.seaStatus.statusClass);
		  $('#visit').addClass(result.visitStatus.statusClass);
		  
		  
	  }
	  function setData(result){
		  
		  document.getElementById("incidents-today").innerHTML =result.imToday;
		  document.getElementById("fulfillments-today").innerHTML =result.frToday;
		  document.getElementById("incidents-month").innerHTML =result.imMonth;
		  document.getElementById("fulfillments-month").innerHTML =result.frMonth;
		  document.getElementById("problems-month").innerHTML =result.prMonth;
		  document.getElementById("changes-month").innerHTML =result.crMonth;
		  var count1 = Number(result.imMonth);
		  var count2 = Number(result.frMonth);
		  var total = count1+count2;
		  document.getElementById("total").innerHTML =total;
		 fillTable(result);
		 findLinks();
		 
		  
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
		                events: result.calArray

		            });
		        })();
		    }
		  
	  }
	  
	  function fillTable(result){
		  var data = result.topIssueList;
		  var r = new Array(), j = -1;
		  for (var key=0, size=data.length; key<size; key++){
		      r[++j] ='<tr><td class="f-500">';
		      r[++j] = data[key].im;
		      r[++j] = '</td><td class="f-500">';
		      r[++j] = data[key].appName;
		      r[++j] = '</td><td class="f-500">';
		      r[++j] = data[key].title;
		      r[++j] = '</td><td class="f-500 c-red">';
		      r[++j] = data[key].priority;
		      r[++j] = '</td><td class="f-500">';
		      r[++j] = data[key].status;
		      r[++j] = '</td></tr>';
		  }
		  $('#topIssues').html(r.join('')); 
	  }
});