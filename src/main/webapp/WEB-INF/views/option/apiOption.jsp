<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<style>
	td{
		width: 25%;
	}
	.selectList:hover{
		background: darkgray;
		color:white;
	}
	.selectList-noaction:hover{
		background: darkgray;
		color:white;
	}
	.selectOne{
		background: black;
		color:white;
	}
	.inrow{
		display: flex;
   		flex-wrap: wrap;
	}
</style>
<div id="layoutSidenav_content">
	<main>
		 <div class="container-fluid">
		     <h1 class="mt-4">Agent Option</h1>
		     <ol class="breadcrumb mb-4">
		         <li class="breadcrumb-item active">수집 에이전트 설정 수정</li>
		     </ol>
		     <div class="card mb-4">
		         <div class="card-header">
		             <i class="fas fa-table mr-1"></i>
		       		Collect Agent 
		         </div>
		         <div class="card-body">
		             <div class="table-responsive">
		                 <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
		                     <thead>
		                         <tr>
		                             <th>수집 목록</th>
		                             <th colspan="4">
		                             	<div class="row">
		                             		<div class="col-md-10">
		                             			상세
		                             		</div>
		                             		<div class="col-md-2">
		                             			<button id="confirmBtn" class="form-control" style="height: 24px; padding: 0px;">추가하기</button>
		                             		</div>
		                             	</div>
		                             </th>
		                         </tr>
		                     </thead>
		                     <tbody>
		                         <tr>
		                             <td rowspan="5" style="padding: 5px;height: 100px">
		                             	<div style="width: 100%;height: 100%;border: 1px dashed lightgray;padding: 0px 4px;">
		                             		<c:forEach items="${parseVOList }" var="parseVO">
		                             			<div class="selectList">${parseVO.parsing_key }</div>
		                             		</c:forEach>
		                             		<div class='selectList-noaction' style="font-size: 17px" onclick="parseInfoReset()">
			                             		<i class="far fa-plus-square"></i>추가
			                             	</div>
		                             	</div>
		                             </td>
		                             <td>
		                             	수집ID<br>
		                             	<input class="form-control" type="text" name="parsing_id">
		                             </td>
		                             <td colspan="2">
		                             	<div class="col-md-12">
		                             		 수집 활성화<br>
			                             	<div style="line-height: 37px;">
			                             		<div class="row">
			                             			<div class="col-md-6"><span><input id="activity_true" type="radio" name="activity" value="true"> 활성화</span></div>
			                             			<div class="col-md-6"><span><input id="activity_false" type="radio" name="activity" value="false"> 비활성화</span></div>
			                             		</div>
			                             	</div>
		                             	</div>
		                             </td>
		                         </tr>
		                         <tr>
		                             <td colspan="3">
		                            	 URL<br>
		                             	<input class="form-control" type="text" name="service_url">
		                             </td>
		                         </tr>
		                         <tr>
		                             <td colspan="3">
		                            	 Key<br>
		                             	<input class="form-control" type="text" name="service_key">
		                             </td>
		                         </tr>
		                         <tr>
		                             <td>
		                             	 Parameter<br>
		                             	<div id="url_parameter" style="width: 100%;min-height: 38px;border: 1px dashed lightgray;padding: 0px 4px;">
		                             	</div>
		                             </td>
		                             <td colspan="3">
		                             	<div class="row">
			                             	<div class="col-md-5">
				                             	변수명<br>
												<input class="form-control" type="text" name="parameter">	
											</div>
			                             	<div class="col-md-5">               
				                             	값<br>
												<input class="form-control" type="text" name="parameter">	
											</div>
			                             	<div class="col-md-2" style="padding-top: 24px">      
			                             		<button class="form-control" style="padding-left: 9px"><i class="fas fa-plus"></i></button>         
											</div>
		                             </td>
		                         </tr>
		                         <tr>
		                             <td>
		                             	<div class="row">
			                             	<div class="col-md-6">
			                             		 날짜 설정<br>
				                             	<div style="line-height: 37px;">
					                             	<span><input type="radio" id="date_param_today_true" name="date_param_today" value="true"> 오늘</span>
					                             	<span style="margin-left: 2px"><input type="radio" id="date_param_today_false" name="date_param_today" value="false"> 지정날짜</span>
				                             	</div>
			                             	</div>
			                             	<div class="col-md-6">
			                             		누락데이터 메꾸기<br>
				                             	<div style="line-height: 37px;">
					                             	<span><input type="checkbox" name="last_to_today"> 확인</span>
				                             	</div>
			                             	</div>
		                             	</div>
		                             </td>
		                             <td style="width: 13%">
		                             	날짜 변수<br>
										<div id="DateParam" name="DateParam" style="width: 100%;min-height: 38px;border: 1px dashed lightgray;padding: 0px 4px;">
		                             	</div>	                             
		                             </td>
		                             <td colspan="2">
		                             	날짜 변수 추가<br>
										<select class="form-control" id="parameterList">
											<option>----</option>
										</select>
		                             </td>
		                         </tr>
		                         <tr>
		                             <td>
		                             	 테이블명<br>
		                             	<input name="table_name" class="form-control" type="text">
		                             </td>
		                             <td colspan="3"></td>
		                         </tr>
		                         <tr>
		                             <td colspan="4">
		                             	 수집 쿼리<br>
		                             	 <textarea name="insert_query" rows="4" cols="120" style="width: 100%; resize: none"></textarea>                       
		                             </td>
		                         </tr>
		                     </tbody>
		                 </table>
		             </div>
		         </div>
		     </div>
		 </div>
	</main>
	<script type="text/javascript">
		var callToken = '${_csrf.token}';
		var rootPath = '${rootPath}';
	</script>
	<script type="text/javascript" src="${rootPath}/js/option/api.js"></script>