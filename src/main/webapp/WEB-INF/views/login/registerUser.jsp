<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register User</title>
</head>
<body>
	<h2>User Registration:</h2>
	<form action="registerUser" method="post">
		<pre>
			First Name: <input type="text" name="firstName" />
			Last Name: <input type="text" name="lastName" />
			Email: <input type="text" name="email" />
			Password: <input type="text" name="password" />
			Confirm Password: <input type="text" name="confirmPassword" />
			<input type="submit" value="register" />
		</pre>
	</form>
</body>
</html>