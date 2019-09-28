<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>错误！</title>
<style type="text/css">
	body{
		font-size:30px;
		color:black;
		font-weight:bolder;
	}
	.submit_back{
		float: left;
		margin-left: 125px;
		margin-top: 30px;
		text-align: center;
		border:#8D8D8D;
		border-radius: 5px;
		width: 60px;
		height: 40px;
	}
</style>
</head>
<body>
		<h1><%=request.getAttribute("message") %></h1>
		<form action="${pageContext.request.contextPath}/DirectionServlet" method="post">
			<input  type="hidden" name="type" value="BACK"/>
			<input  type="hidden" name="name" value="<%=request.getAttribute("name")%>"/>
			<input  class="submit_back" type="submit" name="submit" value="返回">
		</form>
</body>
</html>