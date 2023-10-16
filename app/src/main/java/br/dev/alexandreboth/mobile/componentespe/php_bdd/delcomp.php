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

// Retrieve the JSON parameter from the POST request
$jsonParam = json_decode(file_get_contents('php://input'), true);

if (!empty($jsonParam)) {
    // Prepare the data for deletion
    $idcomponente = isset($jsonParam['idcomponente']) ? intval($jsonParam['idcomponente']) : 0;

    // Prepare the SQL statement for deletion
    $deleteQuery = "DELETE FROM componente WHERE idcomponente = '$idcomponente'";

    if ($con->query($deleteQuery) === true) {
        // Deletion successful
        $response = array(
            'success' => true,
            'message' => 'Componente excluído com sucesso!'
        );
        echo json_encode($response);
    } else {
        // Error in deletion
        $response = array(
            'success' => false,
            'message' => 'Erro ao excluir o componente: ' . $con->error
        );
        echo json_encode($response);
    }
} else {
    // No data provided
    $response = array(
        'success' => false,
        'message' => 'Dados insuficientes para excluir o componente!'
    );
    echo json_encode($response);
}

$con->close();

?>
