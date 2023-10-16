<?php

error_reporting(E_ALL);
ini_set('display_errors', 1);

// Set up your database connection
$servername = "localhost:3306";
$username = "root";
$password = "aluno";
$database = "componentesbdd";

try {
    $conn = new PDO("mysql:host=$servername;dbname=$database", $username, $password);
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch (PDOException $e) {
    echo "Connection failed: " . $e->getMessage();
    die();
}

// Retrieve the JSON parameter from the POST request
$inputFile = file_get_contents('php://input');
if ($data = json_decode($inputFile, true)) {

    if (json_last_error() === JSON_ERROR_NONE) {
        // Validate and sanitize data here (e.g., using filter_var)
        try {
        // Insert data into the 'componente' table using prepared statements
        $stmt = $conn->prepare("INSERT INTO componente (nmcomponente, idtipo, idtensao, idespaco, idgaveta) VALUES (:nmcomponente, :idtipo, :idtensao, :idespaco, :idgaveta)");

        $nm = $data['nmcomponente'];
        $tp = $data['idtipo'];
        $te = $data['idtensao'];
        $es = $data['idespaco'];
        $ga = $data['idgaveta'];

        $stmt->bindValue(':nmcomponente', $nm);
        $stmt->bindValue(':idtipo', $tp);
        $stmt->bindValue(':idtensao', $te);
        $stmt->bindValue(':idespaco', $es);
        $stmt->bindValue(':idgaveta', $ga);

        /*
        $stmt->bindParam(':nmcomponente', $data['nmcomponente']);
        $stmt->bindParam(':idtipo', $data['idtipo'], PDO::PARAM_INT);
        $stmt->bindParam(':idtensao', $data['idtensao'], PDO::PARAM_INT);
        $stmt->bindParam(':idespaco', $data['idespaco'], PDO::PARAM_INT);
        $stmt->bindParam(':idgaveta', $data['idgaveta'], PDO::PARAM_INT);
        */
        if ($stmt->execute()) {
            $response = [
                "success" => true,
                "message" => "Data saved successfully."
            ];
        } else {
            $response = [
                "success" => false,
                "message" => "Failed to save data."
            ];
        }
    } catch (PDOException $e) {
        echo "ERRO PDO " . $e->getMessage();
    }
    } else {
        $response = [
            "success" => false,
            "message" => "Invalid JSON format."
        ];
    }
} else {
    $response = [
        "success" => false,
        "message" => "Input JSON file not found."
    ];
}

// Return the JSON response
header('Content-Type: application/json');
echo json_encode($response, JSON_PRETTY_PRINT);

// Close the database connection
$conn = null;
?>
