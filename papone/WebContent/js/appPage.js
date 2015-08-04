$(document).ready(function(){
	  if ((localStorage.getItem('application') == "") || (localStorage.getItem('application') == null)) {
		 
          localStorage.setItem('application', 'Paperless Instore');
      }
      switch (localStorage.getItem('application')) {
          case 'Paperless Instore': $('#app_icon').attr("src", "img/applications/paperless.png"); sendAjax("PINS");document.getElementById('app_status').innerHTML+="Paperless Instore";document.getElementById('about').innerHTML+=" Paperless Instore";localStorage.setItem('ci', 'PINS%'); break;
          case 'Audit': $('#app_icon').attr("src", "img/applications/audit.png"); sendAjax("PINS-AUDIT");document.getElementById('app_status').innerHTML+="AUDIT";document.getElementById('about').innerHTML+=" Audit";localStorage.setItem('ci', 'PINS-AUDIT%'); break;
          case 'Binder': $('#app_icon').attr("src", "img/applications/binder.png");sendAjax("PINS-BINDER"); document.getElementById('app_status').innerHTML+="BINDER"; document.getElementById('about').innerHTML+=" Binder";localStorage.setItem('ci', 'PINS-BINDER%'); break;
          case 'Dashboard': $('#app_icon').attr("src", "img/applications/dashboard.png");sendAjax("PINS-DASHBOARD"); document.getElementById('app_status').innerHTML+="DASHBOARD";document.getElementById('about').innerHTML+=" Dashboard";localStorage.setItem('ci', 'PINS-DASHBOARD%'); break;
          case 'Know': $('#app_icon').attr("src", "img/applications/know.png");sendAjax("PINS-KNOW"); document.getElementById('app_status').innerHTML+="KNOW"; document.getElementById('about').innerHTML+=" Know";localStorage.setItem('ci', 'PINS-KNOW%');break;
          case 'MyCoach': $('#app_icon').attr("src", "img/applications/mycoach.png");sendAjax("PINS-MYC"); document.getElementById('app_status').innerHTML+="MYCOACH"; document.getElementById('about').innerHTML+=" MyCoach"; localStorage.setItem('ci', 'PINS-MYC%');break;
          case 'Order': $('#app_icon').attr("src", "img/applications/order.png");sendAjax("PINS-ORDER"); document.getElementById('app_status').innerHTML+="ORDER"; document.getElementById('about').innerHTML+=" Order";localStorage.setItem('ci', 'PINS-ORDER%'); break;
          case 'SEApps': $('#app_icon').attr("src", "img/applications/sea.png");sendAjax("PINS-SEA"); document.getElementById('app_status').innerHTML+="SEA APPS"; document.getElementById('about').innerHTML+=" SEA Apps"; localStorage.setItem('ci', 'PINS-SEA%');break;
          case 'Visit NG': $('#app_icon').attr("src", "img/applications/visitng.png");sendAjax("PINS-VISIT");document.getElementById('app_status').innerHTML+="VISIT NG"; document.getElementById('about').innerHTML+=" Visit"; localStorage.setItem('ci', 'PINS-VISIT%');break;
      }

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
	function sendAjax(ciParam){
		
	  $.ajax({

		        type : 'POST',
		          url : 'http://localhost:8080/papone/ApplicationService',	
		          contentType: "application/x-www-form-urlencoded; charset=UTF-8;",
		          dataType: "json",
		          data: {
		              ci: ciParam
		              
		          },
		          success : function(response) {
		        	  setData(response);
		        	  
		        	  
		        }
		    }

		    );
	  
	  $.ajax({

	        type : 'POST',
	          url : 'http://localhost:8080/papone/SpocDetail',	
	          contentType: "application/x-www-form-urlencoded; charset=UTF-8;",
	          dataType: "json",
	          data: {
	              ci: ciParam
	              
	          },
	          success : function(response) {
	        	  setSpocDetails(response);
	        	  
	        	  
	        }
	    }

	    );
	}
	
	  
	  
	  function setSpocDetails(ajaxResponse){
		  document.getElementById('spoc_location').innerHTML='<font size="4px">'+' '+ ajaxResponse.spocLocation+'</font>';
    	  document.getElementById('spoc_email').innerHTML='<font size="4px">'+' '+ ajaxResponse.spocEmail+'</font>';
    	  document.getElementById('app_pdl').innerHTML='<font size="4px">'+' '+ajaxResponse.appPdl+'</font>';
    	  document.getElementById('app_desc').innerHTML=ajaxResponse.appDesc;
    	  document.getElementById('spoc_name').innerHTML=ajaxResponse.spocName;    	  
    	  $('#mail-to').attr("href", ajaxResponse.mailTo );
    	 
	  }
	 
	  function setData(result){
		 
		 fillTable(result);
		 findLinks();
		  $(function () {
              $('#pr-priority').highcharts({
                  chart: {
                      type: 'pie',
                      options3d: {
                          enabled: true,
                          alpha: 45
                      }
                  },
                  exporting:false,
                  title: {
                      text: ''
                  },
                  tooltip: {
                      pointFormat: '{series.name}: <b>{point.y}</b>'
                  },
                  plotOptions: {
                      pie: {
                          point: {
                              events: {
                                  mouseOver: function (event) {
                                      this.slice(true);
                                  },
                                  mouseOut: function (event) {
                                      this.slice(false);
                                  }
                              }
                          },
                          showInLegend: true,
                          allowPointSelect: true,
                          depth: 35,
                          dataLabels: {
                              enabled: false,
                              format: '{point.name}'
                          },
                          innerSize: 100,
                          depth: 45,
                      },

                  },
                  series: [{
                      name: 'Priority',
                      data: [
                             ['Critical',result.criticalProblem],
                             ['Major',result.majorProblem],
                             ['Minor',result.minorProblem]
                             
                         ],
                      params: [3, 2, 1],
                      colors: ['#F44336', '#FF9800', '#4CAF50'],
                      cursor: 'pointer',
                      point: {
                          events: {
                        	  click: function () {
                             	 showModalData(this.series.userOptions.params[this.x],result);
                              }
                          }
                      }
                  }]
              });
          });
		  
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
		 $(function () {
             $('#fulfillment-chart').highcharts({
                 exporting: false,
                 chart: {
                     plotBackgroundColor: null,
                     plotBorderWidth: null,
                     plotShadow: false
                 },
                 title: {
                     text: 'Current open FRs'
                 },
                 tooltip: {
                     pointFormat: '{series.name}: <b>{point.y}</b>'
                 },
                 plotOptions: {
                     pie: {
                         point: {
                             events: {
                                 mouseOver: function (event) {
                                     this.slice(true);
                                 },
                                 mouseOut: function (event) {
                                     this.slice(false);
                                 }
                             }
                         },
                         allowPointSelect: true,
                         cursor: 'pointer',
                         dataLabels: {
                             enabled: false
                         },
                         showInLegend: true
                     }
                 },
                 series: [{
                     type: 'pie',
                     name: 'FRs',
                     data: [
                         ['Open',result.frCount]
                         
                     ],
                     params: [7],
                     colors: ['#7cb5ec'],
                     cursor: 'pointer',
                     point: {
                         events: {
                             click: function () {
                            	 modalDetails(this.series.userOptions.params[this.x],result.frDetails);
                             }
                         }
                     }
                 }]
             });
             $('#incident-chart').highcharts({
                 exporting: false,
                 chart: {
                     plotBackgroundColor: null,
                     plotBorderWidth: null,
                     plotShadow: false
                 },
                 title: {
                     text: 'Current open incidents'
                 },
                 tooltip: {
                     pointFormat: '{series.name}: <b>{point.y}</b>'
                 },
                 plotOptions: {
                     pie: {
                         point: {
                             events: {
                                 mouseOver: function (event) {
                                     this.slice(true);
                                 },
                                 mouseOut: function (event) {
                                     this.slice(false);
                                 }
                             }
                         },
                         allowPointSelect: true,
                         cursor: 'pointer',
                         dataLabels: {
                             enabled: false
                         },
                         showInLegend: true
                     }
                 },
                 series: [{
                     type: 'pie',
                     name: 'Incidents open',
                     data: [ 
                         ["Major",result.majorIncident],
                         ["Minor",result.minorIncident],
                         ["Critical",result.criticalIncident],
                     ],
                     params: [4, 5, 6],
                     colors: ['#e4d354', '#90ed7d', '#f7a35c'],
                     cursor: 'pointer',
                     point: {
                         events: {
                             click: function () {
                            	 showModalData(this.series.userOptions.params[this.x],result);
                             }
                         }
                     }
                 }]
             });
         });
		  
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
	  
	  function fillTable(result){
		  var data = result.topIssueList;
		  var r = new Array(), j = -1;
		  if(data.length>0){
		  for (var key=0, size=data.length; key<size; key++){
		      r[++j] ='<tr><td class="f-500">';
		      r[++j] = data[key].im;
		      r[++j] = '</td><td class="f-500">';
		      r[++j] = data[key].title;
		      r[++j] = '</td><td class="f-500">';
		      r[++j] = data[key].priority;
		      r[++j] = '</td><td class="f-500">';
		      r[++j] = data[key].assigneeName;
		      r[++j] = '</td></tr>';
		      
		  }
		  $('#top-issues').html(r.join('')); 
		  }else{
			  var r = new Array(), j = -1;
			 
			  for (var key=0, size=1; key<size; key++){
			      r[++j] ='<tr><td class="f-500 c-green" colspan="4" align="center" style="font-size:20px">';
			      r[++j] = "No Major/Critcal Issues.";
			      r[++j] = '</td></tr>';
			      
			  } $('#top-issues').html(r.join(''));
		  }
	  }
});