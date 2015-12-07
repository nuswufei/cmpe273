<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta content="IE=edge" http-equiv="X-UA-Compatible">
    <meta content="width=device-width, initial-scale=1" name="viewport">
    <title>Server UI</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <!-- load bootstrap css-->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="resources/css/style.css">
    <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Open+Sans:300italic,300,400italic,400,600italic,600,700italic,700,800italic,800">
</head>

<body>
    <div class="container">
        <div class="jumbotron">
            <h2>Server UI</h2>
            <div id="wrapper">
                <div id="ClientInfo">
                    <h3>Client Detail</h3>
                    <p>
                        <strong>Brand:</strong><span id="ClientInfoManufacture"></span><br>
                        <strong>Model:</strong><span id="ClientInfoModel"></span><br>
                        <strong>VIN:</strong><span id="ClientInfoVIN"></span><br>
                        <strong>Year:</strong><span id="ClientInfoYear"></span><br>
                        <strong>Owner:</strong><span id="ClientInfoOwner"></span>
                    </p>
                </div>

                <h3>Client List</h3>
                <div id="ClientList">
                    <table>
                        <thead>
                        <th>Client ID</th>
                        <th>Observe state</th>
                        <th>On</th>
                        <th>Off</th>
                        </thead>
                        <tbody></tbody>
                    </table>
                </div>
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <script src="resources/js/global.js"></script>
    </div>
</body>
</html>