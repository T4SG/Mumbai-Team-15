<?php   

session_start();
$a3=$_SESSION['array_name'];

//$arrlength = count($a3);
/*
for($x = 0; $x < $arrlength; $x++) {
    echo $a3[$x];
    echo "<br>";
}
*/

 /* CAT:Mathematical */

 /* pChart library inclusions */
 include("../class/pData.class.php");
 include("../class/pDraw.class.php");
 include("../class/pImage.class.php");
 


 
 
/*
 
  Create and populate the pData object 
 $MyData = new pData();  
 //for($i=0;$i<=20;$i++) { $MyData->addPoints(rand(10,30)+$i,"Probe 1"); }
 //for($i=0;$i<=20;$i++) { $MyData->addPoints(rand(0,10)+$i,"Probe 2"); }

$MyData->addPoints(10,"1");
$MyData->addPoints(14,"1");
$MyData->addPoints(22,"1");
$MyData->addPoints(27,"1");


//$maxtask=mysqli_query($con,"SELECT MAX(task) FROM schooldetails");



//echo $maxtask;

*/
//$MyData->addPoints($diff,"1");

$diff = array(0,0, 6, 10, 15, 21 , 25,32 , 38 , 45 , 50); 
$MyData = new pData();
$MyData->addPoints($a3[1],"1");
$MyData->addPoints($a3[2],"1");
$MyData->addPoints($a3[3],"1");
//$MyData->addPoints(27,"1");


 
 
 /* Give a name to the Y axis */
 $MyData->setAxisName(0,"Temperatures");

 /* Create the pChart object */
 $myPicture = new pImage(700,230,$MyData);

 /* Turn of Antialiasing */
 $myPicture->Antialias = FALSE;

 /* Add a border to the picture */
 $myPicture->drawRectangle(0,0,699,229,array("R"=>0,"G"=>0,"B"=>0));
 
 /* Write the chart title */ 
 $myPicture->setFontProperties(array("FontName"=>"../fonts/Forgotte.ttf","FontSize"=>11));
 $myPicture->drawText(150,35,"PROJECT SCHEDULE",array("FontSize"=>20,"Align"=>TEXT_ALIGN_BOTTOMMIDDLE));

 /* Set the default font */
 $myPicture->setFontProperties(array("FontName"=>"../fonts/pf_arma_five.ttf","FontSize"=>6));

 /* Define the chart area */
 $myPicture->setGraphArea(60,40,650,200);

 /* Draw the scale */
 $scaleSettings = array("XMargin"=>10,"YMargin"=>10,"Floating"=>TRUE,"GridR"=>200,"GridG"=>200,"GridB"=>200,"DrawSubTicks"=>TRUE,"CycleBackground"=>TRUE);
 $myPicture->drawScale($scaleSettings);

 /* Turn on Antialiasing */
 $myPicture->Antialias = TRUE;

 /* Draw the line of best fit */
 $myPicture->drawBestFit();

 /* Turn on shadows */
 $myPicture->setShadow(TRUE,array("X"=>1,"Y"=>1,"R"=>0,"G"=>0,"B"=>0,"Alpha"=>10));

 /* Draw the line chart */
 $myPicture->drawPlotChart();

 /* Write the chart legend */
 $myPicture->drawLegend(580,20,array("Style"=>LEGEND_NOBORDER,"Mode"=>LEGEND_HORIZONTAL));

 /* Render the picture (choose the best way) */
 $myPicture->autoOutput("pictures/example.drawBestFit.png");
?>