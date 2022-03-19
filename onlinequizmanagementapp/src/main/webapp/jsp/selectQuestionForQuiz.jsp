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
		  <caption>Select</caption>
			<tr>
				<th class="text-center"><h1>Questions</h1></th>
			</tr>
			<tr>
            	<td class="text-center"><h4>${message}</h4></td>
            </tr>
        </table>
        <form method="post">
        <select class="form-select" multiple name="questions" size="10" >
            <c:forEach items="${questions}" var="question">
                <option value="${question.questionId}">${question.title}</option>
            </c:forEach>
        </select>
        <div class="container text-center">
            <button type="submit" formaction="addQuestionInQuiz?quizId=${quizId}"
            class="btn btn-success text-center">Add Question</button>
        </div>
        </form>
		<a href="viewAQuiz?quizId=${quizId}">
			<button type="button" class="btn btn-light">Back</button>
		</a>
	</div>
</body>
</html>