<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<c:set var="rootPath" value="${pageContext.request.contextPath}"/>
	<script type="text/javascript">
		var rootPath = ${pageContext.request.contextPath}
	</script>
	
	<section class="content">
		<tiles:insertAttribute name="header" />
        <tiles:insertAttribute name="sidebar" />	
		<tiles:insertAttribute name="body" /><!-- body -->
		<tiles:insertAttribute name="footer" />
		<tiles:insertAttribute name="common" />
	</section>
	
</body>
</html>
