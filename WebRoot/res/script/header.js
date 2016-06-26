$(document)
		.ready(
				function() {
					
					
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
					var sc = $("#sector").val();
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
					
					$("#namesc").html(get_SectorName(qs) + ",当前登录用户:");
					$(".logout").bind("click", function() {
						
						sessionStorage.removeItem("countsecond");
						window.location.href = "index.html";
					});
				}
				);