<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% String WsUrl = getServletContext().getInitParameter("WsUrl"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name='viewport' content='minimum-scale=1.0, initial-scale=1.0,
	width=device-width, maximum-scale=1.0, user-scalable=no'/>
<title>single-room-chat</title>
<link rel="stylesheet" type="text/css" href="content/styles/site.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script src="js/jquerysession.js"></script>
<script type="text/javascript" src="scripts/chatroom.js"></script>
<script type="text/javascript">

	//session
	var obj = $.session.get("roll");
	if(typeof obj == "undefined") {
		window.location="http://localhost:8090/Student/login.html";
	}
	var wsUri = '<%=WsUrl%>';
	var proxy = CreateProxy(wsUri);
	
	document.addEventListener("DOMContentLoaded", function(event) {
		console.log(document.getElementById('loginPanel'));
		proxy.initiate({
			loginPanel: document.getElementById('loginPanel'),
			msgPanel: document.getElementById('msgPanel'),
			txtMsg: document.getElementById('txtMsg'),
			txtLogin: document.getElementById('txtLogin'),
			msgContainer: document.getElementById('msgContainer')
		});
	});

</script>
</head>
<body>
<div id="container">
	<div id="loginPanel">
		<div id="infoLabel">Enter a display name for chat room</div>
		<div style="padding: 10px;">
			<input id="txtLogin" type="text" class="loginInput"
				onkeyup="proxy.login_keyup(event)" />
			<button type="button" class="btn btn-success loginInput" onclick="proxy.login()">Enter Chat</button>
		</div>
	</div>
	<div id="msgPanel" style="display: none">
		<div id="msgContainer" style="overflow: auto;"></div>
		<div  id="msgController">
			<div class="col-md-6 form-group shadow-textarea">
			<textarea class="form-control z-depth-1" id="txtMsg" 
				title="Enter to send message" 
				onkeyup="proxy.sendMessage_keyup(event)"
				 placeholder="Write something here..."></textarea>
			</div>
			<button class="btn btn-primary" style="height: 30px; width: 100px" type="button"
				onclick="proxy.logout()">Logout</button>
		</div>
	</div>
	
</div>
</body>
</html>