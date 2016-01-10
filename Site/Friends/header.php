<div id='title_bar'>
<ul>
 <li><a href='index.php'>Home</a></li>
  <?php
  if(loggedin()){
	?>
  <li><a href='profile.php'>Profile</a></li>
  <li><a href='req.php'>Requests</a></li>
  <li><a href='frnds.php'>Friends</a></li>
  <li><a href='members.php'>Members</a></li>
  <li><a href='../album/index2.php'>Album</a></li>  
  <?php
  } else{
	  ?>
	  <li><a href='../index.php'>Log in</a></li>
	  <li><a href='../registerf.php'>Register</a></li>
  <?php
  }
  ?>
  <div class='clear'></div>

</ul>


</div>