<?php
session_start();
if(isset($_SESSION['loginid']) || isset($_SESSION['username']))
{
unset($_SESSION['username']);
unset($_SESSION['loginid']);
session_destroy();
header('Location:./index.php');
}
else
{
header('Location:./index.php');
session_destroy();
}
?>