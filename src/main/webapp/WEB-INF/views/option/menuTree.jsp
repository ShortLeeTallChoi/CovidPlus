<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css" integrity="sha512-HK5fgLBL+xu6dm/Ii3z4xhlSUyZgTT9tuc/hSrtw6uzJOvgRr2a9jyxxT1ely+B+xFAmJKVSTbpM/CuL7qxO8w==" crossorigin="anonymous" />
<link rel="stylesheet" href="${rootPath }/css/jsLists.css" />
<script src="${rootPath }/js/jsLists.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" crossorigin="anonymous"></script>

<ul id="simple_list">
	<li><i class="far fa-file-alt" aria-hidden="true"></i>root
		<ul>
		<li><i class="far fa-file-alt" aria-hidden="true"></i>perent01
			<ul>
			<li><i class="far fa-file-alt" aria-hidden="true"></i>child1-1</li>
			</ul>
		</li>
		<li><i class="far fa-file-alt" aria-hidden="true"></i>perent02
			<ul>
			<li><i class="far fa-file-alt" aria-hidden="true"></i>child2-1
				<ul>
				<li><i class="far fa-file-alt" aria-hidden="true"></i>child2-1-1</li>
				<li><i class="far fa-file-alt" aria-hidden="true"></i>child2-1-2</li>
				</ul>
			</li>
			<li><i class="far fa-file-alt" aria-hidden="true"></i>child2-2</li>
			<li><i class="far fa-file-alt" aria-hidden="true"></i>child2-3</li>
			</ul>
		</li>
		<li><i class="far fa-file-alt" aria-hidden="true"></i>perent03
			<ul>
			<li><i class="far fa-file-alt" aria-hidden="true"></i>child3-1</li>
			</ul>
		</li>
		<li><i class="far fa-file-alt" aria-hidden="true"></i>perent04</li>
		<li><i class="far fa-file-alt" aria-hidden="true"></i>perent05</li>
		</ul>
	</li>
</ul>

<script>JSLists.applyToList('simple_list', 'ALL');</script>