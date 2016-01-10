<html>
<head>
        <title>Members</title>
	<link rel='stylesheet' href='style.css'/>

</head>
<body>
	<?php include 'connect.php'; ?>
	<?php include 'functions.php'; ?>
	<?php include 'header.php'; ?>

<div class= 'container'>
	<h3>Members: </h3>
	<?php
		$mem_query = mysql_query("SELECT user_id FROM user");
		while ($run_mem = mysql_fetch_array ($mem_query)){
			$user_id = $run_mem['id'];
			$username = getuser($user_id, 'username');
			echo "<a href='profile.php?user=$user_id' class='box' styke='display:block'>$username</a>";
                }
	?>
</div>
</body>
</html>
			