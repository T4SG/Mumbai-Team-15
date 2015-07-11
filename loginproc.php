<?php
 if (!isset($_SESSION['loginid']) || !isset($_SESSION['username'])) {
 if (isset($_POST['login'])) {
$username = mysql_real_escape_string(strip_tags($_POST['username']));
$password = md5(mysql_real_escape_string(strip_tags($_POST['password'])));
$query = "select * from users where username='$username' AND password='$password'";
$result = mysql_query($query);
	 if (mysql_num_rows($result) != 1) 
	 {
		 
		echo "Username and password is wrong";
		include "login.php";
	 } 
	 else
	  {
		$row = mysql_fetch_array($result);
		$_SESSION['loginid'] = $row['userid'];
		$_SESSION['username'] = $row['username'];
		menubox();
		echo "login success";
	 }
 } 
 else
 {
		include "login.php";
 }
 } 
 else
  {
		menubox();
		echo "<br>";
		echo "already login";
 }
 ?>
