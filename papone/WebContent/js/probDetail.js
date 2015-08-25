$(document)
		.ready(
				function() {

					function findLinks() {
						var links = $('.find-tickets');
						links.html(replaceURLWithHTMLLinks(links.html()));
					}

					function replaceURLWithHTMLLinks(text) {

						var exp = /((IM|FR|PM|C)\d\d\d\d\d\d\d\d)/ig;
						if (text != undefined)
							return text
									.replace(exp,
											"<a class='link' style='cursor:pointer;' onclick='showModal(\"$1\");'>$1</a>");
					}
					;

					var ci = localStorage.getItem('ci');

					$
							.ajax({

								type : 'POST',
								url : 'http://bdc-qpcsa044.na.pg.com:8084/papone/PrDetailService',
								contentType : "application/x-www-form-urlencoded; charset=UTF-8;",
								dataType : "json",
								data : {
									ci : ci

								},
								success : function(response) {

									drawGraphPrDetails(response);

								}
							});
					$
							.ajax({

								type : 'POST',
								url : 'http://bdc-qpcsa044.na.pg.com:8084/papone/PrService',
								contentType : "application/x-www-form-urlencoded; charset=UTF-8;",
								dataType : "json",
								data : {
									ci : ci

								},
								success : function(response) {

									drawGraphPr(response);

								}
							});
					$
							.ajax({

								type : 'POST',
								url : 'http://bdc-qpcsa044.na.pg.com:8084/papone/ApplicationService',
								contentType : "application/x-www-form-urlencoded; charset=UTF-8;",
								dataType : "json",
								data : {
									ci : ci

								},
								success : function(response) {

									drawGraphSeverity(response);

								}
							});

					function drawGraphSeverity(result) {
						var pr_dat=[];
				         if(result.criticalProblem+result.majorProblem+result.minorProblem != 0) {
				        	 pr_dat = [["Critical",result.criticalProblem],["Major",result.majorProblem],["Minor",result.minorProblem]];
				         }
						$('#pr-priority').highcharts(
										{
											chart : {
												type : 'pie',
											},
											exporting : false,
											title : {
												text : ''
											},
											tooltip : {
												pointFormat : '{series.name}: <b>{point.y}</b>'
											},
											plotOptions : {
												pie : {
													point : {
														events : {
															mouseOver : function(
																	event) {
																this
																		.slice(true);
															},
															mouseOut : function(
																	event) {
																this
																		.slice(false);
															}
														}
													},
													showInLegend : true,
													allowPointSelect : true,
													depth : 35,
													dataLabels : {
														enabled : false,
														format : '{point.name}'
													},
													innerSize : 100,
													depth : 45,
												},

											},
											series : [ {
												name : 'Priority',
												data : pr_dat,
												params : [ "critical", "major",
														"minor" ],
												colors : [ '#F44336',
														'#FF9800', '#4CAF50' ],
												cursor : 'pointer',
												point : {
													events : {
														click : function() {
															modalDetails(
																	this.series.userOptions.params[this.x],
																	result);
														}
													}
												}
											} ]
										});

					}

					function drawGraphPrDetails(response) {
						$(function() {
							// Create the chart
							$('#pr-ageing')
									.highcharts(
											{
												chart : {
													type : 'column'
												},
												title : {
													text : ''
												},
												xAxis : {
													type : 'category'
												},
												yAxis : {
													title : {
														text : 'No. of problem records'
													}

												},
												legend : {
													enabled : false
												},
												plotOptions : {
													series : {
														borderWidth : 0,
														dataLabels : {
															enabled : true,
															format : '{point.y}'
														}
													}
												},

												tooltip : {
													headerFormat : '<span style="font-size:11px">{series.name}</span><br>',
													pointFormat : '{point.name}: <b>{point.y}</b> PRs<br/>'
												},

												series : [ {
													name : "Open for",
													colorByPoint : true,
													data : [
															{
																name : "0-30 days",
																y : response.ageingProblemCount[0],
															},
															{
																name : "30-90 days",
																y : response.ageingProblemCount[1],
															},
															{
																name : "90-180 days",
																y : response.ageingProblemCount[2],
															},
															{
																name : "Over 180 days",
																y : response.ageingProblemCount[3],
															} ],
													colors : [ '#22CC00',
															'#ffd800',
															'#0011EE',
															'#f20c0c' ],
													params : [ 'age30',
															'age90', 'age180',
															'age360' ],
													point : {
														events : {
															click : function() {
																showModalData(
																		this.series.userOptions.params[this.x],
																		response);
															}
														}
													}
												} ],
											});
						});

						var chartData = $.map(response.graphData, function(obj,i) {
							return [ [ obj.phase, obj.phaseCount ] ];
						});
						JSON.stringify(chartData, null, " ");
						console.log(chartData);
						var phase_dat=[];
						if(chartData.length!=0) {
							phase_dat=chartData;
						}
						var phaseArray = $.map(response.graphData, function(obj, i) {
							return [[obj.phase]];
						});						
						JSON.stringify(phaseArray, null, " ");
						$(function() {
							$('#pr-phase').highcharts(
											{
												chart : {
													type : 'pie',
													options3d : {
														enabled : true,
														alpha : 45
													}
												},
												title : {
													text : ''
												},
												tooltip : {
													pointFormat : '{series.name}: <b>{point.y}</b>'
												},
												plotOptions : {
													pie : {
														point : {
															events : {
																mouseOver : function(
																		event) {
																	this
																			.slice(true);
																},
																mouseOut : function(
																		event) {
																	this
																			.slice(false);
																}
															}
														},
														showInLegend : true,
														allowPointSelect : true,
														depth : 35,
														dataLabels : {
															enabled : false,
															format : '{point.name}'
														},
														innerSize : 100,
														depth : 45,
													},

												},
												series : [ {
													name : 'Status',
													data : phase_dat,
													colors : [ '#9C27B0',
															'#FFC107',
															'#00BCD4',
															'#4CAF50',
															'#607D8B',
															'#FF5722',
															'#CDDC39',
															'#E91E63',
															'#009688',
															'#8BC34A',
															'#795548',
															'#F44336',
															'#03A9F4',
															'#FFEB3B' ],
													params : phaseArray,
													cursor : 'pointer',
													point : {
														events : {
															click : function() {
																showModalData(
																		String(this.series.userOptions.params[this.x]),
																		response);
															}
														}
													}
												} ]
											});
						});
					}

					function drawGraphPr(result) {
						$(function() {
							$('#pr-graph-container')
									.highcharts(
											{
												title : {
													text : 'Month-wise problem snap shot',
													x : -20
												// center
												},
												subtitle : {
													text : 'Click-and-drag chart area to zoom in, click \'Reset zoom\' button to reset (not available on touch devices)'
												},
												chart : {
													zoomType : 'x'
												},
												xAxis : {
													categories : result.month
															.reverse()
												},
												yAxis : {
													title : {
														text : 'Number of PRs'
													},
													plotLines : [ {
														value : 0,
														width : 1,
														color : '#808080'
													} ]
												},
												tooltip : {
													valueSuffix : ''
												},
												legend : {
													layout : 'vertical',
													align : 'right',
													verticalAlign : 'middle',
													borderWidth : 0
												},
												series : [ {
													name : 'PR Reported',
													color : '#FF9800',
													data : result.probCount
															.reverse()
												} ]
											});
						});

						// Reactive vs Proactive graph
						var proreac_dat=[];
						if(result.proactiveCount+result.reactiveCount!=0) {
							proreac_dat=[['Proactive',result.proactiveCount ],['Reactive',result.reactiveCount]];
						}
						$(function() {
							$('#pro-reac').highcharts(
											{
												chart : {
													type : 'pie',
													options3d : {
														enabled : true,
														alpha : 45,
														beta : 0
													}
												},
												title : {
													text : ''
												},
												tooltip : {
													pointFormat : '{series.name}: <b>{point.y}</b>'
												},
												plotOptions : {
													pie : {
														point : {
															events : {
																mouseOver : function(
																		event) {
																	this
																			.slice(true);
																},
																mouseOut : function(
																		event) {
																	this
																			.slice(false);
																}
															}
														},
														showInLegend : true,
														allowPointSelect : true,
														depth : 35,
														dataLabels : {
															enabled : false,
															format : '{point.name}'
														}
													}
												},
												series : [ {
													type : 'pie',
													name : 'Problems',
													data : proreac_dat,
													params : [ 'proactive',
															'reactive' ],
													colors : [ '#7CB5EC',
															'#FF9800' ],
													cursor : 'pointer',
													point : {
														events : {
															click : function() {
																showModalData(
																		this.series.userOptions.params[this.x],
																		result);
															}
														}
													}
												} ]
											});
						});

						$(function() {
							var rca_dat=[];
							if(result.rcaKnown+result.rcaUnknown!=0) {
								rca_dat=[['RCA Known',result.rcaKnown],['RCA Unknown',result.rcaUnknown]];
							}
							$('#rca')
									.highcharts(
											{
												chart : {
													type : 'pie',
													options3d : {
														enabled : true,
														alpha : 45,
														beta : 0
													}
												},
												title : {
													text : ''
												},
												tooltip : {
													pointFormat : '{series.name}: <b>{point.y}</b>'
												},
												plotOptions : {
													pie : {
														point : {
															events : {
																mouseOver : function(
																		event) {
																	this
																			.slice(true);
																},
																mouseOut : function(
																		event) {
																	this
																			.slice(false);
																}
															}
														},
														showInLegend : true,
														allowPointSelect : true,
														depth : 35,
														dataLabels : {
															enabled : false,
															format : '{point.name}'
														}
													}
												},
												series : [ {
													type : 'pie',
													name : 'Problems',
													data : rca_dat,
													params : [ 'RCA Known',
															'RCA Unknown' ],
													colors : [ '#8BC34A',
															'#FF5722' ],
													cursor : 'pointer',
													point : {
														events : {
															click : function() {
																showModalData(
																		this.series.userOptions.params[this.x],
																		result);
															}
														}
													}
												} ]
											});
						});

					}

					function showModalData(arr, result) {

						var modal = null;
						switch (arr) {

						case "age30":
							modal = result.detail30;
							break;
						case "age90":
							modal = result.detail90;
							break;
						case "age180":
							modal = result.detail180;
							break;
						case "age360":
							modal = result.detailOver360;
							break;
						case "proactive":
							modal = result.proactiveList;
							break;
						case "reactive":
							modal = result.reactiveList;
							break;
						case "RCA Known":
							modal = result.rcaKnownList;
							break;
						case "RCA Unknown":
							modal = result.rcaUnknownList;
							break;
						case "Identify and Classify Problems":
							modal = result.identifyProblem;
							break;
						case "Investigate and Diagnose Problems":
							modal = result.investigateProblem;
							break;
						case "N.Root Cause Approval":
							modal = result.nRCApproval;
							break;
						case "N.Known Error Investigation":
							modal = result.nKnownErrorInvestigation;
							break;
						case "N.Known Error Solution Approval":
							modal = result.nKnowErrorSolApproval;
							break;

						}

						var m = new Array(), j = -1;
						for ( var key = 0, size = modal.length; key < size; key++) {
							m[++j] = '<tr><td class="f-500 c-black">';
							m[++j] = modal[key].id;
							m[++j] = '</td><td class="f-500 c-black">';
							m[++j] = modal[key].title;
							m[++j] = '</td><td class="f-500 c-black">';
							m[++j] = modal[key].ci;
							m[++j] = '</td><td class="f-500 c-black">';
							m[++j] = modal[key].reportedDate;
							m[++j] = '</td><td class="f-500 c-black">';
							m[++j] = modal[key].status;
							m[++j] = '</td><td class="f-500 c-black">';
							m[++j] = modal[key].phase;
							m[++j] = '</td><td class="f-500 c-black">';
							m[++j] = modal[key].smGroup;
							m[++j] = '</td></tr>';

						}
						$('#pr-modal-detail').html(m.join(''));
						$("#pr-modal").modal('show');

					}

					function modalDetails(arr, result) {

						switch (arr) {
						case "critical":
							modal = result.problemDetailCritical;
							break;
						case "major":
							modal = result.problemDetailMajor;
							break;
						case "minor":
							modal = result.problemDetailMinor;
							break;

						}
						var m = new Array(), j = -1;
						for ( var key = 0, size = modal.length; key < size; key++) {
							m[++j] = '<tr><td class="f-500 c-black">';
							m[++j] = modal[key].id;
							m[++j] = '</td><td class="f-500 c-black">';
							m[++j] = modal[key].title;
							m[++j] = '</td><td class="f-500 c-black">';
							m[++j] = modal[key].appName;
							m[++j] = '</td><td class="f-500 c-black">';
							m[++j] = modal[key].specialist;
							m[++j] = '</td><td class="f-500 c-black">';
							m[++j] = modal[key].status;
							m[++j] = '</td><td class="f-500 c-black">';
							m[++j] = modal[key].reportedDate;
							m[++j] = '</td></tr>';

						}
						$('#modal-pr-severity').html(m.join(''));
						$("#pr-data-modal").modal('show');

					}

				});