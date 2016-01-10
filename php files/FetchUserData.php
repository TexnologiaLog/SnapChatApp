<?php
   $con= mysqli_connect("mysql.hostinger.co.uk","u796674379_ch","svoitX66aX","u796674379_tl");
      
    $username = $_POST["username"];
    $password = $_POST["password"];
    
    $statement = mysqli_prepare($con, "SELECT * FROM user WHERE username = ? AND password = ?");
    mysqli_stmt_bind_param($statement, "ss", $username, $password);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $user_id, $name, $age, $username, $password,$photo,$image);
   
    $user = array();
    
    while(mysqli_stmt_fetch($statement)){
        $user["user_id"]=$user_id;
        $user["name"] = $name;
        $user["age"] = $age;
        $user["username"] = $username;
        $user["password"] = $password;
        $user["photo"] = $photo;
        $user["image"] = $image;
    }
    
    echo json_encode($user);
    mysqli_close($con);
?>		