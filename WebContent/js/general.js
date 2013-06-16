function preloader(x, y,bool,none){
	x = x ? x : 0;
	y = y ? y : 0;
	var p;
	var text = "Loading";
	if(none){
	text = "";
	}
	
	if(bool == "response"){
		p = "<div style='margin-left: "+x+"px; margin-top: "+y+"px;' id='preloader'><img src='images/103.gif' /><br />"+text+"</div>";
	}
	return p;
}