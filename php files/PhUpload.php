<?php
echo "<a href='album/index.php'>Back</a>"."<br /><br />";
echo '<table border="1">';
echo "<tr>";
$file = glob("ImageUpload/*.*");
$count = count($file);
$half = ceil($count/2);
$half1 = ($count/2);
$i=0;

while($i<$half){
	$path_parts = pathinfo($file[$i]);
    $image_name = $path_parts['basename'];
	echo "<td><a href=".$file[$i]." ><img src=".$file[$i]." height='200' width='200' ></a><br />".$image_name.'</td>';
	$i++;
}
echo "</tr><tr>";

$i=$half;
while($i<$count){
	$path_parts = pathinfo($file[$i]);
    $image_name = $path_parts['basename'];
	echo "<td><a href=".$file[$i]." ><img src=".$file[$i]." height='200' width='200' ></a><br />".$image_name.'</td>';
	$i++;
}
if($half1 != $half) {
	echo "<td></td>";
}
echo "</tr>";
echo "</table>";