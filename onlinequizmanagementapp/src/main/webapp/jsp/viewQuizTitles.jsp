<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<body class="text-white bg-dark">
	<div class="container mt-5">

		<table class="table table-dark table-hover table-striped">
		  <caption>Quizzes</caption>
			<tr>
				<th class="text-center"><h1>Quizzes</h1></th>
			</tr>
			<c:forEach items="${quizzes}" var="quiz">
				<tr>
					<td>Quiz ID: ${quiz.quizId}
					${quiz.title}<br> <br>
					</td>
				</tr>
				<tr>
					<td><a href="viewAQuiz?quizId=${quiz.quizId}">View</a>|
					<a href="deleteAQuiz?quizId=${quiz.quizId}">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
		<h4>${message}</h4>
		<a href="quizLibraryPortal">
			<button type="button" class="btn btn-light">Back</button>
		</a>
	</div>
</body>
</html>