<?php

if (!defined('BASEPATH'))
    exit('NO direct script access allowed!');

class Db_summernote extends CI_Model {

    public function __construct() {
        parent::__construct();
        //load the database
        $this->load->database();
    }

    public function db_select() {
        $sql_select = "select static_page_content from rt_cms_static_pages ";
        $stmt = $this->db->conn_id->prepare($sql_select);
        $stmt->execute();
//        $stmt->fetch($sql_select);

        return $stmt->fetchAll(PDO::FETCH_ASSOC);
    }

    public function db_insert($x) {

        $id = 1;
//        $insert = "insert into kosala_login values(?,?,?)";//kbtl server
//        $insert = "insert into rgcms_about values(?,?)";

        $insert = "update rt_cms_static_pages set static_page_content=? where static_page_id = ?";
        $stmt = $this->db->conn_id->prepare($insert);
        $stmt->bindParam(1, $x);
        $stmt->bindParam(2, $id);
//        $stmt->bindParam(3, $y, PDO::PARAM_STR, 200);
        $stmt->execute();
        return $stmt->fetch();
    }

    public function user_select($x) {
        
    }

}
