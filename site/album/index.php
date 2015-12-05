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
	   <td align='center'><a href='upload.php'>Upload Photos</a></td>
           <td align='center'><a href='../PhUpload.php'>Uploaded Photos From Phone</a></td>
           <td align='center'><a href='../index.php'>Log Out</a></td>

	</tr>
   </table>	
  </div >
  <!-- title_bar.php--> 
  
  
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