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


			<h2><?php echo $name; ?>'s Profile </h2><br />
			<table bgcolor="#222">
				<tr><td><img src="<?php session_start(); echo $_SESSION['photo']; ?>" ></td></tr>
				<tr><td>NAME:</td><td><?php session_start(); echo $_SESSION['name']; ?></td></tr>
				<tr><td>AGE:</td><td><?php session_start(); echo $_SESSION['age']; ?></td></tr>
				<tr><td>USERNAME:</td><td><?php session_start(); echo $_SESSION['username']; ?></td></tr>
			</table>
				


    <div class='clear'></div>
  
  </div> 
  </div>

</body>
</html>								