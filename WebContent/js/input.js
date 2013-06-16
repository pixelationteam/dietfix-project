
$(document).ready(function(){
	$("#add-tag").click(function(){
		var newrow = $("<tr>");
		var tagcell = $("<td class='tag' contenteditable>");
		var depcell = $("<td class='depth' contenteditable>");
		
		$(newrow).append(tagcell,depcell);
		$("#tag-table").append(newrow);
		
		setTimeout(function(){$(tagcell).focus();},100);
		
		return false;
	});
	
	$("#submit-msg").click(function(){
		var string = $("#string-text").val();
		var sel = $("#sel-type").val();
		var sent = $("#sent-type").val();
		var tags = [];
		var depths = [];
		$(".tag").each(function(){
			tags.push($(this).html());
		});
		
		$(".depth").each(function(){
			depths.push($(this).html());
		});
		 $.post("input.jsp", { "string":string, type: sel,senttype:sent ,'tags[]': tags,'depths[]':depths},
				  function(data){
			 		if(data==""){
			 			alert("Message has been added.");
			 		}
			 		else{
			 			alert(data);
			 		}
				  });
		
		
	});
});