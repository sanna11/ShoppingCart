<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
    </head>
    <body>
        <form method="post" enctype="multipart/form-data" action="upload.php">
            <table width="350" border="0" cellpadding="1" cellspacing="1" class="box">
                <tr> 
                    <td width="246">
                        <input type="hidden" name="MAX_FILE_SIZE" value="2000000">
                        <input name="userfile" type="file" id="userfile"> 
                    </td>
                    <td width="80"><input name="upload" type="submit" class="box" id="upload" value=" Upload "></td>
                </tr>
            </table>
        </form>
    </body>
</html>
