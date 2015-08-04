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

	
	$(window).load(function() {
		$(".loader").fadeOut("slow");
	});
	
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
		  
		  
		  document.getElementById("percent_audit").innerHTML = result.auditStatus.cwttNum;
		  document.getElementById("percent_binder").innerHTML = result.binderStatus.cwttNum;
		  document.getElementById("percent_dashboard").innerHTML = result.dashboardStatus.cwttNum;
		  document.getElementById("percent_know").innerHTML = result.knowStatus.cwttNum;
		  document.getElementById("percent_myc").innerHTML = result.mycoachStatus.cwttNum;
		  document.getElementById("percent_order").innerHTML = result.orderStatus.cwttNum;
		  document.getElementById("percent_sea").innerHTML = result.seaStatus.cwttNum;
		  document.getElementById("percent_visit").innerHTML = result.visitStatus.cwttNum;
		  
		  document.getElementById("audit_qd").innerHTML = result.auditStatus.qdNum;
		  document.getElementById("binder_qd").innerHTML = result.binderStatus.qdNum;
		  document.getElementById("dashboard_qd").innerHTML = result.dashboardStatus.qdNum;
		  document.getElementById("know_qd").innerHTML = result.knowStatus.qdNum;
		  document.getElementById("myc_qd").innerHTML = result.mycoachStatus.qdNum;
		  document.getElementById("order_qd").innerHTML = result.orderStatus.qdNum;
		  document.getElementById("sea_qd").innerHTML = result.seaStatus.qdNum;
		  document.getElementById("visit_qd").innerHTML = result.visitStatus.qdNum;
		  
		
		  
		  $('#percent_audit').addClass(result.auditStatus.cwttClass);
		  $('#percent_binder').addClass(result.binderStatus.cwttClass);
		  $('#percent_dashboard').addClass(result.dashboardStatus.cwttClass);
		  $('#percent_know').addClass(result.knowStatus.cwttClass);
		  $('#percent_myc').addClass(result.mycoachStatus.cwttClass);
		  $('#percent_order').addClass(result.orderStatus.cwttClass);
		  $('#percent_sea').addClass(result.seaStatus.cwttClass);
		  $('#percent_visit').addClass(result.visitStatus.cwttClass);
		  
		  
		  $('#audit_qd').addClass(result.auditStatus.qdClass);
		  $('#binder_qd').addClass(result.binderStatus.qdClass);
		  $('#dashboard_qd').addClass(result.dashboardStatus.qdClass);
		  $('#know_qd').addClass(result.knowStatus.qdClass);
		  $('#myc_qd').addClass(result.mycoachStatus.qdClass);
		  $('#order_qd').addClass(result.orderStatus.qdClass);
		  $('#sea_qd').addClass(result.seaStatus.qdClass);
		  $('#visit_qd').addClass(result.visitStatus.qdClass);
	  }
	  function setData(result){
		  
		  document.getElementById("row_incidents_today").innerHTML =result.imToday;
		  document.getElementById("row_fulfillments_today").innerHTML =result.frToday;
		  document.getElementById("row_incidents_month").innerHTML =result.imMonth;
		  document.getElementById("row_fulfillments_month").innerHTML =result.frMonth;
		  document.getElementById("row_problems_month").innerHTML =result.prMonth;
		  document.getElementById("row_changes_month").innerHTML =result.crMonth;
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