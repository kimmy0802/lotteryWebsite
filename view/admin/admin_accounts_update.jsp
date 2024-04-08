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
<%-- js --%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/account.js"></script>
<title>Cập nhật thông tin người dùng</title>
</head>

<body
	onload='<c:if test="${isError==1}">updateFromAdminFail("${username}")</c:if>
<c:if test="${isError==0}">updateFromAdminSuccess("${username}")</c:if>'>

	<!---------------------- navbar---------------------------------->
	<c:import url="/view/website/dashboard.jsp"></c:import>
	<!---------------------------UPDATE------------------------------------------>
	<main class="row col-12 mx-auto col-lg-10 px-md-4"
		style="min-height: 100vh;">
		<div class="col row mx-5">
			<form class="row col-10 justify-content-center"
				action="${pageContext.request.contextPath}/UpdateAccounts?action=updateFromAdmin&username=${accountUpdate.username}"
				method="post">
				<h2 class="p-0 m-0 text-center">Cập nhật thông tin người dùng</h2>
				<p id="errorMess" class="text-danger">${errorMess}</p>
				<div class="form-floating col-12 col-sm-7 mt-0 mb-2">
					<input type="text" class="form-control" id="username"
						name="username" value="${accountUpdate.username}"
						disabled="disabled" /><label for="username">Tên đăng nhập</label>
				</div>
				<div class="form-floating col-12 col-sm-7 mb-2">
					<input type="text" class="form-control" id="email" name="email"
						value="${accountUpdate.email}" oninput="checkEmail(this)" />
					<p class="text-danger"></p>
					<label for="email">Email:</label>
				</div>

				<div class="form-floating col-12 col-sm-7 mb-2">
					<input type="text" class="form-control" id="fullname"
						name="fullname" value="${accountUpdate.fullname}"
						oninput="checkFullname(this)" />
					<p class="text-danger"></p>
					<label for="fullname">Họ và tên:</label>
				</div>
				<div class="form-floating col-12 col-sm-7 mb-2">
					<input type="text" class="form-control" id="phonenumber"
						name="phonenumber" value="${accountUpdate.phonenumber}"
						oninput="checkPhonenumber(this)" />
					<p class="text-danger"></p>
					<label for="phonenumber">Số điện thoại</label>
				</div>
				<div class="form-floating col-12 col-sm-7 mb-2">
					<select name="part" class="form-select" id="part"
						aria-label="select part">
						
							<c:if test='${accountUpdate.part=="admin"}'>
								<option value="admin" selected="selected">Admin</option>
								<option value="user">User</option>
							</c:if>
							<c:if test='${accountUpdate.part=="user"}'>
								<option value="admin">Admin</option>
								<option value="user" selected="selected">User</option>
							</c:if>
					</select> <label for="part">Quyền truy cập:</label>
				</div>

				<div class="col-12 col-sm-7  row justify-content-center">
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
	<div style="height: 55px;"></div>
	<c:import url="/view/website/footer.jsp"></c:import>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
		crossorigin="anonymous"></script>
</body>
</html>
