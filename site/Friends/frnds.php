<html>
<head>
	<title>Friends</title>
	<link rel='stylesheet' href='style.css'/>
</head>
<body>
<?php include 'connect.php';?>
<?php include 'functions.php';?>
<?php include 'header.php';?>

<div class ='container'>
	<h3>Friends: </h3>
	<?php
		$my_id= $_SESSION['user_id'];
		$frnd_query = mysql_query ("SELECT user_id, friend_id FROM friends WHERE user_id='$my_id' OR friend_id='$my_id'");
		while($run_frnd = mysql_fetch_array($frnd_query)){
			$user_id = $run_frnd['user_id'];
			$friend_id = $run_frnd['friend_id'];
			if($user_id == $my_id){
				$user = $user_id;
			}else{
				$user = $friend_id;	
			}
			$username = getuser($user, 'username');
			echo "<a href='profile.php?user=$user' class='box' style='display:block'>$username</a>";
}			
?>

</div>
</body>
</html>

