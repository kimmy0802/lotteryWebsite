<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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
<title>Công ty TNHH MTV Thần Tài</title>
</head>
<body>
	<c:import url="/view/website/dashboard.jsp"></c:import>
	<%-- --------------------------------------  --%>
	<main class="container bg-light p-2 mx-auto px-lg-5"
		style="min-height: 100vh;">

		<h2>XỔ SỐ KIẾN THIẾT THẦN TÀI</h2>
		<div class="text-start">
			<p>- Trước 2005 thông tin kết quả xổ số (KQXS) từ Công Ty Xổ Số ở
				các tỉnh đến đại lý chủ yếu bằng radio hoặc fax, các đại lý lớn tổng
				hợp kết quả chung các đài xổ trong ngày trên 1 bảng in gọi là "giấy
				dò" hay "vé dò" rồi chuyển fax đến các đại lý cấp dưới photo phát
				cho người bán vé số dạo. Trước đó nữa, máy in là khái niệm công
				nghiệp chỉ có ở tòa soạn, nhà máy in... thì kqxs được viết tay trên
				những cuốn sổ, một số đại lý sáng kiến làm bộ số thủ công bằng gỗ
				sắp chữ, lấy mực bằng temper... Bộ vé liên kết xổ số kiến thiết các
				tỉnh Miền Nam gồm 21 tỉnh, Miền Trung 14 tỉnh và Miền Bắc. Các Công
				Ty Xổ Số mở thưởng tại mỗi tỉnh khác nhau nên công việc cập nhật
				hàng ngày cần nhân sự tương đối nhiều.</p>
			<p>
				- Vì KQXS đòi hỏi nhanh - chính xác, hệ thống fax đã đầu tư chi phí
				cao nhưng không thể đáp ứng số lượng kết nối, thường xuyên quá tải
				nên chúng tôi phải tìm giải pháp thay thế đó là lý do <a
					href="${pageContext.request.contextPath}/index.jsp"
					title="Đi đến trang Xổ số kiến thiết Thần Tài">Xổ số Kiến thiết
					THẦN TÀI</a> ra đời và dần dần được anh chị em đại lý gần xa giới thiệu
				với nhau, các công cụ tìm kiếm như Google, Yahoo!, Bing ... đánh giá
				cao trên nhiều từ khóa thế là website trở thành địa chỉ tin cậy của
				cộng đồng mạng về thông tin trực tiếp kết quả xổ số, đứng đầu Google
				rất nhiều từ khóa liên quan và có hàng triệu người truy cập thường
				xuyên.
			</p>
			<p>- Hệ thống được đầu tư trang thiết bị, băng thông, công nghệ,
				nguồn lực, nhân sự, mở rộng và update liên tục, tự hào là website
				trực tiếp tổng hợp KQXS Việt Nam nhanh - chính xác nhất, là người
				bạn đồng hành tin cậy cùng đại lý xổ số.</p>
			<p>- Với ý nghĩa kiến thiết bên trong mỗi tờ vé bán ra trên mọi
				nẻo đường chúng tôi tìm thấy hạnh phúc, có nhiều động lực hơn để
				hoàn thiện mình. Tổng doanh thu bán vé được các công ty xổ số trích
				trả thưởng 50%, người bán lẻ trực tiếp đến khách hàng được hưởng 12%
				điều đó giải quyết nhiều mưu sinh cho người khuyết tật, người sức
				khỏe kém, lao động mất sức... Nhưng quan trọng nhất là tất cả lợi
				nhuận sau khi trừ các chi phí được nộp vào ngân sách nhà nước và để
				lại địa phương chi vào y tế, trường học, các công trình phúc lợi xã
				hội từ đó quê hương đất nước ngày một tươi đẹp hơn. Chúng tôi rất
				vui, rất tự hào khi giới thiệu nghề của mình là "người bán vé số"
				ước mơ mỗi ngày bán được nhiều hơn!</p>
			<p>Chúng tôi là những người yêu thích xổ số, điều ấy mang đến cho
				chúng tôi cuộc sống, lòng biết ơn thể hiện qua thái độ làm việc, rất
				nhiều việc không có thu nhập cũng vui vẻ nhiệt huyết. Tình yêu
				thương, sự nhẫn nại làm cho khách hàng đến với Minh Ngọc thấy thân
				thiện, tin tưởng... Tuy nhiên chúng tôi mong muốn nhiều hơn thế,
				mong muốn phụng sự, đưa công nghệ vào để cải thiện hoạt động, tạo
				giá trị gia tăng, phát triển hệ sinh thái mang lại nhiều giá trị hơn
				cho cộng đồng qua đó cũng mang lại đời sống nhân viên được tốt hơn.</p>
		</div>

		<h3>Tầm nhìn và Sứ mệnh:</h3>
		<ul>

			<li>
				<p>
					<span class="fw-semibold">Định hướng:</span> Là Công ty Công nghệ &
					Hệ thống phân phối tại thị trường Việt Nam.
				</p>
			</li>
			<li>
				<p>
					<span class="fw-semibold">Sứ mệnh:</span> Luôn đồng hành cùng đại
					lý xổ số toàn quốc, phát triển hệ sinh thái gia tăng giá trị. Hơn
					một lần nữa thay đổi toàn diện phương thức và hình ảnh "người bán
					vé số".
				</p>
			</li>
			<li>
				<p>
					<span class="fw-semibold">Giá trị cốt lõi:</span></br> - Là sự kết hợp
					hài hòa giữa truyền thống và hiện đại, đưa thế mạnh của niềm đam mê
					công nghệ giải quyết những bài toán thiết thực trong cuộc sống kiến
					tạo giá trị bền vững theo thời gian.</br> -Tập thể nội bộ là một khối
					thống nhất, khởi đầu chính trực, con đường đúng đắn, mục đích rõ
					ràng, môi trường tam bảo[3]. Lãnh đạo xuất thân từ thực tiển cuộc
					sống, hiểu rõ nhu cầu và thị trường, vạch chiến lược cụ thể trên
					tầm nhìn khả thi, quản trị rủi ro phát triển bền vững nhưng không
					thiếu phần quyết liệt cho những giấc mơ.
				</p>
			</li>
			<li>
				<p>
					<span class="fw-semibold">Văn hóa:</span> - Mỗi con người là một
					đại diện, một câu chuyện thật về tình yêu thương, lòng biết ơn và
					truyền cảm hứng trên hành trình phát triển của Chúng tôi.</br> -Cuộc
					sống bình dị để có đủ thời gian làm một việc dù nhỏ nhất cũng trở
					nên hoàn hảo nhất.
				</p>
			</li>

		</ul>
		<div class="text-center">
			<h4>ỔN ĐỊNH - UY TÍN - THÂN THIỆN & PHÁT TRIỂN!</h4>
			<p class="fst-italic">Xin chân thành cảm ơn quý khách hàng, anh chị em đại lý, bạn
				hàng bán lẻ đã đồng hành và tin tưởng!...</p>
		</div>
		<p class="text-end fw-bold">XỐ SỐ KIẾN THIẾT THẦN TÀI</p>

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