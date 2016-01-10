<html>
<head>
	<title>Profile</title> >
	<link rel='stylesheet' href='style.css'/>
</head>
<body>
	<?php include 'connect.php'; ?>
	<?php include 'functions.php'; ?>
	<?php include 'header.php'; ?>
<div class='container'>
<?php
if (isset($_GET['user']) && !empty($_GET['user'])){
	$user = $_GET['user'];
}else{
	$user = $_SESSION['user_id'];
}
	$my_id = $_SESSION['user_id'];
	$username = getuser($user, 'username');
?>

<h3><?php echo $username; ?></h3>
<?php
if ($user != $my_id){
	$check_frnd_query = mysql_query("SELECT id FROM friends WHERE (user_id='$my_id' AND friend_id='$user') OR (user_id='$user' AND friend_id='$my_id')");
	}
if (mysql_num_rows($check_frnd_query) == 1){
	echo "<a href='#' class='box'>Already Frnds</ad> | <a href='action.php?action=unfrnd&user=&user' class='box'>Unfriend $username</a>";
}else{
	$from_query = mysql_query("SELECT `id`FROM `frnd_req` WHERE `from`='$user' AND `to`='$my_id'");
	$to_query = mysql_query("SELECT `id`FROM `frnd_req` WHERE `from`='$my_id' AND `to`='$user'");
	}
if(mysql_num_rows($from_query) == 1){
	echo "<a href='#' class='box'>Ignore</a> | <a href='' class='box'>Accept</a>";
}else if (mysql_num_rows($to_query) == 1){
	echo "<a href='#' class='box'>Cancel Request</a>";
}else {
	echo "<a href='action.php?action=send&user=$user' class='box'>Send Friend Request</a>";
} 
?>
</div>

</body>
</html>