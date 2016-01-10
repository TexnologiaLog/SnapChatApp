<html>
<head>
	<title>Request Friend</title>
	<link rel='stylesheet' href='style.css'/>
</head>
<body>
<?php
include ('connect.php');
 $dc = new DatabaseConnector();
 $con = $dc->getConnection();	
 ?>
<?php include 'functions.php';?>
<?php include 'header.php';?>

<div class ='container'>
	<h3>Requests: </h3>
	<?php
		$my_id = $_SESSION['user_id'];
		$req_query = mysqli_query($con,"SELECT from FROM frnd_req WHERE to='$my_id'");
		while($run_req = mysqli_fetch_array($req_query)){
			$from = $run_req['from'];
			$from_username = getuser($from,'username');
			echo "<a href='profile.php'?user=$from' class='box' style='display:block'>$from_username</a>";
}

	?>

</div>
</body>
</html>
