$(document).ready(function(){
	$("#submit-text").click(function(){
		validate();
	});
});

function addMessage(speaker, message) {
	var chatbox = $(".chatbox");
	var newmsg = $("<div class='msg'>");
	var spkr = $("<span class='speaker'>");
	var msg = $("<span class='message'>");
	switch(speaker){
	case "USER":
		$(newmsg).addClass("question");
		break;
	case "DIETFIX":
		$(newmsg).addClass("answer");
		break;
	}
	$(spkr).html(speaker);
	if(message){
		$(msg).html(message);
	}
	else{
		$(msg).html(preloader(5,3,"response",true));
	}
	$(newmsg).append(spkr, msg, $("<div class='msg-separator'>"));
	$(chatbox).append(newmsg);
	return msg;
}

function validate() {
	var inptext = $("#text-input");
	var string = $(inptext).val();
	var inpform = $("#inp-div");
	if (string === "") {
		alert("Invalid input.");
	} else {
		addMessage("USER", string);
		var msg = addMessage("DIETFIX");
		$(inpform).css("visibility","hidden");
		$(inptext).val("");
		$.post('api',{input:string}, function(data) {
			$(msg).html(data);
			$(inpform).css("visibility","visible");
		}).fail(function(){ 
			  // Handle error here
			$(msg).html("An error has occured.");
		});
	}
	return false;
}