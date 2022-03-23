<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

<title>User Details</title>
</head>
<body class="text-white bg-dark ">
	<div class="container mt-5">
		<h1 class="container text-center">Welcome To The Quiz Application</h1>
		<h4 class="container text-center">${message}</h4>
		<form method="post" class="col-md-4 col-md-offset-4 container">
			<div class="form-group">
				<label for="UserName">UserName</label> <input type="text"
					class="form-control" id="UserName" placeholder="UserName"
					name="userName" required>
			</div>
			<div class="form-group">
				<label for="password">Password</label> <input type="password"
					class="form-control" id="password" placeholder="********"
					name="password" required>
			</div>
			<div class="form-group">
				<label for="Type">Type</label> <input type="text"
					class="form-control" id="Type" placeholder="ADMIN/PLAYER"
					name="type" required>
			</div>
			<div class="container text-center">
				<button type="submit" formaction="login"
					class="btn btn-success text-center">Login</button>
				<button type="submit" formaction="register"
					class="btn btn-success text-center">Register</button>
			</div>

		</form>
	</div>
</body>
</html>