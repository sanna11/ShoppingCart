<?php

if (isset($_POST['upload']) && $_FILES['userfile']['size'] > 0) {
    $fileName = $_FILES['userfile']['name'];
    $tmpName = $_FILES['userfile']['tmp_name'];
    $fileSize = $_FILES['userfile']['size'];
    $fileType = $_FILES['userfile']['type'];
    echo $fileName;
    echo '<br>';
    echo $tmpName;
    echo '<br>';
    echo $fileSize;
    echo '<br>';
    echo $fileType;
    echo '<br>';
    $fp = fopen($tmpName, 'r');
    
    $content = fread($fp, filesize($tmpName));
    echo $content;
    echo '<br>';
        echo '<br>';
    echo '<br>';

    $content = addslashes($content);
    echo $content;
    echo '<br>';
    fclose($fp);

    if (!get_magic_quotes_gpc()) {
        $fileName = addslashes($fileName);
    }
    include 'library/config.php';


    $query = "INSERT INTO files (name, size, type, content ) " .
            "VALUES ('$fileName', '$fileSize', '$fileType', '$content')";

    if (mysqli_query($conn, $query)) {
        echo "New record created successfully";
    } else {
        echo "Error: " . $sql . "<br>" . mysqli_error($conn);
    }

    include 'library/closedb.php';

    echo "<br>File $fileName uploaded<br>";
} 