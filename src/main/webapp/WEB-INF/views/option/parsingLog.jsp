<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main</title>
<style type="text/css">
	.button-style{
		font-weight: 400;
	    line-height: 0.5;
	    color: #495057;
	    background-color: #fff;
	    background-clip: padding-box;
	    border: 1px solid #ced4da;
	    border-radius: 0.25rem;
	    height: 23px;
	}
</style>
</head>
<div id="layoutSidenav_content">
	<main>
	<div class="container-fluid">
		<h1 class="mt-4">수집 로그 조회</h1>
		<ol class="breadcrumb mb-4">
			<li class="breadcrumb-item active">에이전트 로그 수집 조회 페이지</li>
		</ol>
		<div class="card mb-4">
			<div class="card-header">
				<i class="fas fa-table mr-1"></i> 로그 조회
			</div>
			<div class="card-body">
				<div class="table-responsive">
					<div id="dataTable_wrapper"
						class="dataTables_wrapper dt-bootstrap4">
						<div class="row">
							<div class="col-sm-12 col-md-6">
								<div class="dataTables_length" id="dataTable_length">
									<label>
										수집 ID
										<select id="parsing_key_type" onchange="selectParsingKey()" class="custom-select custom-select-sm form-control form-control-sm">
											<option value="all">all</option>
											<c:forEach items="${parseVOList}" var="parseVO">
												<option value="${parseVO.parsing_key }" 
												<c:if test="${parseVO.parsing_key eq searchLogVO.parsing_key}">
													selected="selected"
												</c:if>
												>${parseVO.parsing_key }</option>
											</c:forEach>
										</select> 
									</label>
								</div>
							</div>
							<div class="col-sm-12 col-md-6">
								<div id="dataTable_filter" class="dataTables_filter">
									<!-- <label>Search:<input type="search"
										class="form-control form-control-sm" placeholder=""
										aria-controls="dataTable"></label> -->
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">
								<table class="table table-bordered dataTable" id="dataTable"
									width="100%" cellspacing="0" role="grid"
									aria-describedby="dataTable_info" style="width: 100%;font-size: 14px">
									<thead>
										<tr role="row">
											<th style="width: 10%;">No</th>
											<th style="width: 10%;">수집 ID</th>
											<th style="width: 40%;">URL</th>
											<th style="width: 10%;">로그수</th>
											<th style="width: 30%;">수집일</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${LogList }" var="log">
											<tr role="row" class="odd">
												<td class="sorting_1">${log.log_no }</td>
												<td>${log.parsing_key }</td>
												<td>${log.service_url }</td>
												<td>${log.log_count }</td>
												<fmt:parseDate value="${log.parsing_date}" var="noticePostDate" pattern="yyyy-MM-dd HH:mm:ss.SSS"/>
												<td>
													<fmt:formatDate value="${noticePostDate}" pattern="yy-MM-dd HH:mm:ss"/>
													<input class="button-style" type="button" value="상세" onclick="logInfo(${log.log_no },this)" style="margin-left:13px;">
												</td>
											</tr>
											<tr id="log${log.log_no }" role="row" class="odd" style="display:none" value="false">
												<td colspan="5" style="padding: 2px">
													<div>
														<table style="width:100%">
															<tr style="border: 1px solid #dee2e6;">
																<td>comment</td><td>${log.comment }</td>
																<td>parameter</td><td>${log.parameter_info }</td>
															</tr>
														</table>
													</div>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12 col-md-5">
								<div class="dataTables_info" id="dataTable_info" role="status"
									aria-live="polite">Showing ${searchLogVO.listCnt } logs</div>
							</div>
							<div class="col-sm-12 col-md-7">
								<div class="dataTables_paginate paging_simple_numbers"
									id="dataTable_paginate">
									<ul class="pagination">
										<c:if test="${searchLogVO.prev}">
											<li class="page-item"><a class="page-link" href="#"
												onClick="fn_prev('${searchLogVO.page}', '${searchLogVO.range}', '${searchLogVO.rangeSize}')">Previous</a></li>
										</c:if>

										<c:forEach begin="${searchLogVO.startPage}"
											end="${searchLogVO.endPage}" var="idx">
											<li
												class="page-item <c:out value="${searchLogVO.page == idx ? 'active' : ''}"/> "><a
												class="page-link" href="#"
												onClick="fn_pagination('${idx}', '${searchLogVO.range}', '${searchLogVO.rangeSize}')">
													${idx} </a></li>
										</c:forEach>

										<c:if test="${pagination.next}">
											<li class="page-item"><a class="page-link" href="#" onClick="fn_next('${searchLogVO.range}', '${searchLogVO.range}', '${searchLogVO.rangeSize}')" >Next</a></li>
										</c:if>

									</ul>
								</div>
							</div>
						</div>
						<form action="${rootPath }/option/parsingLog" method="post" id="parsingLogForm">
							<input type="hidden" name="page">
							<input type="hidden" name="range">
							<input type="hidden" name="rangeSize">
							<input type="hidden" name="parsing_key" value="${searchLogVO.parsing_key }">
							<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
						</form>
					</div>
				</div>
			</div>
		</div>
	</main>
	<script>

		function fn_prev(page, range, rangeSize) {
			var page = ((range - 2) * rangeSize) + 1;
			var range = range - 1;
			/* var url = "${pageContext.request.contextPath}/option/parsingLog";
			url = url + "?page=" + page;
			url = url + "&range=" + range;
			location.href = url; */
			$('#parsingLogForm input[name=page]').val(page);
			$('#parsingLogForm input[name=range]').val(range);
			$('#parsingLogForm input[name=rangeSize]').val(rangeSize);
			$('#parsingLogForm').submit();
		}
		//페이지 번호 클릭
		function fn_pagination(page, range, rangeSize, searchType, keyword) {
			/* var url = "${pageContext.request.contextPath}/option/parsingLog";
			url = url + "?page=" + page;
			url = url + "&range=" + range;
			location.href = url; */
			$('#parsingLogForm input[name=page]').val(page);
			$('#parsingLogForm input[name=range]').val(range);
			$('#parsingLogForm input[name=rangeSize]').val(rangeSize);
			$('#parsingLogForm').submit();
		}
		//다음 버튼 이벤트
		function fn_next(page, range, rangeSize) {
			var page = parseInt((range * rangeSize)) + 1;
			var range = parseInt(range) + 1;
			/* var url = "${pageContext.request.contextPath}/option/parsingLog";
			url = url + "?page=" + page;
			url = url + "&range=" + range;
			location.href = url; */
			$('#parsingLogForm input[name=page]').val(page);
			$('#parsingLogForm input[name=range]').val(range);
			$('#parsingLogForm input[name=rangeSize]').val(rangeSize);
			$('#parsingLogForm').submit();
		}
		function selectParsingKey(){
			$('#parsingLogForm input[name=parsing_key]').val($('#parsing_key_type').val());
			$('#parsingLogForm').submit();
		}
		function logInfo(logNo,tag){
			$logtag = $('#log'+logNo);
			if($logtag.attr('value')=='false'){
				$(tag).val('닫기');
				$logtag.css('display','');
				$logtag.attr('value','true');
			}else{
				$(tag).val('상세');
				$logtag.css('display','none');
				$logtag.attr('value','false');
			}
		}
	</script>
</html>