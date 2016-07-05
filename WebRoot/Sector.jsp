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
  <link rel="stylesheet" type="text/css" href="res/css/Sector.css">
  <script src="res/script/jquery-1.9.1.js"></script>
  <script src="res/script/loaddata.js"></script>
  <script src="res/script/time.js"></script>
  <script src="res/script/header.js"></script>
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
	
	<!--<div class="loading" id="loading">
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
			收到来自模块的指令！
		</div>
		<div class="but"><button id="change">响应指令</button>
		</div>		
	</div>
	<div id="fade" class="black_overlay"></div>-->
	<div class="bodycontent">
    <div class="primary">
      <div class="mainfrom">
        <div class="line">
          <div class="xuhao">
            <div class="xh">1</div>
          </div>
          <div class="kj" style="font-weight:bold;text-align:center">
            <select id="YXBM" class="selec">
              <option value="1">选择接收指令模块</option>
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
          <div class="ts">发送指令</div>
        </div>
        <div class="line">
          <div class="xuhao">
            <div class="xh">3</div>
          </div>
          <div class="kj" style="font-weight:bold;text-align:center">添加指令字段</div>
        </div>
        <div class="line tableform">
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
          <button type="button" id="submit" class="btn btn-primary center-block" data-loading-text="发送中...">发送指令</button>
        </div>
      </div>
      <div id="messages" class="message">
      </div>
    </div>
    <div class="secondary">
    </div>      
  </div>

  <div class="modal fade" id="myModal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="top:30%;">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
            &times;
          </button>
          <h4 class="modal-title" id="myModalLabel">
            消息提示
          </h4>
        </div>
        <div class="modal-body" style="text-align:center">
          <h4><strong>指令发送成功</strong></h4>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-primary" data-dismiss="modal">
            确定
          </button>
        </div>
      </div>
    </div>
  </div>
  <div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="top:30%;">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
            &times;
          </button>
          <h4 class="modal-title" id="myModalLabel">
            消息提示
          </h4>
        </div>
        <div class="modal-body" style="text-align:center">
          
        </div>
        <div class="modal-footer">
          <button type="button" id="change" class="btn btn-primary" data-dismiss="modal">
            响应指令
          </button>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal -->
  </div>

	<script src="res/script/bootstrap.js"></script>
</body>
</html>