<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="myEntity.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>个人中心</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/myCSS/getUpdateUser.css">
<script type="text/javascript">
	function check_image()
	{
		var imageName = form_image.imageName.value;
		if(imageName == null || imageName == "")
		{
			alert("未选择图片！！！")
			return false;
		}
		return true;
	}
 	function check()
 	{
 		var password = form.password.value;
 		if(password == "" || password == null)
 		{
 			alert("密码不能为空！！！");
 			return false;
 		}
 		else
 		{
 			return true;
 		}
 	}
</script>
</head>
<body>
<div class="div_table">
	<form name="form" action="${pageContext.request.contextPath }/OperateServlet" method="post" onSubmit="return check()">
		<table class="table">
			<caption>详细信息</caption>
			<%
				  User user = (User)request.getAttribute("user");
			%>
			<tr>
				<td rowspan="2" colspan="2"><image src="/Image/<%=user.getImagename()%>" height="100px" width="100px"/></td>
				<td >姓名：<input class="input_text" type="text" readonly="readonly" name="name" value=<%=user.getName() %>></td>
				<td >性别：<input class="radio" type="radio" name="sex" value="男" checked="checked">男&nbsp;&nbsp;
							<input class="radio" type="radio" name="sex" value="女">女
				</td>
				<td >
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
			</tr>
			<tr>
				<td >专业：<input class="input_text" type="text" name="profession" value=<%=user.getProfession() %>></td>
				<td >联系电话：<input class="input_text" type="text" name="phonenumber" value=<%=user.getPhonenumber() %>></td>
				<td >登录密码：<input class="input_text" type="text" name="password" value=<%=user.getPassword() %>></td>
			</tr>
			<tr>
				<td  colspan="6">
					爱好：<textarea class="textarea_hobby" rows="1" cols="100" name="hobby"><%=user.getHobby() %></textarea>
				</td>
			</tr>
		</table>
		<div class="div_revise">
			<input type="hidden" name="type" value="U">
			<input class="submit_revise" type="submit" name="submit" value="修改"/>
		</div>
	</form>
	<form action="${pageContext.request.contextPath }/OperateServlet" method="post">
		<input type="hidden" name="type" value="R">
		<input type="hidden" name="name" value="">
		<input class="submit_back" type="submit" name="submit" value="返回首页">	
	</form>
	</div>
	<div class="image">
		<form action="${pageContext.request.contextPath }/AUpdateImageServlet" method="post" enctype="multipart/form-data" onSubmit="return check_image()">
			选择一张图片：<input type="file" name="imageName">
			<input type="hidden" name="name" value="<%=user.getName() %>">
			<input class="submit_back" type="submit" name="submit" value="上传">	
	</div>
</body>
</html>