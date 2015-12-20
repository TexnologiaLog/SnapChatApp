<?php
	$con= mysqli_connect("mysql.hostinger.co.uk","u796674379_ch","svoitX66aX","u796674379_tl");

	$username = $_POST["username"];
	$latitude = $_POST["latitude"];
	$longitude = $_POST["longitude"];

	
	$statement = mysqli_prepare($con,"INSERT INTO gps(username,latitude,longitude) VALUES(?,?,?)");
	mysqli_stmt_bind_param($statement, "siss", $username, $latitude, $longitude);
	mysqli_stmt_execute($statement);
	
	mysqli_stmt_close($statement);
	
	mysqli_close($con);
	
?>