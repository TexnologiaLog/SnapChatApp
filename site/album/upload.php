<html>
<head>
<title>PHP - Album System</title>
<link rel='stylesheet' href='style.css'/>
</head>
<body>


<!-- connect to DB --> 
<?php
include ('db.php');
 $dc = new DatabaseConnector();
 $con = $dc->getConnection();
?>
<!-- connect to DB --> 

<div id='body'>

 <!-- title_bar.php--> 
  <div id='title_bar'>
   <table>
    <tr>
	   <td align='center'><a href='index.php'>Home</a></td>
	   <td align='center'><a href='create.php'>Create Album</a></td>
	   <td align='center'><a href='upload.php'>Upload Photo</a></td>
	   <td align='center'><a href='../index.php'>Log Out</a></td>

	</tr>
  </table>	
 </div>
 <!-- title_bar.php--> 

<div id='container'>
   <h3>Upload Photos: </h3>
   <form enctype='multipart/form-data' method='post'>
   <?php
	     if(isset($_POST['upload'])){
	     $name = $_POST['name'];
		 $album_id = $_POST['album'];
		 $file = $_FILES['file']['name'];
		 $file_type = $_FILES['file']['type'];
		 $file_size = $_FILES['file']['size'];
		 $file_tmp = $_FILES['file']['tmp_name'];
		 $random_name = rand();
		 
		  if(empty($name) or empty($file)){
			 echo "Please Fill all the Fields! <br/><br/>";
		 }
		 else{
			
			move_uploaded_file($file_tmp, 'uploads/'.$random_name.'.jpg');
			mysqli_query($con,"INSERT INTO photos VALUES('', '$name', '$album_id', '$random_name.jpg')");
		     
			 
			 echo "Photo Uploaded!! <br/><br/>";
		     }
	}
	 ?>

	 Name : <br/>
	  <input type='text' name='name' />
	  <br/><br/>
	  Select Album : <br/>
	  <select name='album'>
	   <?php
		  $query = mysqli_query($con,"SELECT `id`, `name` FROM `albums`");
		  while($run = mysqli_fetch_array($query)){
			  $album_id = $run['id'];
			  $album_name= $run['name'];
			  echo "<option value='$album_id'>$album_name</option>";
		
		  }
		 ?>
	  </select>
	  <br/><br/>
	  Select Photo : <br/>
	  <input type='file' name='file' />
	  <br/><br/>
	  <input type='submit' name='upload' value='Upload' />
    </form>
   </div>
  </div>
 </body>
</html> 