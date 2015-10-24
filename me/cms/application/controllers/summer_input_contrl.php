<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class Summer_input_contrl extends CI_Controller {

    public function __construct() {
        parent::__construct();
        $this->load->library('session');
        $this->load->helper('form');
        $this->load->library('form_validation');
        $this->load->model('about_xml_model');
        $this->load->model('db_summernote');
    }

    public function about() {
        // $check = TRUE;
//        $abc = "";
        $txt = $this->input->post('content'); //get summernote textarea value to txt variable
//        if($txt == "<p><br></p>"){
//        $abc = "kkkk";    
//    }else{
//        $abc = "rrrrrr";
//    }
//        var_dump($abc);
        if ($this->db_summernote->db_insert($txt) == FALSE) {//check db_model function return value is true of false
            $check = FALSE; //data not insert to database
        } else if ($this->db_summernote->db_insert($txt) == TRUE) {
            $check = TRUE; //data insert to database
        } else {
            $check = "not true or false";
        }
        echo json_encode($check);
    }
    public function select_about(){
//        $select_data = $this->db_summernote->db_select();
////        echo json_encode($select_data);
//        $data = array('about'=>$select_data);
//        
//        $this->load->view('summernote_view',$data);

//    
        
    }

    public function about_xml() {

        $this->about_xml_model->index();
    }

}

?>
