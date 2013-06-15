<%@page import="pup.thesis.server.DietfixServer"%>
<%@page import="pup.thesis.util.ClientData"%>
<%@page import="pup.thesis.knowledgebase.expert.Experts"%>
<%@page import="pup.thesis.knowledgebase.KeyTagSet"%>
<%@page import="java.util.HashMap"%>
<%
	ClientData cdata = (ClientData)request.getAttribute("clientdata");

	String str = request.getParameter("string");
	if(str !=null){
		String exp =request.getParameter("sel"); 
		String tags = request.getParameter("tags");
		out.println(exp+":"+tags+":"+str);
		if(!DietfixServer.isRunning()){
	DietfixServer.start();
		}
		
		KeyTagSet kts = new KeyTagSet(tags);
	
		
		DietfixServer.getKBase().insertAnswer(cdata, Experts.valueOf(exp), str,kts , new HashMap<Integer,String>());
	}
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="" method="post" >
<input type="text" name="string" style="width:300px;" /><br/>
<input type="text" name="tags" style="width:300px;" /><br/>
<select name = "sel" >
	<%
	out.println("<option value = \""+Experts.DIETITIAN.toString()+ "\">"+Experts.DIETITIAN.toString()+"</option>");
	out.println("<option value = \""+Experts.FITNESS_INSTRUCTOR.toString()+ "\">"+Experts.FITNESS_INSTRUCTOR.toString()+"</option>");
	out.println("<option value = \""+Experts.NUTRITIONIST.toString()+ "\">"+Experts.NUTRITIONIST.toString()+"</option>");
	%>
</select>
<input type="submit" />
</form>
</body>
</html>