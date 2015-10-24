<?php

if (!defined('BASEPATH'))
    exit('No direct script access allowed');

class Home extends CI_Controller {
    
    public function __construct() {
        parent::__construct();
        //load the database
        
        $this->load->model('db_summernote');
    }

    public function index() {
        $data = array(
            'title' => "RG CMS"
        );
        $this->load->view('templates/header', $data);
        $this->load->view('templates/sidebar');
        $this->load->view('blank_page');
        $this->load->view('templates/footer');
    }

    public function edit() {
        
        $about_data = "";//define to assing about details 
        $select_data = $this->db_summernote->db_select();//get data from model
        foreach ($select_data as $row){
           $about_data['about_us']=$row['static_page_content'];
        }
        
//        var_dump($d);
//         die();
        //data array use for title
        $data = array(
            'title' => "RG CMS"
        );
        $this->load->view('templates/header', $data);
        $this->load->view('templates/sidebar');
        $this->load->view('panel/summernote/summernote_view',$about_data);
        $this->load->view('templates/footer');
    }

}
?>