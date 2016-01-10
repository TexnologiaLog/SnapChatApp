<?php

include_once("Connect.php");

$user_id=urldecode($_POST['user_id']);
$friend_id=urldecode($_POST['friend_id']);



//$query="INSERT INTO friends VALUES(0,$user_id,$friend_id)";
$query="INSERT INTO friends VALUES(0,$user_id,$friend_id)";

$result=mysqli_query($dbc,$query)
or die('Error in query: $query'.mysqli_error($dbc));

echo 'User with user_id='.$user_id.' just added user with user_id='.$friend_id.' as his friend';


$query1="INSERT INTO friends VALUES(0,$friend_id,$user_id)";

$result1=mysqli_query($dbc,$query1)
or die('Error in query: $query1'.mysqli_error($dbc));

echo 'User with user_id='.$friend_id.' just added user with user_id='.$user_id.' as his friend';



?>