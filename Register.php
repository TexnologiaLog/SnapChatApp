<?php
	$con= mysqli_connect("mysql.hostinger.co.uk","u796674379_ch","svoitX66aX","u796674379_tl");

	$name = $_POST["name"];
	$age = $_POST["age"];
	$password = $_POST["password"];
	$username = $_POST["username"];
	
	$statement = mysqli_prepare($con,"INSERT INTO user(name,age,username,password) VALUES(?,?,?,?)");
	mysqli_stmt_bind_param($statement, "siss", $name, $age, $username, $password);
	mysqli_stmt_execute($statement);
	
	mysqli_stmt_close($statement);
	
	mysqli_close($con);
	
?>