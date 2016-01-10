<?php
	include_once("Connect.php");
	$name = $_POST["username"];
	$image = $_POST["image"];
	$file_path = 'upload/'.$name.'.jpg';
	//$absolute_path='http://192.168.1.4/android/'.$file_path;
	$absolute_path='http://projectdb.esy.es/Android/upload/'.$name.'.jpg';
	$decodedImage = base64_decode("$image");
	file_put_contents($file_path, $decodedImage);
	
	
	
	$query="UPDATE user SET photo='$absolute_path'WHERE username='$name' ";
	$result=mysqli_query($dbc,$query)
	or die('Error in query: $query'.mysqli_error($dbc));

echo 'done'
	
	

 ?>