function GetId(id){return document.getElementById(id);}
var timer=null;
function Web(){
	GetId("container").style.height=window.innerHeight+"px";
	GetId("container").style.width=window.innerWidth+"px";
	GetId("Rcontainer").style.height=window.innerHeight+"px";
	GetId("Rcontainer").style.width=window.innerWidth+"px";
	GetId("Rcontainer2").style.height=window.innerHeight+"px";
	GetId("Rcontainer2").style.width=window.innerWidth+"px";
}
GetId("All").style.height=window.innerHeight;
GetId("All").style.width=window.innerWidth;
GetId("Navigation").style.height=window.innerHeight+"px";
function init(){
	timer=setInterval('Web()',100);
}
init();


function Register(){
	GetId("container").style.display="none";
	GetId("Rcontainer").style.display="block";
	GetId("Rcontainer").style.zIndex="100";
}

function BackSign(){
	GetId("container").style.display="block";
	GetId("Rcontainer").style.display="none";
	GetId("container").style.zIndex="100";
}

function Register2(){
	GetId("Rcontainer").style.display="none";
	GetId("Rcontainer2").style.display="block";
	GetId("Rcontainer2").style.zIndex="100";
}

function ToPhone(){
	GetId("Rcontainer2").style.display="none";
	GetId("Rcontainer").style.display="block";
	GetId("Rcontainer").style.zIndex="100";
}

function BackSign2(){
	GetId("container").style.display="block";
	GetId("Rcontainer2").style.display="none";
	GetId("container").style.zIndex="100";
}

function ToSign(){
	GetId("User").style.opacity="0";
	GetId("ToSign").style.opacity="1";
}

function ToLeave(){
	GetId("User").style.opacity="1";
	GetId("ToSign").style.opacity="0";
}

function GoSign(){
	GetId("container").style.display="block";
	GetId("container").style.zIndex="100";
}

function GoRegister(){
	GetId("Rcontainer").style.display="block";
	GetId("Rcontainer").style.zIndex="100";
}

function Disappear(){
	GetId("container").style.display="none";
}

function RDisappear(){
	GetId("Rcontainer").style.display="none";
}

function RDisappear2(){
	GetId("Rcontainer2").style.display="none";
}


/*验证码
var counts = 60;
 
function settime(val) {
 if(counts == 0) {
  val.removeAttribute("disabled");
  val.value = "获取验证码";
  counts = 60;
  return false;
 } else {
  val.setAttribute("disabled", true);
  val.value = "重新发送（" + counts + "）";
  counts--;
 }
 setTimeout(function() {
  settime(val);
 }, 1000);
}*/