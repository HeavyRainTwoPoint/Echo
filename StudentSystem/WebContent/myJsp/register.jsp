<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新用户注册</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/myCSS/table.css">
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
<div class="div">
	<form name="form" action="${pageContext.request.contextPath }/RegisterServlet" method="post" onSubmit="return check_form()">
		<table  class="table">
			<caption>个人信息</caption>
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
			<input type="hidden" name="imageName" value="null">
			<input class="submit" type="submit" name="submit" value="注册"/>
			<a href="login.jsp"><input class="submit" type="button" value="返回"></a>
		</div>
	</form>
</div>
</body>
</html>