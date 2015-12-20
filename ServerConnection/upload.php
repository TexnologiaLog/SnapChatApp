<?php
 define('HOST','mysql.hostinger.gr');
 define('USER','u364896646_tl');
 define('PASS','lu31ySx75N');
 define('DB','u364896646_tl');
 

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
 
 $actualpath = "http://projectldb.16mb.com/ImageUpload/$path";
 
 $sql = "INSERT INTO images (image) VALUES ('$actualpath')";
 
 if(mysqli_query($con,$sql)){
 file_put_contents($path,base64_decode($image));
 echo "Successfully Uploaded";
 }
 
 mysqli_close($con);
 }else{
 echo "Error";
 }