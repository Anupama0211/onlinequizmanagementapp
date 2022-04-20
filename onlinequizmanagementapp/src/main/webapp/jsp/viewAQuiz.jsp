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

		<table class="table table-dark table-hover table-striped">
			<caption>Quiz</caption>
			<tr>
				<th class="text-center"><h1>ID:${id}&nbsp;&nbsp;${title}</h1></th>
			</tr>

			<c:forEach items="${questions}" var="question">
				<tr>
					<td>Question ID: ${question.questionId} ${question.title}<br>
						<br> <c:forEach items="${question.options}" var="option">
						>>${option.value}&nbsp;&nbsp;${option.answer}<br>
						</c:forEach> <br> Difficulty:${question.difficulty}||
						Topic:${question.topic}|| Marks:${question.marks}
					</td>
				</tr>
				<tr>
					<td><a
						href="deleteQuestionInQuiz?questionId=${question.questionId}&quizId=${id}">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
		<h4>${message}</h4>
		<div class="container text-center">
			<a href="selectQuestionForQuiz?quizId=${id}">
				<button type="button" class="btn btn-light">Add Question</button>
			</a>
		</div>
		<a href="viewQuizTitles">
			<button type="button" class="btn btn-light">Back</button>
		</a>
	</div>
</body>
</html>