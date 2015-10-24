<!DOCTYPE html>

<html>
    <head>
        <meta charset="UTF-8">
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

        <title></title>
        <script>
//                    $(document).ready(function () {
//            document.getElementById("instruction").blur();
//            }
        </script>
        <script>

            function findDuplicates() {

                document.getElementById("ok").innerHTML = "";
                document.getElementById("error").innerHTML = "";
                var inputs = document.getElementById("number").value.trim();
                if (inputs.length < 1) {
                    document.getElementById("error").innerHTML = "Input some value and click button... :( :(";
                    return;
                }
                var matches = inputs.match(/[[a-z][A-Z]]/);
                if (matches == null) {
                    document.getElementById("error").innerHTML = "Input only numbers and commas... :( :(";
                    return;
                }
                var inputArr = inputs.split(",");
                var length = inputArr.length;
                var duplicateFound = false;
                for (input in inputArr) {
                    var index = inputArr.indexOf(input);
                    var lastIndex = inputArr.lastIndexOf(input);
                    if (index !== lastIndex) {
                        document.getElementById("error").innerHTML = "You have duplicate values...!:( :(";
                        duplicateFound = true;
                        break;
                    }
                }
                if (duplicateFound === false) {
                    document.getElementById("ok").innerHTML = "You don't have any duplicated values...:)";
                }

//                for (index = 0, len = cars.length, text = ""; i < len; i++) {
//                    text += cars[i] + "<br>";
//                }
            }
        </script>
    </head>
    <body>
        <br><br><br><br><br><br><br><br><br><br>
        <!--        <div class="container center_div">            
                    <div class="col-md-5">-->
        <div class="col-sm-offset-2 col-sm-10">
            <div class="supreme-container">
                <font size="2" color="grey"><p>***Input Numbers Seperated by Commas**</p></font>
            </div>
        </div>
        <form class="form-horizontal">
            <div class="form-group">
                <label for="InputNumber" class="col-sm-2 control-label">Input Numbers</label>
                <div class="col-sm-8">
                    <input type="text" class="form-control" id="number" name="number" placeholder="1 , 2 , 3 "/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="button" class="btn btn-default" onclick="findDuplicates()
                                    ;" id="findDuplicatesButton" value="Find Duplicates"/>
                </div>
            </div>
            <div class="col-sm-offset-2 col-sm-10">
                <font color="red"><b><p id="error"></p></b></font>
                <b><p id="ok"></p></b>
            </div>
        </form>
        <!--</div>-->

        <!--</div>
        
        
        
        </div>-->


    </body>
</html>
