<?php

include_once("Connect.php");

$user_id=urldecode($_POST['user_id']);



$query="SELECT user.user_id,user.name,user.age,user.username,user.password,user.photo from user,friends WHERE user.user_id=friends.friend_id AND friends.user_id='$user_id'";

$result=mysqli_query($dbc,$query)
or die('Error in query: $query'.mysqli_error($dbc));



while($row=mysqli_fetch_assoc($result))
{
	$output[]=$row;
}
echo json_encode($output,JSON_PRETTY_PRINT);

?>