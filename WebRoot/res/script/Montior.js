$(document).ready(function() {	
	var sectorListcount=0;
	$(".header").animate({height:"50px"},function(){				
	});
	
	$(".bd").animate({width:"1800px",height:"90%"},function(){				
	});

	$(".SectorItem").animate({width:"160px",height:"60px"},function(){
		$(".SectorMSG").animate({width:"828px",height:"100%"},function(){			
			$(".message").animate({height:"100%"});
		});				
	});
	
	function get_SectorName(code){		
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

	var webSocket = new WebSocket('ws://127.0.0.1:8080/DataFlowShow/SectorControl');	 
	webSocket.onerror = function(event) {
		onError(event)
	};

	webSocket.onopen = function(event) {
		onOpen(event)
	};

	webSocket.onmessage = function(event) {
		onMessage(event)
	};
	 
	function onMessage(event) {	    		    	
		var rs= eval(event.data);
		console.log(rs[0].actionType);
		actiontype=rs[0].actionType||rs[0].ActionType;
		if(actiontype=="online"){
			var c= $("#"+rs[0].sector).html();
			console.log($("#"+rs[0].sector).html());
			c=c+" <br/>登录用户 :<font color='red'>"+rs[0].username+"</font><br/>登录时刻:<span style='color:red;font-size:10px'>"+rs[0].logintime+"</span>";
			$("#"+rs[0].sector).html(c);
			$("#"+rs[0].sector).animate({ backgroundColor: "#6495ED",color:"#fff",lineHeight:"20px"}, 600);

			console.log(c);
			document.getElementById('messages').innerHTML
			+= '<br />'+getNowFormatDate() +" "+"模块 <font color='red'>"+get_SectorName(rs[0].sector)+"</font> 状态 <font color='color'>"+rs[0].MSG+"</font>";
		}
		if(actiontype=="offline"){
			$("#"+rs[0].sector).animate({ backgroundColor: "#f8f8f8",color:"#000",lineHeight:"60px"}, 1200);
			document.getElementById(rs[0].sector).innerHTML=get_SectorName(rs[0].sector);
			document.getElementById('messages').innerHTML
			+= '<br />'+getNowFormatDate() +" "+"模块 <font color='red'>"+get_SectorName(rs[0].sector)+"</font> 状态 <font color='color'>"+rs[0].MSG+"</font>";		    	  
		}
		if(actiontype=="NextSector"){
			var t=rs[0].time_InsertData;
			var InsertToken=rs[0].insertToken||rs[0].InsertToken;
			var from_sector=rs[0].from_Sector;
			var to_sector=rs[0].to_sector;
			var I=rs[0].i;
			sectorListcount++;
			console.log(rs[0]);

			var str="<div class='linemsg'><div class='xuhao'><div class='xh'>"+sectorListcount+"</div></div><div class='time'>"+t+"</div><div class='fromsector'><div class='listsector'>"+get_SectorName(from_sector)+"</div></div><div class='SJL'><div class='I'>"+I+"</div><div class='Iprogress'><div class='allprocess'><div class='nowprocess'></div></div><div class='triangle'></div></div></div><div class='toSector'><div class='listsector'>"+get_SectorName(to_sector)+"</div></div><div class='showData' datafrom='"+InsertToken+"'><button class='showbutton' >查看指令数据</button></div></div>";
			$("div[class='SectorMSG']").append(str);

			$("#x").unbind("click");                        //#x和.showData查看指令数据页面
			$("#x").bind("click",function(){						
				$("div[class='table-line tr']").remove();
				$("#light").animate({marginTop:"0px",height:"0px"},function(){
					$(this).css({display:"none"})
				});
				$("#fade").animate({opacity:"0"},function(){
					$(this).css({display:"none"})
				});												
			});
			$(".showData").unbind("click");                                  
			$(".showData").bind("click",function(){  		    		  
				_scrollWidth = Math.max(document.body.scrollWidth,document.documentElement.scrollWidth);
				_scrollHeight = Math.max(document.body.scrollHeight,document.documentElement.scrollHeight);
				document.getElementById('fade').style.width=_scrollWidth+"px";
				document.getElementById('fade').style.height=_scrollHeight+"px";
				document.getElementById('fade').style.display='block';
				document.getElementById('light').style.display='block';
				$("#fade").animate({opacity:"0.8"});
				$("#light").animate({marginTop:"15%",height:"600px"});

				var Insert_token=$(this).attr("datafrom");
		    		 	// alert(Insert_token);

		    		 	$.ajax({
		    		 		type : "POST",
		    		 		url : "get_InsertDataItem.action",
		    		 		dataType : "json",
		    		 		contentType : "application/x-www-form-urlencoded; charset=utf-8",
		    		 		async:false,
		    		 		data : {
		    		 			InsertToken:Insert_token,
		    		 		},
		    		 		success : function(data) {
		    		 			var D = eval(data);
		    		 			for(var i=0;i<D.length;i++){
		    		 				var Dkey=D[i].IDataKey||D[i].iDataKey;
		    		 				var Dvalue=D[i].IDataValue||D[i].iDataValue;
		    		 				var DID=D[i].id;

		    		 				var divline="<div class='table-line tr'><div class='table-line-xuhao'></div><div class='table-line-key'><textarea class='text' name='key' ItemID='"+DID+"' placeholder='输入数据名称'>"+Dkey+"</textarea></div><div class='table-line-value'><textarea class='text' name='value' placeholder='输入相应的数据值'>"+Dvalue+"</textarea></div><div class='table-line-cz'><button class='cz_button'  ItemID='"+DID+"' >删除</button></div></div>";
		    		 				$("div[class='line table']").append(divline);
		    		 				$("div[class='line table']>div[class='table-line tr']:last").animate({height:"40px"});
		    		 				var index2=0;
										$(".table-line-xuhao").each(function(){//序号排列
											if(index2!=0)$(this).html(index2);
											index2++;
										});
										
										$(".cz_button").bind("click",function(){//删除某一行后重置序号
											
											var Delete_ID=$(this).attr("ItemID");
										//	alert(Delete_ID);
										var thisbutton=$(this);
										$.ajax({
											type : "POST",
											url : "Delete_IDataID.action",
											contentType : "application/x-www-form-urlencoded; charset=utf-8",
											
											data : {
												Delete_ID:Delete_ID,
											},
											beforeSend : function(XMLHttpRequest) {
												_scrollWidth = Math.max(document.body.scrollWidth,document.documentElement.scrollWidth);
												_scrollHeight = Math.max(document.body.scrollHeight,document.documentElement.scrollHeight);
												document.getElementById('loading').style.width=_scrollWidth+"px";
												document.getElementById('loading').style.height=_scrollHeight+"px";
												$("#loading").animate({marginTop:"0px"},0,function(){
													$(this).css({display:"block"})
												});
											},
											success : function(data) {
												$("#loading").animate({marginTop:"0px"},500,function(){
													$(this).css({display:"none"})
												});
												thisbutton.parent().parent().remove();
												var index=0;
												$(".table-line-xuhao").each(function(){
													if(index!=0)$(this).html(index);
													index++;
												});												
											}										
										});		
									});
								}							
							}		    			  
						});	  
					});

var ChangeID=[];
var ChangeKey=[];
var ChangeValue=[];
$("#MSGx").bind("click",function(){
	$("#MSGlight").animate({marginTop:"0px",height:"0px"},500,function(){
		$(this).css({display:"none"})
	});
	$("#MSGfade").animate({opacity:"0.1"},600,function(){
		$(this).css({display:"none"})
	});		
});
$("#updateData").bind("click",function(){						
							/*
						 	*保存修改 的操作
						 	* */
						 	var Changeindex=0;
						 	$("textarea[name='key']").each(function(){
						 		ChangeID[Changeindex]=$(this).attr("ItemID");
						 		ChangeKey[Changeindex]=$(this).val();
						 		ChangeValue[Changeindex]=$(this).parent().next().children("textarea").val();
						 		Changeindex++;	  
						 	});

						 	$.ajax({
						 		type : "POST",
						 		url : "Updata_IData.action",
						 		contentType : "application/x-www-form-urlencoded; charset=utf-8",
						 		traditional: true,
						 		data:{
						 			ChangeID:ChangeID,
						 			ChangeKey:ChangeKey,
						 			ChangeValue:ChangeValue,
						 		},
						 		beforeSend : function(XMLHttpRequest) {
						 			_scrollWidth = Math.max(document.body.scrollWidth,document.documentElement.scrollWidth);
						 			_scrollHeight = Math.max(document.body.scrollHeight,document.documentElement.scrollHeight);
						 			document.getElementById('loading').style.width=_scrollWidth+"px";
						 			document.getElementById('loading').style.height=_scrollHeight+"px";
						 			$("#loading").animate({marginTop:"0px"},0,function(){
						 				$(this).css({display:"block"})
						 			});
						 		},
						 		success : function(data) {
						 			$("#loading").animate({marginTop:"0px"},500,function(){
						 				$(this).css({display:"none"})
						 			});
						 			_scrollWidth = Math.max(document.body.scrollWidth,document.documentElement.scrollWidth);
						 			_scrollHeight = Math.max(document.body.scrollHeight,document.documentElement.scrollHeight);
						 			document.getElementById('MSGfade').style.width=_scrollWidth+"px";
						 			document.getElementById('MSGfade').style.height=_scrollHeight+"px";
						 			document.getElementById('MSGfade').style.display='block';
						 			document.getElementById('MSGlight').style.display='block';
						 			$("#MSGfade").animate({opacity:"0.8"});
						 			$("#MSGlight").animate({marginTop:"15%",width:"320px",height:"200px"});
						 			$("#MSGcontent").html("<font color='red'>数据修改成功</font>");									
						 		}						
						 	});		
});
		    	// 动画效果
		    	document.getElementById("showmsg").scrollTop=document.getElementById("showmsg").scrollHeight;
		    	$(".SectorMSG .linemsg").last().children(".fromsector").animate({height:"100%"},2000);
		    	$(".SectorMSG .linemsg").last().children(".toSector").animate({height:"100%"},2000,function(){
		    		$(".SectorMSG .linemsg").last().children(".SJL").children(".Iprogress").children(".allprocess").children(".nowprocess").animate({width:"100%"},4000,function(){  
		    			$(this).parent().next(".triangle").animate({borderLeftColor:"#55b055"});	
		    		});		    		  
		    	});	  
		}	    
	}

	function onOpen(event) {
		document.getElementById('messages').innerHTML
		= getNowFormatDate()+' 监控系统上线';
	}

	function onError(event) {
		alert(event.data);
	}

	function start() {
	  // webSocket.send('hello');
	  return false;
	}
});