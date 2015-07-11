<?php
if(isset($_POST['reg'])) {
    require "dbconn.php";
    if(isset($_POST['username']) && isset($_POST['password']))
     {
        $username = strip_tags($_POST['username']);
        $password=md5(strip_tags($_POST['password']));
        $repass=md5(strip_tags($_POST['repassword']));
        $email = strip_tags($_POST['email']);
        
        if($username=='')
        {
            header('location:./reg.php?error=user name empty');
            exit();
        }
        
        if(!strcmp($password,$repass)==0) {
            header('Location:./reg.php?error=password not match');
            exit();
        }
        
        if(!filter_var($email, FILTER_VALIDATE_EMAIL)) {
            header('location:reg.php?error=email not valid');
            exit();
        }
        
        $query = "select * from users where username='$username'";
        
        $result = mysql_query($query);
        
        if (mysql_num_rows($result) >= 1) {
            
            header('location:reg.php?error=username already taken. please try another one');
            exit();
        }

        mysql_query("INSERT INTO users(username,password,email) VALUES ('$username','$password','$email')") or  die("".mysql_error());
        echo "Register success";
    }
}
 else {
    header('location:reg.php');
    exit();
}
?>
