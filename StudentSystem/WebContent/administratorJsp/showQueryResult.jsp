<%@page import="myEntity.Time"%>
<%@page import="myEntity.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查找结果</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/myCSS/showAllUser.css">
<script type="text/javascript">
	function take_confirm()
	{
		var name = delete_form.name.value;
		var result = confirm("确认要删除"+name+"吗？");
		if(result == true)
		{
			return true;
		}
		else
		{
			return false;
		}
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
	<%
			List<User> userlist = (List<User>)request.getAttribute("userlist");		
	%>
		<table class="table">
			<tr>
				<td class="td_titlle">用户名</td>
				<td class="td_titlle">性别</td>
				<td class="td_titlle">年龄</td>
				<td class="td_titlle">专业</td>
				<td class="td_titlle">联系电话</td>
				<td class="td_titlle">爱好</td>
				<td class="td_titlle">密码</td>
				<td class="td_titlle">操作</td>
				<td class="td_titlle">操作</td>
			</tr>
		<%
				for(User user:userlist)
				{
		%>
			<tr>
				<td><%=user.getName() %></td>
				<td><%=user.getSex() %></td>
				<td><%=user.getAge() %></td>
				<td><%=user.getProfession() %></td>
				<td><%=user.getPhonenumber() %></td>
				<td><%=user.getHobby() %></td>
				<td><%=user.getPassword() %></td>
				<td>
					<form action="${pageContext.request.contextPath }/OperateServlet" method="post">
						<input type="hidden" name="name" value=<%=user.getName() %>>
						<input type="hidden" name="type" value="SM">
						<input class="td_revise" type="submit" name="submit" value="修改">
					</form>
				</td>
				<td>
					<form name="delete_form" action="${pageContext.request.contextPath }/OperateServlet" method="post" onSubmit="return take_confirm()">
						<input type="hidden" name="name" value=<%=user.getName() %>>
						<input type="hidden" name="type" value="D">
						<input class="td_delete" type="submit" name="submit" value="删除">
					</form>
				</td>
			</tr>
		<%
				}
		%>
		</table>
</body>
</html>