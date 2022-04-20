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
			<caption>Select</caption>
			<tr>
				<th class="text-center"><h1>Questions</h1></th>
			</tr>
			<tr>
				<td class="text-center"><h4>${message}</h4></td>
			</tr>
		</table>
		<form method="post">
		    <div class="form-group">
                				<label for="quizId">QuizID</label> <input type="text"
                					class="form-control" id="quizId" value=${quiz.quizId}
                					name="quizId" readonly>
            </div>
		    <div class="form-group">
        				<label for="title">Title</label> <input type="text"
        					class="form-control" id="title" value=${quiz.title}
        					name="title">
            </div>
            <label for="questionIds">Select the questions you want to add...</label>
			<select class="form-select" multiple name="questionIds" size="10">
				<c:forEach items="${questions}" var="question">
					<option value="${question.questionId}">${question.title}</option>
				</c:forEach>
			</select>
			<div class="container text-center">
				<button type="submit"
					formaction="updateQuiz?quizId=${quiz.quizId}"
					class="btn btn-success text-center">Add Questions</button>
			</div>
		</form>
		<a href="/viewAQuiz?quizId=${quiz.quizId}">
			<button type="button" class="btn btn-light">Back</button>
		</a>
	</div>
</body>
</html>