<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<h1 class="container text-center">Update A Question</h1>
		<h4>${message}</h4>
		<form method="post" class="col-md-4 col-md-offset-4 container">
			<div class="form-group">
				<label for="questionId">Question ID</label> <input type="text"
					class="form-control" id="questionId" name="questionId"
					value="${question.questionId}" readonly="readonly" size=4>
			</div>
			<div class="form-group">
				<label for="title">Title</label> <input type="text"
					class="form-control" id="title" value="${question.title}"
					name="title">
			</div>
			<div class="form-group">
				<select class="form-select" name="difficulty">
					<option selected>${question.difficulty}</option>
					<option value="Easy">Easy</option>
					<option value="Medium">Medium</option>
					<option value="Hard">Hard</option>
				</select>
			</div>
			<div class="form-group">
				<label for="topic">Topic</label> <input type="text"
					class="form-control" id="topic" value="${question.topic}"
					name="topic">
			</div>
			<div class="form-group">
				<label for="marks">Marks</label> <input type="number"
					class="form-control" id="marks" value="${question.marks}"
					name="marks">
			</div>
			<label for="options">Options</label>

			<c:forEach items="${question.options}" var="option" varStatus="loop">
				<div class="form-group">
					<input type="text" class="form-control" id="options"
						name="options[${loop.index}].value" value="${option.value}">
					<label for="isAnswer">Is it the answer?</label> <select
						class="form-select" name="options[${loop.index}].answer"
						required>
						<option selected value="${option.answer}">"${option.answer}"</option>
						<option value="true">Yes</option>
						<option value="false">No</option>
					</select>
				</div>
			</c:forEach>

			<div class="container text-center">
				<button type="submit" formaction="updateQuestion"
					class="btn btn-success text-center">Update Question</button>
				<a href="viewQuestions">
					<button type="button" class="btn btn-light">Back</button>
				</a>
			</div>
		</form>


	</div>
</body>
</html>