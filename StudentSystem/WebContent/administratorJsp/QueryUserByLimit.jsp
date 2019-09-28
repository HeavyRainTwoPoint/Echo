<%@page import="myEntity.Time"%>
<%@page import="myEntity.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>爪哇部落信息管理系统</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/myCSS/showAllUser.css">
</head>
<body>
<div class="div_title">
	<div class="div_top">
		时间：<%=new Time().toString() %>
	</div>
	<div class="div_buttom">
		<div class="div_function">
			<form action="${pageContext.request.contextPath }/QueryUserByPageServlet" method="post">
						<input type="hidden" name="currentPage" value=<%=1%>>
						<input class="function" type="submit" name="submit" value="首页">
			</form>
		</div>
		<div class="div_function">
			<form action="${pageContext.request.contextPath }/OperateServlet" method="post">
					<input type="hidden" name="type" value="QUB">
					<input type="hidden" name="name" value="">
					<input class="function" type="submit" name="submit" value="查找"	>
			</form>
		</div>
		<div class="div_function">
			<form action="${pageContext.request.contextPath }/OperateServlet" method="post">
					<input type="hidden" name="type" value="A">
					<input type="hidden" name="name" value="">
					<input class="function" type="submit" name="submit" value="添加"	>
			</form>
		</div>
		<div class="div_function">
			<form action="${pageContext.request.contextPath }/OperateServlet" method="post">
					<input type="hidden" name="type" value="SR">
					<input type="hidden" name="name" value="">
					<input class="function" type="submit" name="submit" value="审核"	>
			</form>
		</div>
		<div class="div_function">
			<form action="${pageContext.request.contextPath }/OperateServlet" method="post">
					<input type="hidden" name="type" value="EXIT">
					<input type="hidden" name="name" value="">
					<input class="function" type="submit" name="submit" value="注销"	>
			</form>
		</div>
	</div>
</div>
<div class="">
	<form action="${pageContext.request.contextPath}/OperateServlet" method="post">
		<table >
			<caption>输入相关信息进行查询</caption>
			<tr>
				<td>
					姓名：<input type="text" name="name" >
				</td>
				<td>
					年龄：
					<select name="age">
				<%
					for(int i=0;i<100;i++)
					{
				%>
						<option value=<%=i%>><%=i%></option>
				<%
					}
				%>
					</select>
				</td>
				<td>
					性别：
					<input class="radio" type="radio" name="sex" value="男">男&nbsp;&nbsp;
					<input class="radio" type="radio" name="sex" value="女">女
				</td>
				<td>
					专业：<input type="text" name="profession" >
				</td>
				<td>
					联系电话：<input type="text" name="phonenumber" >
				</td>
				<td>
					爱好：<input type="text" name="hobby" >
				</td>
				<td>
					密码：<input type="text" name="password" >
				</td>
			</tr>
			<tr>
				<td colspan="6">
					<input type="hidden" name="type" value="SEARCH">
					<input type="hidden" name="name" value="">
					<input style="border: none;
							border-radius: 5px;
							background: #B7B7B7;
							width:150px;
							height: 18px;
							font-size:16px;"type="submit" name="submit" value="查找">
				</td>
			</tr>
		</table>
	</form>
</div>
	<p><span > <%= request.getAttribute("data")==""?"暂无满足条件的记录！":""%></span></p>
				
</body>
</html>