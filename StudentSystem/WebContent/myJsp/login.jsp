<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/myCSS/login.css">
<script type="text/javascript">
	function check()
	{
		var name = form.userName.value;
		var password = form.userPassword.value;
		if(name == "" || name == null)
		{
			alert("用户名不能为空！！！")
			return false;
		}
		else if(password == ""|| password == null)
		{
			alert("密码不能为空！！！"+password+"?");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
<div class="title">
		爪哇部落信息管理系统
</div>
<div class="div">
	<p><span class="span"> <%= request.getAttribute("message")==null?"":request.getAttribute("message")%></span></p>
	<% request.removeAttribute("message"); %>
	<form name="form" action="${pageContext.request.contextPath }/ConnectServlet"  method="post" onSubmit="return check()">
		<table class="table">
			<tr>
				<td class="td_select" colspan="2">
					<select class="select" name="loginUser" size="1">
						<option value="普通用户">普通用户</option>
						<option value="管理员">管理员</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>用户名：<input class="input_name" type="text" id="userName" name="userName" /><br>
				</td>
			</tr>
			<tr>
				<td>密&nbsp;&nbsp;&nbsp;&nbsp;码：<input class="input_password"
					type="password" id="userPassword" name="userPassword" /><br>
				</td>
			</tr>
			<tr>
					<td colspan="2">
						<input class="submit"type="submit" value="登录" />
						<a href="${pageContext.request.contextPath}/myJsp/register.jsp">
								<input class="button" type="button" value="注册">
						</a>
					</td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>