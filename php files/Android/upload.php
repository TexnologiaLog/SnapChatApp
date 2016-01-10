<?php
 include_once("Connect.php");

$json=json_decode($_POST['json'],true);
$image =$_POST['image'];
$decoded_image=base64_decode("$image");
$sender_id=$json[0]['userid'];
$time=time();
$file_path = 'snaps/'.$sender_id.$time.'.jpg';
$absolute_path='http://projectdb.esy.es/Android/snaps/'.$sender_id.$time.'.jpg';
file_put_contents($file_path, $decoded_image);

$count=$_POST['count'];

for($i=0; $i<$count; $i++){
$userid=$json[$i]['userid'];
$senderid=$json[$i]['senderid'];
$time=time();
echo $userid." " .$senderid;
$query =" INSERT INTO Snaps values(0,'$userid','$senderid','$absolute_path',$time)";
$result=mysqli_query($dbc,$query) or die(mysqli_error($dbc));
}
?>
  						
