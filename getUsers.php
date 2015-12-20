<?php
	$con= mysqli_connect("mysql.hostinger.co.uk","u796674379_ch","svoitX66aX","u796674379_tl");

	
	$statement = mysqli_prepare($con,"SELECT * FROM user");
	mysqli_stmt_execute($statement);
	
	echo ($statement);
	mysqli_stmt_close($statement);
	
	mysqli_close($con);
	
?>