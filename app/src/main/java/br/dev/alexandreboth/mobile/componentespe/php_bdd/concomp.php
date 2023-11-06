<?php

ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);

$servername = 'localhost'; // Change to your MySQL server hostname
$username = 'root'; // Change to your MySQL username
$password = 'aluno'; // Change to your MySQL password
$dbname = 'componentesbdd'; // Change to your MySQL database name

// Add the following lines to set CORS headers
header("Access-Control-Allow-Origin: *"); // Allow requests from any origin (you can restrict this in a production environment)
header("Access-Control-Allow-Methods: POST, GET"); // Allow POST and GET requests
header("Access-Control-Allow-Headers: Content-Type"); // Allow Content-Type header

$con = new mysqli($servername, $username, $password, $dbname);

if ($con->connect_error) {
    die("Connection failed: " . $con->connect_error);
}

// Retrieve the request parameter
$stringParam = file_get_contents('php://input');

// Convert to JSON parameter
$jsonParamRequest = json_decode($stringParam, true);

// Checking if it's a JSON array
if ($stringParam[0] == '[') {
    $jsonParam = $jsonParamRequest[0]; // Take the first object of the JSON array as the filter
} else {
    $jsonParam = $jsonParamRequest; // Keep what was received if it's a JSON object
}

$json = array();// Create a response array

if (!empty($jsonParam)) {
    // Prepare the WHERE clause
    $whereClause = ' WHERE ';
    foreach ($jsonParam as $field => $value) {
        if ($value != '' && $value != '0') {
            $whereClause .= "$field = '$value' AND ";
        }
    }
    $whereClause = rtrim($whereClause, ' AND ');

    // Prepare the SQL statement
    //$consulta = "SELECT idcomponente, nmcomponente, idtipo, idtensao, idespaco, idgaveta FROM componente $whereClause";

    $consulta = "SELECT idcomponente, nmcomponente, detipo,  detensao,  deespaco,  degaveta 
FROM componente c join espaco e
on c.idespaco = e.idespaco
JOIN gaveta g
on c.idgaveta = g.idgaveta
JOIN tensao t 
on c.idtensao = t.idtensao
JOIN tipo tp
on c.idtipo = tp.idtipo";

// Set the content type to JSON
    header('Content-Type: application/json');

    // Output the JSON data

    //echo $consulta;

    $result = $con->query($consulta);

    if ($result) {
        if ($result->num_rows > 0) {
            while ($row = $result->fetch_assoc()) {
                    $json[] = $row;
            }
        }
    }
}
$result->free_result();
$con->close();
// Send the JSON response
header('Content-Type: application/json; charset=utf-8');
echo json_encode($json);

?>
