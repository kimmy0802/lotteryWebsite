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
<!-- JS -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/account.js"></script>	
<title>Quên mật khẩu</title></head>

<body onload='<c:if test="${isError==1}">forgetPasswordFail()</c:if>
<c:if test="${isError==0}">forgetPasswordSuccess()</c:if>'>

	<!---------------------- navbar---------------------------------->
	<c:import url="/view/website/dashboard.jsp"></c:import>
	<!---------------------------UPDATE------------------------------------------>
	<main
		class="row justify-content-center  mx-auto  px-md-4" >
			<h2 class="mt-5 text-center">Nhập thông tin để cấp lại mật khẩu
				mới</h2>
			<form class="col-10 col-md-5 row mx-auto pt-3 justify-content-center"
				action="${pageContext.request.contextPath}/ForgetPassword?action=resetPassword"
				method="post">
				
				<p class="text-danger">${errorMess}</p>				
				<div class="form-floating mb-3 ">
					<input type="text" class="form-control" id="email" name="email"
						value="${email}" oninput="checkEmail(this)"/>
					<p class="text-danger"></p>	
						<label for="email">Email</label>
				</div>
				<div class="text-center col mx-auto">
						<button name="submit" type="submit" value="submit"
							class="btn btn-primary">Xác nhận</button>
					</div>
			</form>
	</main>

	<!-- FOOTER -->
	<div style="height:55px;"></div>
	<c:import url="/view/website/footer.jsp"></c:import>
	
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
		crossorigin="anonymous"></script>
</body>
</html>
