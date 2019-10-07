var CurScreen=0;
var timer=null;
function switchpic(){//改变图像路径
	GetId("cur").id="cur"+CurScreen;
	if(CurScreen<4){
		CurScreen++;
	}
	else if(CurScreen==4){
		CurScreen=0;
	}
	GetId("wrap-ul").style.marginLeft="-"+CurScreen+"00%";
	GetId("cur"+CurScreen).id="cur";
}

function reStart(){
	init();//调用init（）函数
}
function pause(){
	clearInterval(timer);//清除周期函数
}
function init(){
	timer=setInterval('switchpic();',4000);//每隔5s调用一次switchpic函数
}
/*------------轮播图导航小圆点------------------------------------------------------------------------------------*/
function click(num){
	GetId("wrap-ul").style.marginLeft="-"+num+"00%";
	GetId("cur").id="cur"+CurScreen;
	GetId("cur"+num).id="cur";
	CurScreen=num;
	pause();
}
function more(num){
	GetId("rpm-"+num).style.opacity="1";
}
function less(num){
	GetId("rpm-"+num).style.opacity="0";
}
var ML=600;
var flag=true;
var ToT=null;
function SLMove(){
	if(flag){
		ML=ML+40;
		GetId("SLI1").style.marginLeft="-"+ML+"px";
		if(ML=="1240"){
			flag=false;
		}
	}
	else{
		ML=ML-40;
		if(ML=="-40"){
			GetId("SLI1").style.marginLeft="40px";
			flag=true;
		}
		else{
			GetId("SLI1").style.marginLeft="-"+ML+"px";
		}
	}
}
function initx(){
	ToT=setInterval("SLMove()",300);
}
initx();
function SLStop(){
	clearInterval(ToT);
}
function SLStart(){
	initx();
}
function SLC(num){
	GetId("SLC"+num).style.opacity="1";
}
function SLC2(num){
	GetId("SLC"+num).style.opacity="0";
}
audio=new Audio();
audio.loop = false;
audio.src="img/LOKEY低调组合%20-%20终点起点.mp3";