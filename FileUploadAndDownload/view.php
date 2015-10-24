<html>
    <head>
        <title>Download File From MySQL</title>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    </head>

    <body>
        <?php
        include 'library/config.php';

        $query = "SELECT id, name FROM files";
        $result= $conn->query($query);
        if ($result->num_rows <= 0) {
            echo "Database is empty <br>";
        } else {
            while($row = $result->fetch_assoc()) {
                ?>
                <a href="download.php?id=2">2</a> <br>
                <?php
            }
        }
        include 'library/closedb.php';
        ?>
    </body>
</html>