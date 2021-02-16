<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<body>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" crossorigin="anonymous"></script>
	<script type="text/javascript">
	$(function(){
		alert('${message}');
		location.href='${rootPath}${redirectPath}';
	});
	</script>
</body>
</html>