<?php 
	
include 'connect.php';
include 'functions.php';

$action = $_GET['action'];

$user = $_GET ['user'];

$my_id = $_SESSION ['user_id'];

if($action == 'send'){
	mysql_query("INSERT INTO frnd_req VALUES('', '$my_id', '$user')");
}
if ($action == 'accept'){
	mysql_query("DELETE FROM `frnd_req` WHERE `from`='$user' AND `to`='$my_id'");

	mysql_query("INSERT INTO friends VALUES ('','$user', '$my_id)");
}

if ($action == 'unfrnd'){
	mysql_query("DELETE FROM friends WHERE(user_id='$my_id' AND friend_id='$user') OR (user_id='$user' AND friend_id='$my_id')");
}

header('Location: profile,php?user='.$user);