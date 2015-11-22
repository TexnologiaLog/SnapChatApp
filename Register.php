<?php
/**
 * Created by PhpStorm.
 * User: NIKOS
 */
 ?>
<!DOCTYPE html>
<html>
<head>
<link href="css_page.css" rel="stylesheet" type="text/css" />	
<title>Register Page</title>
</head>
<body>
<div id="leftform" style="width:50%;float:left;margin:0 auto;">
<div class="text-center" style="padding:50px 0">
	<div class="logo">Register</div>
	<div class="login-form-1">
		<form method="post" id="register-form" class="text-left">
			<div class="login-form-main-message"></div>
			<div class="main-login-form">
				<div class="login-group">
					<div class="form-group">
						<label for="reg_username" class="sr-only">Username</label>
						<input type="text" class="form-control" id="reg_username" name="reg_username" placeholder="username">
					</div>
					<div class="form-group">
						<label for="reg_password" class="sr-only">Password</label>
						<input type="password" class="form-control" id="reg_password" name="reg_password" placeholder="password">
					</div>
			
					<div class="form-group">
						<label for="reg_age" class="sr-only">Age</label>
						<input type="number" class="form-control" id="reg_age" name="reg_age" placeholder="age">
					</div>
					<div class="form-group">
						<label for="reg_fullname" class="sr-only">Full Name</label>
						<input type="text" class="form-control" id="reg_fullname" name="reg_fullname" placeholder="full name">
					</div>
					
					
					<!-- <div class="form-group login-group-checkbox">
						<input type="checkbox" class="" id="reg_agree" name="reg_agree">
						<label for="reg_agree">i agree with <a href="#">terms</a></label>
					</div>
					-->
				</div>
				<button type="submit" class="login-button"><i class="fa fa-chevron-right"></i></button>
			</div>
			
		</form>
	</div>
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
