<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<html>
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

<title>Thay đổi mật khẩu</title>
</head>

<body
	onload='<c:if test="${isError==1}">changePasswordFail()</c:if>
<c:if test="${isError==0}">changePasswordSuccess()</c:if>'>

	<!---------------------- navbar---------------------------------->
	<c:import url="/view/website/dashboard.jsp"></c:import>
	<!---------------------------UPDATE------------------------------------------>
	<main
		class="row justify-content-center col-12 col-md-9 mb-2 mx-auto col-lg-10 p-0"
		>
		<div class="row mx-auto p-0">
			<form
				class="row mt-0 col-12 col-sm-8 col-lg-6 justify-content-center mb-0 mx-auto"
				action="${pageContext.request.contextPath}/UpdateAccount?action=changePassword"
				method="post">
				<h2 class="p-0 m-0">Thay đổi mật khẩu:</h2>
				<c:if test='${errorMess!=""}'>
					<p id="messenger" class="text-danger">${errorMess}</p>
				</c:if>

				<div class="form-floating col-12  mb-4 ">
					<input type="text" class="form-control" id="username"
						name="username" value="${account.username}" disabled="disabled" /><label
						for="username">Tên đăng nhập</label>
				</div>
				<div class="form-floating col-12  mb-4 ">
					<input type="text" class="form-control" id="email" name="email"
						value="${account.email}" disabled="disabled" /><label for="email">Email</label>
				</div>
				<div class="form-floating col-12  mb-4 input-group ">
					<input type="password" class="form-control" id="curPasswordRepeat"
						name="curPasswordRepeat" value="${curPasswordRepeat}"
						placeholder="Nhập Mật khẩu hiện tại" /> <span
						class="input-group-text hiddenPassword" onclick="showPassword(this)"><i class="bi bi-eye-slash"></i></span>
					<label for="curPasswordRepeat">Nhập Mật khẩu hiện tại:</label>
				</div>
				<div class="form-floating col-12  mb-4 input-group">
					<input type="password" class="form-control" id="newPassword"
						onchange="alertUpdateSuccess()" name="newPassword"
						value="${newPassword}" placeholder="Mật khẩu mới" /> <span
						class="input-group-text hiddenPassword" onclick="showPassword(this)"><i class="bi bi-eye-slash"></i></span>
					<label for="newPassword">Mật khẩu mới:</label>

				</div>
				<div class="form-floating col-12  mb-4 input-group">
					<input type="password" class="form-control" id="newPasswordRepeat"
						name="newPasswordRepeat" value="${newPasswordRepeat}"
						placeholder="Nhập lại Mật khẩu mới" /> <span
						class="input-group-text hiddenPassword" onclick="showPassword(this)"><i class="bi bi-eye-slash"></i></span>
					<label for="newPasswordRepeat">Nhập lại Mật khẩu mới:</label>
				</div>

				<div class="row justify-content-center">
					<div class="col-7 col-sm-5 text-end">
						<button name="submit" type="submit" value="submit"
							class="btn btn-primary ">Xác nhận</button>
					</div>
					<div class="col-4">
						<button name="submit" type="submit" value="cancel"
							class="btn btn-danger">Hủy</button>
					</div>
				</div>
			</form>
		</div>
	</main>

	<!-- FOOTER -->
	<div style="height: 55px;"></div>
	<c:import url="/view/website/footer.jsp"></c:import>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
		crossorigin="anonymous"></script>
</body>
</html>
