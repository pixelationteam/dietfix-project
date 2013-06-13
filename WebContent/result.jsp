<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*, edu.stanford.nlp.trees.Tree, net.didion.jwnl.data.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dietfix Test Result</title>
</head>
<body>
	<h1 align="center">Result</h1>
	<table align="center" border="1" bordercolor="black" style="width: 500px; border-collapse: collapse; 
	border-spacing: 5px">
		<tr>
			<td>Token</td>
			<td>Lemma</td>
			<td>Tag</td>
		</tr>
		<%
			ArrayList<String> tag = (ArrayList<String>)request.getAttribute("tags");
			ArrayList<String> token = (ArrayList<String>)request.getAttribute("tokens");
			ArrayList<String> lemma = (ArrayList<String>)request.getAttribute("lemma");
			
			if(!tag.isEmpty()) {
				for(int i = 0; i < tag.size(); i++) {
					out.print("<tr>");
					out.print("<td>" + token.get(i) + "</td>");
					out.print("<td>" + lemma.get(i) + "</td>");
					out.print("<td>" + tag.get(i) + "</td>");
					out.print("</tr>");
				}
			}
			
		%>
	</table>
	<br />
	<table align="center" border="1" bordercolor="black" style="border-collapse: collapse; 
	border-spacing: 5px">
		<tr>
			<td>User ID</td>
			<td>Username</td>
			<td>Password</td>
			<td>Salt</td>
			<td>First Name</td>
			<td>Middle Name</td>
			<td>Last Name</td>
			<td>Date Created</td>
			<td>Date Modified</td>
		</tr>
		<%
			ArrayList<String> tableData = new ArrayList<String>();
			tableData = (ArrayList<String>)request.getAttribute("sqlResult");
			
			Iterator<String> i = tableData.iterator();
			
			while(i.hasNext()) {
				out.print("<tr>");
				out.print("<td>" + i.next() + "</td>");
				out.print("<td>" + i.next() + "</td>");
				out.print("<td>" + i.next() + "</td>");
				out.print("<td>" + i.next() + "</td>");
				out.print("<td>" + i.next() + "</td>");
				out.print("<td>" + i.next() + "</td>");
				out.print("<td>" + i.next() + "</td>");
				out.print("<td>" + i.next() + "</td>");
				out.print("<td>" + i.next() + "</td>");
				out.print("</tr>");
			}
		%>
	</table>
	<%
		String path = request.getAttribute("path").toString();
		try{
			Tree parseTree = (Tree)request.getAttribute("tree");
			
			if(!parseTree.isEmpty()) {
				out.print(parseTree.pennString());
			}
				
		}catch(Exception e) {
			out.print("<p>" + e.toString() + "</p>");
		}
		
		out.print("<p>" + path + "</p>");
		
		
		ArrayList<Synset> synonyms = new ArrayList<Synset>();
		synonyms = (ArrayList<Synset>)request.getAttribute("word");
		
		if(!synonyms.isEmpty()) {
			Iterator<Synset> synset = synonyms.iterator();
			
			while(synset.hasNext()) {
				Synset set = synset.next();
			}
		}
		
		int depth = Integer.parseInt(request.getAttribute("depth").toString());
		out.print(depth);
	%>
	<a href="index.html">Click me!</a>
</body>
</html>