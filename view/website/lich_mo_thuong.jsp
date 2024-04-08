<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
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

<meta charset="UTF-8">
<title>Lịch mở thưởng</title>
</head>
<body>
	<c:import url="/view/website/dashboard.jsp"></c:import>
	<%-- --------------------------------------  --%>
	<main class="container my-2 text-center">
		<table class="table table-striped table-hover table-bordered">
			<tbody>
				<tr>
					<th style="width: 15%">Lịch mở thưởng</th>
					<th>Miền Bắc</th>
					<th>Miền Trung</th>
					<th>Miền Nam</th>
				</tr>
				<tr>
					<td><b>Thứ 2</b></td>
					<td>XS Hà Nội</td>
					<td>XS Phú Yên <br>XS Thừa Thiên Huế
					</td>
					<td>XS TP.HCM <br> XS Cà Mau <br> XS Đồng Tháp
					</td>
				</tr>
				<tr>
					<td><b>Thứ 3</b></td>
					<td>XS Quảng Ninh</td>
					<td>XS Đắc Lắc <br>XS Quảng Nam
					</td>
					<td>XS Bạc Liêu <br> XS Bến Tre <br> XS Vũng Tàu
					</td>
				</tr>
				<tr>
					<td><b>Thứ 4</b></td>
					<td>XS Bắc Ninh</td>
					<td>XS Đà Nẵng </br> XS Khánh Hòa
					</td>
					<td>XS Cần Thơ <br> XS Đồng Nai <br> XS Sóc Trăng
					</td>
				</tr>
				<tr>
					<td><b>Thứ 5</b></td>
					<td>XS Hà Nội</td>
					<td>XS Bình Định <br> XS Quảng Bình <br> XS Quảng
						Trị
					</td>
					<td>XS An Giang <br> XS Bình Thuận <br> XS Tây Ninh
					</td>
				</tr>
				<tr>
					<td><b>Thứ 6</b></td>
					<td>XS Hải Phòng</td>
					<td>XS Gia Lai <br> XS Ninh Thuận
					</td>
					<td>XS Bình Dương <br> XS Trà Vinh <br> XS Vĩnh Long
					</td>
				</tr>
				<tr>
					<td><b>Thứ 7</b></td>
					<td>XS Nam Định</td>
					<td>XS Đà Nẵng <br> XS Đắk Nông <br> XS Quảng Ngãi
					</td>
					<td>XS TP.Hồ Chí Minh<br> XS Bình Phước <br> XS Hậu
						Giang <br> XS Long An
					</td>
				</tr>
				<tr>
					<td><b>Chủ Nhật</b></td>
					<td>XS Thái Bình</td>
					<td>XS Khánh Hòa <br> XS Kon Tum <br>XS Thừa Thiên
						Huế
					</td>
					<td>XS Kiên Giang <br> XS Đà Lạt - Lâm Đồng <br> XS
						Tiền Giang
					</td>
				</tr>
			</tbody>
		</table>
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