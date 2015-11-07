<?php
    require('databaseConnection.php');
   
    // If the values are posted, insert them into the database.
    if (isset($_POST['givenname']) && isset($_POST['givenage']) && isset($_POST['givenusername']) && isset($_POST['givenpassword'])){
        $name = $_POST['givename'];
		$age = $_POST['givenage'];
		$username = $_POST['givenusername'];
        $password = $_POST['givenpassword'];
        $query = "INSERT INTO `user` (name, age, username, password) VALUES ('$name', '$age', '$username', '$password')";
        $result = mysql_query($query);

        if($result){
            // redirection to ......
        }
    }
    ?>
