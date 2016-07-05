$(document).ready(function() {

	var sectorListcount=0;
	var Start_MSG="{'ActionType':'NextSector','FromSector':'A','FromSector_I':'2'}";
	
  function get_I_MapName(I){ 
   	/*
     指令编号 1-30 对应的 前台 显示的 中文 信息
   	*/
   	// test
	   
	   if(I=="1")
		   return "受领上级任务";
	   if(I=="2")
		   return "编队系统预先号令";
	   if(I=="3")
		   return "收集下级的状态信息和建议";
	   if(I=="4")
		   return "收集下级的状态信息和建议";
	   if(I=="5")
		   return "收集下级的状态信息和建议";
	   if(I=="6")
		   return "编队系统作战计划";
	   if(I=="7")
		   return "接受下级的计划";
	   if(I=="8")
		   return "接受航保、引导系统计划";
	   if(I=="9")
		   return "接受航保、引导系统计划";
	   if(I=="10")
		   return "接受本舰、航空兵系统计划";
	   if(I=="11")
		   return "接受下级计划";
	   if(I=="12")
		   return "接受导航、气象、平台系统计划";
	   if(I=="13")
		   return "接受导航、气象、平台系统计划";
	   if(I=="14")
		   return "接受导航、气象、平台系统计划";
	   if(I=="15")
		   return "接受编队系统命令";
	   if(I=="16")
		   return "接受航空兵系统指令";
	   if(I=="17")
		   return "接受本舰系统指令";
	   if(I=="18")
		   return "接受导航、气象信息";
	   if(I=="19")
		   return "接受导航、气象信息";
	   if(I=="20")
		   return "下达命令";
	   if(I=="21")
		   return "接受航保指挥管理系统命令";
	   if(I=="22")
		   return "接受起飞舰载机";
	   if(I=="23")
		   return "接受移交过来的舰载机";
	   if(I=="24")
		   return "接受航空兵系统请求返航";
	   if(I=="25")
		   return "接受编队系统信息";
	   if(I=="26")
		   return "接受返航信息";
	   if(I=="27")
		   return "接受航空兵系统移交信息";
	   if(I=="28")
		   return "接受本舰信息";
	   if(I=="29")
		   return "接受舰载机";
	   if(I=="30")
		   return "接受舰载机";
	   if(I=="31")
		   return "";
	   if(I=="32")
		   return "";	   
  }
  function get_SectorName(code){    //暂时没用到
		
		if(code=="A")
			return "编队作战指挥系统";
		if(code=="B")
			return "本舰作战指挥系统";
		if(code=="C")
			return "航空兵作战指挥系统";
		if(code=="D")
			return "航空管制系统";
		if(code=="E")
			return "综合导航系统";
		if(code=="F")
			return "综合气象水文系统";
		if(code=="G")
			return "航保指挥管理系统";
		if(code=="H")
			return "航保起飞与着舰引导系统";      
		return "监控系统";
	}
	//$("#MissionStart").bind("click",function(){
	//		 var str="<div class='linemsg' style='background:#000'><div style='line-height:80px;text-align:center;font-size:30px;color:red'>"+$("#MissionType").val()+"</div></div>";
	//   	    $("div[class='SectorMSG']").append(str);
	//		$(".ShowMissionType").css({"display":"block"});
	//		$(".ShowMissionTypeline").animate({height:"40px"});
	//		$("#DQRW").html($("#MissionType").val());
  // 开始 与后台Websocket 建立链接 并且发送初始请求消息
	AutoMissionWebSocket =new WebSocket(AutoMissionURL);
	AutoMissionWebSocket.onopen = function(event){
		onOpen(event);
		AutoMissionWebSocket.send(Start_MSG);
	};//连接成功时触发
		 
	AutoMissionWebSocket.onmessage = function(event) {
		onMessage(event)
	};//服务器返回数据时触发

	AutoMissionWebSocket.onerror = function(event) {
		onError(event);
	};//发生错误时触发
					  	 	   
	function sendMSG(MSG){
	  AutoMissionWebSocket.send(MSG);	   	     
	}
	     
	function CS(){// 复原所在
		var html="";
		var headhtml="<table class='table table-bordered table-hover table-striped'><thead><tr><th colspan=2>指令名称</th></tr></thead>";
		var tempstr="<tbody>"
		var endstr=" </tbody></table>";
		tempstr=headhtml+tempstr+endstr;
		html=html+tempstr
		$("#msgright").html(html);	//  $("#父窗口元素ID",window.parent.document)  操作父窗口对象    	 
	}

	function onOpen(event) {
		//		      document.getElementById('messages').innerHTML
		//		        += "<br/>"+getNowFormatDate()+ " "+$("#MissionType").val()+" "+'任务开始';
		//		    //  webSocket.send('hello');
		//		      $(".SectorItem").each(function(){
		//		    	   $(this).animate({ backgroundColor: "#6495ED",color:"#fff"});
		//		    	   document.getElementById('messages').innerHTML+= "<br/>"+getNowFormatDate()+" "+"<span style='color:red'>"+$(this).html()+"</span> 模块<span style='color:#4f8fcd'>上线</span>";
		//		    	   
		//		    	  
		//		      });
		console.log("成功建立Websocket链接");		   
	}
		 
	function onError(event) {
		//   document.getElementById('messages').innerHTML
		//  +=event.data;
		console.log(event.data);
	}
		 
	function sendNextOneMSG(Return_MSG){			 
		console.log("每次执行"+CanNextMSG);
		if(CanNextMSG){
			CanNextMSG=false;
			AutoMissionWebSocket.send(Return_MSG);
			console.log(Return_MSG);
			clearInterval(IScannextonemsgflag);
		}
	}//无用函数

	                                                                       //右侧表格显示
	function getfangzhengMSG(fromsector,fromI){
		$.ajax({
			type : "POST",
			url : "GetMSGOfLib.action",
			dataType : "json",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			data : {
				FromSectorName : fromsector,
				FromI:fromI
			},
			success : function(data) {
				if(data!=null){	
					var D = eval(data);
					var html="";
					for(var i=0;i<D.length;i++){
						var count=D[i].body.length;
						var headhtml="<table class='table table-bordered table-hover table-striped'><thead><tr><th colspan=2>"+D[i].tablename+"</th></tr></thead>";
						var tempstr="<tbody>"
						for(var j=0;j<D[i].body.length;j++){
						  var str="<tr><td style='width:40%;'>"+D[i].body[j].str+"</td>"+"<td style='width:60%;'>"+D[i].body[j].value+"</td></tr>";
						  tempstr=tempstr+str;						        	
						}						    	
						var endstr="</tbody></table>";
						tempstr=headhtml+tempstr+endstr;
						html=html+tempstr						    	
					}
					$("#msgright").html(html);
				}
			}
		});			 
	}

	function onMessage(event) {		    	
		var rs= eval(event.data);
		console.log(rs[0].actionType);
		actiontype=rs[0].actionType||rs[0].ActionType;
			     
		if(actiontype=="NextSector"){
			CS();
			var t=rs[0].time_InsertData;
			var InsertToken=rs[0].insertToken||rs[0].InsertToken;
			var from_sector=rs[0].from_Sector;
			var to_sector=rs[0].to_sector;
			var return_I=rs[0].return_I;
			var Return_MSG="{'ActionType':'NextSector','FromSector':'"+to_sector+"','FromSector_I':'"+return_I+"'}";
			var I=rs[0].i;
			sectorListcount++;

			$("#MSGItem").html("指令编号:"+I+" 含义:"+get_I_MapName(I));

			getfangzhengMSG(from_sector,I);
			PaintOneMSG(from_sector,to_sector,150,1,AutoMissionWebSocket,Return_MSG);// 最后一个为默认速度，Xcenter默认为200

			//右侧表格
			var str="<tr><td style='text-align:center;vertical-align:middle;'>"+sectorListcount+"</td><td>"+get_SectorName(from_sector)+"</td><td>"+get_SectorName(to_sector)+"</td><td>"+get_I_MapName(I)+"</td><td>"+t+"</td></tr>";
			$(".ZLtable").append(str);
			document.getElementById("showmsg").scrollTop=document.getElementById("showmsg").scrollHeight;

			console.log("scrollHeight:"+document.getElementById("showmsg").scrollHeight);
			/*$(".SectorMSG .linemsg").last().children(".fromsector").animate({height:"100%"},800);
			$(".SectorMSG .linemsg").last().children(".toSector").animate({height:"100%"},800,function(){
				$(".SectorMSG .linemsg").last().children(".SJL").children(".Iprogress").children(".allprocess").children(".nowprocess").animate({width:"100%"},1000,function(){
					$(this).parent().next(".triangle").animate({borderLeftColor:"#55b055"},function(){
					});	  			
				});
			});*/			    				    	 
		}

		if(actiontype=="End"){
			$("#myModal1").modal("show");
			//_scrollWidth = Math.max(document.body.scrollWidth,document.documentElement.scrollWidth);   //=================
			//_scrollHeight = Math.max(document.body.scrollHeight,document.documentElement.scrollHeight);//自动演示完成后弹
			//document.getElementById('MSGfade').style.width=_scrollWidth+"px";                          //出提示框
			//document.getElementById('MSGfade').style.height=_scrollHeight+"px";                        //=================
			//document.getElementById('MSGfade').style.display='block';
			//document.getElementById('MSGlight').style.display='block';
			$("#msgright").html("");
			$("#MSGItem").html("模拟任务完成");
			//$("#MSGfade").animate({opacity:"0.8"});
			//$("#MSGlight").animate({marginTop:"15%",width:"320px",height:"200px"});
			//$("#MSGcontent").html("<font color='red'>模拟任务完成</font>");
			//$("#MSGx").bind("click",function(){													
				//$("#MSGlight").animate({marginTop:"0px",height:"0px"},500,function(){
				//	$(this).css({display:"none"})
				//});
				//$("#MSGfade").animate({opacity:"0.1"},600,function(){
				//	$(this).css({display:"none"})
			//	});						   								
			//});				      
			//				      $(".SectorItem").each(function(){
			//				    	   $(this).animate({ backgroundColor: "#f8f8f8",color:"#000"});
			//				    	   document.getElementById('messages').innerHTML+= "<br/>"+getNowFormatDate()+" "+"<span style='color:red'>"+$(this).html()+"</span> 模块<span style='color:red'>下线</span>";
			//				    	   
			//				    	  
			//				      });
		}		    	 
	}
});
