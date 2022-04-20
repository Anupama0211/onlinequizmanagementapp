<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">

<title>Hello, world!</title>
</head>
<body class="text-white bg-dark text-center">
  <div class="card text-center">
      <div class="card-header">
            <ul class="nav nav-tabs card-header-tabs">
              <li class="nav-item">
                <a class="nav-link" href="logout">Logout</a>
              </li>
            </ul>
        </div>
      </div>
	<div class="container mt-5">
		<h4>${message}</h4>
		<h1>Welcome to The Quiz Portal</h1>
		<table class="table table-dark table-hover table-striped">
			<caption>Menu</caption>
			<tr>
				<th><a href="createQuiz" class="link-light">Create A Quiz</a></th>
			</tr>
			<tr>
				<th><a href="viewQuizTitles" class="link-light">View
						Quizzes</a></th>
			</tr>
		</table>
		<a href="adminPortal">
			<button type="button" class="btn btn-light">Back</button>
		</a>
	</div>
</body>
</html>