var ifphone=/^[1][3,4,5,7,8,9][0-9]{9}$/;
var ifemail = /^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/; 
function IsPhone(){
	var phone=GetId("phone").value;
	if(phone==""){
		return false;
	}
	else if(!myreg.test(phone)){
		GetId("PAlertText").innerHTML="*手机号错误";
		return false;
	}
	else{
		GetId("PAlertText").innerHTML="√";
		return true;
	}
}
	function SignIn(){
		console.log(2);		
		var user=GetId("account").value;
		var pass=GetId("password").value;
		var status;
		if(user==""&&pass=="")
		{
			GetId("AlertText").innerHTML="*用户名和密码不能为空";
			GetId("alert").style.opacity="1";
		}
		if(user=="")
		{
			GetId("AlertText").innerHTML="*用户名不能为空";
			GetId("alert").style.opacity="1";
		}
		if(pass=="")
		{
			GetId("AlertText").innerHTML="*密码不能为空";
			GetId("alert").style.opacity="1";
		}
		$.ajax({
			url:"http://localhost:8080/rehus/load.do",
			data:{"SignIn":$("#account").val(),"PassWord":$("#password").val()},
			success:function(data){
				if(data==0){
					GetId("AlertText").innerHTML="*用户名或密码错误";
					GetId("alert").style.opacity="1";
				}
				else if(data==1){
					GetId("AlertText").innerHTML="*验证码错误";
					GetId("alert").style.opacity="1";
				}
				else if(data==2)
				{
					GetId("container").style.display="none";
				}
				else if(data==8){
					GetId("AlertText").innerHTML="*用户名或密码错误";
					GetId("alert").style.opacity="1";
				}
				else if(data==9){
					GetId("AlertText").innerHTML="*此邮箱尚未激活";
					GetId("alert").style.opacity="1";
				}
				else if(data==10)
				{
					GetId("container").style.display="none";
				}
			}
		})
		
	}
 var IfFlag=false;
function IfHad(){
	$.ajax({
		url:"http://localhost:8080/rehus/post.do",
		data:{"Raccount":$("#Raccount").val(),"status":0},
		success:function(data){
			if(data==1){
				GetId("RAlertText").innerHTML="*该昵称已被使用";
				IfFlag=false;
			}
			else{
				GetId("RAlertText").innerHTML="√";
				IfFlag=true;
			}
		}
	})
}
var Code=null;
function getCode(){
	$.ajax({
		url:"http://localhost:8080/rehus/PhonePostAjax.do",
		data:{
			"Raccount":$("#Raccount").val(),
			"phone":$("#phone").val()
		},
		success:function(ConCode){
			Code=ConCode;
		}
	})
}

function ToRegisterSJ(){
	if(GetId("Verification").value==Code){
		$.ajax({
			url:"http://localhost:8080/rehus/post.do",
			data:{
				"Raccount":$("#Raccount").val(),
				"RPassWord":$("#Rpassword").val(),
				"phone":$("#phone").val(),
				"status":1
			},
			success:function(or){
				if(or==true){
					alert("注册成功");
					GetId("Rcontainer").style.display="none";
				}
				else{
					return;
				}
			}
		})
	}
	else{
		alert("验证码错误");
	}
}

function ToRegisterEmail(){
	$.ajax({
		url:"http://localhost:8080/rehus/post.do",
		data:{
			"Raccount2":$("#Raccount2").val(),
			"RPassWord2":$("#Rpassword2").val(),
			"Email":$("#Email").val(),
			"status":2
		},
		success:function(or){
			if(or==true){
				alert("激活码已发送至邮箱");
				GetId("Rcontainer2").style.display="none";
			}
			else{
				return;
			}
		}
	})
}

