<?php

Class User extends CI_Model {

    function login($username, $password) {
        try {
            $sql = "select username, password from users  where username=? and password=?";
            $stmt = $this->db->conn_id->prepare($sql);
            $stmt->bindParam(1, $username);
            $stmt->bindParam(2, $password);
            $stmt->execute();
            return $stmt->fetchAll(PDO::FETCH_ASSOC);
        } catch (Exception $ex) {
            return null;
        }
    }

}

?>