<?php  

	session_start();
	require('databaseConnection.php');

	if (isset($_POST['givenusername']) and isset($_POST['givenpassword'])){

		$username = $_POST['givenusername'];
		$password = $_POST['givenpassword'];
		
		$query = "SELECT * FROM `user` WHERE username = '$username' and password = '$password'";
		$result = mysql_query($query) or die(mysql_error());
		$count = mysql_num_rows($result);

		if ($count == 1){
			// redirection to .....
		}
		else{
			// error message
		}
	}
?>

