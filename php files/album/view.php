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
  
    $album_id = $_GET['id'];
	
	$query = mysqli_query($con,"SELECT `id`, `name`, `url` FROM `photos` WHERE `album_id` = '$album_id'");
	while($run = mysqli_fetch_array($query)){
		
		$name = $run['name'];
		$url = $run['url'];
		
	
	?>
	
	<div id='view_box'>
	  <img src='uploads/<?php echo $url ?>' />
	  <br/>
	  <b><?php echo $name; ?></b>
	</div>
	<?php
	    }
	?>
  <div class='clear'></div>
  
  
  </div>
</div>

</body>
</html>