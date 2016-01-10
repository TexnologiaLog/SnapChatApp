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

<?php include('header.php') ?>

<div id='container'>
   <h3>Create Album</h3>
   
   <form method='post'>
   <?php
   
       if(isset($_POST['name'])){
		   
		   $name = $_POST['name'];
		   
		   if(empty($name)){
			   echo "Please Enter the album Name <br/><br/>";
		   }
		   else{
			   mysqli_query($con,"INSERT INTO albums VALUES('', '$name')");
			   
			   echo"Albums Created Successfully! <br/><br/>";
			   
		   }
	   }
   ?>

   Album Name: <input type='text' name='name' /> <input type='submit' value='Create' />
   </form>  

</body>
</html>