<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta charset="UTF-8">
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

<title>Địa chỉ lãnh thưởng</title>
</head>
<body>
	<!---------------------- navbar---------------------------------->
	<c:import url="/view/website/dashboard.jsp"></c:import>
	<!-- ---------------------------------------------------------- -->
	<main class="container bg-light p-5 mx-auto" style="min-height: 100vh;">
		<div class="blog-post-repeat">
			<h3 class="">ĐỊA CHỈ LÃNH THƯỞNG</h3>
			<h4 class="fst-italic text-decoration-underline">I. Nhận thưởng
				trực tiếp tại Công ty xổ số:</h4>
			<div class="mx-3">
				<ul>

					<li>Thông thường, các công ty xổ số đều đặt ở thành phố. Do
						đó, sau khi theo dõi kết quả xổ số miền bắc hay kết quả xổ số kiến
						thiết miền Nam thì có thể tới thẳng Công ty xổ số trong khu
						vực.Sau đó, đưa ra tờ vé số trúng kèm theo giấy tờ tùy thân của
						mình. Phổ biến nhất là chứng minh nhân dân. Trong trường hợp không
						thể nhận giải thưởng, bạn hoàn toàn có thể viết giấy ủy quyền cho
						người thân của mình tới nhận.</li>
					<li>Cụ thể, Công ty xổ số kiến thiết thủ đô hoạt động tại 53E
						Hàng Bài, Hoàn Kiếm, Hà Nội. Với khu vực phía Nam, nếu như bạn ở
						thành phố Hồ Chí Minh thì có thể đến 77 Trần Nhân Tôn, Phường 9,
						TPHCM. Ngoài ra, miền Nam còn có hệ thống các công ty phân bổ các
						tỉnh thành khác nhau. Cụ thể như An Giang, Bạc Liêu, Đà Lạt, Bến
						Tre, Bình Dương, Sóc Trăng, Cà Mau,… Với những ai tham gia xổ số
						đài miền trung, các công ty xổ số kiến thiết hoạt động tại Bình
						Định, Đắk Lắk, Đắk Nông,…</li>
				</ul>

			</div>
			<h4 class="fst-italic text-decoration-underline">II. Nhận thưởng
				tại đại lý vé xổ:</h4>
			<div class="mx-3">
				<ul>

					<li>Ưu điểm của việc lãnh tiền thưởng xổ số kiến thiết miền
						Nam tại các đại lý chắc chắn phải kể đến thuận tiện. Theo đó, bạn
						không cần phải di chuyển quá xa, đảm bảo mức độ an toàn. Tuy
						nhiên, bạn cần chi trả một khoản phí cho các đại lý nhờ việc nhận
						thưởng trung gian này. Mức phí sẽ chiếm khoảng 0.5-1% tổng số tiền
						thưởng bạn nhận được.</li>
				</ul>

			</div>

			<p style="text-align: right;">
				<strong>Xổ Số Kiến Thiết Thần Tài</strong>
			</p>
			<p class="fw-semibold mt-3">Các tin khác</p>
			<ul class="default-wp-page h_list_relate_post">
				<li><a class="link-secondary text-decoration-none"
					href="${pageContext.request.contextPath}/InformationController?action=xsmb">XỔ
						SỐ MIỀN BẮC</a></li>
				<li><a class="link-secondary text-decoration-none"
					href="${pageContext.request.contextPath}/InformationController?action=xsmt">XỔ
						SỐ MIỀN TRUNG</a></li>
				<li><a class="link-secondary text-decoration-none"
					href="${pageContext.request.contextPath}/InformationController?action=xsmn">XỔ
						SỐ SỐ MIỀN NAM</a></li>
				<li><a class="link-secondary text-decoration-none"
					href="${pageContext.request.contextPath}/InformationController?action=ttlt">THỦ
						TỤC LÃNH THƯỞNG</a></li>
			</ul>
		</div>
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