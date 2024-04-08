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
<title>Login page</title>

</head>

<body>
	<!---------------------- navbar---------------------------------->
	<c:import url="/view/website/dashboard.jsp"></c:import>
	<!--  -------------------------------------------------------- -->
	<div class="row container m-auto mt-2">
		<div class="col-12 col-md-6 context p-3 m-2 bg-light">
			<div class="form p-3 ">
				<form name="login_form"
					action="${pageContext.request.contextPath}/LoginWithSystemAccountController"
					method="post">
					<h2 class="text-center">Đăng nhập</h2>
					<input type="hidden" name="action" value="login">
					<p id="errorMess" style="color: red; index: 1000;">${errorMess}</p>
					<div class="input-group my-2 row mx-auto">
						<input class="col-10 form-control py-3" type="text"
							placeholder="Tên đăng nhập:" name="username" value="${username}"
							oninput="checkUsername(this)" /> 
							<p class="text-danger"></p>

					</div>
					<div class="input-group mb-2">
						<input type="password" class="form-control py-3" id="password"
							name="password" value="${password}"
							placeholder="Nhập Mật khẩu" /> <span
							class="input-group-text hiddenPassword" onclick="showPassword(this)" ><i class="bi bi-eye-slash"></i></span>

					</div>
					<input class="mb-2" type="checkbox" checked="checked" name="rememberme" id="remenberme" > <label for="remenberme">Ghi nhớ </label>
					<div class="col mx-auto row justify-content-center mb-3">
						<input class="col btn btn-danger "
							style="-bs-white-rgb: 248, 246, 240; - -bs-danger: #dc3545"
							type="submit" value="Đăng nhập">
					</div>

				</form>


				<div class="row justify-content-center text-center mt-4">
					<p>
						Đăng nhập qua <a
							class="btn btn-danger btn-sm  px-3 py-1 link-light"
							style="text-decoration-line: none;"
							href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/LotteryWeb/LoginWithGoogleController&response_type=code
    &client_id=359929206342-era8qo70h6fj0kc87ei11c4ab2n2v7tp.apps.googleusercontent.com&approval_prompt=force"><i
							class="bi bi-google"></i> Google</a> <span> hoặc</span> <a
							class="btn btn-primary btn-sm px-2 py-1 link-light"
							style="text-decoration-line: none;"
							href="https://www.facebook.com/dialog/oauth?client_id=340544328412559&redirect_uri=http://localhost:8080/LotteryWeb/LoginWithFb"><i
							class="bi bi-facebook"></i> Facebook</a>
				</div>

				<div class="row text-center mt-4">
					<div class="col">
						<a href="${pageContext.request.contextPath}/ForgetPassword">Quên
							Mật khẩu?</a>
					</div>

					<div class="col">
						<a href="${pageContext.request.contextPath}/Register">Đăng ký</a>
					</div>
				</div>



			</div>

		</div>
		<div class="col-12 col-md-4  p-3 m-2 d-md-block">
			<div class="border-white mt-0 pt-0">
				<div class="container ">
					<h5 class="text-center mt-0 pt-0">
						Chào mừng đến với trang <br> Vé số Thần Tài !
					</h5>
					<p class=" text-center">
						<a href="${pageContext.request.contextPath}/Login"
							style="text-decoration: none;">Đăng Nhập</a> hoặc <a
							href="${pageContext.request.contextPath}/Register"
							style="text-decoration: none;">Đăng ký</a> để sử dụng tốt hơn
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