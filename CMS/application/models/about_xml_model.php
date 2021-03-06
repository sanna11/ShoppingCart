<?php

if (!defined('BASEPATH'))
    exit('NO direct script access allowed!');

class About_xml_model extends CI_Model {

    public function __construct() {
        parent::__construct();
        $this->load->database();
    }
  

    public function index() {
	
		header("Content-type: text/xml");
			 
			$doc = new DOMDocument( );
			$root = $doc->createElement('about');
			$doc->appendChild( $root );
			
			$sql_select = "SELECT about FROM rgcms_about WHERE id='1' ";
			$stmt = $this->db->conn_id->prepare($sql_select);
			$stmt->execute();
			
			$row = $stmt->fetch();
				
			$node1 = $doc->createElement( 'description' );
			$newnode1 = $root->appendChild($node1);
			$newnode1->nodeValue = $row['about'];
				
			
			$doc->save('xml/about.xml');
			echo $doc->saveXML();
	
	}
	
	}