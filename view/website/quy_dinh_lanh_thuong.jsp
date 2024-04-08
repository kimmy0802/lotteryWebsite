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

<title>Quy định lãnh thưởng</title>
</head>
<body>
	<!---------------------- navbar---------------------------------->
	<c:import url="/view/website/dashboard.jsp"></c:import>
	<!-- ---------------------------------------------------------- -->
	<main class="container bg-light p-5 mx-auto "style="min-height: 100vh;">
		<div class="blog-post-repeat">
			<h3 class="">QUY ĐỊNH LÃNH THƯỞNG</h3>
			<div class="mx-3">
				<ul>
					<li>Vé trúng thưởng phải còn nguyên hình, nguyên khổ, không
						rách rời, không chắp vá, không tẩy xóa, không sửa chữa.</li>
					<li>Thời hạn lãnh thưởng của vé số trúng là 30 ngày kể từ ngày
						mở thưởng.</li>
					<li>Lãnh thưởng trọn 1 lần bằng tiền mặt hoặc chuyển khoản
						theo yêu cầu.</li>
					<li><a href="${pageContext.request.contextPath}/InformationController?action=diachilanhthuong" title="click để xem chi tiết địa chỉ lãnh thưởng">Địa chỉ lãnh thưởng:</a> Công ty phát hành vé số.</li>
				</ul>

			</div>
			<div>
				<p class="fst-italic text-decoration-underline">* Trường hợp xem xét trả thưởng và từ chối
					trả thưởng:</p>
				<p>– Trường hợp vì lý do khách quan mà vé trúng thưởng bị phai
					màu, sờn rách, rách rời nhưng chắp vá lại được, vẫn còn đủ căn cứ
					để xác định hình dạng ban đầu và tính xác thực của tờ vé, vị trí
					rách rời không ảnh hưởng đến các yếu tố cần kiểm tra để xác định
					trúng thưởng, không thuộc đối tượng nghi ngờ gian lận và có đơn
					trình bày lý do hợp lý, Công ty phát hành xổ số	xem xét trả thưởng.</p>
				<p>– Trường hợp vé trúng thưởng bị nhàu nát, biến dạng, bị cháy
					xém hoặc rách mất dưới 10% diện tích tờ vé … thì tùy theo tình
					trạng thực tế của tờ vé và kết quả thẩm tra, xác định, mà Công ty phát hành xổ số xem xét trả thưởng hoặc
					từ chối trả thưởng.</p>
				<p>– Trường hợp cần thiết, Công ty phát hành xổ số trưng cầu giám định của cơ quan công an trước khi trả
					thưởng, phí giám định do người sở hữu vé số chi trả.</p>
			</div>
			<div>
				<p class="fst-italic text-decoration-underline">∗ Trường hợp vé xổ số sai sót kỹ thuật:</p>
				<p>– Vé xổ số bị sai sót kỹ thuật là vé xổ số do Công ty phát hành Vé số phát hành nhưng có sai sót trong quá
					trình in vé như: hàng số dự thưởng in không đủ 06 chữ số, trùng số
					sê-ri (cặp vé bị tăng lên), cắt phạm khổ vé, vé hư đóng chung trong
					cặp vé … Trường hợp vé xổ số sai sót kỹ thuật nhưng đủ điều kiện để
					xác định trúng thưởng, Công ty phát hành xổ số 
					lập thủ tục trả thưởng cho người trúng thưởng.</p>
			</div>
			<p style="text-align: right;">
				<strong>Xổ Số Kiến Thiết Thần Tài</strong>
			</p>
			<p class="fw-semibold mt-3">Các tin khác</p>
			<ul class="default-wp-page h_list_relate_post">
				<li><a class="link-secondary text-decoration-none"
						href="${pageContext.request.contextPath}/InformationController?action=xsmb">XỔ SỐ MIỀN BẮC</a></li>
					<li><a class="link-secondary text-decoration-none"
						href="${pageContext.request.contextPath}/InformationController?action=xsmt">XỔ SỐ MIỀN TRUNG</a></li>
					<li><a class="link-secondary text-decoration-none"
						href="${pageContext.request.contextPath}/InformationController?action=xsmn">XỔ SỐ SỐ MIỀN NAM</a></li>
				<li><a class="link-secondary text-decoration-none" href="${pageContext.request.contextPath}/InformationController?action=ttlt">THỦ
						TỤC LÃNH THƯỞNG</a></li>
			</ul>
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