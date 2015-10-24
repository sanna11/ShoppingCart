<?php

$message = $_POST ["message"];

if (isset($message) && !empty($message)) {
    $username = "root";
    $password = "";
    try {
        $conn = new PDO('mysql:host=localhost;dbname=test', $username, $password);
        $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        $stmt = $conn->prepare('INSERT INTO messages (message) VALUES(:message)');
        $stmt->bindParam(':message', $message);      
        $stmt->execute();

    } catch (PDOException $e) {
        echo json_encode('ERROR: ' . $e->getMessage());
    }

    echo json_encode(TRUE);
} else {
    echo json_encode(FALSE);
}

