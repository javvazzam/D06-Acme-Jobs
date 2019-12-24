<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<link rel="stylesheet" href="libraries/acme/css/chart.css"/>

<script type="text/javascript" src="libraries/chart.js/2.7.2/js/chart.min.js"></script>

<acme:form readonly="true">
	<acme:form-integer code="administrator.dashboard.form.label.totalNumberOfAnnouncements" path="totalNumberOfAnnouncements"/>
	<acme:form-integer code="administrator.dashboard.form.label.totalNumberOfCompanyRecords" path="totalNumberOfCompanyRecords"/>
	<acme:form-integer code="administrator.dashboard.form.label.totalNumberOfInvestorRecords" path="totalNumberOfInvestorRecords"/>
	
	
	<!-- Reward -->
	<acme:form-panel code="administrator.dashboard.form.label.rewardInformation">
		<acme:form-double code="administrator.dashboard.form.label.minimumReward" path="minimumReward"/>
		<acme:form-double code="administrator.dashboard.form.label.maximumReward" path="maximumReward"/>
		<acme:form-double code="administrator.dashboard.form.label.averageReward" path="averageReward"/>
		<acme:form-double code="administrator.dashboard.form.label.standardDesviationReward" path="standardDesviationReward"/>
	</acme:form-panel>
	
	<!-- Offer -->
	<acme:form-panel code="administrator.dashboard.form.label.offerInformation">
		<acme:form-double code="administrator.dashboard.form.label.minimumOffer" path="minimumOffer"/>
		<acme:form-double code="administrator.dashboard.form.label.maximumOffer" path="maximumOffer"/>
		<acme:form-double code="administrator.dashboard.form.label.averageOffer" path="averageOffer"/>
		<acme:form-double code="administrator.dashboard.form.label.standardDesviationOffer" path="standardDesviationOffer"/>
	</acme:form-panel>
	
	<div class="graficas">
	     <div class="chart">
	       <acme:form-label code="administrator.dashboard.form.label.chartTitle" path="chartTitle"/>
	       <canvas id="chart1" ></canvas>
	     </div>
	</div>
	
		<!-- Listing indicators -->
	<acme:form-panel code="administrator.dashboard.form.label.listdashboard">
		<acme:form-double code="administrator.dashboard.form.label.averageJobsPerEmployer" path="averageJobsPerEmployer"/>
		<acme:form-double code="administrator.dashboard.form.label.averageApplicationsPerEmployer" path="averageApplicationsPerEmployer"/>
		<acme:form-double code="administrator.dashboard.form.label.averageApplicationsPerWorker" path="averageApplicationsPerWorker"/>
	</acme:form-panel>
	
	<!-- Listing indicators -->
	<h2>
		<acme:message code="administrator.dashboard.form.label.chart1"/>
	</h2>	
	
	<div>
		<canvas id="canvas1"></canvas>
	</div>
	
	<script type="text/javascript">
		$(document).ready(function() {
			var data = {
				labels : ["PUBLISHED","DRAFT"],
				datasets : [
					{
						label: 'Jobs',
						data : [
							<jstl:out value="${ratioPublishedJobs}"/>,
							<jstl:out value="${ratioDraftJobs}"/>,
						],
						backgroundColor : 'LightSkyBlue'
						
					},
				]
			};
			var options = {
				scales : {
					yAxes : [
						{
							ticks : {
								suggestedMin : 0.0,
								suggestedMax : 1.0
							}
						}
					]
				}
			};
			var canvas,context;
			canvas = document.getElementById("canvas1");
			context = canvas.getContext("2d");
			new Chart(context, {
				type : "bar",
				data : data,
				options : options
			});
			
		});
	</script>
	
	<!-- Chart Application Ratios -->
	<h2>
		<acme:message code="administrator.dashboard.form.label.chart2"/>
	</h2>	
	
	<div>
		<canvas id="canvas2"></canvas>
	</div>
	
	<script type="text/javascript">
		$(document).ready(function() {
			var data = {
				labels : ["PENDING","ACCEPTED","REJECTED"],
				datasets : [
					{
						label: 'Applications',
						data : [
							<jstl:out value="${ratioPendingApplications}"/>,
							<jstl:out value="${ratioAcceptedApplications}"/>,
							<jstl:out value="${ratioRejectedApplications}"/>,
						],
						backgroundColor : 'LightSkyBlue'
						
					},
				]
			};
			var options = {
				scales : {
					yAxes : [
						{
							ticks : {
								suggestedMin : 0.0,
								suggestedMax : 1.0
							}
						}
					]
				}
			};
			var canvas,context;
			canvas = document.getElementById("canvas2");
			context = canvas.getContext("2d");
			new Chart(context, {
				type : "bar",
				data : data,
				options : options
			});
			
		});
	</script>
	
	<!-- Chart Application Time Series -->
	<head>
	    <title>Line Chart</title>
	    <script src="http://cdnjs.cloudflare.com/ajax/libs/moment.js/2.13.0/moment.min.js"></script>
	    <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.bundle.js"></script>
	    <style>
	        canvas {
	            -moz-user-select: none;
	            -webkit-user-select: none;
	            -ms-user-select: none;
	        }
	    </style>
	</head>
	
	<h2>
		<acme:message code="administrator.dashboard.form.label.chart3"/>
	</h2>	
	
	
	<!-- Chart 3 -->
	<div style="width:75%;">
    <canvas id="canvas3"></canvas>
	</div>
	
	<script>

		<!-- Pending -->
		var dateListTimeSeriesPendingApplication = new Array();
		<jstl:forEach items="${dateListTimeSeriesPendingApplication}" var="item">
			dateListTimeSeriesPendingApplication.push("${item}");
		</jstl:forEach>
		
		var countListTimeSeriesPendingApplication = new Array();
		<jstl:forEach items="${countListTimeSeriesPendingApplication}" var="item">
			countListTimeSeriesPendingApplication.push("${item}");
		</jstl:forEach>		
		
		class Ejes{
	        constructor(xAxe,yAxe){
	            this.x=xAxe;
	            this.y=yAxe;
	        }
	    }
		
		var dataPending = new Array();
		
	    for (var i = 0; i < dateListTimeSeriesPendingApplication.length; i++) {
	        myAxe = new Ejes(dateListTimeSeriesPendingApplication[i],countListTimeSeriesPendingApplication[i]);
	        dataPending.push(myAxe)
	    }
	    
	    <!-- Accepted -->
	    var dateListTimeSeriesAcceptedApplication = new Array();
		<jstl:forEach items="${dateListTimeSeriesAcceptedApplication}" var="item">
			dateListTimeSeriesAcceptedApplication.push("${item}");
		</jstl:forEach>
		
		var countListTimeSeriesAcceptedApplication = new Array();
		<jstl:forEach items="${countListTimeSeriesAcceptedApplication}" var="item">
			countListTimeSeriesAcceptedApplication.push("${item}");
		</jstl:forEach>		
		
		var dataAccepted = new Array();
		
	    for (var i = 0; i < dateListTimeSeriesAcceptedApplication.length; i++) {
	        myAxe = new Ejes(dateListTimeSeriesAcceptedApplication[i],countListTimeSeriesAcceptedApplication[i]);
	        dataAccepted.push(myAxe)
	    }
	    
	    <!-- Rejected -->
	    var dateListTimeSeriesRejectedApplication = new Array();
		<jstl:forEach items="${dateListTimeSeriesRejectedApplication}" var="item">
			dateListTimeSeriesRejectedApplication.push("${item}");
		</jstl:forEach>
		
		var countListTimeSeriesRejectedApplication = new Array();
		<jstl:forEach items="${countListTimeSeriesRejectedApplication}" var="item">
			countListTimeSeriesRejectedApplication.push("${item}");
		</jstl:forEach>		
		
		var dataRejected = new Array();
		
	    for (var i = 0; i < dateListTimeSeriesRejectedApplication.length; i++) {
	        myAxe = new Ejes(dateListTimeSeriesRejectedApplication[i],countListTimeSeriesRejectedApplication[i]);
	        dataRejected.push(myAxe)
	    }
		
	    
	    <!-- CHART -->
	    var timeFormat = 'YYYY-MM-DD';
	
	    var config = {
	        type:    'line',
	        data:    {
	            datasets: [
	                {
	                    label: "Pending",
	                    data: dataPending,
	                    fill: false,
	                    borderColor: 'red'
	                },
	                {
	                    label: "Accepted",
	                    data: dataAccepted,
	                    fill:  false,
	                    borderColor: 'blue'
	                },
	                {
	                    label: "Rejected",
	                    data:  dataRejected,
	                    fill:  false,
	                    borderColor: 'orange'
	                }
	            ]
	        },
	        options: {
	            responsive: true,
	            scales:     {
	                xAxes: [{
	
						type: 'time',
						time:{
						    unit: 'day',
	                        format: timeFormat
						},
						distribution: 'series'
							
	                }],
	                yAxes: [{
	                    ticks : {
									suggestedMin : 0,
									suggestedMax : 5
								}
	                }]
	            }
	        }
	    };
	
	    window.onload = function () {
	        var ctx       = document.getElementById("canvas3").getContext("2d");
	        window.myLine = new Chart(ctx, config);
	    };

	</script>
	
  	<acme:form-return code="administrator.dashboard.form.button.return"/>
</acme:form>


	<script src="libraries/acme/js/chart.js" charset="utf-8"></script>
	<script type="text/javascript">
	
		var companyEtiquetes = new Array();
		<jstl:forEach items="${companySectors}" var="item">
			companyEtiquetes.push("${item}");
		</jstl:forEach>
	
		var companyData = new Array();	
		<jstl:forEach items="${numberCompanys}" var="item">
			companyData.push(${item});
		</jstl:forEach>
	
		var inversorEtiquetes = new Array();
		<jstl:forEach items="${investorSectors}" var="item">
			inversorEtiquetes.push("${item}");
		</jstl:forEach>
	
		var inversorData = new Array();	
		<jstl:forEach items="${numberInvestors}" var="item">
			inversorData.push(${item});
		</jstl:forEach>
		
		
		var ctx = document.getElementById('chart1');	

		var data = [companyData, inversorData];
		var ets = [companyEtiquetes, inversorEtiquetes];
		var labels = ["Empresas","Inversores"];
		var title = "Empresas/Inversores por sector";
		
		//console.log(data);
		//console.log(ets);
		var chart = construyeGrafica(ctx, "bar", data,ets,labels, "",[false,false]);

	</script>

