<?php
//Give your mysql username password and database name
$username="root";
$password="";
$dbname="test1";
$con=mysql_connect("localhost",$username,$password);
mysql_select_db($dbname,$con);

?>