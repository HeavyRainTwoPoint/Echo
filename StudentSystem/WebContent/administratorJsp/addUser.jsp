<%@page import="myEntity.Time"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>添加新用户</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/myCSS/table.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/myCSS/showAllUser.css">
<script type="text/javascript">
	function check_form()
	{
		var name = form.name.value;
		var password = form.password.value;
		if(name == "" || name == null)
		{
			alert("姓名为必填字段！！！");
			return false;
		}
		else if(password == "" || sex == null)
		{
			alert("密码不能为空！！！");
			return false;
		}
		return true;
	}
</script>
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
<div class="div">
	<form name="form" action="${pageContext.request.contextPath }/OperateServlet" method="post" onSubmit="return check_form()">
		<table  class="table">
			<caption>待添加个人信息</caption>
			<tr>
				<td >姓名：<input class="input_text" type="text" name="name" ></td>
				<td >性别：<input class="radio" type="radio" name="sex" value="男" checked="checked">男&nbsp;&nbsp;
							<input class="radio" type="radio" name="sex" value="女">女
				</td>
				<td >年龄：
					<select name="age" size="1">
			<% 	
					for(int i=1;i<100;i++)
					{
			%>
						<option value=<%=i %>><%=i %></option>
			<%
					}
			%>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="1">专业：<input class="input_text" type="text" name="profession" value=""></td>
				<td colspan="1">密码：<input class="input_text" type="text" name="password" value=""></td>
				<td colspan="1">联系电话：<input class="input_text" type="text" name="phonenumber" value=""></td>
			</tr>
			<tr>
				<td class="td" colspan="6">
					爱好：<textarea class="textarea_hobby" rows="5" cols="60" name="hobby" value=""></textarea>
				</td>
			</tr>
		</table>
		<div class="div_revise">
			<input type="hidden" name="type" value="ADD">
			<input class="submit" type="submit" name="submit" value="添加"/>
		</div>
	</form>
</div>
</body>
</html>