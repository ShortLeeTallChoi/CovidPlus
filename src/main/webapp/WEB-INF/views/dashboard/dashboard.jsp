<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
<header>
	<title>CovidPlus 대시보드</title>
</header>
<style>
	.small_font{
		font-size:10px;
	}
	.small_red{
		color:red;
	}
	.small_blue{
		color:blue;
	}
	th{
		position: sticky;
	    top: 0px;
		padding:5px!important;
		background: lightgray;
		border: none!important;
	}
	td{padding:5px!important;}
	Table {
	    width: 500px;
	    border: 0px;
	    border-collapse: collapse;
	}
	#sidoBody > tr > td{
		text-align: center;
	}
</style>
<div id="layoutSidenav_content">
	<main>
	<div class="container-fluid">
		<h1 class="mt-4">Covid-19 Dashboard</h1>
		<ol class="breadcrumb mb-4">
			<div class="col-md-12" style="background-color: white; border-radius: 0.25rem; display: inline-block; padding-bottom: 10px; padding-top: 10px; text-align: center">
				<div class="row">
					<div class="col-md-2">
						<div class="x-large"><fmt:formatNumber value="${totalCase.decide_cnt }" pattern="#,###" /></div>
						<div>(<span class="distance_value">${totalCase_distance.decide_cnt }</span>)</div>
					</div>
					<div class="col-md-2">
						<div class="x-large"><fmt:formatNumber value="${totalCase.clear_cnt }" pattern="#,###" /></div>
						<div>(<span class="distance_value">${totalCase_distance.clear_cnt }</span>)</div>
					</div>
					<div class="col-md-2">
						<div class="x-large"><fmt:formatNumber value="${totalCase.death_cnt }" pattern="#,###" /></div>
						<div>(<span class="distance_value">${totalCase_distance.death_cnt }</span>)</div>
					</div>
					<div class="col-md-2">
						<div class="x-large"><fmt:formatNumber value="${totalCase.care_cnt }" pattern="#,###" /></div>
						<div>(<span class="distance_value">${totalCase_distance.care_cnt }</span>)</div>
					</div>
					<div class="col-md-2">
						<div class="x-large"><fmt:formatNumber value="${totalCase.exam_cnt }" pattern="#,###" /></div>
						<div>(<span class="distance_value">${totalCase_distance.exam_cnt }</span>)</div>
					</div>
					<div class="col-md-2">
						<div class="x-large"><fmt:formatNumber value="${totalCase.acc_exam_cnt }" pattern="#,###" /></div>
						<div>(<span class="distance_value">${totalCase_distance.acc_exam_cnt }</span>)</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-2">확진자</div>
					<div class="col-md-2">완치</div>
					<div class="col-md-2">사망</div>
					<div class="col-md-2">치료중</div>
					<div class="col-md-2">검사 수</div>
					<div class="col-md-2">누적 검사 수</div>
				</div>
			</div>
			<li class="breadcrumb-item active"></li>
		</ol>
			<div class="col-xl-12">
				<div class="card mb-4">
					<div class="card-header">
						<i class="fas fa-chart-bar mr-1"></i> 지역별 감염 통계
					</div>
					<div class="card-body">
						<div class="row">
							<div class="col-xl-6">
								<%@include file="/WEB-INF/views/dashboard/koreaMap.jsp" %>
							</div>
							<div class="col-xl-6">
								<div id="amBarChart" width="100%" style="height: 250px"></div>
								<div id="totalLineChart" width="100%" style="height: 250px"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		<div class="col-xl-12">
			<div class="row">
				<div class="col-xl-6">
					<div class="card mb-4">
						<div class="card-header">
							<i class="fas fa-chart-pie mr-1"></i> 연령별 감염 통계
						</div>
						<div class="card-body">
							<div id="agePieChart" width="100%" style="height: 250px"></div>
						</div>
					</div>
				</div>
				<div class="col-xl-6">
					<div class="card mb-4">
						<div class="card-header">
							<i class="fas fa-chart-pie mr-1"></i> 성별 감염 통계
						</div>
						<div class="card-body">
							<div id="genderPieChart" width="100%" style="height: 250px"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-xl-12">
			<div class="card mb-4">
				<div class="card-header">
					<i class="fas fa-table mr-1"></i> DataTable Example
				</div>
				<div class="card-body">
					<div class="table-responsive">
						<!-- <div id="container" class="container"></div> -->
						<div>
							<div class="table-responsive" style="overflow-x: hidden; height: 100%;">
								<table class="table table-bordered" id="dataTable" width="100%"
									cellspacing="0">
									<thead>
										<tr style="text-align: center">
											<th>지역</th>
											<th>확진자</th>
											<th>누적확진자</th>
										</tr>
									</thead>
									<tbody id="sidoBody">
										<tr>
											<td colspan="3">데이터 없음</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</main>

	
    <link href="${rootPath}/css/dashboard/dashboard.css" rel="stylesheet" />
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.bundle.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.css">
	<script type="text/javascript" src="${rootPath}/js/dashboard/dashboard-chart.js"></script>
	<script type="text/javascript" src="${rootPath}/js/dashboard/dashboard.js"></script>
	<script type="text/javascript" src="${rootPath}/js/dashboard/dashboard-map.js"></script>
	<script type="text/javascript" src="${rootPath}/js/amchart/maps.js"></script>
	<script type="text/javascript" src="${rootPath}/js/amchart/worldLow.js"></script>
	<script type="text/javascript" src="${rootPath}/js/amchart/countries2.js"></script>
	<script type="text/javascript">
	var debugData;
	var callToken = '${_csrf.token}';
	$(document).ready(function(){
		$.ajax({
	         type: 'POST',
	         url: '${rootPath}/dashboard/chartData.do',
	         data:{'${_csrf.parameterName}':'${_csrf.token}'},
			success: function(data){
				debugData = data;
				createSiDoChart(data);
				createSiDoTable(data);
				createAgeChart(data);
				createGenderChart(data);
				createMapChart(data);
				createTotalLineChart(data);
			}	         
	     });
	});
	</script>
