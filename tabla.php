<?php
$conexion=mysql_connect('localhost','root','','pruebas')
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ejercicio implementado en la base de datos</title>
</head>
<body>
    <br>

    <table>
        <tr>
            <td>--------</td>
            <td>Origen 1</td>
            <td>Origen 2</td>
            <td>Origen 3</td>
            <td>Origen 4</td>
            <td>Ofertas</td>
        </tr>

        <?php
        $sql="SELECT FROM * costomin";
        $result=mysql_query($conexion,$sql);
        while($mostrar=msqli_fetch_array($result)){

        }

        ?>

        <tr>
            <td><?php echo $mostrar['--------']?></td>
            <td><?php echo $mostrar['Origen 1']?></td>
            <td><?php echo $mostrar['Origen 2']?></td>
            <td><?php echo $mostrar['Origen 3']?></td>
            <td><?php echo $mostrar['Origen 4']?></td>
            <td><?php echo $mostrar['Ofertas']?></td>
        </tr>
    </table>
</body>
</html>