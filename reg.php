<?php
	if(isset($_GET['error']))
	echo $_GET['error'];

?>

<form action="regproc.php" enctype="multipart/form-data" method="post">
    <label>Username        :<input name="username" type="text" /></label><br>
    <label>password        :<input name="password" type="password" /></label><br>
    <label>Re-Type Password:<input name="repassword" type="password" /></label><br>
    <label>E-mail		   :<input name="email" type="email" /></label><br>
    <input name="reg" type="submit" value="reg" />
</form>