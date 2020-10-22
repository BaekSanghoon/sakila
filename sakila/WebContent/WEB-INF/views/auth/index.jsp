<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<h1>index.jsp</h1>
	<div>
		<span>${loginStaff}</span>관리자님 반갑습니다!! 
	</div>
	<div>
		<a href="${pageContext.request.contextPath}/auth/LogoutServlet">로그아웃</a>
	</div>
	
</body>
</html>