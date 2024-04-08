<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html lang="vi">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<!--Bootstrap core CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM"
	crossorigin="anonymous" />

<!-- Icons-->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" />

<!-- Style css-->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" />
	
<!-- js -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/account.js"></script>
	
<title>Register</title>
</head>
<body
	onload='<c:if test="${success==0}">alertCreateFail()</c:if>
<c:if test="${success==1}">alertCreateSuccess()</c:if>'>
	<!---------------------- navbar---------------------------------->
	<c:import url="/view/website/dashboard.jsp"></c:import>
	<!--  -------------------------------------------------------- -->
	<div class="row container m-auto mt-2">
		<div class="col-12 col-md-6 p-3 m-2 bg-light">
			<div class="form p-3 ">
				<form name="register_form"
					action="${pageContext.request.contextPath}/Register" method="post">
					<h2 class="text-center">Đăng ký tài khoản mới:</h2>
					<input type="hidden" name="action" value="doRegister">
					<p  id="errorMess" style="color: red; index: 1000;">${errorMess}</p>
					<div class="input-group my-2 row mx-auto">
						<input class="col-10 form-control py-3" type="text"
							placeholder="Tên đăng nhập:" name="username" value="${username}"
							oninput="checkUsername(this)" />
						<p class="text-danger"></p>

					</div>
					<div class="input-group my-2 row mx-auto">
						<input class="col-10 form-control py-3" type="text"
							placeholder="Email:" name="email" value="${email}"
							oninput="checkEmail(this)" />
						<p class="text-danger"></p>
					</div>
					<div class="input-group my-2 row mx-auto">
						<input class="col-10 form-control py-3" type="text"
							placeholder="Họ và tên:" name="fullname" value="${fullname}"
							oninput="checkFullname(this)" />
						<p class="text-danger"></p>

					</div>
					<div class="input-group my-2 row mx-auto">
						<input class="col-10 form-control py-3" type="text"
							placeholder="Số điện thoại:" name="phonenumber"
							value="${phonenumber}" oninput="checkPhonenumber(this)" />
						<p class="text-danger"></p>

					</div>
					<div class="col mx-auto row justify-content-center mb-3">
						<input class="col btn btn-danger "
							style="-bs-white-rgb: 248, 246, 240; - -bs-danger: #dc3545"
							type="submit" value="Đăng ký">
					</div>

				</form>
			</div>


			<div class="col mx-auto row justify-content-center mt-3 text-center">
				<p>
					Đăng nhập qua <a
						class="btn btn-danger btn-sm  px-2 py-1 link-light"
						style="text-decoration-line: none;"
						href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/Lottery/LoginWithGoogle&response_type=code
    &client_id=359929206342-fo9e37sdola03ue8matorj4epuqq1kq4.apps.googleusercontent.com&approval_prompt=force"><i
						class="bi bi-google"></i> Google</a> <span> hoặc</span> <a
						class="btn btn-primary btn-sm px-2 py-1 link-light"
						style="text-decoration-line: none;"
						href="https://www.facebook.com/dialog/oauth?client_id=340544328412559&redirect_uri=http://localhost:8080/Lottery/LoginWithFb"><i
						class="bi bi-facebook"></i> Facebook</a>
			</div>

		</div>
		<div class="col-12 col-md-4  p-3 m-2 d-md-block">
			<div class="border-white mt-0 pt-0">
				<div class="container ">
					<h5 class="text-center mt-0 pt-0">
						Chào mừng đến với trang <br> Vé số Thần Tài !
					</h5>
					<p class=" text-center">
						<a href="${pageContext.request.contextPath}/Login">Đăng Nhập</a>
						hoặc <a href="${pageContext.request.contextPath}/Register">Đăng
							ký</a> để sử dụng tốt hơn
					</p>
				</div>
				<div>
					<img class=" h-75 w-100"
						src="${pageContext.request.contextPath}/images/avatar.jpg"
						alt="avatar">
				</div>
			</div>
		</div>
	</div>
	
	<!-- FOOTER -->
	<div style="height:55px;"></div>
	<c:import url="/view/website/footer.jsp"></c:import>
	
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
		crossorigin="anonymous"></script>
</body>
</html>