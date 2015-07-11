<?php
function menubox()
{
if(isset($_SESSION['loginid']) || isset($_SESSION['username']))
{
$u = $_SESSION['username'];
echo "<table style='width:100%'><tr>
<td width='10'><a href=#>Home</a></td>
<td align='right'><a href='#'>Welcome $u </a> </td>
<td width='10' align='right'><a href='./logout.php'>Logout</a>
</td></tr>
</table>";
}
else
include "login.php";
}
?>