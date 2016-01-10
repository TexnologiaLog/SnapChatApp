<?php

include_once("Connect.php");

$username=urldecode($_POST['username']);



$query="SELECT * FROM user WHERE username LIKE '$username%' OR name LIKE '$username%'";
//$query="SELECT * FROM user WHERE username LIKE 's%'";

$result=mysqli_query($dbc,$query)
or die('Error in query: $query'.mysqli_error($dbc));



while($row=mysqli_fetch_assoc($result))
{
	$output[]=$row;
}
echo json_encode($output,JSON_PRETTY_PRINT);

?>