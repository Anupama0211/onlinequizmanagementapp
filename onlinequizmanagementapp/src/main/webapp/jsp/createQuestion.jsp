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
		<h1 class="container text-center">Create A Question</h1>
		<form method="post" class="col-md-4 col-md-offset-4 container">
			<div class="form-group">
				<label for="title">Title</label> <input type="text"
					class="form-control" id="title" placeholder="Question....."
					name="title" required size=4>
			</div>
			<div class="form-group">
				<select class="form-select" name="difficulty" required>
					<option selected>Difficulty</option>
					<option value="Easy">Easy</option>
					<option value="Medium">Medium</option>
					<option value="Hard">Hard</option>
				</select>
			</div>
			<div class="form-group">
				<label for="topic">Topic</label> <input type="text"
					class="form-control" id="topic" placeholder="Topic" name="topic"
					required>
			</div>
			<div class="form-group">
				<label for="marks">Marks</label> <input type="number"
					class="form-control" id="marks" placeholder="Marks" name="marks"
					required>
			</div>
			<div class="form-group">
				<label for="options1">Option 1</label> <input type="text"
					class="form-control" id="options1" placeholder="Option 1"
					name="options[0].value" required> <label for="isAnswer">Is
					it the answer?</label> <select class="form-select"
					name="options[0].answer" >
					<option value="true">Yes</option>
					<option value="false" selected>No</option>
				</select>
			</div>
			<div class="form-group">
				<label for="options2">Option 2</label> <input type="text"
					class="form-control" id="options2" placeholder="Option 2"
					name="options[1].value"  required> <label for="isAnswer">Is
					it the answer?</label> <select class="form-select"
					name="options[1].answer" >
					<option value="true">Yes</option>
					<option value="false" selected>No</option>
				</select>

			</div>
			<div class="form-group">
				<label for="options3">Option 3</label> <input type="text"
					class="form-control" id="options3" placeholder="Option 3"
					name="options[2].value"> <label for="isAnswer">Is
					it the answer?</label> <select class="form-select"
					name="options[2].answer">
					<option value="true">Yes</option>
					<option value="false" selected>No</option>
				</select>

			</div>
			<div class="form-group">
				<label for="options4">Option 4</label> <input type="text"
					class="form-control" id="options4" placeholder="Option 4"
					name="options[3].value"> <label for="isAnswer">Is
					it the answer?</label> <select class="form-select"
					name="options[3].answer">
					<option value="true">Yes</option>
					<option value="false" selected>No</option>
				</select>

			</div>
			<div class="container text-center">
				<button type="submit" formaction="addQuestion"
					class="btn btn-success text-center">Add Question</button>
				<a href="questionLibraryPortal">
					<button type="button" class="btn btn-light">Back</button>
				</a>
			</div>
		</form>

	</div>


	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
</body>
</html>