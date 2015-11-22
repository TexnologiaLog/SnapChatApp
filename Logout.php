<?php
/**
 * Created by PhpStorm.
 * User: NIKOS
 */
session_start();
session_destroy();
header("location: Login.php");
?>
