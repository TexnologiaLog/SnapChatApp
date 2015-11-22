<?php
/**
 * Created by PhpStorm.
 * User: NIKOS
 */
require_once('DbConnection.php');

if (isset($_POST['reg_fullname']) && isset($_POST['reg_age']) && isset($_POST['reg_username']) && isset($_POST['reg_password'])){
    $name = $_POST['reg_fullname'];
    $age = $_POST['reg_age'];
    $username = $_POST['reg_username'];
    $password = $_POST['reg_password'];
    $query = "INSERT INTO `user` (username, password) VALUES ('$name', '$age', '$username', '$password')";
    $result = mysql_query($query);
    if($result){
        // redirection ......
    }
}
?>
