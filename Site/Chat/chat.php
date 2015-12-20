<?php
	require('core.inc.php');
	
	if(isset($_POST['send'])){
		if(send_msg($_POST['sender'], $_POST['message'])){
			echo 'Message Send.';
		}else{
			echo 'Message Failed to sent.';
		}
	}
?>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>SnapChat Chat</title>
		
		<link type="text/css" rel="stylesheet" href="css/chat.css"/>
	</head>
	<body>
	
		<div id="input">
			<form action="chat.php" method="post">
				<label>Enter Name:<input type="text" name="sender"/></label>
				<label>Enter Message:<input type="text" name="message"/></label><br />
				<input type="submit" name="send" value="Send Message"/>
			</form>
		</div>
		
		<div id="messages">
		
		</div>
		
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/auto_chat.js"></script>


		
	</body>
</html>