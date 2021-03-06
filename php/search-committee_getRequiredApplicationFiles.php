<?php
/**
 * Created by PhpStorm.
 * User: asa
 * Date: 3/5/2020
 * Time: 7:27 PM
 */

require_once './dbcontroller.php';

$conn = new DBController();

$data = array();

//get description of each application file
$sql = "SELECT AutoID, IsRequired, Description, ShortDescription FROM tlkpApplicationFiles f WHERE f.IsRequired = 1";

$result = $conn->runSelectQuery($sql);
if ($result->num_rows > 0) {
    while($row = $result->fetch_assoc()) {
        $data[] = $row;
    }
}

echo json_encode($data);