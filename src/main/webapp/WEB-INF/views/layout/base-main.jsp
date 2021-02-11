<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Covid+</title>
</head>
<body>
	<section class="content">
		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="body" /><!-- body -->
		<tiles:insertAttribute name="footer" />
	</section>
</body>
</html>