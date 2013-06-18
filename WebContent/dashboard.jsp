<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    
    %>
<html>
	<head>
		<title>Dashboard</title>
		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" href="css/dashboard.css">
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
		<script type="text/javascript" src="js/bootstrap.min.js"></script>
		<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
		<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
	</head>

	<body>
		<div id="container" class="row-fluid">
			<nav class="span2 tabbable tabs-left tabsDash">
				<div id="userPic">
					<img src="img/profilepic.jpg">	
				</div>

				<ul class="nav tab-options">
					<a href="#"><li class="selected"><i class="icon-search icon-home"></i>Dashboard</li></a>
					<a href="#"><li><i class="icon-search icon-comment "></i>Consult Expert</li></a>
					<a href="#"><li><i class="icon-search icon-book "></i>Diet Plan</li></a>
					<a href="#"><li><i class="icon-search icon-user "></i>Profile</li></a>
					<a href="#"><li><i class="icon-search icon-calendar "></i>Schedule</li></a>
					<a href="#"><li><i class="icon-search icon-off"></i>Log-out</li></a>
				</ul>
			</nav>
			<div class="span10 main-section">
				<article>
					<section id="progress-section" class="form-style">
						<h2>Progress</h2>
						<div class="section-content">
							<p>asdasd</p>
						</div>
					</section>
					<div class="row-fluid">
						<section id="bmi-section" class="span4 form-style">
							<h2 >BMI</h2>
							<div class="section-content">
								<p>asdasd</p>
							</div>
						</section>
						<section id="schedule-section" class="span6 form-style">
							<h2>Schedule</h2>
							<div class="section-content">
								<p>asdasd</p>
							</div>
						</section>
					</div>
				</article>
			</div>	
		</div>
		<script type="text/javascript" src="js/dashboard.js"></script>
	</body>
</html>