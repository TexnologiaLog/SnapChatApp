<html>
<head>
	<title>Request Friend</title>
	<link rel='stylesheet' href='style.css'/>
</head>
<body>
<?php include 'connect.php';?>
<?php include 'functions.php';?>
<?php include 'header.php';?>

<div class ='container'>
	<h3>Requests: </h3>
	<?php
		$my_id = $_SESSION['user_id'];
		$req_query = mysql_query("SELECT from FROM frnd_req WHERE to='$my_id'");
		while($run_req = mysql_fetch_array($req_query)){
			$from = $run_req['from'];
			$from_username = getuser($from,'username');
			echo "<a href='profile.php'?user=$from' class='box' style='display:block'>$from_username</a>";
}

	?>

</div>
</body>
</html>
