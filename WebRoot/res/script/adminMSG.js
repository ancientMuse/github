$(document).ready(function() {
	// 消息管理对应的 js文件
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
	 	function get_I_MapName(I){ 
	 		/*		   
	      指令编号 1-30 对应的 前台 显示的 中文 信息
	  	*/
		   
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
			   return "接受导航、气象、台位系统计划";
		   if(I=="13")
			   return "接受导航、气象、台位系统计划";
		   if(I=="14")
			   return "接受导航、气象、台位系统计划";
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
	  
	 $.ajax({
			type : "POST",
			url : "get_Sector_I_NameMapping.action",
			dataType : "json",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			success : function(data) {
				// var t=JSON.stringify(data);
				// var j=;
				//
				var sectorlist=[];
				var sectorNolist=[];
				var sectorcount=[];
				var AllsectorIlist=[];
				var D = eval(data);
			    var indextemp=0;
			    sectorlist=D[0].sectorlist;
				sectorcount=D[0].sector_CountOF_Ilist;
				sectorNolist=D[0].NOlist;
				AllsectorIlist=D[0].ilist;
				IlistMINIndexArray=[];
				IlistMAXIndexArray=[];
				for(var i=0;i<sectorcount.length;i++){
					IlistMINIndexArray[i]=indextemp;
					indextemp=parseInt(sectorcount[i])+indextemp;
					IlistMAXIndexArray[i]=indextemp;
					
				}
				for(var i=0;i<sectorlist.length;i++){
					var count=parseInt(sectorcount[i])+1;
					var htmlSector="<tr><td rowspan='"+count+"'><p>"+get_SectorName(sectorlist[i])+"</p><p><button type='button' class='btn btn-primary' style='margin-left:3px'>全局编辑</button></p></td>";
					$("#bodytable").append(htmlSector);
					var htmlIlist="";
					for(var j=IlistMINIndexArray[i];j<IlistMAXIndexArray[i];j++){
						var htmlItemI=" <tr><td>"+AllsectorIlist[j]+"</td><td>"+get_I_MapName(sectorNolist[j])+"<td><p><button type='button' class='btn btn-primary ' style='margin-left:3px'>查看指令</button><button type='button' class='btn btn-success ' style='margin-left:3px'>编辑指令</button><button type='button' class='btn btn-danger ' style='margin-left:3px'>删除指令</button></p></td></tr>"
						htmlIlist=htmlIlist+htmlItemI;
					}
					$("#bodytable").append(htmlIlist);
					
				}
				
				
				
			}
			});
	
	
});