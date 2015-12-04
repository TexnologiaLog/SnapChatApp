<?php
$files = glob("ImageUpload/*.*");
echo "<a href='album/index.php'>Back</a>"."<br /><br />";

for ($i=0; $i<count($files); $i++) {
    $image = $files[$i];
    print $image ."<br />";
   
    echo '<img src="'.$image .'" alt="Random image" />'."<br /><br />";

}

?>	