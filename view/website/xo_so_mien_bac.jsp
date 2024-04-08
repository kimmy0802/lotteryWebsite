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

<title>Xổ số truyền thống Miền Bắc.</title>
</head>
<body>
	<!---------------------- navbar---------------------------------->	
	<c:import url="/view/website/dashboard.jsp"></c:import>
	<!-- ---------------------------------------------------------- -->
	<main class="container mx-auto bg-light" style="min-height: 100vh;">
		<div class="p-3 text-align-justify;">
			<h3 align="center">XỔ SỐ TRUYỀN THỐNG MIỀN BẮC</h3>
			<p>
				<b>Xổ số kiến thiết miền Bắc</b> (gọi tắt là <b>XSKT Miền Bắc</b>)là
				loại hình xổ số có in sẵn trước giá vé, các chữ số, chữ cái để khách
				hàng lựa chọn tham gia dự thưởng. Số lượng các chữ số, chữ cái được
				giới hạn trong phạm vi vé số phát hành và việc xác định kết quả
				trúng thưởng được thực hiện sau thời điểm phát hành vé số.
			</p>
			<p>
				Hàng ngày, Hội đồng xổ số kiến thiết miền Bắc tiến hành quay số mở
				thưởng công khai bắt đầu từ <b>18h15ph.</b>
			</p>
			<p align="center">
				<b><a class="link-warning text-decoration-none"
					href="${pageContext.request.contextPath}/index.jsp">XỔ SỐ KIẾN
						THIẾT THẦN TÀI</a> </b>nơi cập nhật thông tin trúng thưởng <b> NHANH -
					CHÍNH XÁC NHẤT</b>
			</p>
			<p>Cơ cấu trả thưởng vé XSKT Miền Bắc thường kỳ như sau:</p>
			<ul>
				<li><span style="font-size: 14px;"><span><strong>Loại
								vé 10.000 đ – 15 ký hiệu</strong></span></span></li>
				<li><span style="font-size: 14px;"><span><strong>06&nbsp;giải
								đặc biệt</strong>: mỗi giải trị giá 500.000.000 đ. Quay 1 lần 5 số và 6
							ký hiệu.</span></span></li>
				<li><span style="font-size: 14px;"><span><strong>9
								giải phụ của giải đặc biệt</strong>: mỗi giải trị giá 25.000.000 đ cho
							các vé trúng cả 5 số của giải đặc biệt nhưng khác ký hiệu.</span></span></li>
				<li><span style="font-size: 14px;"><span><strong>15.000
								giải khuyến khích của giải đặc biệt</strong>: mỗi giải trị giá 40.000 đ
							cho các vé có 2 số cuối trùng với 2 số cuối của giải đặc biệt.</span></span></li>
				<li><span style="font-size: 14px;"><span>Vé trùng
							nhiều giải được lĩnh đủ giá trị các giải</span></span></li>
			</ul>
			<div class="text-start mx-auto">
				<table class="table table-border mx-auto">
					<tbody>
						<tr>
							<th>Giải thưởng</th>
							<th>Số lượng giải</th>
							<th>Số chữ số</th>
							<th>Giá trị giải thưởng</th>
							<th>Tổng giá trị giải thưởng</th>

						</tr>
						<tr>
							<td>Giải Đặc Biệt</td>
							<td>06</td>
							<td>5 số</td>
							<td>500.000.000 đ</td>
							<td><strong>3.000.000.000 đ</strong></td>
						</tr>
						<tr>
							<td>Giải nhất</td>
							<td>15</td>
							<td>5 số</td>
							<td>10.000.000 đ</td>
							<td><strong>150.000.000đ</strong></td>
						</tr>
						<tr>
							<td>Giải nhì</td>
							<td>30</td>
							<td>5 số</td>
							<td>5.000.000 đ</td>
							<td><strong>150.000.000đ</strong></td>
						</tr>
						<tr>
							<td>Giải ba</td>
							<td>90</td>
							<td>5 số</td>
							<td>1.000.000 đ</td>
							<td><strong>9.000.000đ</strong></td>
						</tr>
						<tr>
							<td>Giải tư</td>
							<td>600</td>
							<td>4 số</td>
							<td>400.000 đ</td>
							<td><strong>240.000.000đ</strong></td>
						</tr>
						<tr>
							<td>Giải năm</td>
							<td>900</td>
							<td>4 số</td>
							<td>200.000 đ</td>
							<td><strong>180.000.000đ</strong></td>
						</tr>
						<tr>
							<td>Giải sáu</td>
							<td>4.500</td>
							<td>3 số</td>
							<td>100.000 đ</td>
							<td><strong>450.000.000đ</strong></td>
						</tr>
						<tr>
							<td>Giải bảy</td>
							<td>60.000</td>
							<td>2 số</td>
							<td>40.000 đ</td>
							<td><strong>2.400.000đ</strong></td>
						</tr>
						<tr>
							<td>Giải phụ ĐB</td>
							<td>09</td>
							<td>5 số</td>
							<td>25.000.000 đ</td>
							<td><strong>225.000.000đ</strong></td>
						</tr>
						<tr>
							<td>Giải khuyến khích</td>
							<td>60.000</td>
							<td>2 số</td>
							<td>40.000 đ</td>
							<td><strong>600.000.000đ</strong></td>
						</tr>
						<tr>
							<th>Tổng cộng</th>
							<th>81.150</th>
							<th></th>
							<th></th>
							<th><strong>7.485.000.000đ</strong></th>
						</tr>
					</tbody>
				</table>
			</div>
			<p style="text-align: right;">
				<strong>Xổ Số Kiến Thiết Thần Tài</strong>
			</p>
		</div>
		<p class="fw-semibold mt-3">Các tin khác</p>
		<ul class="default-wp-page h_list_relate_post">
			<li><a class="link-secondary text-decoration-none"
				href="${pageContext.request.contextPath}/InformationController?action=xsmt">XỔ SỐ MIỀN TRUNG</a></li>
			<li><a class="link-secondary text-decoration-none"
				href="${pageContext.request.contextPath}/InformationController?action=xsmn">XỔ SỐ MIỀN NAM</a></li>
			<li><a class="link-secondary text-decoration-none"
				href="${pageContext.request.contextPath}/InformationController?action=qdlt">QUY
					ĐỊNH LÃNH THƯỞNG</a></li>
			<li><a class="link-secondary text-decoration-none"
				href="${pageContext.request.contextPath}/InformationController?action=ttlt">THỦ
					TỤC LÃNH THƯỞNG</a></li>
		</ul>
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