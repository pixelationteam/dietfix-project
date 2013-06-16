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

function validate(form) {
	var inputter = form.elements["input"];
	if (inputter.value === "") {
		alert("Invalid input.");
	} else {
		addMessage("USER", inputter.value);
		var msg = addMessage("DIETFIX");
		var inpform = $("#input-form");
		$(inpform).css("visibility","hidden");
		$(inputter).val("");
		$.post('api', $(form).serialize(), function(data) {
			$(msg).html(data);
			$(inpform).css("visibility","visible");
		});
	}
	return false;
}