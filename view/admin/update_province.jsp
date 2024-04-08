<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	src="${pageContext.request.contextPath}/js/province.js"></script>
<title>Cập nhật Nhà đài</title>
</head>

<body
	onload='<c:if test="${isError==1}">updateFail("${province.province_id}")</c:if>
<c:if test="${isError==0}">updateSuccess("${province.province_id}")</c:if>'>

	<!---------------------- navbar---------------------------------->
	<c:import url="/view/website/dashboard.jsp"></c:import>
	<!---------------------------UPDATE------------------------------------------>
	<main class="row col-12 mx-auto col-lg-10 px-md-4">
		<div class="col row mx-5">
			<form class="row col-10 justify-content-center"
				action="${pageContext.request.contextPath}/Provinces" method="post">
				<h2 class="p-0 m-0 text-center">Cập nhật Nhà đài</h2>
				<p id="errorMess" class="text-center text-danger">${errorMess}</p>
				<div class="form-floating col-12 col-sm-7 my-2 py-0">
					<input type="text" class="form-control" id="province"
						name="province" value="${province.province}" />
					<p class="text-danger"></p>
					<label for="province">Tên Nhà đài:</label>
				</div>

				<div class="form-floating col-12 col-sm-7 mb-2 py-0">
					<select name="region" id="region" class="form-select"
						aria-label="Default select ">
						<c:forEach var="r" items="${regions}">
							<c:if test='${r==region}'>
								<option value="${r}" selected="selected">${region}</option>
							</c:if>
							<c:if test='${r!=region}'>
								<option value="${r}">${r}</option>
							</c:if>
						</c:forEach>
					</select> <label for="region">Miền:</label>
				</div>
				<div class="form-floating col-12 col-sm-7 my-2">
					<p class="fw-semibold  text-decoration-underline">Chọn ngày mở thưởng:</p>
					<div class="row">
						<div class="col form-check">
							<c:if test="${thu2==true}">
								<input name="thu2" class="form-check-input" type="checkbox"
									checked="checked" id="thu2">
							</c:if>
							<c:if test="${thu2!=true}">
								<input name="thu2" class="form-check-input" type="checkbox"
									id="thu2">
							</c:if>
							 <label class="form-check-label" for="thu2">
								Thứ Hai </label>
						</div>
						<div class=" col form-check">
							<c:if test="${thu3==true}">
								<input name="thu3" class="form-check-input" type="checkbox"
									checked="checked" id="thu3">
							</c:if>
							<c:if test="${thu3!=true}">
								<input name="thu3" class="form-check-input" type="checkbox"
									id="thu3">
							</c:if>
							<label class="form-check-label" for="thu3"> Thứ Ba</label>
						</div>
						<div class="col form-check">
							<c:if test="${thu4==true}">
								<input name="thu4" class="form-check-input" type="checkbox"
									checked="checked" id="thu4">
							</c:if>
							<c:if test="${thu4!=true}">
								<input name="thu4" class="form-check-input" type="checkbox"
									id="thu4">
							</c:if>
							<label class="form-check-label"
								for="thu4"> Thứ Tư </label>
						</div>
						<div class="col form-check">
							<c:if test="${thu5==true}">
								<input name="thu5" class="form-check-input" type="checkbox"
									checked="checked" id="thu5">
							</c:if>
							<c:if test="${thu5!=true}">
								<input name="thu5" class="form-check-input" type="checkbox"
									id="thu5">
							</c:if> <label class="form-check-label"
								for="thu5"> Thứ Năm</label>
						</div>
					</div>
					<div class="row">

						<div class="col form-check">
							<c:if test="${thu6==true}">
								<input name="thu6" class="form-check-input" type="checkbox"
									checked="checked" id="thu6">
							</c:if>
							<c:if test="${thu6!=true}">
								<input name="thu6" class="form-check-input" type="checkbox"
									id="thu6">
							</c:if>
							<label class="form-check-label"
								for="thu6"> Thứ Sáu </label>
						</div>
						<div class="col form-check">
							<c:if test="${thu7==true}">
								<input name="thu7" class="form-check-input" type="checkbox"
									checked="checked" id="thu7">
							</c:if>
							<c:if test="${thu7!=true}">
								<input name="thu7" class="form-check-input" type="checkbox"
									id="thu7">
							</c:if> <label class="form-check-label"
								for="thu7"> Thứ Bảy</label>
						</div>
						<div class=" col form-check">
							<c:if test="${thu8==true}">
								<input name="thu8" class="form-check-input" type="checkbox"
									checked="checked" id="thu8">
							</c:if>
							<c:if test="${thu8!=true}">
								<input name="thu8" class="form-check-input" type="checkbox"
									id="thu8">
							</c:if><label class="form-check-label"
								for="thu8"> Chủ Nhật </label>
						</div>
					</div>
				</div>

				<div class="col-12 col-sm-7  row justify-content-center">
					<input name="action" value="updateProvince" type="hidden"/>
					<input name="province_id" value="${province.province_id}" type="hidden"/>					
					<div class="col-5">
						<button name="submit" type="submit" class="btn btn-primary ">Cập nhật</button>
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
