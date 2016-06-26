// JavaScript Document
$(document).ready(function(){		
	$(".btn-primary").bind("click",function(){
		var pt = $(this).parent().parent().prev().text();
		var code = get_SectorName(pt);
		getSectorMSG(code);
	});
});


function get_SectorName(code){		
	if(code=="编队作战指挥系统")
		return "A";
	if(code=="本舰作战指挥系统")
		return "B";
	if(code=="航空兵作战指挥系统")
		return "C";
	if(code=="航空管制系统")
		return "D";
	if(code=="综合导航系统")
		return "E";
	if(code=="综合气象水文系统")
		return "F";
	if(code=="航保指挥管理系统")
		return "G";
	if(code=="航保起飞与着舰引导系统")
		return "H";  
}

function getSectorMSG(fromsector){
	 	$.ajax({
			type : "POST",
			url : "GetMSGOfLibBYSector.action",
			dataType : "json",
			contentType : "application/x-www-form-urlencoded; charset=utf-8",
			data : {
				FromSectorName : fromsector
			},
			success : function(data) {
				// var t=JSON.stringify(data);
				// var j=;
				//
				if(data!=null){	
					var D = eval(data);
					var html="";
			    for(var i=0;i<D.length;i++){
			    	var count=D[i].body.length;
			    	var headhtml="<table class='table table-bordered table-hover'><col width='220px'/><thead><tr><th colspan=2 style='padding-top:2px;padding-bottom:2px;' class='no-border'><div class='tablename'>"+D[i].tablename+"</div></th></tr></thead>";
			    	var tempstr="<tbody>";
			      for(var j=0;j<D[i].body.length;j++){
		        	var str="<tr><td>"+D[i].body[j].str+"</td>"+"<td>"+D[i].body[j].value+"</td></tr>";
		        	tempstr=tempstr+str; 	
		        }
			      var endstr=" </tbody></table>";
			      tempstr=headhtml+tempstr+endstr;
			      html=html+tempstr								    	
			    }
			    $(".modal-body").html(html);         
				}
			}
		});						 
 	}