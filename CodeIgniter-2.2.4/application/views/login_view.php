<?php session_start(); ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>Simple Login with CodeIgniter</title>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0-alpha1/jquery.js"></script>
        <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.11.3.min.js"></script>
        <script>
            $(document).ready(function(){
                $("#login").click(function(){
                   type: "POST",
                   url: "verifylogin.php",
                   dataType: 'json',
                   data: {name:username,pwd:password},
                   success: function (html) {  
                      var msg=html.return;
                      alert(msg);
                   },
                   error: function (jqXHR, textStatus, errorThrown) {
                   }
                });
            });
        </script>
    </head>
    <body>
        <h1>Login</h1>
        <?php echo validation_errors(); ?>

        <label for="username">Username:</label>
        <input type="text" size="20" id="username" name="username"/>
        <br/>
        <label for="password">Password:</label>
        <input type="password" size="20" id="passowrd" name="password"/>
        <br/>
        <input type="button" id="login" value="Login"/>
        </form>
    </body>
</html>