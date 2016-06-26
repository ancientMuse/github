function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var h=date.getHours();
    var m=date.getMinutes();
    var s=date.getSeconds();
    
    if(h<10)
    	h="0"+h;
    if(m<10)
    	m="0"+m;
    if(s<10)
    	s="0"+s;
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
            + " " + h + seperator2 + m
            + seperator2 + s;
    return currentdate;
}
var t = null;
//var countsecond = 0;
var countsecond = sessionStorage.getItem("countsecond");

if(countsecond==null)
sessionStorage.setItem("countsecond",0); 
countsecond = sessionStorage.getItem("countsecond");
t = setTimeout(time, 1000);// 开始执行
function time() {
	countsecond=parseInt(countsecond);
	countsecond++;
	sessionStorage.setItem("countsecond",countsecond); 
	
	
	var ch = 0;
	var cm = 0;

	ch = parseInt(countsecond / 3600);
   if(ch<10)
	   ch="0"+ch;
	cm = parseInt((countsecond - ch * 3600) / 60);
	  if(cm<10)
		   cm="0"+cm;
	var cs = countsecond - 3600 * ch - 60 * cm;
	  if(cs<10)
		   cs="0"+cs;
	// clearTimeout(t);//清除定时器
	dt = new Date();
	var h = dt.getHours();
	var m = dt.getMinutes();
	var s = dt.getSeconds();
	document.getElementById("nowtime").innerHTML = "现在时间"
			+ h + ":" + m + ":" + s + "" + "，已登录" + ch
			+ ":" + cm + ":" + cs + "";
	t = setTimeout(time, 1000); //设定定时器，循环执行             
}

