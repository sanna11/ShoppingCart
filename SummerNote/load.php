<?php
    $username = "root";
    $password = "";
    try {
        $conn = new PDO('mysql:host=localhost;dbname=test', $username, $password);
        $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        $sql = "SELECT message FROM messages order by id desc";
        $stmt = $conn->query($sql);
        
        $row = $stmt->fetchObject();
        
        echo json_encode($row->message);
        
    } catch (PDOException $e) {
        echo json_encode('ERROR: ' . $e->getMessage());
    }

    

