<?php 
	$con= mysqli_connect("mysql.hostinger.co.uk","u796674379_ch","svoitX66aX","u796674379_tl");
	
	$password = $_POST["password"];
	$username = $_POST["username"];
	
	$statement = mysqli_prepare ($con, "SELECT * FROM user WHERE username = ? AND password = ? ");
	mysqli_stmt_bind_param($statement, "ss", $username, $password);
	mysqli_stmt_execute($statement);
	
	mysqli_stmt_store_result($statement);
	mysqli_stmt_bind_result($statement, $user_id,$name,$age,$username,$password);
	
	$user = array();
	
	while(mysqli_stmt_fetch($statement)){
		$user["name"]=$name;
		$user["age"]=$age;
		$user["username"]=$username;
		$user["password"]=$password;
	}
	
	echo json_encode($user);
		
	mysqli_stmt_close($statement);
	
	mysqli_close($con);
	
?>
	