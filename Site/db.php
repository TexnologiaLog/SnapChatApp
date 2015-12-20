<?php


class DatabaseConnector
{
    public $con;
    //function for getting connected to database
    public function getConnection()
    {
        $con=mysqli_connect("mysql.hostinger.co.uk","u796674379_ch","svoitX66aX","u796674379_tl");
        // Check connection
        if (mysqli_connect_errno()) {
          echo "Failed to connect to MySQL: " . mysqli_connect_error();
        }
        return $con;
    }
    public function closeConnection()
    {
        mysqli_close($con);
    }
}

?>