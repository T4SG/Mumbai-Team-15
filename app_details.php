<?php


 
 $db_host  = "ec2-52-69-49-99.ap-northeast-1.compute.amazonaws.com";

 $db_uid  = "root";

 $db_pass = "code4good";
  
 $db_name  = "cfg"; 

 $db_con = mysql_connect($db_host,$db_uid,$db_pass) or die('could not connect');


 mysql_select_db($db_name);
 


 $task_number=$_POST['task'];    
 $curr_date=$_POST['date'];

 $sql4="insert into schooldetails(task,date) values ('$task_number','$announce_txt')";
 mysql_query($sql4);    

echo done;

?>