<?php

ini_set('display_errors', 1);
ini_set('display_startup_errors', 1);
error_reporting(E_ALL);

$servername = 'localhost';
$username = 'root';
$password = 'aluno';
$dbname = 'componentesbdd';

$con = new mysqli($servername, $username, $password, $dbname);

if ($con->connect_error) {
    die("Connection failed: " . $con->connect_error);
}

// Retrieve the JSON parameter from the POST request
$jsonParam = json_decode(file_get_contents('php://input'), true);

if (!empty($jsonParam)) {
    // Prepare the data for updating
    $idcomponente = isset($jsonParam['idcomponente']) ? intval($jsonParam['idcomponente']) : 0;
    $nmcomponente = isset($jsonParam['nmcomponente']) ? $jsonParam['nmcomponente'] : '';
    $idtipo = isset($jsonParam['idtipo']) ? intval($jsonParam['idtipo']) : 0;
    $idtensao = isset($jsonParam['idtensao']) ? intval($jsonParam['idtensao']) : 0;
    $idespaco = isset($jsonParam['idespaco']) ? intval($jsonParam['idespaco']) : 0;
    $idgaveta = isset($jsonParam['idgaveta']) ? intval($jsonParam['idgaveta']) : 0;

    // Prepare the SQL statement for updating
    $updateQuery = "UPDATE componente SET nmcomponente = '$nmcomponente', idtipo = '$idtipo', idtensao = '$idtensao',
        idespaco = '$idespaco', idgaveta = '$idgaveta' WHERE idcomponente = '$idcomponente'";

    if ($con->query($updateQuery) === true) {
        // Update successful
        $response = array(
            'success' => true,
            'message' => 'Componente atualizado com sucesso!'
        );
        echo json_encode($response);
    } else {
        // Error in update
        $response = array(
            'success' => false,
            'message' => 'Erro ao atualizar o componente: ' . $con->error
        );
        echo json_encode($response);
    }
} else {
    // No data provided
    $response = array(
        'success' => false,
        'message' => 'Dados insuficientes para atualizar o componente!'
    );
    echo json_encode($response);
}

$con->close();

?>
