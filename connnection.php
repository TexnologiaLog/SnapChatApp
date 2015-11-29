<?php
/**
 * Created by PhpStorm.
 * User: NIKOS
 */

$servername = "localhost";
$username = "root";
$password = "";
$dbname = "texnologialog";

// Create connection
$conn = mysqli_connect($servername, $username, $password, $dbname);

// Check connection
if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}
mysqli_query($conn,"SET CHARACTER SET 'utf8'");
mysqli_query($conn,"SET NAMES 'utf8'");

?>
