<?php
/**
 * Created by PhpStorm.
 * User: NIKOS
 */

require_once("DbConnection.php");

$username = $_POST["lg_username"];
$password = $_POST["lg_password"];

if(!$username || !$password){
    session_start();
    $_SESSION['error_message']=("You need to fill both fields");
    header("location: ../Login.php");
}
else {



    $sql="SELECT * FROM users WHERE username='$username' and password='$password'";
    $result = $connection->query($sql);

    if ($result->num_rows == 1){
        session_start();
        $row = $result->fetch_assoc();
        $_SESSION['lg_username'] = $row["lg_username"];
        $_SESSION['lg_password'] = $row["lg_password"];
        header("location: ../ ");

    }
    else{
        session_start();
        $_SESSION['error_message'] = "Wrong Username or Password";
        header("location: ../Login.php");
    }
}
?>
