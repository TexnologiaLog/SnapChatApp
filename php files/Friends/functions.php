<?php


include ('connect.php');
 $dc = new DatabaseConnector();
 $con = $dc->getConnection();

 session_start();

function loggedin(){
	if(isset($_SESSION['user_id']) && !empty($_SESSION['user_id'])){
		return true;
		
	}
	else{
		return false;
	}
	
}

function getuser ($id, $field){
	$query = mysqli_query ($con,"SELECT $field FROM users WHERE id='$id'");
	$run = mysqli_fetch_array($query);
	return $run[$field];
}

?>