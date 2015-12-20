<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Login</title>
    <link rel="stylesheet" href="css/css_page.css" />
</head>
<body>
<?php

include ('db.php');
 $dc = new DatabaseConnector();
 $con = $dc->getConnection();

if (isset($_POST['username'])){
    $username = $_POST['username'];
    $password = $_POST['password'];
    $username = stripslashes($username);
    $username = mysqli_real_escape_string($con,$username);
    $password = stripslashes($password);
    $password = mysqli_real_escape_string($con,$password);
    
    $query = "SELECT * FROM user WHERE username='$username' and password='$password'";
    $result = mysqli_query($con,$query) or die(mysqli_error());
    $rows = mysqli_num_rows($result);

    if($rows==1){
        $_SESSION['username'] = $username;
        header("Location: user.html");
    }else{
        echo "<div class='form'><h3>Username/password is incorrect.</h3><br/>Click here to <a href='index.php'>Login</a></div>";
    }
}else{
    ?>

<!-- LOGIN FORM -->
<div id="rightform" style="float:center;width:50%;margin:0 auto;">
<div class="text-center" style="padding:50px 0">
	<div class="logo">Login</div>
	<!-- Main Form -->
	<div class="login-form-1">
		<form id="login-form" class="text-left" method="post">
			<div class="login-form-main-message"></div>
			<div class="main-login-form">
				<div class="login-group">
					<div class="form-group">
						<label for="lg_username" class="sr-only">Username</label>
						<input type="text" class="form-control" id="lg_username" name="username" placeholder="username">
					</div>
					<div class="form-group">
						<label for="lg_password" class="sr-only">Password</label>
						<input type="password" class="form-control" id="lg_password" name="password" placeholder="password">
					</div>
				</div>
				<button type="submit" class="login-button" name="submit"><i class="fa fa-chevron-right"></i></button>
			</div>
		</form>
	</div>
	<!-- end:Main Form -->
</div>
        <a href="registerf.php">Sign Up Here</a>
</div>
    



<?php } ?>

</body>
</html>	