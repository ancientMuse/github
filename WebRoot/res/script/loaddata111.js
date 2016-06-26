$(document)
		.ready(
				function() {
					var sc = $("#sector").val();
					
				
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
						if(code=="I")
							return "平台";
						return "监控系统";
					}
					 var webSocket =new WebSocket('ws://127.0.0.1:8080/DataFlowShow/SectorControl');
					 
					    webSocket.onerror = function(event) {
					      onError(event)
					    };
					 
					    webSocket.onopen = function(event) {
					      onOpen(event)
					    };
					 
					    webSocket.onmessage = function(event) {
					      onMessage(event)
					    };
					     function sendMSG(MSG){
					     webSocket.send(MSG);
					     
					     
					     }
					    function onMessage(event) {
					      console.log(event);
					     
					      var rs= eval("(" + event.data + ")");
					      
					      
					      if(rs.ActionType=="Response")
					      {
					    	  $("#ZL_From").empty();
							   $("#YXBM").empty();
							   var newoption1=$("<option>").val(1).text("选着接受指令模块");
							   var newoption2=$("<option>").val(1).text("选着发送指令");
							   $("#YXBM").append(newoption1);
							   $("#ZL_From").append(newoption2);
							   canchange=true;
					    	  $("div[class='line table']").empty();
							  $("div[class='line table']").append("<div class='table-line th'><div class='table-line-xuhao'>序号</div><div class='table-line-key'>数据段名称</div><div class='table-line-value'>数据段值</div><div class='table-line-cz'>操作</div></div><div class='table-line add'><div class='table-line-add'><div class='button'><button id='addline'>添加数据</button></div></div></div>");
							  $("#addline").bind("click",function(){
									
									var div_add="<div class='table-line tr'><div class='table-line-xuhao'>"+line_number+"</div><div class='table-line-key'><textarea class='text' name='key' placeholder='输入数据名称'></textarea></div><div class='table-line-value'><textarea class='text' name='value' placeholder='输入相应的数据值'></textarea></div><div class='table-line-cz'><button class='cz_button'>删除</button></div></div>";
									$(this).parent().parent().parent().before(div_add);
									var index2=0;
									line_number++;
									$(".table-line-xuhao").each(function(){
										
									   console.log(index2);
										if(index2!=0)$(this).html(index2);
										index2++;
									});
									$(".cz_button").bind("click",function(){
										//console.log($(this));
										//alert($(this).parent().parent());
									$(this).parent().parent().remove();
									line_number--;
									var index=0;
									$(".table-line-xuhao").each(function(){
										
										 console.log(index);
										if(index!=0)$(this).html(index);
										index++;
									});
										
									});
									
								}); 
							  $
								.ajax({
									type : "POST",
									url : "get_I_sector.action",
									dataType : "json",
									contentType : "application/x-www-form-urlencoded; charset=utf-8",
									data : {
										sector : $("#sector").val().trim(),
									},
									success : function(data) {
										// var t=JSON.stringify(data);
										// var j=;
										//
										var D = eval(data);
										for ( var i = 0; i < D.length; i++) {
											I_sector[i] = [];
											I_sector[i][0] = D[i].Sector;
										    var code = D[i].Sector;
										    console.log(code);
											I_sector[i][1] = D[i].From_I.length;
											

													var option = $("<option>").val(code).text(get_SectorName(code));
															$("#YXBM").append(option);
													

											for ( var j = 0; j < D[i].From_I.length; j++) {
												I_sector[i][j + 2] = D[i].From_I[j];

											}

										}
									}
									});
							  document.getElementById('messages').innerHTML
					        += '<br />'+getNowFormatDate()+" "+ rs.MSG;
					      }
					      if(rs.ActionType=="NextSector"){
					      
					        var I=rs.FromSector_I;
					        var FromSector=rs.FromSector;
					        $.ajax({
													type : "POST",
													url : "NextSector.action",
													traditional:true,
													contentType : "application/x-www-form-urlencoded; charset=utf-8",
													data : {
														Fromsector:FromSector,
														ToSector:qs,
														FromSector_I:I
													},
													success : function(data,textStatus) {
														var medata =  eval("(" +data + ")");
														console.log(textStatus);
														var fromsector=medata.Source_FromSector;
														var Fromsector_I=medata.FromSector_I;
														if(medata.Status=="201")
														{  
														  $("div[class='line table']").empty();
														  $("div[class='line table']").append("<div class='table-line th'><div class='table-line-xuhao'>序号</div><div class='table-line-key'>数据段名称</div><div class='table-line-value'>数据段值</div><div class='table-line-cz'>操作</div></div><div class='table-line add'><div class='table-line-add'><div class='button'><button id='addline'>添加数据</button></div></div></div>");
															$("#addline").bind("click",function(){
																
																var div_add="<div class='table-line tr'><div class='table-line-xuhao'>"+line_number+"</div><div class='table-line-key'><textarea class='text' name='key' placeholder='输入数据名称'></textarea></div><div class='table-line-value'><textarea class='text' name='value' placeholder='输入相应的数据值'></textarea></div><div class='table-line-cz'><button class='cz_button'>删除</button></div></div>";
																$(this).parent().parent().parent().before(div_add);
																var index2=0;
																line_number++;
																$(".table-line-xuhao").each(function(){
																	
																   console.log(index2);
																	if(index2!=0)$(this).html(index2);
																	index2++;
																});
																$(".cz_button").bind("click",function(){
																	//console.log($(this));
																	//alert($(this).parent().parent());
																$(this).parent().parent().remove();
																line_number--;
																var index=0;
																$(".table-line-xuhao").each(function(){
																	
																	 console.log(index);
																	if(index!=0)$(this).html(index);
																	index++;
																});
																	
																});
																
															}); 
														  canchange=false;
														   $("#ZL_From").empty();
														   $("#YXBM").empty();
														   
														   var zloption=$("<option>").val(medata.Icode).text(medata.Icode);
														   $("#ZL_From").append(zloption);
														   
														 
														   
														   for(var i=0;i<medata.Sector.length;i++){
														   var option = $("<option>").val(medata.Sector[i]).text(get_SectorName(medata.Sector[i]));
														   $("#YXBM").append(option);
															
															}
														     _scrollWidth = Math.max(document.body.scrollWidth,document.documentElement.scrollWidth);
														   	  _scrollHeight = Math.max(document.body.scrollHeight,document.documentElement.scrollHeight);
														   	  document.getElementById('fade').style.width=_scrollWidth+"px";
															  document.getElementById('fade').style.height=_scrollHeight+"px";
														      document.getElementById('fade').style.display='block';
														      document.getElementById('light').style.display='block';
														      $(".but").css({display:"block"});
														      $("#title>span").html("消息提示");
														      $("#fade").animate({opacity:"0.8"});
														      $("#light").animate({marginTop:"15%",width:"220px",height:"200px"});
														      
														      $("#content").html("接受来自模块 <font color='red'>"+get_SectorName(fromsector)+"</font>的指令<font color='red'>"+Fromsector_I+"</font>");
														      $("#change").html("响应指令");
														      document.getElementById('messages').innerHTML+= '<br />'+getNowFormatDate()+" "+"接受来自模块 <font color='red'>"+get_SectorName(fromsector)+"</font>的指令<font color='red'>"+Fromsector_I+"</font>";
														}
														else{
															 $("#change").html("响应指令");
															 $("#content").html("接受来自模块 <font color='red'>"+get_SectorName(fromsector)+"</font>的指令<font color='red'>"+Fromsector_I+"</font>");
														   document.getElementById('messages').innerHTML+= '<br />'+getNowFormatDate()+" "+"接受来自模块 <font color='red'>"+get_SectorName(fromsector)+"</font>的指令<font color='red'>"+Fromsector_I+"</font>";
														   canchange=true;
														
														}
														
														
													}
												});
					      
					      }
					    
					    }
					 
					    function onOpen(event) {
					      document.getElementById('messages').innerHTML
					        = getNowFormatDate()+" "+'系统已上线';
					    //  webSocket.send('hello');
					    
					    }
					 
					    function onError(event) {
					       document.getElementById('messages').innerHTML
					        +=event.data;
					    }
					var line_number=1;
					var canchange=true;
					var qs;
					if (sc == "001")
						qs = "A";
					if (sc == "002")
						qs = "B";
					if (sc == "003")
						qs = "C";
					if (sc == "004")
						qs = "D";
					if (sc == "005")
						qs = "E";
					if (sc == "006")
						qs = "F";

					if (sc == "007")
						qs = "G";
					if (sc == "008")
						qs = "H";
					var I_sector = [];
					var Sector_ZM = [ "A", "B", "C", "D", "E", "F", "G", "H" ];
					// 编队作战指挥系统
					// 本舰作战指挥系统
					// 航空兵作战指挥系统
					// 航空管制系统
					// 综合导航系统
					// 综合气象水文系统
					// 航保指挥管理系统
					// 航保起飞与着舰引导系统
                   
					function get_i_index(sector) {

						for ( var i = 0; i < I_sector.length; i++) {

							if (sector == I_sector[i][0])
								return i;

						}

					}
					
					var key=[];
					var value=[];
					function keychange(){
						
						
						
					}
					
					$(window).resize(function() {
			           if( document.getElementById('fade').style.display=="block"){
			        	   _scrollWidth = Math.max(document.body.scrollWidth,document.documentElement.scrollWidth);
						   _scrollHeight = Math.max(document.body.scrollHeight,document.documentElement.scrollHeight);
						   document.getElementById('fade').style.width=_scrollWidth+"px";
						   document.getElementById('fade').style.height=_scrollHeight+"px";
			        	   
			        	   
			           }
			        });
					$("#submit").bind("click",function(){
						var key_value_index=0;
						if(line_number>1){
							$("textarea[name='key']").each(function(){
						
							key[key_value_index]=$(this).val();
							value[key_value_index]=$(this).parent().next().children().val();
							
							key_value_index++;
							console.log(key+":"+value);
							
						});
							$
							.ajax({
								type : "POST",
								url : "InsertData.action",
								traditional:true,
								
								data : {
									Fromsector:qs,
									ToSector:$("#YXBM").val(),
									FromSector_I:$("#ZL_From").val(),
									I_data_key:key,
									I_data_value:value	
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
								success : function(data,textStatus) {
									 $("#loading").animate({marginTop:"0px"},500,function(){
										 $(this).css({display:"none"})
									 });
									if(textStatus=="success")
									{
										// alert(data);
										 data=eval(data);
										 console.log("请求的insert Time"+data);
										// alert(data);
										  var JSON_WS="{'ActionType':'NextSector',"+"'ToSector':"+$("#YXBM").val()+",'FromSector':"+qs+",'FromSector_I':"+$("#ZL_From").val()+",'TimeInsertData':'"+data[0].TimeInsertData+"','InsertToken':'"+data[0].InsertToken+"'}";
										  sendMSG(JSON_WS);
										 _scrollWidth = Math.max(document.body.scrollWidth,document.documentElement.scrollWidth);
									   	  _scrollHeight = Math.max(document.body.scrollHeight,document.documentElement.scrollHeight);
									   	  document.getElementById('fade').style.width=_scrollWidth+"px";
										  document.getElementById('fade').style.height=_scrollHeight+"px";
									      document.getElementById('fade').style.display='block';
									      document.getElementById('light').style.display='block';
									      $("#title>span").html("消息提示");
									      $("#fade").animate({opacity:"0.8"});
									      $("#light").animate({marginTop:"15%",width:"220px",height:"200px"});
									      $("#content").html("指令发送成功！");
									      $(".but").css({display:"block"});
									      $("#change").html("确定");
									      
										$("div[class='table-line tr']").remove();
										line_number=1;
									}
									
									
								}
							});
							
							
							
						}
						
						
						
						});
					$("#change").bind("click",function(){
						//alert("ce");
						
						 $("#light").animate({marginTop:"0px",width:"220px",height:"0px"},500,function(){
							 $(this).css({display:"none"})
						 });
						 $("#fade").animate({opacity:"0.1"},600,function(){
							 $(this).css({display:"none"})
						 });
					   	
						
					});
					$("#x").bind("click",function(){
						//alert("ce");
						
						 $("#light").animate({marginTop:"0px",width:"220px",height:"0px"},500,function(){
							 $(this).css({display:"none"})
						 });
						 $("#fade").animate({opacity:"0.1"},600,function(){
							 $(this).css({display:"none"})
						 });
					   	
						
					});
					$("#addline").bind("click",function(){
					
						var div_add="<div class='table-line tr'><div class='table-line-xuhao'>"+line_number+"</div><div class='table-line-key'><textarea class='text' name='key' placeholder='输入数据名称'></textarea></div><div class='table-line-value'><textarea class='text' name='value' placeholder='输入相应的数据值'></textarea></div><div class='table-line-cz'><button class='cz_button'>删除</button></div></div>";
						$(this).parent().parent().parent().before(div_add);
						var index2=0;
						line_number++;
						$(".table-line-xuhao").each(function(){
							
						   console.log(index2);
							if(index2!=0)$(this).html(index2);
							index2++;
						});
						$(".cz_button").bind("click",function(){
							//console.log($(this));
							//alert($(this).parent().parent());
						$(this).parent().parent().remove();
						line_number--;
						var index=0;
						$(".table-line-xuhao").each(function(){
							
							 console.log(index);
							if(index!=0)$(this).html(index);
							index++;
						});
							
						});
						
					});
					
					
					$
							.ajax({
								type : "POST",
								url : "get_I_sector.action",
								dataType : "json",
								contentType : "application/x-www-form-urlencoded; charset=utf-8",
								data : {
									sector : $("#sector").val().trim(),
								},
								success : function(data) {
									// var t=JSON.stringify(data);
									// var j=;
									//
									var D = eval(data);
									for ( var i = 0; i < D.length; i++) {
										I_sector[i] = [];
										I_sector[i][0] = D[i].Sector;
									    var code = D[i].Sector;
									    console.log(code);
										I_sector[i][1] = D[i].From_I.length;
										

												var option = $("<option>").val(code).text(get_SectorName(code));
														$("#YXBM").append(option);
												

										for ( var j = 0; j < D[i].From_I.length; j++) {
											I_sector[i][j + 2] = D[i].From_I[j];

										}

									}
								}
								});
									$("#YXBM")
											.change(
													function() {
                                               var val=$(this).val();
                                               console.log("改变的值 ："+val);
                                           	$("div[class='line table']").css({display:"table"});
                                        	$("div[class='line']:eq(2)").css({display:"block"});
												if(canchange&&val!="I"){		
													$("#ZL_From").empty();

													
														index = get_i_index($(
																this).val());
														for ( var j = 0; j < I_sector[index][0 + 1]; j++) {
															var number=I_sector[index][2 + j];
															$
																	.ajax({
																		type : "POST",
																		url : "get_I_code.action",
																		contentType : "application/x-www-form-urlencoded; charset=utf-8",
																		data : {
																			i : I_sector[index][2 + j]
																		},
																		success : function(
																				data) {
																			data=eval(data);
																			console.log(data);
																			var ii=data[0].I;
																			var i_map=data[0].I_MAP;
																		

																			var option = $(
																					"<option>")
																					.val(
																							ii)
																					.text(
																							i_map);
																			$(
																					"#ZL_From")
																					.append(
																							option);
																		}
																	});//

														}

														
												}
												else if(val=="I"){
													//console.log("zhixingl ")
												 	$("div[class='line table']").css({display:"none"});
													$("div[class='line']:eq(2)").css({display:"none"});
													
													/*
													 发送 请求的地方* 
													 */
													$.ajax({
														type : "POST",
														url : "get_PTData.action",
														contentType : "application/x-www-form-urlencoded; charset=utf-8",
														success : function(data) {
															  data=eval(data);
															  _scrollWidth = Math.max(document.body.scrollWidth,document.documentElement.scrollWidth);
														   	  _scrollHeight = Math.max(document.body.scrollHeight,document.documentElement.scrollHeight);
														   	  document.getElementById('fade').style.width=_scrollWidth+"px";
															  document.getElementById('fade').style.height=_scrollHeight+"px";
														      document.getElementById('fade').style.display='block';
														      document.getElementById('light').style.display='block';
														      $("#fade").animate({opacity:"0.8"});
														      $("#light").animate({marginTop:"15%",width:"545px",height:"510px"});
														      $(".but").css({display:"none"});
														      $("#title>span").html("平台数据显示");
														      $("#content").html("");
														      var ptdiv="<div class='PTDatadiv'><div class='lineTH ptth'><div class='ptsectorname'>平台名称</div><div class='ptkey'>数据段名称</div><div class='ptvalue'>数据段值</div></div><div class='lineTH' id='e'><div class='e'>电力系统</div></div><div class='lineTH' id='d'><div class='d'>损管系统</div></div><div class='lineTH' id='p'><div class='p'>动力系统</div></div></div>"
															  
														      
														      $("#content").append(ptdiv);
														      var e_height=22*data[0].e.length+2;
														      $(".e").css({
														    	  "height":e_height+"px",
														    	  "line-height":e_height+"px"});
														      for(var i=0;i<data[0].e.length;i++){
														    	  var linediv="<div class='ptkey'>"+data[0].e[i].name+"</div><div class='ptvalue'>"+data[0].e[i].value+"</div>";
														    	  $("#e").append(linediv);
														    	  
														      }
														      var d_height=22*data[0].d.length+2;
														      $(".d").css({ 
														    	  "height":d_height+"px",
														    	  "line-height":d_height+"px"});
														      for(var i=0;i<data[0].d.length;i++){
														    	  var linediv="<div class='ptkey'>"+data[0].d[i].name+"</div><div class='ptvalue'>"+data[0].d[i].value+"</div>";
														    	  $("#d").append(linediv);
														    	  
														      }
														      var p_height=22*data[0].p.length+2;
														      $(".p").css({
														    	  "height":p_height+"px",
														    	  "line-height":p_height+"px"});
														      for(var i=0;i<data[0].p.length;i++){
														    	  var linediv="<div class='ptkey'>"+data[0].p[i].name+"</div><div class='ptvalue'>"+data[0].p[i].value+"</div>";
														    	  $("#p").append(linediv);
														    	  
														      }
														    	  console.log("平台e的长度："+data[0].e.length);
														    	  
														    		$("#x").bind("click",function(){
																		//alert("ce");
																		
																		 $("#light").animate({marginTop:"0px",width:"220px",height:"0px"},500,function(){
																			 $(this).css({display:"none"})
																		 });
																		 $("#fade").animate({opacity:"0.1"},600,function(){
																			 $(this).css({display:"none"})
																		 });
																	   	
																		
																	});
														}
													});//
													
													
													
												}
													});

								

								
					
							
				});
