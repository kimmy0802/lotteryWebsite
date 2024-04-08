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
<title>Cập nhật tài khoản</title>
</head>
<body
	onload='<c:if test="${isError==1}">updateAccountFail()</c:if> <c:if test="${isError==0}">updateAccountSuccess()</c:if>'>

	<!---------------------- navbar---------------------------------->
	<c:import url="/view/website/dashboard.jsp"></c:import>
	<!---------------------------UPDATE------------------------------------------>
	<main
		class="row justify-content-center col-md-9 mx-auto col-lg-10 px-md-4">
		<div class="col row mx-5 pt-5">
			<form class="row col-10 justify-content-center"
				action="${pageContext.request.contextPath}/UpdateAccount?action=updateAccount"
				method="post">
				<h2 class="p-0 m-0">Cập nhật thông tin Cá nhân</h2>
				<p id="errorMess"  class="text-danger">${errorMess}</p>

				<div class="form-floating col-12 col-lg-6 mb-2">
					<input type="text" class="form-control" id="username"
						name="username" value="${account.username}" disabled="disabled" /><label
						for="username">Tên đăng nhập</label>
				</div>
				<div class="form-floating col-12 col-lg-6 mb-2">
					<input type="text" class="form-control" id="email" name="email"
						disabled="disabled" value="${account.email}" /> <label
						for="email">Email:</label>
				</div>

				<div class="form-floating col-12 col-lg-6 mb-2">
					<input type="text" class="form-control" id="fullname"
						oninput='checkInput("fullname")' name="fullname"
						value="${fullname}" /><label for="fullname">Họ và tên:</label>
					<p class="text-danger" id="fullnameMess"></p>
				</div>
				<div class="form-floating col-12 col-lg-6 mb-2">
					<input type="text" class="form-control" id="phonenumber"
						oninput='checkInput("phonenumber")' name="phonenumber"
						value="${phonenumber}" /> <label for="phonenumber">Số
						điện thoại</label>
					<p class="text-danger" id="phonenumberMess"></p>
				</div>
				<div class="row justify-content-center">
					<div class="col-5">
						<button name="submit" type="submit" value="update"
							class="btn btn-primary ">Cập nhật</button>
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
	<div  style="height: 55px;"></div>
	<c:import url="/view/website/footer.jsp"></c:import>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
		crossorigin="anonymous"></script>
</body>
</html>
