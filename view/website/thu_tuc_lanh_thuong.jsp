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

<title>Thủ tục lãnh thưởng</title>
</head>
<body>
	<!---------------------- navbar---------------------------------->
	<c:import url="/view/website/dashboard.jsp"></c:import>
	<!-- ---------------------------------------------------------- -->
	<main class="container bg-light p-5 mx-auto" style="min-height: 100vh;">
		<div class="blog-post-repeat">
			<h3 class="">THỦ TỤC LÃNH THƯỞNG</h3>
			<div class="text-start mt-3">
				<p class="fw-bold">Khách hàng trúng thưởng cần đến trực tiếp tại
					trụ sở Công ty phát hành vé hoặc đến đại lý vé gần nhất để lĩnh
					thưởng.</p>
				<p class="mt-3">– Khách hàng đến lĩnh thưởng cần mang theo chứng
					minh nhân dân, hộ chiếu hoặc giấy tờ chứng minh nhân thân như giấy
					phép lái xe, hộ khẩu.</p>
				<p class="mt-3">– Khách hàng trúng thưởng được Công ty trả
					thưởng đủ giá trị thưởng 1 lần, bằng VNĐ theo hình thức tiền mặt
					hoặc chuyển khoản qua ngân hàng theo đề nghị của khách hàng.</p>
				<p class="mt-3">– Khách hàng khi nhận thưởng phải nộp thuế theo
					quy định tại điều 2 và điều 15 của thông tư 111/2013/TT-BTC ngày
					15/08/2013 của Bộ tài chính hướng dẫn thực hiện luật thuế Thu nhập
					cá nhân, luật sửa đổi bổ sung, bổ sung một số điều của luật thuế
					Thu nhập cá nhân và nghị định số 65/2013/NĐ-CP của Chính phủ quy
					định chi tiết một số điều của luật thuế Thu nhập cá nhân và luật
					sửa đổi, bổ sung một số điều của luật thuế Thu nhập cá nhân.</p>
				<p>VD: khách hàng A trúng giải ĐB 2.000.000.000đ, theo quy định
					A phải nộp thuế 10% cho phần vượt trên 10.000.000đ, vì vậy:</p>
				<p>
					+ Số tiền thuế A phải nộp: <b>(2.000.000.000đ -10.000.000đ) x
						10% = 199.000.000đ</b>
				</p>
				<p>
					+ Số tiền A thực lãnh: <b>2.000.000.000đ – 199.000.000đ =
						1.801.000.000đ</b>
				</p>
				<p class="mt-3">– Khách hàng trúng thưởng được ủy quyền cho cá
					nhân, tổ chức nhận thưởng thay theo quy định của pháp luật.</p>
				<p class="mt-3">– Khách hàng được công ty giữ bí mật về thông
					tin trả thưởng của cá nhân, ngoại trừ theo yêu cầu của cơ quan quản
					lý Nhà nước có thẩm quyền.</p>
				<p style="text-align: right;">
					<strong>Xổ Số Kiến Thiết Thần Tài</strong>
				</p>
				<div class="postmeta">
					<div class="post-categories"></div>
					<div class="post-tags"></div>
					<div class="clear"></div>

				</div>
				<!-- postmeta -->
				<b>Các tin khác</b>
				<ul class="default-wp-page h_list_relate_post">
					<li><a class="link-secondary text-decoration-none"
						href="${pageContext.request.contextPath}/InformationController?action=xsmb">XỔ SỐ MIỀN BẮC</a></li>
					<li><a class="link-secondary text-decoration-none"
						href="${pageContext.request.contextPath}/InformationController?action=xsmt">XỔ SỐ MIỀN TRUNG</a></li>
					<li><a class="link-secondary text-decoration-none"
						href="${pageContext.request.contextPath}/InformationController?action=xsmn">XỔ SỐ SỐ MIỀN NAM</a></li>
					<li><a class="link-secondary text-decoration-none"
						href="${pageContext.request.contextPath}/InformationController?action=qdlt">QUY
							ĐỊNH LÃNH THƯỞNG</a></li>
				</ul>
			</div>

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