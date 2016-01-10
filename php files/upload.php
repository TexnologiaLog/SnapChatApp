<?php
 define('HOST','mysql.hostinger.co.uk');
 define('USER','u796674379_ch');
 define('PASS','svoitX66aX');
 define('DB','u796674379_tl');


 if($_SERVER['REQUEST_METHOD']=='POST'){
 
 $image = $_POST['image'];

 $con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');

 $sql ="SELECT id FROM images ORDER BY id ASC";
 
 $res = mysqli_query($con,$sql);
 
 $id = 0;
 
 while($row = mysqli_fetch_array($res)){
 $id = $row['id'];
 }
 
 $path = "ImageUpload/$id.png";
 $m="$id.png";
 $actualpath = "http://projectdb.esy.es/ImageUpload/$m";
 
 $sql = "INSERT INTO images (image) VALUES ('$actualpath')";
 
 if(mysqli_query($con,$sql) ){
 file_put_contents($path,base64_decode($image));
 echo "Successfully Uploaded";
 }
 
 mysqli_close($con);
 }else{
 echo "Error";
 }