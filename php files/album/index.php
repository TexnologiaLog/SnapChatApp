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
     <?php
	 $query = mysqli_query($con,"SELECT `id`, `name` FROM `albums`");
		while($run = mysqli_fetch_array($query)){
			$album_id = $run['id'];
			$album_name = $run['name'];
			 
			 
			 
			 $query1 = mysqli_query($con,"SELECT `url` FROM `photos` WHERE `album_id`='$album_id'");
			 $run1 = mysqli_fetch_array($query1);
			 $pic = $run1['url'];
		
		
		?>
		 
		 <a href='view.php?id=<?php echo $album_id; ?>'>
		 <div id='view_box'>
		    <img src='uploads/<?php echo $pic; ?>' />
			<br/>
			<b><?php echo $album_name ?></b>
		 
		 </div>
		 </a>
		 
		 <?php
		 
		}
		 ?>
        <div class='clear'></div>
  
  </div> 
  </div>

</body>
</html>				