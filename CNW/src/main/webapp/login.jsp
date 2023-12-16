<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
	<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
	<!------ Include the above in your HEAD tag ---------->
	
	
	<!--Pulling Awesome Font -->
	<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
</head>
<body>
    
    <form class="col-md-offset-5 col-md-3" action="HomeController_1" method="post">
        <input type="hidden" name="action" value="login">
        <h2>Login</h2>
        <label for="username">Username:</label>
        <input type="text" class="form-control input-sm chat-input" name="username" required>
        <br>
        <label for="password">Password:</label>
        <input type="password" class="form-control input-sm chat-input" name="password" required>
        <br>
        <button type="submit" class="btn btn-primary btn-md">Login <i class="fa fa-sign-in"></i></button>
        <br><br>
        <p>${errorMessage}</p>
    </form>
    
</body>
</html>
