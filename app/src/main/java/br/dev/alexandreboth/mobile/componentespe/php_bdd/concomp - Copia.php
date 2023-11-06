<?php

ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);

$servername = 'localhost'; // Change to your MySQL server hostname
$username = 'root'; // Change to your MySQL username
$password = 'aluno'; // Change to your MySQL password
$dbname = 'componentesbdd'; // Change to your MySQL database name

$con = new mysqli($servername, $username, $password, $dbname);

if ($con->connect_error) {
    die("Connection failed: " . $con->connect_error);
}

// Retrieve the JSON parameter
$jsonParam = json_decode(file_get_contents('php://input'), true);

if (!empty($jsonParam)) {
    // Prepare the WHERE clause
    $whereClause = ' WHERE ';
    foreach ($jsonParam as $field => $value) {
        if ($value !== '' && $value !== '0') {
            $whereClause .= "$field = '$value' AND ";
        }
    }
    $whereClause = rtrim($whereClause, ' AND ');

    // Prepare the SQL statement
    $consulta = "SELECT idcomponente, nmcomponente, idtipo, idtensao, idespaco, idgaveta FROM componente $whereClause";

    $result = $con->query($consulta);

    $json = array();

    if ($result->num_rows > 0) {
        while ($row = $result->fetch_assoc()) {
            $componente = array(
                "idcomponente" => $row['idcomponente'],
                "nmcomponente" => $row['nmcomponente'],
                "idtipo" => $row['idtipo'],
                "idtensao" => $row['idtensao'],
                "idespaco" => $row['idespaco'],
                "idgaveta" => $row['idgaveta']
            );
            $json[] = $componente;
        }
    } else {
        $componente = array(
            "idcomponente" => 0,
            "nmcomponente" => "",
            "idtipo" => 0,
            "idtensao" => 0,
            "idespaco" => 0,
            "idgaveta" => 0
        );
        $json[] = $componente;
    }

    if ($json) {
        $encoded_json = json_encode($json);
        if ($encoded_json === false) {
            echo "Error encoding JSON: " . json_last_error_msg();
        } else {
            header('Content-Type: application/json; charset=utf-8');
            echo $encoded_json;
        }
    } else {
        echo "Empty JSON data.";
    }

    $result->free_result();
}

$con->close();

?>
