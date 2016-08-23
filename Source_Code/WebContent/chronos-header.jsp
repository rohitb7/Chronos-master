<%@page import="coen275.chronos.project.User"%>
	<div class="header">
		<a href="/chronos/index.jsp">
		<img src="public/main-logo.png" alt="chronos-Logo" class="img-logo">
		<h3 class="site-name">CHRONOS</h3>
		</a>
		<span class="pull-right" style="height: 100%; font-size:medium; margin-top:0.5%;color: white;font-weight:bold;padding-right:1%;">
			<%if (session.getAttribute("user")!=null) { %>
			Logged in: <%= ((User)session.getAttribute("user")).getUsername() %> <BR>
			<a href="logout" class="head-logout"> Logout </a>
			<%} %>
		</span>
	</div>
