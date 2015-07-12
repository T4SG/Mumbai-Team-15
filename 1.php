<?php

session_start();


$db_host  = "localhost";

 $db_uid  = "root";

 $db_pass = "code4good";
  
 $db_name  = "cfg";

 $db_con = mysql_connect($db_host,$db_uid,$db_pass) or die('could not connect');


 mysql_select_db($db_name);
 
$array1 = array(0,2, 6, 10, 15, 21 , 25,32 , 38 , 45 , 50); 

$sql4="SELECT MAX(task) as val FROM schooldetails";
$result= mysql_query($sql4);
$row=mysql_fetch_array($result);
    

$maxtask=$row["val"];

$a2=array();

for($x=1;$x<=$maxtask;$x++)
{
$result= mysql_query("SELECT date FROM schooldetails WHERE task=$x");
$row=mysql_fetch_array($result);
$a=$row['date'];
// $a=mysql_query($con,"SELECT date FROM schooldetails WHERE task=$x");  
 //$a=$a['date'];
 $a=intval($a);
 $diff=$a-$array1[$x];
 $diff=intval($diff);
 echo $a;
 echo $diff;
 $a2[$x]=$diff; 
 //echo $diff;
//
//$MyData->addPoints(27,"1");
}

$_SESSION['array_name'] = $a2;







?>

