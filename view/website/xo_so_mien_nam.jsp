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
	
<title>Xổ số truyền thống Miền Nam.</title>
</head>
<body>
	<!---------------------- navbar---------------------------------->
	<jsp:include page="/view/website/dashboard.jsp"></jsp:include>
	<!-- ---------------------------------------------------------- -->
	<main class="container bg-light p-5 mx-auto">
		<div class="">
			<h3 class="">XỔ SỐ TRUYỀN THỐNG MIỀN NAM</h3>
			<div class="text-start mt-3">
				<p>Xổ số truyền thống Miền Nam được quay số mở thưởng lúc <b>16 giờ 15 phút</b> hàng
					tuần. Với mệnh giá 10.000 đ,vé số truyền thống miền Nam có tất cả 18 lô trúng: 9 giải thưởng, 9
					giải phụ đặc biệt, 45 giải khuyến khích trong đó:</p>
				<div class="text-start mx-auto">
					<table class="table table-border mx-auto">
						<tbody>
							<tr>
								<th>Giải thưởng</th>
								<th>Số lượng giải</th>
								<th>Số lần quay</th>
								<th>Số</th>
								<th>Giá trị giải thưởng</th>

							</tr>
							<tr>
								<td style="width: 151px;">Giải ĐB</td>
								<td style="width: 80px;">1</td>
								<td style="width: 85px;">1</td>
								<td style="width: 42px;">6 số</td>
								<td style="width: 185px;"><strong>2.000.000.000đ</strong></td>
							</tr>
							<tr>
								<td style="width: 151px;">Giải nhất</td>
								<td style="width: 80px;">10</td>
								<td style="width: 85px;">1</td>
								<td style="width: 42px;">5 số</td>
								<td style="width: 185px;"><strong>30.000.000đ</strong></td>
							</tr>
							<tr>
								<td style="width: 151px;">Giải nhì</td>
								<td style="width: 80px;">10</td>
								<td style="width: 85px;">1</td>
								<td style="width: 42px;">5 số</td>
								<td style="width: 185px;"><strong>15.000.000đ</strong></td>
							</tr>
							<tr>
								<td style="width: 151px;">Giải ba</td>
								<td style="width: 80px;">20</td>
								<td style="width: 85px;">2</td>
								<td style="width: 42px;">5 số</td>
								<td style="width: 185px;"><strong>10.000.000đ</strong></td>
							</tr>
							<tr>
								<td style="width: 151px;">Giải tư</td>
								<td style="width: 80px;">70</td>
								<td style="width: 85px;">7</td>
								<td style="width: 42px;">5 số</td>
								<td style="width: 185px;"><strong>3.000.000đ</strong></td>
							</tr>
							<tr>
								<td style="width: 151px;">Giải năm</td>
								<td style="width: 80px;">100</td>
								<td style="width: 85px;">1</td>
								<td style="width: 42px;">4 số</td>
								<td style="width: 185px;"><strong>1.000.000đ</strong></td>
							</tr>
							<tr>
								<td style="width: 151px;">Giải sáu</td>
								<td style="width: 80px;">300</td>
								<td style="width: 85px;">3</td>
								<td style="width: 42px;">4 số</td>
								<td style="width: 185px;"><strong>400.000đ</strong></td>
							</tr>
							<tr>
								<td style="width: 151px;">Giải bảy</td>
								<td style="width: 80px;">1.000</td>
								<td style="width: 85px;">1</td>
								<td style="width: 42px;">3 số</td>
								<td style="width: 185px;"><strong>200.000đ</strong></td>
							</tr>
							<tr>
								<td style="width: 151px;">Giải tám</td>
								<td style="width: 80px;">10.000</td>
								<td style="width: 85px;">1</td>
								<td style="width: 42px;">2 số</td>
								<td style="width: 185px;"><strong>100.000đ</strong></td>
							</tr>
							<tr>
								<td style="width: 151px;">Giải phụ ĐB</td>
								<td style="width: 80px;">9</td>
								<td style="width: 85px;"></td>
								<td style="width: 42px;">5 số</td>
								<td style="width: 185px;"><strong>50.000.000đ</strong></td>
							</tr>
							<tr>
								<td style="width: 151px;">Giải khuyến khích</td>
								<td style="width: 80px;">45</td>
								<td style="width: 85px;"></td>
								<td style="width: 42px;">5 số</td>
								<td style="width: 185px;"><strong>6.000.000đ</strong></td>
							</tr>
						</tbody>
					</table>
				</div>
				<p align="center"> <b><a class="link-warning text-decoration-none"
						href="${pageContext.request.contextPath}/index.jsp">XỔ SỐ KIẾN
							THIẾT THẦN TÀI</a> </b>nơi cập nhật thông tin trúng thưởng <b> NHANH -
						CHÍNH XÁC NHẤT</b></p>
				<p style="text-align: justify;">
					–&nbsp;<strong>Giải phụ đặc biệt</strong>: cho những vé trúng 5 chữ
					số cuối cùng liên tiếp theo hàng thứ tự của giải đặc biệt.<br>
					VD: Giải ĐB là&nbsp;<strong>467828</strong>, số của bạn là&nbsp;<strong><u>3</u></strong><strong>67828</strong>&nbsp;thì
					trúng giải phụ đặc biệt trị giá là&nbsp;<strong>50.000.000đ&nbsp;</strong>mỗi
					giải.<br> –&nbsp;<strong>Giải khuyến khích</strong>: cho những
					vé chỉ sai một số bất kỳ hàng nào so với giải đặc biệt 6 số (ngoại
					trừ sai số ở hàng trăm ngàn).<br> VD: Giải ĐB là&nbsp;<strong>467828</strong>,
					số của bạn là&nbsp;<strong>46782<u>9</u></strong>&nbsp;hay&nbsp;<strong>4678<u>3</u>8
					</strong>&nbsp;thì trúng giải khuyến khích trị giá&nbsp;<strong>6.000.000đ</strong>&nbsp;mỗi
					giải.
				</p>
				<p style="text-align: right;">
					<strong>Xổ Số Kiến Thiết Thần Tài</strong>
				</p>
				
				<b>Các tin khác</b>
				<ul class="default-wp-page h_list_relate_post">
					<li><a class="link-secondary text-decoration-none"
						href="${pageContext.request.contextPath}/InformationController?action=xsmb">XỔ SỐ MIỀN BẮC</a></li>

					<li><a class="link-secondary text-decoration-none"
						href="${pageContext.request.contextPath}/InformationController?action=xsmt">XỔ SỐ MIỀN TRUNG</a></li>
					<li><a class="link-secondary text-decoration-none"
						href="${pageContext.request.contextPath}/InformationController?action=ttlt">THỦ
							TỤC LÃNH THƯỞNG</a></li>

					<li><a class="link-secondary text-decoration-none"
						href="${pageContext.request.contextPath}/InformationController?action=qdlt">QUY
							ĐỊNH LÃNH THƯỞNG</a></li>

				</ul>
			</div>

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