<?php

include_once("Connect.php");

$id=urldecode($_POST['rec_id']);



$query="SELECT photo_url,sender_id FROM Snaps where receiver_id='$id' ORDER BY timer DESC ";

$result=mysqli_query($dbc,$query)
or die('Error in query: $query'.mysqli_error($dbc));


while($row=mysqli_fetch_assoc($result))
{
	$output[]=$row;
}
echo json_encode($output,JSON_PRETTY_PRINT);


?>
