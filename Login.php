<?php
/**
 * Created by PhpStorm.
 * User: NIKOS
 */
 ?>
<html>
<head>
<link href="css_page.css" rel="stylesheet" type="text/css" media="screen" />	
<title>Login Page</title>
</head>
<body>
   <div id="rightform" style="float:right;width:50%;margin:0 auto;">
<div class="text-center" style="padding:50px 0">
	<div class="logo">Login</div>
	<div class="login-form-1">
		<form method="post" id="login-form"  action="checkLogin.php" class="text-left">
			<div class="login-form-main-message"></div>
			<div class="main-login-form">
				<div class="login-group">
					<div class="form-group">
						<label for="lg_username" class="sr-only">Username</label>
						<input type="text" class="form-control" id="lg_username" name="lg_username" placeholder="username">
					</div>
					<div class="form-group">
						<label for="lg_password" class="sr-only">Password</label>
						<input type="password" class="form-control" id="lg_password" name="lg_password" placeholder="password">
					</div>
					<div class="form-group login-group-checkbox">
						<input type="checkbox" id="lg_remember" name="lg_remember">
						<label for="lg_remember">remember</label>
					</div>
				</div>
				<button type="submit" class="login-button"><i class="fa fa-chevron-right"></i></button>
			</div>
		</form>
	</div>
</div>
</div>
            <?php
            session_start();
           // $_SESSION['error_message'] = "Wrong Username or Password";
            if (isset($_SESSION['error_message']))
            {
                echo $_SESSION['error_message'] . "!";
                unset($_SESSION['error_message']);
            }
            ?>
</body>
</html>
