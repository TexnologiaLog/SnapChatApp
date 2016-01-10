<?php 
	
include ('connect.php');
 $dc = new DatabaseConnector();
 $con = $dc->getConnection();

include 'functions.php';

$action = $_GET['action'];

$user = $_GET ['user'];

$my_id = $_SESSION ['user_id'];

if($action == 'send'){
	mysqli_query($con,"INSERT INTO frnd_req VALUES('', '$my_id', '$user')");
}
if ($action == 'accept'){
	mysqli_query($con,"DELETE FROM `frnd_req` WHERE `from`='$user' AND `to`='$my_id'");

	mysqli_query($con,"INSERT INTO friends VALUES ('','$user', '$my_id)");
}

if ($action == 'unfrnd'){
	mysqli_query($con,"DELETE FROM friends WHERE(user_id='$my_id' AND friend_id='$user') OR (user_id='$user' AND friend_id='$my_id')");
}

header('Location: profile,php?user='.$user);