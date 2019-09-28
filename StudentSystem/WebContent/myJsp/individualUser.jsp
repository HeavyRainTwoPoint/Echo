<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="myService.imp.QueyIndividualUserServiceImp" %>
    <%@ page import="myEntity.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>个人中心</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/myCSS/individualUser.css">
</head>
<body>
		<%
					String name = (String)request.getAttribute("name");
				  	QueyIndividualUserServiceImp userService = new QueyIndividualUserServiceImp();
				  	User user = userService.getUser(name);
		%>
	<image class="image" src="/Image/<%=user.getImagename()%>" />
<div class="div_table">
	<table class="table">
		<caption>个人信息</caption>
		<tr>
			<td >姓名：<%=user.getName() %></td>
			<td >性别：<%=user.getSex() %></td>
			<td >年龄：<%=user.getAge() %>
		</tr>
		<tr>
			<td >专业：<%=user.getProfession() %></td>
			<td >登录密码：<%=user.getPassword() %></td>
			<td >联系电话：<%=user.getPhonenumber() %></td>
		</tr>
		<tr>
			<td  colspan="6"rowspan="2">
				爱好：<%=user.getHobby() %>
			</td>
		</tr>
	</table>
	<div class="div_revise">
		<form action="${pageContext.request.contextPath}/DirectionServlet" method="post">
			<input  type="hidden" name="type" value="U"/>
			<input  type="hidden" name="name" value="<%=name%>"/>
			<input class="submit_revise" type="submit" name="revise" value="修改"/>
		</form>
		<form action="${pageContext.request.contextPath}/DirectionServlet" method="post">
			<input  type="hidden" name="type" value="EXIT"/>
			<input  type="hidden" name="name" value=""/>
			<input class="submit_revise" type="submit" name="revise" value="注销"/>
		</form>
	</div>
</div>
</body>
</html>