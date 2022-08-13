<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/common/head.jsp"%>
</head>
<body>
	<%@ include file="/common/header.jsp"%>
	<div class="container">
		<div class="row d-flex justify-content-center align-items-center">

			<div class="col-lg-4 col-12 mb-5">
				<h2 class="tm-text-primary mb-5">Register</h2>
				<form id="register-form" action="register" method="POST"
					class="tm-contact-form mx-auto">
					<div class="form-group">
						<input type="text" name="username" class="form-control rounded-0"
							placeholder="Userame" required />
					</div>
					<div class="form-group">
						<input type="password" name="password" class="form-control rounded-0"
							placeholder="Password" required />
					</div>
					<div class="form-group">
						<input type="password" name="cfmPass" class="form-control rounded-0"
							placeholder="Confirm Password" required />
					</div>
					<div class="form-group">
						<input type="email" name="email" class="form-control rounded-0"
							placeholder="Email" required />
					</div>
					<div class="form-group tm-text-right">
						<button type="submit" class="btn btn-primary">Register</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<%@ include file="/common/footer.jsp"%>
</body>
</html>