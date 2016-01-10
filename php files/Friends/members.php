<html>
<head>
        <title>Members</title>
	<link rel='stylesheet' href='style.css'/>

</head>
<body>
<?php
include ('connect.php');
 $dc = new DatabaseConnector();
 $con = $dc->getConnection();	
 ?>
<?php include 'functions.php'; ?>
	<?php include 'header.php'; ?>

<div class= 'container'>
	<h3>Members: </h3>
	<?php
		$mem_query = mysqli_query($con,"SELECT user_id FROM user");
		while ($run_mem = mysqli_fetch_array ($mem_query)){
			$user_id = $run_mem['id'];
			$username = getuser($user_id, 'username');
			echo "<a href='profile.php?user=$user_id' class='box' styke='display:block'>$username</a>";
                }
	?>
</div>
</body>
</html>
			