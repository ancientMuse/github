<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>部门操作界面</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="model">
<meta http-equiv="description" content="Model">
<link rel="stylesheet" type="text/css" href="res/css/bootstrap.css">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script src="res/script/jquery-1.9.1.js"></script>
<script src="res/script/loaddata.js"></script>
<script src="res/script/time.js"></script>
<script src="res/script/header.js"></script>

<style type="text/css">
  html,body {
    margin: 0px;
    padding: 0px;
    height: 100%;
  }

  body{
    background-image: url(res/img/bg2.jpg);
    background-size: cover;
    background-repeat: no-repeat;
    background-attachment:fixed;
  }
  .header {
    background-color: #0088cc;
    font-weight: bold;
    font-family: "宋体";
    font-size: 14px;     
    width: 100%;    
    color:#fff;
    height: 45px;
    line-height: 45px;
    position: fixed;
    top: 0px；
    left:0px;
    z-index: 9999;
  }

  .logout {
    float: right;
    margin-right: 30px;
    font-weight: bold;
  }

  .sectorname {
    float: left;
    margin-left: 20px;
    font-weight: bold;
  }

  .sectorUser {
    float: left;
    margin-left: 10px;
    font-weight: bold;
    color: red;
  }

  .sectorMission{
      float: left;
      color: #fff;
      margin-left: 100px;
    }

  .logout:hover {
    background: #a0a0a0;
    color: red;
    cursor: pointer
  }

  .timeloginhad {
    margin-right: 20px;
    float: right;
    font-weight: bold;
  }

  .bodycontent {
    width: 1080px;
    margin: 0 auto;
    padding-top: 65px;
    overflow: hidden;
  }

  .primary{
    width:720px;
    float: left;
  }

  .secondary{
    width: 340px;
    background-color: rgba(255,255,255,0.8);
    float: right;
    height: 734px;
    border-radius: 2px;
    -moz-border-radius: 2px;
    overflow: auto;
  }

  .mainfrom {
    background-color: rgba(255,255,255,0.8);
    margin: 0 auto 20px;
    border-radius: 2px;
    -moz-border-radius: 2px;
    border: 1px solid #a0a0a0;
    height: 500px;
    width: 718px;
    overflow: auto;
  }

  .message {
    margin: 0px auto;
    width: 720px;
    height: 214px;
    background-color: rgba(0,0,0,0.8);
    color: #fff;
    overflow: auto;
    padding:6px; 
  }

  .line {
    padding-top: 20px;
    width: 70%;
    height: 50px;
    margin: 0px auto;
  }

  .table {
    min-height:200px;
    max-height:600px;
    overflow: auto;
    font-family: "宋体", "Helvetica Neue", Helvetica, Arial, sans-serif;
  }

  .table-line {
    display: table;
    width: 90%;
    margin: 0px auto;
    border-collapse: separate;
    border: 1px solid #bbb;
    border-top: 0;
    text-align: center;

  }
  .th {
    background: #4F4F4F;
    font-weight: bold;
    color: #fff;
    
  }

  .tr {
    background: #E5E5E5;
    color: #000;
    
  }

  .table-line-xuhao {
    width: 10%;
    display: table-cell;
    vertical-align: middle;
      
    padding-top: 6px;
    padding-bottom: 6px;
    border-collapse: separate;
  }

  .table-line-key {
    width: 35%;
    display: table-cell;
    border-collapse: separate;
    padding-top: 6px;
    padding-bottom: 6px;
  }

  .table-line-value {
    width: 35%;
    display: table-cell;
    border-collapse: separate;
    padding-top: 6px;
    padding-bottom: 6px;
  }

  .text {
    width: 90%;
    display: block;
    border: 1px solid #ccc;
    height: 100%;
    border-radius: 3px;
    -moz-border-radius: 3px;
    margin: 0 auto;
    outline: none;
    height:50px;
    color: #555;
    font-size: 15px;
  }

  .table-line-cz {
    width: 20%;
    display: table-cell;
    padding-top: 6px;
    padding-bottom: 6px;
    border-collapse: separate;
    vertical-align: middle;    
  }

  .cz_button {
    color: #fff;
    background-color: #5cb85c;
    border-color: #4cae4c;
    display: block;
    font-size: 14px;   
    font-weight: 400;
    cursor: pointer;
    background-image: none;
    border: 1px solid transparent;
    border-radius: 4px;
    -webkit-user-select: none;
    margin: 0 auto;
  }

  .table-line-add {
    width: 100%;
    height: 100%;
    text-aligin: center;
    padding-top: 3px;
    padding-bottom: 3px;
    margin: 0 auto;
    border-collapse: separate;
    border: 1px solid #ddd;
  }
  .table-line-submit{
      width: 100%;
    height: 100%;
    margin: 0 auto;
    border-collapse: separate;
    
  }

  .table-line-add .button {
    margin: 0 auto;
    cursor: pointer;
    width: 100px;
  }

  .table-line-add .button button {
    height: 100%;
    background: #0088cc;
    color: #fff;
    border-color: transparent;
    border-radius: 7px;
    -moz-border-radius: 7px;    
    cursor: pointer;
    width: 100px;
    outline: none;
  }

  .table-line-add .button button:hover {
    background: #428bca;
    border-color: transparent;
    outline: none;
  }
  
  .selec {
    
    font-size: 15px;
    border-radius: 5px;
    -moz-border-radius: 5px;
    border: 1px solid #aaa;   
    width: 100%;
    text-align: center;
    outline: none;    
    height: 30px;
  }

  .xuhao {
    width: 30%;
    float: left;
    height: 30px;
    line-height: 30px;
  }

  .xuhao .xh {
    border-color: transparent;
    background: #0088cc;
    color: #fff;
    font-weight: bold;
    width: 30px;
    height: 30px;
    line-height: 30px;
    text-align: center;
    border-radius: 50%;;
    -moz-border-radius:50%;
    margin: auto;
    margin-left: 30px;
  }

  .kj {
    float: left;
    width: 40%;
    height: 30px;
    line-height: 30px;
  }

  .ts {
    float: left;
    height: 30px;
    line-height: 30px;
    font-size: 12px;
    color: red;
    width: 30%;
    text-indent: 20px;
  }

  .text:focus {
    border-color: #66afe9;
  }
  #submit{
    color: #000;       
    box-shadow: 0px 0px 3px 3px #a0a0a0;
    border-color: #4cae4c;
    display: block;
    padding: 3px 6px;
    margin: 0 auto;
    font-size: 14px;
    font-weight: 400;
    line-height: 1.428571429;
    text-align: center;
    white-space: nowrap;
    vertical-align: middle;
    cursor: pointer;
    background-image: none;
    border: 1px solid transparent;
    border-radius: 4px;
    -webkit-user-select: none;
  }
  #submit:hover{
          background:#428bca;
          color: #fff;

  }
  

  

  .cz_button:hover {
    background-color: #55b055;
    color: #fff;
  }
  .loading{
    
      display: none;
    position: absolute;
    top: 0%;
    left: 0%;
    width: 100%;
    height: 100%;
    background-color: black;
    z-index: 1010;
    -moz-opacity: 0.8;
    opacity: .80;
    filter: alpha(opacity = 80);
    }
  .loading div{

  margin: auto;
  margin-left:45%;
  margin-top:300px;
  width:124px;
  height:124px;
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
    filter: alpha(opacity = 10);
  }

  .white_content {
      border-radius: 8px;
    display: none;
    position: absolute;
    
      margin-top:0px;
      margin-left:40%;
    width: 220px;
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
    border-radius: 3px;
  }
  .white_content .title span {
      margin-left:6px;
    text-align: left;
    font-weight: normal;
    color:#fff;
    font-size:14px;
    font-family: "lucida Grande",Verdana,"Microsoft YaHei";
    
    
  }

  .white_content .title a {
      padding:1px;
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

  .white_content    .content {
    width: 100%;
    margin-top:20px;
    height: 90%;
    margin-left: 3px;
    overflow: auto;
  }
  .white_content .but{
     
    height: 10%;
    width: 100%;
    margin-left: -6px;
    margin-top:-50px;
  }
  .white_content .but button{
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
    font-family:"黑体";
  }
  .PTDatadiv{
     width:534px;
     height:400px;
     margin:0px auto;
     }
     .lineTH{
      display: table;
    width: 534px;
    }
    .e{
    width:110px;
    display: table-cell;
    border-collapse: separate;
    height:20px;
    float:left;
    
    border:1px solid #a0a0a0;
    margin:0px;
    padding:3px;
    }
    .p{
    width:110px;
    display: table-cell;
    border-collapse: separate;
    height:20px;
    
    float:left;
    border:1px solid #a0a0a0;
    margin:0px;
    padding:3px;}
    .d{
    width:110px;
    display: table-cell;
    border-collapse: separate;
    height:20px;
    float:left;
    
    border:1px solid #a0a0a0;
    margin:0px;
    padding:3px;}
    .ptsectorname{
    width:110px;
    display: table-cell;
    border-collapse: collapse;
    height:100%;
    float:left;
    margin:0px;
    padding:3px;
    border:1px solid #a0a0a0;
    }
    .ptkey{
    width:200px;
    display: table-cell;
    border-collapse: collapse;
    height:100%;
    float:left;
    margin:0px;
    padding:0px;
    border:1px solid #a0a0a0;
    margin:0px;
    padding:3px;
    }
    .ptvalue{
    width:200px;
    display: table-cell;
    border-collapse: collapse;
    height:100%;
    float:left;
    margin:0px;
    padding:0px;
    border:1px solid #a0a0a0;
    margin:0px;
    padding:3px;
    }
    .ptth{
    background:#d5d5d5;
    font-weight: bold;
    height:20px;
    }
    div.tablename{
      background-color:#0088cc; 
      background-image: linear-gradient(to bottom, #ddd 1%,#22aadd 3%, #0088cc 12%);
      background-image: webkit-linear-gradient(to bottom, #ddd 1%,#22aadd 3% #0088cc 12%);
      box-shadow: inset 0 1px 1px rgba(0,0,0,0.075);
      border-top-left-radius:7px;
      border-top-right-radius:7px;
      margin-left:-9px;
      margin-right:-9px;
      height:30px;
      line-height:30px;
      padding-left:9px;
      color:#fff
    }
</style>
</head>
	<% if(session.getAttribute("user")==null) 
	   response.sendRedirect("index.html");
	%>

<body>
	<input type="hidden" id="sector" value="<%=session.getAttribute("sector")%>"/>
	<div class="header">
		<div class="sectorname" id="namesc"></div>
		<div class="sectorUser"><%=session.getAttribute("user") %></div>
    <div class="sectorMission">当前任务：<strong style="color:red">舰载机出航</strong></div>
		<div class="logout">退出</div>
		<div class="timeloginhad" id="nowtime">时间</div>
	</div>
	
	<div class="loading" id="loading">
		<div>
			<img src="res/img/loading.gif"/>
		</div>
	</div>
	<div id="light" class="white_content">
		<div class="title" id="title">
			<span>消息提示</span>
			<a href="javascript:" id="x">×</a>
		</div>
		<div class="content" id="content">
			收到来之模块的指令！
		</div>
		<div class="but"><button id="change">响应指令</button>
		</div>		
	</div>
	<div id="fade" class="black_overlay"></div>
	<div class="bodycontent">
    <div class="primary">
      <div class="mainfrom">
        <div class="line">
          <div class="xuhao">
            <div class="xh">1</div>
          </div>
          <div class="kj" style="font-weight:bold;text-align:center">
            <select id="YXBM" class="selec">
              <option value="1">选择接受指令模块</option>
            </select>
          </div>
          <div class="ts">接受指令模块</div>
        </div>
        <div class="line">
          <div class="xuhao">
            <div class="xh">2</div>
          </div>
          <div class="kj" style="font-weight:bold;text-align:center">
            <select id="ZL_From" class="selec">
              <option value="1">选择发送指令</option>
            </select>
          </div>
          <div class="ts">发送给选择模块的指令</div>
        </div>
        <div class="line">
          <div class="xuhao">
            <div class="xh">3</div>
          </div>
          <div class="kj" style="font-weight:bold;text-align:center">添加指令字段</div>
        </div>
        <div class="line table">
          <div class="table-line th">
            <div class="table-line-xuhao">序号</div>
            <div class="table-line-key">数据段名称</div>
            <div class="table-line-value">数据段值</div>
            <div class="table-line-cz">操作</div>
          </div>      
          <div class="table-line add">
            <div class="table-line-add">
              <div class="button">
                <button id="addline">添加数据</button>
              </div>
            </div>
          </div>
        </div>
        <div class="line">
          <div class="table-line-submit">
              <div class="button">
                <button id="submit">发送指令</button>
              </div>
            </div>
        </div>
      </div>
      <div id="messages" class="message">
      </div>
    </div>
    <div class="secondary">
    </div>      
  </div>
	<script src="res/script/bootstrap.js"></script>
</body>
</html>