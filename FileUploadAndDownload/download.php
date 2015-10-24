<?php

if (isset($_GET['id'])) {
// if id is set then get the file with the id from database

    include 'library/config.php';

    $id = $_GET['id'];
    $query = "SELECT name, type, size, content " .
            "FROM files WHERE id = '$id'";

    $result = $conn->query($query);

    if ($result->num_rows > 0) {
        $row = $result->fetch_assoc();
//        $name = $row["name"];
//        $size = $row["size"];
//        $type = $row["type"];
        $content = $row["content"];
//        header("Content-length: $size");
//        header("Content-type: $type");
//        header("Content-Disposition: attachment; filename=$name");
//        echo $content;
        
        echo "<img src=$content />";

        include 'library/closedb.php';
        exit;
    }
}
