<!DOCTYPE html>
<html>
<head>
    <link href="css/css_page.css" rel="stylesheet" type="text/css" media="screen" />
    <title>Register</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
</head>
<body>
<?php

include ('db.php');
$dc = new DatabaseConnector();
$con = $dc->getConnection();

if (isset($_POST['username'])){
    $sql = "INSERT INTO `user`(`name`, `age`, `username`, `password`) VALUES ('$_POST[name]', '$_POST[age]', '$_POST[username]', '$_POST[password]')";
    $result = mysqli_query($con,$sql)  or die(mysqli_error());
    if($result){
        echo "<div class='form'><h3>You are registered successfully.</h3><br/>Click here to <a href='index.php'>Login</a></div>";
    }else{print "passwords or username doesnt match";}

}else{
    ?>

<!-- REGISTRATION FORM -->
<div id="leftform" style="width:50%;float:center;margin:0 auto;">
<div class="text-center" style="padding:50px 0">
	<div class="logo">Register</div>
	<!-- Main Form -->
	<div class="login-form-1">
		<form id="register-form" class="text-left" method="post">
			<div class="login-form-main-message"></div>
			<div class="main-login-form">
				<div class="login-group">
					<div class="form-group">
						<label for="reg_username" class="sr-only">Username</label>
						<input type="text" class="form-control" id="reg_username" name="username" placeholder="username">
					</div>
					<div class="form-group">
						<label for="reg_password" class="sr-only">Password</label>
						<input type="password" class="form-control" id="reg_password" name="password" placeholder="password">
					</div>

					<div class="form-group">
						<label for="reg_fullname" class="sr-only">Full Name</label>
						<input type="text" class="form-control" id="reg_fullname" name="name" placeholder="full name">
					</div>
                                        <div class="form-group">
						<label for="reg_fullname" class="sr-only">Age</label>
						<input type="number" class="form-control" id="reg_fullname" name="age" placeholder="Age">
					</div>


				</div>
				<button type="submit" class="login-button"><i class="fa fa-chevron-right"></i></button>
			</div>

		</form>
	</div>
	<!-- end:Main Form -->
</div>
</div>
</div>
<?php } ?>
</body>
</html>
