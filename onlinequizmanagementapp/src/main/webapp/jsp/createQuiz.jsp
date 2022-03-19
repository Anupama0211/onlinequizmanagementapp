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

<title>Create Quiz</title>
</head>
<body class="text-white bg-dark">
	<div class="container mt-5">
        <form method="post">
        <div class="form-group">
            <label for="title">Title</label> <input type="text"
                class="form-control" id="title" placeholder="Quiz Title...."
                name="title" required >
        </div>
            <label for="questionIds">Select the questions you want to add...</label>
        <select class="form-select" multiple name="questionIds" size="10" required>
            <c:forEach items="${questions}" var="question">
                <option value="${question.questionId}">${question.title}</option>
            </c:forEach>
        </select>
        <div class="container text-center">
            <button type="submit" formaction="insertQuiz"
            class="btn btn-success text-center">Create Quiz</button>
        </div>
        </form>
		<h4>${message}</h4>
		<a href="quizLibraryPortal">
			<button type="button" class="btn btn-light">Back</button>
		</a>
	</div>
</body>
</html>