<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		$("#btn").click(function() {
			if ($("#id").val() == "") {
				alert("아이디를 입력해주세요");
				return;
			} else if ($("#pw").val() == "") {
				alert("비밀번호를 입력해주세요");
				return;
			}
			$("#loginForm").submit();
		});
	});
</script>
</head>
<body>
	<div>
		오늘 접속자 수 : ${stats.count}명
	</div>
	<div>
		전체 접속자 수 : ${totalCount}명
	</div>
	
	<form method="post" action="${pageContext.request.contextPath}/LoginServlet" id="loginForm" >
	<h1>로그인 폼</h1>
		<div>
			<input type="text" placeholder="Email" name="id" id="id">
		</div>
		<div>
			<input type="password" placeholder="PW" name="pw" id="pw">
		</div>
		<div>
			<button type="button" id="btn">Log-in</button>
		</div>
	</form>	
</body>
</html>