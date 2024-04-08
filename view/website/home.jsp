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
<title>Menu</title>
</head>
<body>
	<c:import url="/view/website/dashboard.jsp"></c:import>
	<%-- --------------------------------------  --%>
	<main class="row  bg-light mx-0 p-1">
		<!-- --------------------------left content-------------------------------- -->
		<div class="px-0 col-lg-2 "
			style="--bs-btn-hover-color: #D80032; background-color: #F5EBEB;">
			<div class="col px-0  border-end my-1">
				<form class="bg-danger"
					action="${pageContext.request.contextPath}/LotteryOfProvince"
					method="post">
					<input name="order" value="desc" type="hidden" /> <input
						name="colOrder" value="lottery_date" type="hidden" /> <input
						name="province" value="Tất cả" type="hidden" /> <input
						name="region" value="Miền Nam" type="hidden" /> <input
						name="action" value="filter" type="hidden" /> <input
						class="btn btn-outline-none px-2" type="submit"
						value="XỔ SỐ MIỀN NAM">
				</form>
				<ul>
					<li>
						<form class=""
							action="${pageContext.request.contextPath}/LotteryOfProvince"
							method="post">
							<input name="order" value="desc" type="hidden" /> <input
								name="colOrder" value="lottery_date" type="hidden" /> <input
								name="province" value="TP. Hồ Chí Minh" type="hidden" /> <input
								name="region" value="Miền Nam" type="hidden" /> <input
								name="action" value="filter" type="hidden" /> <input
								class="btn btn-outline-none" type="submit"
								value="TP. Hồ Chí Minh">
						</form>
					</li>
					<li>
						<form class=""
							action="${pageContext.request.contextPath}/LotteryOfProvince"
							method="post">
							<input name="order" value="desc" type="hidden" /> <input
								name="colOrder" value="lottery_date" type="hidden" /> <input
								name="province" value="An Giang" type="hidden" /> <input
								name="region" value="Miền Nam" type="hidden" /> <input
								name="action" value="filter" type="hidden" /> <input
								class="btn btn-outline-none" type="submit" value="An Giang">
						</form>
					</li>
					<li>
						<form class=""
							action="${pageContext.request.contextPath}/LotteryOfProvince"
							method="post">
							<input name="order" value="desc" type="hidden" /> <input
								name="colOrder" value="lottery_date" type="hidden" /> <input
								name="province" value="Bình Dương" type="hidden" /> <input
								name="region" value="Miền Nam" type="hidden" /> <input
								name="action" value="filter" type="hidden" /> <input
								class="btn btn-outline-none" type="submit" value="Bình Dương">
						</form>
					</li>
					<li>
						<form class=""
							action="${pageContext.request.contextPath}/LotteryOfProvince"
							method="post">
							<input name="order" value="desc" type="hidden" /> <input
								name="colOrder" value="lottery_date" type="hidden" /> <input
								name="province" value="Bạc Liêu" type="hidden" /> <input
								name="region" value="Miền Nam" type="hidden" /> <input
								name="action" value="filter" type="hidden" /> <input
								class="btn btn-outline-none" type="submit" value="Bạc Liêu">
						</form>
					</li>
					<li>
						<form class=""
							action="${pageContext.request.contextPath}/LotteryOfProvince"
							method="post">
							<input name="order" value="desc" type="hidden" /> <input
								name="colOrder" value="lottery_date" type="hidden" /> <input
								name="province" value="Bình Phước" type="hidden" /> <input
								name="region" value="Miền Nam" type="hidden" /> <input
								name="action" value="filter" type="hidden" /> <input
								class="btn btn-outline-none" type="submit" value="Bình Phước">
						</form>
					</li>
					<li>
						<form class=""
							action="${pageContext.request.contextPath}/LotteryOfProvince"
							method="post">
							<input name="order" value="desc" type="hidden" /> <input
								name="colOrder" value="lottery_date" type="hidden" /> <input
								name="province" value="Bến Tre" type="hidden" /> <input
								name="region" value="Miền Nam" type="hidden" /> <input
								name="action" value="filter" type="hidden" /> <input
								class="btn btn-outline-none" type="submit" value="Bến Tre">
						</form>
					</li>
					<li>
						<form class=""
							action="${pageContext.request.contextPath}/LotteryOfProvince"
							method="post">
							<input name="order" value="desc" type="hidden" /> <input
								name="colOrder" value="lottery_date" type="hidden" /> <input
								name="province" value="Bình Thuận" type="hidden" /> <input
								name="region" value="Miền Nam" type="hidden" /> <input
								name="action" value="filter" type="hidden" /> <input
								class="btn btn-outline-none" type="submit" value="Bình Thuận">
						</form>
					</li>
					<li>
						<form class=""
							action="${pageContext.request.contextPath}/LotteryOfProvince"
							method="post">
							<input name="order" value="desc" type="hidden" /> <input
								name="colOrder" value="lottery_date" type="hidden" /> <input
								name="province" value="Cà Mau" type="hidden" /> <input
								name="region" value="Miền Nam" type="hidden" /> <input
								name="action" value="filter" type="hidden" /> <input
								class="btn btn-outline-none" type="submit" value="Cà Mau">
						</form>
					</li>
					<li>
						<form class=""
							action="${pageContext.request.contextPath}/LotteryOfProvince"
							method="post">
							<input name="order" value="desc" type="hidden" /> <input
								name="colOrder" value="lottery_date" type="hidden" /> <input
								name="province" value="Cần Thơ" type="hidden" /> <input
								name="region" value="Miền Nam" type="hidden" /> <input
								name="action" value="filter" type="hidden" /> <input
								class="btn btn-outline-none" type="submit" value="Cần Thơ">
						</form>
					</li>
					<li>
						<form class=""
							action="${pageContext.request.contextPath}/LotteryOfProvince"
							method="post">
							<input name="order" value="desc" type="hidden" /> <input
								name="colOrder" value="lottery_date" type="hidden" /> <input
								name="province" value="Đà Lạt- Lâm Đồng" type="hidden" /> <input
								name="region" value="Miền Nam" type="hidden" /> <input
								name="action" value="filter" type="hidden" /> <input
								class="btn btn-outline-none" type="submit"
								value="Đà Lạt- Lâm Đồng">
						</form>
					</li>
					<li>
						<form class=""
							action="${pageContext.request.contextPath}/LotteryOfProvince"
							method="post">
							<input name="order" value="desc" type="hidden" /> <input
								name="colOrder" value="lottery_date" type="hidden" /> <input
								name="province" value="Đồng Nai" type="hidden" /> <input
								name="region" value="Miền Nam" type="hidden" /> <input
								name="action" value="filter" type="hidden" /> <input
								class="btn btn-outline-none" type="submit" value="Đồng Nai">
						</form>
					</li>
					<li>
						<form class=""
							action="${pageContext.request.contextPath}/LotteryOfProvince"
							method="post">
							<input name="order" value="desc" type="hidden" /> <input
								name="colOrder" value="lottery_date" type="hidden" /> <input
								name="province" value="Đồng Tháp" type="hidden" /> <input
								name="region" value="Miền Nam" type="hidden" /> <input
								name="action" value="filter" type="hidden" /> <input
								class="btn btn-outline-none" type="submit" value="Đồng Tháp">
						</form>
					</li>
					<li>
						<form class=""
							action="${pageContext.request.contextPath}/LotteryOfProvince"
							method="post">
							<input name="order" value="desc" type="hidden" /> <input
								name="colOrder" value="lottery_date" type="hidden" /> <input
								name="province" value="Hậu Giang" type="hidden" /> <input
								name="region" value="Miền Nam" type="hidden" /> <input
								name="action" value="filter" type="hidden" /> <input
								class="btn btn-outline-none" type="submit" value="Hậu Giang">
						</form>
					</li>
					<li>
						<form class=""
							action="${pageContext.request.contextPath}/LotteryOfProvince"
							method="post">
							<input name="order" value="desc" type="hidden" /> <input
								name="colOrder" value="lottery_date" type="hidden" /> <input
								name="province" value="Kiên Giang" type="hidden" /> <input
								name="region" value="Miền Nam" type="hidden" /> <input
								name="action" value="filter" type="hidden" /> <input
								class="btn btn-outline-none" type="submit" value="Kiên Giang">
						</form>
					</li>
					<li>
						<form class=""
							action="${pageContext.request.contextPath}/LotteryOfProvince"
							method="post">
							<input name="order" value="desc" type="hidden" /> <input
								name="colOrder" value="lottery_date" type="hidden" /> <input
								name="province" value="Long An" type="hidden" /> <input
								name="region" value="Miền Nam" type="hidden" /> <input
								name="action" value="filter" type="hidden" /> <input
								class="btn btn-outline-none" type="submit" value="Long An">
						</form>
					</li>
					<li>
						<form class=""
							action="${pageContext.request.contextPath}/LotteryOfProvince"
							method="post">
							<input name="order" value="desc" type="hidden" /> <input
								name="colOrder" value="lottery_date" type="hidden" /> <input
								name="province" value="Sóc Trăng" type="hidden" /> <input
								name="region" value="Miền Nam" type="hidden" /> <input
								name="action" value="filter" type="hidden" /> <input
								class="btn btn-outline-none" type="submit" value="Sóc Trăng">
						</form>
					</li>
					<li>
						<form class=""
							action="${pageContext.request.contextPath}/LotteryOfProvince"
							method="post">
							<input name="order" value="desc" type="hidden" /> <input
								name="colOrder" value="lottery_date" type="hidden" /> <input
								name="province" value="Tiền Giang" type="hidden" /> <input
								name="region" value="Miền Nam" type="hidden" /> <input
								name="action" value="filter" type="hidden" /> <input
								class="btn btn-outline-none" type="submit" value="Tiền Giang">
						</form>
					</li>
					<li>
						<form class=""
							action="${pageContext.request.contextPath}/LotteryOfProvince"
							method="post">
							<input name="order" value="desc" type="hidden" /> <input
								name="colOrder" value="lottery_date" type="hidden" /> <input
								name="province" value="Tây Ninh" type="hidden" /> <input
								name="region" value="Miền Nam" type="hidden" /> <input
								name="action" value="filter" type="hidden" /> <input
								class="btn btn-outline-none" type="submit" value="Tây Ninh">
						</form>
					</li>
					<li>
						<form class=""
							action="${pageContext.request.contextPath}/LotteryOfProvince"
							method="post">
							<input name="order" value="desc" type="hidden" /> <input
								name="colOrder" value="lottery_date" type="hidden" /> <input
								name="province" value="Trà Vinh" type="hidden" /> <input
								name="region" value="Miền Nam" type="hidden" /> <input
								name="action" value="filter" type="hidden" /> <input
								class="btn btn-outline-none" type="submit" value="Trà Vinh">
						</form>
					</li>
					<li>
						<form class=""
							action="${pageContext.request.contextPath}/LotteryOfProvince"
							method="post">
							<input name="order" value="desc" type="hidden" /> <input
								name="colOrder" value="lottery_date" type="hidden" /> <input
								name="province" value="Vĩnh Long" type="hidden" /> <input
								name="region" value="Miền Nam" type="hidden" /> <input
								name="action" value="filter" type="hidden" /> <input
								class="btn btn-outline-none" type="submit" value="Vĩnh Long">
						</form>
					</li>
					<li>
						<form class=""
							action="${pageContext.request.contextPath}/LotteryOfProvince"
							method="post">
							<input name="order" value="desc" type="hidden" /> <input
								name="colOrder" value="lottery_date" type="hidden" /> <input
								name="province" value="Vũng Tàu" type="hidden" /> <input
								name="region" value="Miền Nam" type="hidden" /> <input
								name="action" value="filter" type="hidden" /> <input
								class="btn btn-outline-none" type="submit" value="Vũng Tàu">
						</form>
					</li>

				</ul>
			</div>
		</div>
		<!-- ---------------------------center content------------------------------ -->
		<div class="col-12 col-lg-8 mt-3 mx-auto px-2">
			<h3 class="text-center mb-4">Kết Quả xổ số 3 miền:</h3>
			<!-- display lotteries list -->
			<div id="contextLottery" class="table-responsive small text-start">
				<p class="fs-5 fw-semibold">
					1. Miền Bắc:
					<c:out value="${lotteryShowMB.get(0).dayOfWeek}"></c:out>, ngày
					<span class="text-danger fs-5 fw-semibold"><c:out value="${lotteryShowMB.get(0).date}"></c:out></span>
				</p>
				<table
					class="table table-striped  table-sm text-start border border-3 border-dark">
					<thead class="table-danger">
						<tr class="align-top border ">
							<th class="border " scope="col">Giải</th>
							<c:forEach var="result" items="${lotteryShowMB}">
								<th class="border text-center item-lottery position-relative"
									scope="col">
									<p>${result.province}</p>
								</th>
							</c:forEach>
						</tr>
					</thead>
					<tbody class="table-group-divider">
						<tr>
							<th class="border col-1" scope="col">ĐB</th>
							<c:forEach var="result" items="${lotteryShowMB}">
								<td class="border text-center"><c:out
										value="${result.lottery.special_prize}"></c:out></td>
							</c:forEach>
						</tr>
						<tr>
							<th class="border col-1" scope="col">1</th>
							<c:forEach var="result" items="${lotteryShowMB}">
								<td class="border text-center"><c:out
										value="${result.lottery.first_prize}"></c:out></td>
							</c:forEach>
						</tr>
						<tr>
							<th class="border col-1" scope="col">2</th>
							<c:forEach var="result" items="${lotteryShowMB}">
								<td class="border text-center"><c:out
										value="${result.lottery.second_prize}"></c:out></td>
							</c:forEach>
						</tr>
						<tr>
							<th class="border col-1" scope="col">3</th>
							<c:forEach var="result" items="${lotteryShowMB}">
								<td class="border text-center"><c:out
										value="${result.lottery.third_prize}"></c:out></td>
							</c:forEach>
						</tr>
						<tr>
							<th class="border col-1" scope="col">4</th>
							<c:forEach var="result" items="${lotteryShowMB}">
								<td class="border text-center"><c:out
										value="${result.lottery.fourth_prize}"></c:out></td>
							</c:forEach>
						</tr>
						<tr>
							<th class="border col-1" scope="col">5</th>
							<c:forEach var="result" items="${lotteryShowMB}">
								<td class="border text-center"><c:out
										value="${result.lottery.fifth_prize}"></c:out></td>
							</c:forEach>
						</tr>
						<tr>
							<th class="border col-1" scope="col">6</th>
							<c:forEach var="result" items="${lotteryShowMB}">
								<td class="border text-center"><c:out
										value="${result.lottery.sixth_prize}"></c:out></td>
							</c:forEach>
						</tr>
						<tr>
							<th class="border col-1" scope="col">7</th>
							<c:forEach var="result" items="${lotteryShowMB}">
								<td class="border text-center"><c:out
										value="${result.lottery.seventh_prize}"></c:out></td>
							</c:forEach>
						</tr>
					</tbody>
				</table>

				<p class="fs-5 fw-semibold">
					2. Miền Trung:
					<c:out value="${lotteryShowMT.get(0).dayOfWeek}"></c:out>, ngày
					<span class="text-danger fs-5 fw-semibold"><c:out value="${lotteryShowMT.get(0).date}"></c:out></span>
				</p>
				<table
					class="table table-striped  table-sm text-start border border-3 border-dark">
					<thead class="table-danger">
						<tr class="align-top border ">
							<th class="border " scope="col">Giải</th>
							<c:forEach var="result" items="${lotteryShowMT}">
								<th class="border text-center item-lottery position-relative"
									scope="col">
									<p>${result.province}</p>
								</th>
							</c:forEach>
						</tr>
					</thead>
					<tbody class="table-group-divider">
						<tr>
							<th class="border col-1" scope="col">ĐB</th>
							<c:forEach var="result" items="${lotteryShowMT}">
								<td class="border text-center"><c:out
										value="${result.lottery.special_prize}"></c:out></td>
							</c:forEach>
						</tr>
						<tr>
							<th class="border col-1" scope="col">1</th>
							<c:forEach var="result" items="${lotteryShowMT}">
								<td class="border text-center"><c:out
										value="${result.lottery.first_prize}"></c:out></td>
							</c:forEach>
						</tr>
						<tr>
							<th class="border col-1" scope="col">2</th>
							<c:forEach var="result" items="${lotteryShowMT}">
								<td class="border text-center"><c:out
										value="${result.lottery.second_prize}"></c:out></td>
							</c:forEach>
						</tr>
						<tr>
							<th class="border col-1" scope="col">3</th>
							<c:forEach var="result" items="${lotteryShowMT}">
								<td class="border text-center"><c:out
										value="${result.lottery.third_prize}"></c:out></td>
							</c:forEach>
						</tr>
						<tr>
							<th class="border col-1" scope="col">4</th>
							<c:forEach var="result" items="${lotteryShowMT}">
								<td class="border text-center"><c:out
										value="${result.lottery.fourth_prize}"></c:out></td>
							</c:forEach>
						</tr>
						<tr>
							<th class="border col-1" scope="col">5</th>
							<c:forEach var="result" items="${lotteryShowMT}">
								<td class="border text-center"><c:out
										value="${result.lottery.fifth_prize}"></c:out></td>
							</c:forEach>
						</tr>
						<tr>
							<th class="border col-1" scope="col">6</th>
							<c:forEach var="result" items="${lotteryShowMT}">
								<td class="border text-center"><c:out
										value="${result.lottery.sixth_prize}"></c:out></td>
							</c:forEach>
						</tr>
						<tr>
							<th class="border col-1" scope="col">7</th>
							<c:forEach var="result" items="${lotteryShowMT}">
								<td class="border text-center"><c:out
										value="${result.lottery.seventh_prize}"></c:out></td>
							</c:forEach>
						</tr>
						<tr>
							<th class="border col-1" scope="col">8</th>
							<c:forEach var="result" items="${lotteryShowMT}">
								<td class="border text-center"><c:out
										value="${result.lottery.eighth_prize}"></c:out></td>
							</c:forEach>
						</tr>
					</tbody>
				</table>

				<p class="fs-5 fw-semibold">
					3. Miền Nam:
					<c:out value="${lotteryShowMN.get(0).dayOfWeek}"></c:out>, ngày
					<span class="text-danger fs-5 fw-semibold"><c:out value="${lotteryShowMN.get(0).date}"></c:out></span>
				</p>
				<table
					class="table table-striped  table-sm text-start border border-3 border-dark">
					<thead class="table-danger">
						<tr class="align-top border ">
							<th class="border " scope="col">Giải</th>
							<c:forEach var="result" items="${lotteryShowMN}">
								<th class="border text-center item-lottery position-relative"
									scope="col">
									<p>${result.province}</p>
								</th>
							</c:forEach>
						</tr>
					</thead>
					<tbody class="table-group-divider">
						<tr>
							<th class="border col-1" scope="col">ĐB</th>
							<c:forEach var="result" items="${lotteryShowMN}">
								<td class="border text-center"><c:out
										value="${result.lottery.special_prize}"></c:out></td>
							</c:forEach>
						</tr>
						<tr>
							<th class="border col-1" scope="col">1</th>
							<c:forEach var="result" items="${lotteryShowMN}">
								<td class="border text-center"><c:out
										value="${result.lottery.first_prize}"></c:out></td>
							</c:forEach>
						</tr>
						<tr>
							<th class="border col-1" scope="col">2</th>
							<c:forEach var="result" items="${lotteryShowMN}">
								<td class="border text-center"><c:out
										value="${result.lottery.second_prize}"></c:out></td>
							</c:forEach>
						</tr>
						<tr>
							<th class="border col-1" scope="col">3</th>
							<c:forEach var="result" items="${lotteryShowMN}">
								<td class="border text-center"><c:out
										value="${result.lottery.third_prize}"></c:out></td>
							</c:forEach>
						</tr>
						<tr>
							<th class="border col-1" scope="col">4</th>
							<c:forEach var="result" items="${lotteryShowMN}">
								<td class="border text-center"><c:out
										value="${result.lottery.fourth_prize}"></c:out></td>
							</c:forEach>
						</tr>
						<tr>
							<th class="border col-1" scope="col">5</th>
							<c:forEach var="result" items="${lotteryShowMN}">
								<td class="border text-center"><c:out
										value="${result.lottery.fifth_prize}"></c:out></td>
							</c:forEach>
						</tr>
						<tr>
							<th class="border col-1" scope="col">6</th>
							<c:forEach var="result" items="${lotteryShowMN}">
								<td class="border text-center"><c:out
										value="${result.lottery.sixth_prize}"></c:out></td>
							</c:forEach>
						</tr>
						<tr>
							<th class="border col-1" scope="col">7</th>
							<c:forEach var="result" items="${lotteryShowMN}">
								<td class="border text-center"><c:out
										value="${result.lottery.seventh_prize}"></c:out></td>
							</c:forEach>
						</tr>
						<tr>
							<th class="border col-1" scope="col">8</th>
							<c:forEach var="result" items="${lotteryShowMN}">
								<td class="border text-center"><c:out
										value="${result.lottery.eighth_prize}"></c:out></td>
							</c:forEach>
						</tr>
					</tbody>
				</table>


			</div>
		</div>
		<!-- ---------------------------right content------------------------------ -->
		<div class="px-0 col-lg-2 "
			style="--bs-btn-hover-color: #D80032; background-color: #F5EBEB;">
			<div class="col px-0  border-end my-1">
				<form class="bg-danger"
					action="${pageContext.request.contextPath}/LotteryOfProvince"
					method="post">
					<input name="order" value="desc" type="hidden" /> <input
						name="colOrder" value="lottery_date" type="hidden" /> <input
						name="province" value="Tất cả" type="hidden" /> <input
						name="region" value="Miền Bắc" type="hidden" /> <input
						name="action" value="filter" type="hidden" /> <input
						class="btn btn-outline-none px-2" type="submit"
						value="XỔ SỐ MIỀN BẮC">
				</form>
				<ul>
					<li>
						<form class=""
							action="${pageContext.request.contextPath}/LotteryOfProvince"
							method="post">
							<input name="order" value="desc" type="hidden" /> <input
								name="colOrder" value="lottery_date" type="hidden" /> <input
								name="province" value="Hà Nội" type="hidden" /> <input
								name="region" value="Miền Bắc" type="hidden" /> <input
								name="action" value="filter" type="hidden" /> <input
								class="btn btn-outline-none" type="submit" value="Hà Nội">
						</form>
					</li>
					<li>
						<form class=""
							action="${pageContext.request.contextPath}/LotteryOfProvince"
							method="post">
							<input name="order" value="desc" type="hidden" /> <input
								name="colOrder" value="lottery_date" type="hidden" /> <input
								name="province" value="Quảng Ninh" type="hidden" /> <input
								name="region" value="Miền Bắc" type="hidden" /> <input
								name="action" value="filter" type="hidden" /> <input
								class="btn btn-outline-none" type="submit" value="Quảng Ninh">
						</form>
					</li>
					<li>
						<form class=""
							action="${pageContext.request.contextPath}/LotteryOfProvince"
							method="post">
							<input name="order" value="desc" type="hidden" /> <input
								name="colOrder" value="lottery_date" type="hidden" /> <input
								name="province" value="Bắc Ninh" type="hidden" /> <input
								name="region" value="Miền Bắc" type="hidden" /> <input
								name="action" value="filter" type="hidden" /> <input
								class="btn btn-outline-none" type="submit" value="Hải Phòng">
						</form>
					</li>
					<li>
						<form class=""
							action="${pageContext.request.contextPath}/LotteryOfProvince"
							method="post">
							<input name="order" value="desc" type="hidden" /> <input
								name="colOrder" value="lottery_date" type="hidden" /> <input
								name="province" value="Nam Định" type="hidden" /> <input
								name="region" value="Miền Bắc" type="hidden" /> <input
								name="action" value="filter" type="hidden" /> <input
								class="btn btn-outline-none" type="submit" value="Nam Định">
						</form>
					</li>
					<li>
						<form class=""
							action="${pageContext.request.contextPath}/LotteryOfProvince"
							method="post">
							<input name="order" value="desc" type="hidden" /> <input
								name="colOrder" value="lottery_date" type="hidden" /> <input
								name="province" value="Thái Bình" type="hidden" /> <input
								name="region" value="Miền Bắc" type="hidden" /> <input
								name="action" value="filter" type="hidden" /> <input
								class="btn btn-outline-none" type="submit" value="Thái Bình">
						</form>
					</li>
				</ul>
			</div>
			<div class="col px-0  border-end mb-1">
				<form class="bg-danger"
					action="${pageContext.request.contextPath}/LotteryOfProvince"
					method="post">
					<input name="order" value="desc" type="hidden" /> <input
						name="colOrder" value="lottery_date" type="hidden" /> <input
						name="province" value="Tất cả" type="hidden" /> <input
						name="region" value="Miền Trung" type="hidden" /> <input
						name="action" value="filter" type="hidden" /> <input
						class="btn btn-outline-none px-2" type="submit"
						value="XỔ SỐ MIỀN TRUNG">
				</form>
				<ul>
					<li>
						<form class=""
							action="${pageContext.request.contextPath}/LotteryOfProvince"
							method="post">
							<input name="order" value="desc" type="hidden" /> <input
								name="colOrder" value="lottery_date" type="hidden" /> <input
								name="province" value="Bình Định" type="hidden" /> <input
								name="region" value="Miền Trung" type="hidden" /> <input
								name="action" value="filter" type="hidden" /> <input
								class="btn btn-outline-none" type="submit" value="Bình Định">
						</form>
					</li>
					<li>
						<form class=""
							action="${pageContext.request.contextPath}/LotteryOfProvince"
							method="post">
							<input name="order" value="desc" type="hidden" /> <input
								name="colOrder" value="lottery_date" type="hidden" /> <input
								name="province" value="Đắk Lắk" type="hidden" /> <input
								name="region" value="Miền Trung" type="hidden" /> <input
								name="action" value="filter" type="hidden" /> <input
								class="btn btn-outline-none" type="submit" value="Đắk Lắk">
						</form>
					</li>
					<li>
						<form class=""
							action="${pageContext.request.contextPath}/LotteryOfProvince"
							method="post">
							<input name="order" value="desc" type="hidden" /> <input
								name="colOrder" value="lottery_date" type="hidden" /> <input
								name="province" value="Đà Nẵng" type="hidden" /> <input
								name="region" value="Miền Trung" type="hidden" /> <input
								name="action" value="filter" type="hidden" /> <input
								class="btn btn-outline-none" type="submit" value="Đà Nẵng">
						</form>
					</li>
					<li>
						<form class=""
							action="${pageContext.request.contextPath}/LotteryOfProvince"
							method="post">
							<input name="order" value="desc" type="hidden" /> <input
								name="colOrder" value="lottery_date" type="hidden" /> <input
								name="province" value="Đắk Nông" type="hidden" /> <input
								name="region" value="Miền Trung" type="hidden" /> <input
								name="action" value="filter" type="hidden" /> <input
								class="btn btn-outline-none" type="submit" value="Đắk Nông">
						</form>
					</li>
					<li>
						<form class=""
							action="${pageContext.request.contextPath}/LotteryOfProvince"
							method="post">
							<input name="order" value="desc" type="hidden" /> <input
								name="colOrder" value="lottery_date" type="hidden" /> <input
								name="province" value="Gia Lai" type="hidden" /> <input
								name="region" value="Miền Trung" type="hidden" /> <input
								name="action" value="filter" type="hidden" /> <input
								class="btn btn-outline-none" type="submit" value="Gia Lai">
						</form>
					</li>
					<li>
						<form class=""
							action="${pageContext.request.contextPath}/LotteryOfProvince"
							method="post">
							<input name="order" value="desc" type="hidden" /> <input
								name="colOrder" value="lottery_date" type="hidden" /> <input
								name="province" value="Khánh Hòa" type="hidden" /> <input
								name="region" value="Miền Trung" type="hidden" /> <input
								name="action" value="filter" type="hidden" /> <input
								class="btn btn-outline-none" type="submit" value="Khánh Hòa">
						</form>
					</li>
					<li>
						<form class=""
							action="${pageContext.request.contextPath}/LotteryOfProvince"
							method="post">
							<input name="order" value="desc" type="hidden" /> <input
								name="colOrder" value="lottery_date" type="hidden" /> <input
								name="province" value="Kon Tum" type="hidden" /> <input
								name="region" value="Miền Trung" type="hidden" /> <input
								name="action" value="filter" type="hidden" /> <input
								class="btn btn-outline-none" type="submit" value="Kon Tum">
						</form>
					</li>
					<li>
						<form class=""
							action="${pageContext.request.contextPath}/LotteryOfProvince"
							method="post">
							<input name="order" value="desc" type="hidden" /> <input
								name="colOrder" value="lottery_date" type="hidden" /> <input
								name="province" value="Ninh Thuận" type="hidden" /> <input
								name="region" value="Miền Trung" type="hidden" /> <input
								name="action" value="filter" type="hidden" /> <input
								class="btn btn-outline-none" type="submit" value="Ninh Thuận">
						</form>
					</li>
					<li>
						<form class=""
							action="${pageContext.request.contextPath}/LotteryOfProvince"
							method="post">
							<input name="order" value="desc" type="hidden" /> <input
								name="colOrder" value="lottery_date" type="hidden" /> <input
								name="province" value="Phú Yên" type="hidden" /> <input
								name="region" value="Miền Trung" type="hidden" /> <input
								name="action" value="filter" type="hidden" /> <input
								class="btn btn-outline-none" type="submit" value="Phú Yên">
						</form>
					</li>
					<li>
						<form class=""
							action="${pageContext.request.contextPath}/LotteryOfProvince"
							method="post">
							<input name="order" value="desc" type="hidden" /> <input
								name="colOrder" value="lottery_date" type="hidden" /> <input
								name="province" value="Quảng Bình" type="hidden" /> <input
								name="region" value="Miền Trung" type="hidden" /> <input
								name="action" value="filter" type="hidden" /> <input
								class="btn btn-outline-none" type="submit" value="Quảng Bình">
						</form>
					</li>
					<li>
						<form class=""
							action="${pageContext.request.contextPath}/LotteryOfProvince"
							method="post">
							<input name="order" value="desc" type="hidden" /> <input
								name="colOrder" value="lottery_date" type="hidden" /> <input
								name="province" value="Đồng Nai" type="hidden" /> <input
								name="region" value="Quảng Ngãi" type="hidden" /> <input
								name="action" value="filter" type="hidden" /> <input
								class="btn btn-outline-none" type="submit" value="Quảng Ngãi">
						</form>
					</li>
					<li>
						<form class=""
							action="${pageContext.request.contextPath}/LotteryOfProvince"
							method="post">
							<input name="order" value="desc" type="hidden" /> <input
								name="colOrder" value="lottery_date" type="hidden" /> <input
								name="province" value="Quảng Nam" type="hidden" /> <input
								name="region" value="Miền Trung" type="hidden" /> <input
								name="action" value="filter" type="hidden" /> <input
								class="btn btn-outline-none" type="submit" value="Quảng Nam">
						</form>
					</li>
					<li>
						<form class=""
							action="${pageContext.request.contextPath}/LotteryOfProvince"
							method="post">
							<input name="order" value="desc" type="hidden" /> <input
								name="colOrder" value="lottery_date" type="hidden" /> <input
								name="province" value="Quảng Trị" type="hidden" /> <input
								name="region" value="Miền Trung" type="hidden" /> <input
								name="action" value="filter" type="hidden" /> <input
								class="btn btn-outline-none" type="submit" value="Quảng Trị">
						</form>
					</li>
					<li>
						<form class=""
							action="${pageContext.request.contextPath}/LotteryOfProvince"
							method="post">
							<input name="order" value="desc" type="hidden" /> <input
								name="colOrder" value="lottery_date" type="hidden" /> <input
								name="province" value="Thừa Thiên Huế" type="hidden" /> <input
								name="region" value="Miền Trung" type="hidden" /> <input
								name="action" value="filter" type="hidden" /> <input
								class="btn btn-outline-none" type="submit"
								value="Thừa Thiên Huế">
						</form>
					</li>
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