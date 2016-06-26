$(document).ready(function() {
	$(".logout").bind("click", function() {
		
		sessionStorage.removeItem("countsecond");
		window.location.href = "index.html";
	});
	function notshow(){
		
			$("#mainframe").attr("src","");
			
		$("#leftnav").children("div").css({'display':'none'});
	}
	
	$("a[class='list-group-item padleft']").bind("click",function(){
		//alert(1);
		$("#mainframe").attr("src",$(this).attr("tohref"));
	});
	$("ul[class='nav navbar-nav']").children("li").children("a").bind("click",function(){
		notshow();
		$("#"+$(this).attr("toID")).css({'display':'block'});
	});
	
	
});