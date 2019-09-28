<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="myService.imp.QueyIndividualUserServiceImp" %>
    <%@ page import="myEntity.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>个人中心</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/myCSS/updateIndividualUser.css">
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
	function check_form()
	{
		var password = form_general.password.value;
		if(password == "" || password == null)
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
	<form name="form_general"action="${pageContext.request.contextPath }/UpdateCurrentUser" method="post" onSubmit="return check_form()">
		<table frame="border" rules="all" class="table">
			<caption>个人信息</caption>
			<%
					String name = (String)request.getAttribute("name");
				  	QueyIndividualUserServiceImp userService = new QueyIndividualUserServiceImp();
				  	User user = userService.getUser(name);
			%>
			<tr>
				<td rowspan="2" colspan="2"><image src="/Image/<%=user.getImagename()%>" height="100px" width="100px"/></td>
				<td >姓名：<input class="input_text" type="text" readonly="readonly" name="name" value=<%=user.getName() %>></td>
				<td >性别：<input class="radio" type="radio" name="sex" value="男" checked="checked">男&nbsp;&nbsp;
							<input class="radio" type="radio" name="sex" value="女">女</td>
				<td >年龄：
					<select name="age" size="1">
			<% 	
					for(int i=1;i<100;i++)
					{
			%>
						<option name="age" value=<%=i %>><%=i %></option>
			<%
					}
			%>
					</select>
				</td>
			</tr>
			<tr>
				<td >专业：<input class="input_text" type="text" name="profession" value=<%=user.getProfession() %>></td>
				<td >登录密码：<input class="input_text" type="text" name="password" value=<%=user.getPassword() %>></td>
				<td >联系电话：<input class="input_text" type="text" name="phonenumber" value=<%=user.getPhonenumber() %>></td>
			</tr>
			<tr>
				<td  colspan="6">
					爱好：<textarea class="textarea_hobby" rows="10" cols="10" name="hobby"><%=user.getHobby() %></textarea>
				</td>
			</tr>
		</table>
		<div class="div_revise">
			<input class="submit_revise" type="submit" name="submit" value="确认"/>
		</div>
	</form>
	<form action="${pageContext.request.contextPath }/DirectionServlet" method="post">
			<input type="hidden" name="name" value=<%=user.getName() %>>
			<input type="hidden" name="type" value="BACK">
			<input class="submit_back" type="submit" name ="submit" value = "返回">
	</form>
	<div class="show_image">
		<form name="form_image" action="${pageContext.request.contextPath }/UploadImageServlet" method="post" enctype="multipart/form-data" onSubmit="return check_image()">
			选择一张图片：<input type="file" name = "imageName" ><br>
			<input type="hidden" name="name" value=<%=user.getName() %>>
			<input class="submit_upload" type="submit" name ="submit" value = "上传">
		</form>
	</div>
</body>
</html>