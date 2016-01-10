<?php
   include_once("Connect.php");
    
    $username =$_POST['username'];
    $password =$_POST['password'];
    //echo 'username='.$username.'\n'.'password:'.$password;
    
    $statement = mysqli_prepare($dbc, "SELECT * FROM user WHERE username = ? AND password = ?");
    mysqli_stmt_bind_param($statement, "ss", $username, $password);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $user_id, $name, $age, $username, $password,$photo,$image);
   
    $user = array();
    
    while(mysqli_stmt_fetch($statement)){
        $user['user_id']=$user_id;
	$user["name"] = $name;
        $user["age"] = $age;
        $user["username"] = $username;
        $user["password"] = $password;
	$user["photo"] = $photo;
        $user["image"] = $image;
    }
    
    echo json_encode($user);
    mysqli_close($dbc);
?>	