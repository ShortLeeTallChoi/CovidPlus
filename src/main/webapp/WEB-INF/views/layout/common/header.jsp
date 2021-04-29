<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<link href="${rootPath}/css/styles.css" rel="stylesheet" />
<link href="${rootPath}/css/common.css" rel="stylesheet" />
<link href="${rootPath}/css/dataTables.bootstrap4.min.css" rel="stylesheet" />
<script src="${rootPath}/js/jquery-3.5.1.min.js"></script>
</head>
<body class="sb-nav-fixed">
	<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
		<a class="navbar-brand" href="${rootPath}">Covid Plus</a>
		<button class="btn btn-link btn-sm order-1 order-lg-0"
			id="sidebarToggle" href="#">
			<i class="fas fa-bars"></i>
		</button>
		<!-- Navbar Search-->
		<form
			class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0">
			<!-- <div class="input-group">
                    <input class="form-control" type="text" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2" />
                    <div class="input-group-append">
                        <button class="btn btn-primary" type="button"><i class="fas fa-search"></i></button>
                    </div>
                </div> -->
		</form>
		<!-- Navbar-->
		<ul class="navbar-nav ml-auto ml-md-0">
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" id="userDropdown" href="#"
				role="button" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
				<div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
					<sec:authorize access="isAnonymous()">
						<div class="input-group" style="padding: 10px">
                            <form id="loginForm" action="${rootPath }/login/loginProcess" method="post" >
								<div style="width: 100%; text-align: center;">LOGIN</div>
	                    		<input class="form-control" name="member_id" type="text" placeholder="ID" style="width: 100%; margin-bottom: 5px"/>
	                    		<input class="form-control" name="member_pass" type="password" placeholder="PASSWORD" style="width: 100%; margin-bottom: 5px"  />
	                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	                    		<button class="form-control btn-primary" type="submit">로그인</button>
	                    	</form>
                    	</div>
					</sec:authorize>
					<sec:authorize access="isAuthenticated()">
						<div class="dropdown-item"><sec:authentication property="principal.member_id"/></div>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="${rootPath }/user/logout">Logout</a>
					</sec:authorize>
				</div>
			</li>
		</ul>
	</nav>
	<script type="text/javascript">
	var $form = $('#loginForm').serialize();
	function login(){
		$.ajax({
	        type: 'POST',
	        url: '${rootPath }/login/loginProcess',
	        data:$form,
			success: function(data){
				console.log(data);
			}	         
	    });
	}
	</script>