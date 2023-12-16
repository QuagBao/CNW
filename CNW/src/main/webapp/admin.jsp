<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Page</title>
</head>
<body>
    <h2>Welcome, ${authenticatedUser.fullName}!</h2>
    <p>You have access to the admin page.</p>
    <a href="HomeController?action=logout">Logout</a>
</body>
</html>