<?php

include_once("Connect.php");

$user_id=urldecode($_POST['user_id']);



$query="DELETE FROM friends WHERE friend_id=$user_id";

$result=mysqli_query($dbc,$query)
or die('Error in query: $query'.mysqli_error($dbc));



echo 'User with user_id:'.$user_id.' has deleted a friend';

$query1="DELETE FROM friends WHERE user_id=$user_id";

$result=mysqli_query($dbc,$query1)
or die('Error in query: $query1'.mysqli_error($dbc));

echo 'User with user_id:'.$user_id.' has deleted himself';

?>