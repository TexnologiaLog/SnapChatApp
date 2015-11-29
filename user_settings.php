<?php
/**
 * Created by PhpStorm.
 * User: NIKOS
 * Date: 29/11/2015
 */
require_once('connection.php');

if (isset($_POST['pfstatus']) ){
    $status = $_POST['pfstatus'];
    $query =  mysql_query("UPDATE user SET status=".$status."where username=".$username);
}
if (isset($_POST['birthday'])){
    $birth = $_POST['birthday'];
    $query =  mysql_query("UPDATE user SET birthday=".$birth."where username=".$username);
}
if (isset($_POST['gender'])){
    $sex = $_POST['gender'];
    $query =  mysql_query("UPDATE user SET birthday=".$sex."where username=".$username);
}
if (isset($_POST['language']) ){
    $lang= $_POST['language'];
    $query =  mysql_query("UPDATE user SET lang=".$lang."where username=".$username);
}
if (isset($_POST['email']) ){
    $mail = $_POST['email'];
    $query =  mysql_query("UPDATE user SET email=".$mail."where username=".$username);
}
if (isset($_POST['numb']) ){
    $num = $_POST['numb'];
    $query =  mysql_query("UPDATE user SET numbr=".$num."where username=".$username);
}

?>
