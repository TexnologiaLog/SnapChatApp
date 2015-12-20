<?php


	$db_host = 'mysql.hostinger.co.uk';
	$db_user = 'u796674379_ch';
	$db_pass = 'svoitX66aX';

	$db_name = 'u796674379_tl';

	$connection =@mysql_connect($db_host, $db_user, $db_pass);
	$database=@mysql_select_db($db_name);

?>