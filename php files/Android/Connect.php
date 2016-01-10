<?php

$server_name="mysql.hostinger.co.uk";
$user_name="u796674379_ch";
$password="svoitX66aX";
$database="u796674379_tl";


/*$server_name="localhost";
$user_name="root";
$password="";
$database="db";*/

$dbc=mysqli_connect($server_name,$user_name,$password,$database)
or die('Server Error: ' .mysqli_error($dbc));

?>