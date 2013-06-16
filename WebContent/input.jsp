<%@page import="pup.thesis.server.DietfixServer"%>
<%@page import="pup.thesis.util.ClientData"%>
<%@page import="pup.thesis.knowledgebase.expert.Experts"%>
<%@page import="pup.thesis.knowledgebase.KeyTagSet"%>
<%@page import="pup.thesis.knowledgebase.KeyTag"%>
<%@page import="pup.thesis.knowledgebase.SentType"%>
<%@page import="java.util.HashMap"%>
<%
	ClientData cdata = (ClientData) request.getAttribute("clientdata");

	String str = request.getParameter("string");
	if (str != null) {
		if(str.length()==0){
			out.println("Message is required.");
			return;
		}
		String exp = request.getParameter("type");
		String sent = request.getParameter("senttype");
		String[] tags = request.getParameterValues("tags[]");
		String[] depths = request.getParameterValues("depths[]");
		if (!DietfixServer.isRunning()) {
			DietfixServer.start();
		}
		
		
		if (tags==null||depths==null||tags.length!=depths.length||tags.length==0||depths.length==0) {
			out.println("Invalid tag/s");
			return;

		}
		KeyTagSet kts = new KeyTagSet();
		for(int i = 0;i<tags.length;i++){
			int dep;
			try{
				  dep = Integer.parseInt(depths[i]);
				  // is an integer!
				} catch (NumberFormatException e) {
				  // not an integer!
				  out.println("Invalid depth on index:"+i);
				  return;
				}
			kts.addKeyTag(new KeyTag(dep,tags[i]));
		}

		
		//out.println(sent);
		DietfixServer.getKBase().insertAnswer(cdata, Experts.valueOf(exp),SentType.valueOf(sent), str,kts , new HashMap<Integer,String>());
		return;
	}
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Response Input</title>
<link rel="stylesheet" type="text/css" href="css/general.css">
<link rel="stylesheet" type="text/css" href="css/input.css">
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/input.js" type="text/javascript"></script>
</head>
<body>
	<div id="main-div">
		<div class="header">
			<h1>DIETFIX Response Input</h1>
		</div>
		<div class="content">
				Expert:<select id="sel-type">
					<%
						out.println("<option value = \"" + Experts.DIETITIAN.toString()
								+ "\">" + Experts.DIETITIAN.toString() + "</option>");
						out.println("<option value = \""
								+ Experts.FITNESS_INSTRUCTOR.toString() + "\">"
								+ Experts.FITNESS_INSTRUCTOR.toString() + "</option>");
						out.println("<option value = \"" + Experts.NUTRITIONIST.toString()
								+ "\">" + Experts.NUTRITIONIST.toString() + "</option>");
					%>
				</select>Message Type:<select id="sent-type" ><option value="ADVICE">ADVICE</option><option value="INFORMATION">INFORMATION</option></select><br /> Message: <input type="text" id="string-text"
					style="width: 300px;" />
				<table id="tag-table">
					<tr>
						<th>Tag</th>
						<th>Depth</th>
					</tr>
				</table>
				<button id="add-tag">Add Tag</button>
				<input id="submit-msg" type="submit" style="float: right" />
		</div>
	</div>
</body>
</html>