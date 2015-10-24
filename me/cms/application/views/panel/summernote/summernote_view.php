<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <script src="//code.jquery.com/jquery-1.9.1.js"></script> 
    <!-- include libraries BS3 -->
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css" />
    <script type="text/javascript" src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.min.css" />

    <link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/codemirror/3.20.0/codemirror.min.css" />
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/codemirror/3.20.0/theme/blackboard.min.css">
        <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/codemirror/3.20.0/theme/monokai.min.css">
            <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/codemirror/3.20.0/codemirror.js"></script>
            <script src="//cdnjs.cloudflare.com/ajax/libs/codemirror/3.20.0/mode/xml/xml.min.js"></script>
            <script src="//cdnjs.cloudflare.com/ajax/libs/codemirror/2.36.0/formatting.min.js"></script>
            <script type="text/javascript" src='/dist/cms/message.js'></script>
            <link rel="stylesheet" type="text/css" href="/dist/cms/message_default.css"></link>
            <!-- include summernote css/js-->
            <link href="/dist/cms/summer/summernote.css"  rel="stylesheet"></link>
            <script src="/dist/cms/summer/summernote.min.js"></script>
            <script>
                $(document).ready(function () {

                    $('#summernote').summernote({
                        height: 200,
                    });
//                    $.ajax({
//                        type : "POST",
//                        url : "summer_input_contrl/select_about",
//                        dataType :"json",
//                        
//                        success : function (ressult){
//                            document.getElementById("summernote").innerHTML = ressult;
//                        }
//                    });

//                    $("#submit").click(function (){
////                        var form_input = $("#summer_form").serialize(); //get form data to form_input variable 
//                        var txt_input = $("#summernote").val(); //get textarea input data to txt_input variable 
//                        if(txt_input === ""){
////                            return false;
//                        }
////                        var data = csrf_test_x:$('input[name=csrf_test_x]').val();//get csrf tocken to data variable
//                        $.ajax({
//                            type : "POST",
//                            url : "http://www.kbtpl.com/rgcms/CMS/summer_input_contrl/about",
//                            data : {content:txt_input,csrf_test_x:$('input[name=csrf_test_x]').val()},//pass txtarea input with cssrf tolcke
//                            dataType : "json",
//                            success : function (result){
//                                alert(result);
//                            }
////                            return false;
//                        });
//                    });



                    //        function sendFile(file, editor, welEditable) {     
                    //            data = new FormData();
                    //            data.append("file", file);//You can append as many data as you want. Check mozilla docs for this
                    //            data.append("csrf_test_name", csrf);
                    //            
                    //            console.log(data)
                    //           
                    //            //alert('lsdj');
                    //            $.ajax({
                    //                data: data,
                    //                type: "POST",
                    //                url: 'http://localhost/file_upload/summer_upload/upload',
                    //                 cache: false,
                    //
                    //            contentType: false,
                    //
                    //            processData: false,
                    //                success: function(url) {
                    //                    editor.insertImage(welEditable, url);
                    //                   // $('.summernote').summernote("insertImage", data, 'filename');
                    //                }
                    //            });
                    //        }
                });

                function validation() {
                    //                      
                    var txt_input = $("#summernote").val();
                    console.log("11");

                    if (txt_input === "") {
                        console.log("if");
                    } else {
                        console.log("else");
                        $.ajax({
                            type: "POST",
                            url: "<?= base_url(); ?>summer_input_contrl/about",
                            data: {content: txt_input, csrf_test_x: $('input[name=csrf_test_x]').val()}, //pass txtarea input with cssrf tolcke
                            dataType: "json",
                            success: function (result) {
                                console.log("successs");
                                console.log(result);
                                dhtmlx.message("Changes has been saved...!");
                            }
                        });

                    }
                }

            </script>
            <style type="text/css">

                .button {
                    margin-left: 20px;
                }
                .note-editor{
                    width: 97%;
                }
            </style>
            <head>
                <title>CMS Editor</title>
                <meta http-equiv="content-type" content="text/html;charset=utf-8" />
            </head>


            <body>
                <div class="container">
                    <div class="row">
                        <?php echo form_open(); ?>
                        <fieldset>
                            <legend>Editor</legend>
                            <div class="">
                                <p class="container ">
                                    <textarea class="input-block-level" id="summernote" name="content" rows="18">
                                        <?php echo $about_us; ?>
                                    </textarea>
                                </p>
                            </div>
                        </fieldset>
                        <?php echo form_close(); ?>
                        <button id="submit" onclick="validation();"  class="btn btn-primary button">Save changes</button>

                    </div>
                </div>
            </body>
            </html>
