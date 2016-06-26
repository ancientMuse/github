$(document).ready(function(){
	
	var usernname;
	var psw;
	var sector;

	
	
	$("#submit").bind("click",function(){
		 username=$("#username").val().trim();
		 psw=$("#psw").val().trim();
		 sector=$("#sector").val();
		// alert(sector);
		 $.ajax({
				type : "POST",
				url : "login.action",
				data: {
					username:username, 
					psw:psw,
					sector:sector
					},
				success : function(data,textStatus) {
					//alert(data);
					if(textStatus=="success")
						window.location.href=data;
					else alert("输入信息有误请重新输入!");
				
			}	
	});
	});
	$("#reset").bind("click",function(){
		 $("#username").val("");
		 $("#psw").val("");
		 $("#sector").val("");	
	});	

});