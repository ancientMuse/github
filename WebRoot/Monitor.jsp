<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>监控页面</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script src="res/script/jquery-1.9.1.js"></script>
<script src="res/script/jquery-ui.js"></script>
<script src="res/script/time.js"></script>
<script src="res/script/header.js"></script>
<script src="res/script/Montior.js"></script>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<%  if(session.getAttribute("user")==null) 
	   response.sendRedirect("index.html");
	     %>
<script type="text/javascript">

    function onClose(){
  
    
    }</script>
<style type="text/css">
html,body {
	margin: 0px;
	padding: 0px;
	height: 100%;
}

.header {
	font-weight: bold;
	font-family: "宋体";
	padding-top: 0px;
	margin: 0px;
	width: 100%;
	background: #ADD8E6;
	height: 0px;
	line-height: 50px;
	float: right;
	box-shadow: 0px 0px 5px 1px #888888;
}

.bd {
	margin: 0px auto;
	width: 900px;
	height: 100%;
	padding-top: 60px;
	padding-bottom: 10px;
}

.SectorList {
   padding-left:30px;
    margin: 0px auto;
	width: 900px;
	height: 200px;
}

.SectorItem {
	word-break: break-all;
	color: #000;
	margin-left: 12px;
	margin-top: 12px;
	width: 180px;
	height: 0px;
	line-height: 60px;
	box-shadow: 0px 0px 5px 3px #a0a0a0;
	border-color: #4cae4c;
	display: block;
	margin-bottom: 0px;
	padding: 3px 6px;
	font-size: 14px;
	text-align: center;
	vertical-align: middle;
	cursor: pointer;
	background: #f8f8f8;
	border: 1px solid transparent;
	border-radius: 4px;
	float: left;
}

.logout {
	float: right;
	margin-right: 30px;
}

.sectorname {
	float: left;
	margin-left: 20px;
}

.sectorUser {
	float: left;
	margin-left: 10px;
	color: red;
}

.logout:hover {
	background: #a0a0a0;
	color: red;
	cursor: pointer
}

.timeloginhad {
	margin-right: 20px;
	float: right;
}

.message {

	margin: 0px auto;
	width: 828px;
	height: 0px;
	background: #000;
	color: #fff;
	overflow: auto;
	padding:6px;
	margin-top:5px;
}

.SectorMSG {
	box-shadow: 0px 0px 5px 1px #888888;
	background: #a0a0a0;
	margin: 0px auto;
	overflow: auto;
	padding:6px;
	width: 0px;
	height: 0px;
	overflow: auto;
	border-radius: 1px;
	border: 1px solid #bbb;
}

.linemsg {
	float: left;
	width: 820px;
	height: 80px;
	background: #f8f8f8;
	box-shadow: 0px 0px 5px 3px #a0a0a0;
	border: 1px solid #f8f8f8;
	margin-top: 10px;
	border-radius: 1px;
}

.time {
	width: 80px;
	float: left;
	height: 100%;
	padding-top: 20px;
	text-align: center;
	font-size: 10px;
}

.fromsector {
	width: 140px;
	float: left;
	height: 0%;
}

.SJL {
	width: 280px;
	float: left;
	height: 100%;
	background: #f8f8f8;
}

.toSector {
	width: 140px;
	float: left;
	height: 0%;
}

.showData {
	width: 110px;
	float: left;
	height: 100%;
}

.listsector {
	word-break: break-all;
	color: #000;
	margin-top: 10px;
	width: 135px;
	height: 60px;
	line-height: 60px;
	box-shadow: 0px 0px 5px 3px #a0a0a0;
	float: left;
	margin-bottom: 0px;
	font-size: 14px;
	text-align: center;
	vertical-align: middle;
	cursor: pointer;
	background: #4f8fcd;
	border: 1px solid #4f8fcd;
	border-radius: 4px;
	float: left;
	color: #fff;
}

.I {
	margin-top: 9px;
	margin-bottom: 3px;
	width: 280px;
	height: 20px;
	line-height: 20px;
	text-align: center;
	color: red;
	font-weight: bold;
}

.triangle {
	width: 0;
	height: 0;
	border-top: 15px solid transparent;
	border-left: 35px solid #000;
	border-bottom: 15px solid transparent;
	float: left;
	border-radius: 3px;
}

.allprocess {
	float: left;
	margin-top: 8px;
	width: 245px;
	background: #000;
	height: 15px;
}

.nowprocess {
	width: 0%;
	height: 100%;
	background-color: #55b055;
}

.xuhao {
	width: 38px;
	float: left;
	height: 100%;
	padding-top: 24px;
	padding-left: 6px;
}

.xuhao .xh {
	border-color: transparent;
	background: #6495ED;
	color: #fff;
	width: 28px;
	height: 28px;
	font-size: 11px;
	line-height: 28px;
	text-align: center;
	border-radius: 50%;;
	-moz-border-radius: 50%;;
}

.showData {
	text-align: center;
	line-height: 80px;
}

.showbutton {
	color: #fff;
	background-color: #5cb85c;
	border-color: #4cae4c;
	display: block;
	padding: 1px 3px;
	font-size: 14px;
	margin-left: 0px;
	font-weight: 400;
	line-height: 1.428571429;
	text-align: center;
	white-space: nowrap;
	vertical-align: middle;
	cursor: pointer;
	background-image: none;
	border: 1px solid transparent;
	border-radius: 4px;
	margin-top:20px;
	margin-bottom:20px;
	height:30px;
}

.showbutton:hover {
	background-color: #35a035;
	color: #fff;
}

.loading {
	display: none;
	position: absolute;
	top: 0%;
	left: 0%;
	width: 100%;
	height: 100%;
	background-color: black;
	z-index: 1100;
	-moz-opacity: 0.8;
	opacity: .80;
	filter: alpha(opacity =   80);
}

.loading div {
	margin: auto;
	margin-left: 45%;
	margin-top: 300px;
	width: 124px;
	height: 124px;
}

.black_overlay {
	display: none;
	position: absolute;
	top: 0%;
	left: 0%;
	width: 100%;
	height: 100%;
	background-color: black;
	z-index: 1001;
	-moz-opacity: 0.1;
	opacity: .10;
	filter: alpha(opacity =   10);
}

.white_content {
	border-radius: 8px;
	display: none;
	position: absolute;
	margin-top: 0px;
	margin-left: 30%;
	width: 620px;
	height: 0px;
	padding: 0px;
	border: 5px solid white;
	background: #fff;
	z-index: 1002;
	text-align: center;
	box-shadow: 0px 0px 5px 1px #ddd;
}

.white_content .title {
	width: 100%;
	background-color: #4B90F9;
	text-align: left;
	font-weight: bold;
	box-shadow: 0px 0px 2px 1px #888888;
	border-color: transparent;
	border-radius: 1px;
}

.white_content .title span {
	margin-left: 6px;
	text-align: left;
	font-weight: normal;
	color: #fff;
	font-size: 14px;
	font-family: "lucida Grande", Verdana, "Microsoft YaHei";
}

.white_content .title a {
	padding: 1px;
	float: right;
	width: 18px;
	color: red;
	text-decoration: none;
	font-weight: bold;
	text-align: center;
	vertical-align: middle;
}

.white_content .title a:hover {
	color: white;
	background: #444;
}

.white_content  	.content {
	width: 100%;
	height: 500px;
	margin: 0 auto;
	overflow: auto;
}

.white_content .btn {
	height: 55px;
	width: 100%;
	margin-left: -6px;
}

.white_content .btn button {
	width: 80px;
	height: 25px;
	border: groove #000 1px;
	background: #1576B8;
	border-color: transparent;
	box-shadow: 0px 0px 3px 1px #555;
	color: #fff;
	border-radius: 6px;
	-moz-border-radius: 6px;
	cursor: Pointer;
	font-family: "黑体";
}

.line {
	padding-top: 0px;
	width: 100%;
	height: 30px;
	margin: 0px auto;
}

.table {
	height: 80%;
	overflow-x: hidden;
	overflow-y: auto;
font-family: "lucida Grande", Verdana, "Microsoft YaHei";
}

.table-line {
	display: table;
	width: 100%;
	margin: 0px auto;
	margin-left: 0px;
	border-collapse: separate;
	border: 1px solid #bbb;
}

.th {
	background: #4F4F4F;
	color: #fff;
}

.table-line-xuhao {
	width: 40px;
	display: table-cell;
	border-collapse: separate;
	padding-top: 6px;
	padding-bottom: 6px;
}

.table-line-key {
	width: 30%;
	display: table-cell;
	border-collapse: separate;
	padding-top: 6px;
	padding-bottom: 6px;
}

.table-line-value {
	width: 30%;
	display: table-cell;
	border-collapse: separate;
	padding-top: 6px;
	padding-bottom: 6px;
}
.tr {
	background: #E5E5E5;
	color: #000;
	height:0px;
}

.table-line-cz {
	width: 50px;
	display: table-cell;
	padding-top: 6px;
	padding-bottom: 6px;
	padding-left: 8px;
	border-collapse: separate;
    position:relative;
}
.text {
	width: 98%;
	display: block;
	border: 1px solid #ccc;
	height: 100%;
	border-radius: 3px;
	-moz-border-radius: 3px;
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075), 0 0 8px
		rgba(102, 175, 233, 0.6);
	outline: none;
	height:40px;
	color: #555;
}

.text:focus {
	border-color: #66afe9;
}
.cz_button {
    position:absolute;  
	color: #fff;
	background-color: #5cb85c;
	border-color: #4cae4c;
	display: block;
	font-size: 14px;
	margin:0px auto;
	text-align: center;
	white-space: nowrap;
	vertical-align: middle;
	cursor: pointer;
	background-image: none;
	border: 1px solid transparent;
	border-radius: 4px;
    margin-top:10px;
    margin-left:50px;
    height:20px;
    margin-bottom:10px;
}

.cz_button:hover {
	background-color: #30a050;
	color: #fff;
}
.white_content  .btn{
   
	height: 55px;
	width: 100%;
	margin-left: 6px;

}
.msgblack_overlay{
    display: none;
	position: absolute;
	top: 0%;
	left: 0%;
	width: 100%;
	height: 100%;
	background-color: black;
	z-index: 1004;
	-moz-opacity: 0.1;
	opacity: .10;
	filter: alpha(opacity =   10);

}
.msghwhite_content{
    border-radius: 8px;
	display: none;
	position: absolute;
    margin:0 auto;
    margin-left:40%;
	width: 320px;
	height: 0px;
	padding: 0px;
	border: 5px solid white;
	background: #fff;
	z-index: 1005;
	text-align: center;
	box-shadow: 0px 0px 5px 1px #ddd;

}

.msghwhite_content .title {
	width: 100%;
	background-color: #4B90F9;
	text-align: left;
	font-weight: bold;
	box-shadow: 0px 0px 2px 1px #888888;
	border-color: transparent;
	border-radius: 1px;
}

.msghwhite_content .title span {
	margin-left: 6px;
	text-align: left;
	font-weight: normal;
	color: #fff;
	font-size: 14px;
	font-family: "lucida Grande", Verdana, "Microsoft YaHei";
}

.msghwhite_content .title a {
	padding: 1px;
	float: right;
	width: 18px;
	color: red;
	text-decoration: none;
	font-weight: bold;
	text-align: center;
	vertical-align: middle;
}

.msghwhite_content .title a:hover {
	color: white;
	background: #444;
}
.msghwhite_content  	.content {
	width: 100%;
	height: 100px;
	margin: 50px auto;
	overflow: auto;
}
</style>
</head>

<body>
	<div class="loading" id="loading">
		<div>
			<img src="res/img/loading.gif" />
		</div>
	</div>
   <div id="MSGfade" class="msgblack_overlay"></div>
   <div id="MSGlight" class="msghwhite_content">
      <div class="title" id="title">
			<span>消息提示</span><a href="javascript:" id="MSGx">×</a>
		</div>
		<div class="content" id="MSGcontent"></div>
    </div>
   <div id="fade" class="black_overlay"></div>
	<div id="light" class="white_content">
		<div class="title" id="title">
			<span>消息提示</span><a href="javascript:" id="x">×</a>
		</div>
		<div class="content" id="content">
			<div class="line table">
				<div class="table-line th">
					<div class="table-line-xuhao">序号</div>
					<div class="table-line-key">数据段名称</div>
					<div class="table-line-value">数据段值</div>
					<div class="table-line-cz">操作</div>
				</div>
			</div>
		</div>
		<div class="btn">
			<button id="updateData">保存修改</button>
		</div>

	</div>
	<div class="header">
		<div class="sectorname" id="namesc"></div>
		<div class="sectorUser"><%=session.getAttribute("user") %></div>
		<div class="logout">退出</div>
		<div class="timeloginhad" id="nowtime">时间</div>
	</div>
	<div class="bd">
		<div class="SectorList">
			<div class="SectorItem" id="A">编队作战指挥系统</div>
			<div class="SectorItem" id="B">本舰作战指挥系统</div>
			<div class="SectorItem" id="C">航空兵作战指挥系统</div>
			<div class="SectorItem" id="D">航空管制系统</div>
			<div class="SectorItem" id="E">综合导航系统</div>
			<div class="SectorItem" id="F">综合气象水文系统</div>
			<div class="SectorItem" id="G">航保指挥管理系统</div>
			<div class="SectorItem" id="H">航保起飞与着舰引导系统</div>
		</div>
		<div class="SectorMSG" id="showmsg"></div>
		<div id="messages" class="message"></div>
	</div>

</body>
</html>
