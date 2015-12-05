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
           <td align='center'><a href='../PhUpload.php'>Uploaded Photos From Phone</a></td>
	   <td align='center'><a href='../index.php'>Log Out</a></td>

	</tr>
   </table>	
  </div >
  <!-- title_bar.php--> 
  
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