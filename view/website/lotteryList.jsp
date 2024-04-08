<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html lang="vi">
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
<!-- js -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/lottery.js"></script>
<title>Danh sách Số trúng thưởng</title>
</head>
<body>
	<!---------------------- navbar---------------------------------->
	<c:import url="/view/website/dashboard.jsp"></c:import>
	<!---------------------------------- LOTTERY DETAIL------------------------------------------>
	<main class="col-md-9 m-auto col-lg-10 px-2 px-md-4" style="min-height: 100vh;">
		<div class="mb-3">
			<h2 class="col col-lg-5 pt-3">Danh sách Số trúng thưởng theo miền</h2>
		</div>

		<!-- --------Sort  -->
		<div class="justify-content-between mb-4">
			<form class="row justify-content-between"
				action="${pageContext.request.contextPath}/Lottery" method="post">

				<div id="filterContent"
					class="col-12 col-sm-10 col-lg-8 row me-0 m-0">
					<div class="col-3 col-sm-4 m-0 p-0">
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

						</select>
					</div>
					<div class="col-3 col-sm-4 m-0 p-0">
						<select name="colOrder" id="colOrder" class="form-select m-0"
							aria-label="Default select ">
							<c:if test='${colOrder=="province"}'>
								<option value="province" selected="selected">Nhà đài</option>
								<option value="lottery_date">Ngày</option>
							</c:if>
							<c:if test='${colOrder=="lottery_date"}'>
								<option value="lottery_date" selected="selected">Ngày</option>
								<option value="province">Nhà đài</option>
							</c:if>

						</select>
					</div>
					<div class="col-3 col-sm-3 m-0 p-0">
						<select name="order" id="order" class="form-select"
							aria-label="Default select">

							<c:if test='${order=="asc"}'>
								<option value="asc">Tăng dần</option>
								<option value="desc">Giảm dần</option>
							</c:if>
							<c:if test='${order=="desc"}'>
								<option value="desc">Giảm dần</option>
								<option value="asc">Tăng dần</option>
							</c:if>

						</select>
					</div>

				</div>
				<div class="col">
					<input name="action" value="filter" type="hidden" />
					<button class="btn btn-danger" title="lọc" type="submit">
						<i class="bi bi-funnel"></i>
					</button>
				</div>
			</form>

		</div>

		<!--  show result -->
		<c:if test="${pages==0}">
			<p style="color: red;">Không có kết quả.</p>
		</c:if>
		<c:if test="${pages!=0}">
			<c:set var="search" value="${searchLotteries}" scope="page"></c:set>
			<!-- display lotteries list -->
			<div id="contextLottery" class="table-responsive small text-start">
				<c:if test='${region!="Tất cả"}'>
					<table
						class="table table-striped  table-sm text-start border border-3 border-dark">
						<thead class="table-danger">
							<tr class="align-top border ">
								<th class="border " scope="col">Giải</th>
								<c:forEach var="result" items="${lotteryShow}">
									<th class="border text-center item-lottery position-relative"
										scope="col">
										<p>${result.dayOfWeek}-${result.date}-${result.region}</p>
										<p>${result.province}</p>
									</th>
								</c:forEach>
							</tr>
						</thead>
						<tbody class="table-group-divider">							
							<tr>
								<th class="border col-1" scope="col">ĐB</th>
								<c:forEach var="result" items="${lotteryShow}">
									<td class="border text-center"><c:out
											value="${result.lottery.special_prize}"></c:out></td>
								</c:forEach>
							</tr>
							<tr>
								<th class="border col-1" scope="col">1</th>
								<c:forEach var="result" items="${lotteryShow}">
									<td class="border text-center"><c:out
											value="${result.lottery.first_prize}"></c:out></td>
								</c:forEach>
							</tr>
							<tr>
								<th class="border col-1" scope="col">2</th>
								<c:forEach var="result" items="${lotteryShow}">
									<td class="border text-center"><c:out
											value="${result.lottery.second_prize}"></c:out></td>
								</c:forEach>
							</tr>
							<tr>
								<th class="border col-1" scope="col">3</th>
								<c:forEach var="result" items="${lotteryShow}">
									<td class="border text-center"><c:out
											value="${result.lottery.third_prize}"></c:out></td>
								</c:forEach>
							</tr>
							<tr>
								<th class="border col-1" scope="col">4</th>
								<c:forEach var="result" items="${lotteryShow}">
									<td class="border text-center"><c:out
											value="${result.lottery.fourth_prize}"></c:out></td>
								</c:forEach>
							</tr>
							<tr>
								<th class="border col-1" scope="col">5</th>
								<c:forEach var="result" items="${lotteryShow}">
									<td class="border text-center"><c:out
											value="${result.lottery.fifth_prize}"></c:out></td>
								</c:forEach>
							</tr>
							<tr>
								<th class="border col-1" scope="col">6</th>
								<c:forEach var="result" items="${lotteryShow}">
									<td class="border text-center"><c:out
											value="${result.lottery.sixth_prize}"></c:out></td>
								</c:forEach>
							</tr>
							<tr>
								<th class="border col-1" scope="col">7</th>
								<c:forEach var="result" items="${lotteryShow}">
									<td class="border text-center"><c:out
											value="${result.lottery.seventh_prize}"></c:out></td>
								</c:forEach>
							</tr>
							<tr>
								<th class="border col-1" scope="col">8</th>
								<c:forEach var="result" items="${lotteryShow}">
									<td class="border text-center"><c:out
											value="${result.lottery.eighth_prize}"></c:out></td>
								</c:forEach>
							</tr>
						</tbody>
					</table>
				</c:if>
				<!-- hien thi 3 mien---------------------------- -->
				<c:if test='${region=="Tất cả"}'>
					<c:set var="d" value="0"></c:set>
					<c:if test="${lotteryShowMB.size()>0}">
						<c:set var="d" value="${d+1}"></c:set>
						<p>${d}. Miền Bắc:</p>
						<table
							class="table table-striped  table-sm text-start border border-3 border-dark">
							<thead class="table-danger">
								<tr class="align-top border ">
									<th class="border " scope="col">Giải</th>
									<c:forEach var="result" items="${lotteryShowMB}">
										<th class="border text-center item-lottery position-relative"
											scope="col">
											<p>${result.dayOfWeek}-${result.date}-${result.region}</p>
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
					</c:if>
					<c:if test="${lotteryShowMT.size()>0}">
						<c:set var="d" value="${d+1}"></c:set>
						<p>${d}. Miền Trung:</p>
						<table
							class="table table-striped  table-sm text-start border border-3 border-dark">
							<thead class="table-danger">
								<tr class="align-top border ">
									<th class="border " scope="col">Giải</th>
									<c:forEach var="result" items="${lotteryShowMT}">
										<th class="border text-center item-lottery position-relative"
											scope="col">
											<p>${result.dayOfWeek}-${result.date}-${result.region}</p>
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
					</c:if>
					<c:if test="${lotteryShowMN.size()>0}">
						<c:set var="d" value="${d+1}"></c:set>
						<p>${d}. Miền Nam:</p>
						<table
							class="table table-striped  table-sm text-start border border-3 border-dark">
							<thead class="table-danger">
								<tr class="align-top border ">
									<th class="border " scope="col">Giải</th>
									<c:forEach var="result" items="${lotteryShowMN}">
										<th class="border text-center item-lottery position-relative"
											scope="col">
											<p>${result.dayOfWeek}-${result.date}-${result.region}</p>
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
					</c:if>

				</c:if>
			</div>

			<!--  pagination - phân trang  -->
			<nav class="row justify-content-center" aria-label="Page navigation  ">
			
				<ul id="pagination" class="pagination col-12 col-sm-9 justify-content-center">
					<!-- previous pages -->
					<c:if test="${page>1}">
						<li class="page-item">
							<form action="${pageContext.request.contextPath}/Lottery" method="post">
								<input name="action" value="goToPage" type="hidden" /> 
								<input name="page" value="1" type="hidden" />
								<input name="region" value="${region}" type="hidden" />
								<input name="colOrder" value="${colOrder}" type="hidden" />
								<input name="order" value="${order}" type="hidden" />
								<button type="submit" class="btn btn-outline-primary"
									title="Chuyển đến trang ${1}">
									<span aria-hidden="true">&laquo;</span>
								</button>
							</form>
						</li>
						<li class="page-item">
							<form action="${pageContext.request.contextPath}/Lottery" method="post">
								<input name="action" value="goToPage" type="hidden" /> 
								<input name="page" value="${page-1}" type="hidden" />
								<input name="region" value="${region}" type="hidden" />
								<input name="colOrder" value="${colOrder}" type="hidden" />
								<input name="order" value="${order}" type="hidden" />
								<button type="submit" class="btn btn-outline-primary"
									title="Chuyển đến trang ${page-1}">
									<span aria-hidden="true">&lsaquo;</span>
								</button>
							</form>
						</li>
					</c:if>
					
					<c:if test="${page<4}">
						<c:forEach var="i" begin="1" end="${page-1}" step="1">
							<li class="page-item">
								<form action="${pageContext.request.contextPath}/Lottery" method="post">
									<input name="action" value="goToPage" type="hidden" /> 
									<input name="page" value="${i}" type="hidden" />
									<input name="region" value="${region}" type="hidden" />
									<input name="colOrder" value="${colOrder}" type="hidden" />
									<input name="order" value="${order}" type="hidden" />
									<button type="submit" class="btn btn-outline-primary"
										title="Chuyển đến trang ${i}">${i}
									</button>
								</form>
							</li>
						</c:forEach>
					</c:if>
					<c:if test="${page>3}">
						<c:forEach var="i" begin="${page-3}" end="${page-1}" step="1">
							<li class="page-item">
								<form action="${pageContext.request.contextPath}/Lottery" method="post">
									<input name="action" value="goToPage" type="hidden" /> 
									<input name="page" value="${i}" type="hidden" />
									<input name="region" value="${region}" type="hidden" />
									<input name="colOrder" value="${colOrder}" type="hidden" />
									<input name="order" value="${order}" type="hidden" />
									<button type="submit" class="btn btn-outline-primary"
										title="Chuyển đến trang ${i}">${i}
									</button>
								</form>
							</li>
						</c:forEach>
					</c:if>
					<!-- current page -->
					<li class="page-item">
								<form action="${pageContext.request.contextPath}/Lottery" method="post">
									<input name="action" value="goToPage" type="hidden" /> 
									<input name="page" value="${page}" type="hidden" />
									<input name="region" value="${region}" type="hidden" />
									<input name="colOrder" value="${colOrder}" type="hidden" />
									<input name="order" value="${order}" type="hidden" />
									<button type="submit" class="btn btn-outline-primary active"
										title="Chuyển đến trang ${page}">${page}
									</button>
								</form>
					</li>
					
					<!-- next pages -->
					<c:if test="${page<pages}">
						<c:forEach var="i" begin="${page+1}" end="${page+3}" step="1">
							<c:if test="${i<=pages}">
								<li class="page-item">
									<form action="${pageContext.request.contextPath}/Lottery" method="post">
										<input name="action" value="goToPage" type="hidden" /> 
										<input name="page" value="${i}" type="hidden" />
										<input name="region" value="${region}" type="hidden" />
										<input name="colOrder" value="${colOrder}" type="hidden" />
										<input name="order" value="${order}" type="hidden" />
										<button type="submit" class="btn btn-outline-primary"
											title="Chuyển đến trang ${i}">${i}
										</button>
									</form>
								</li>
							</c:if>
						</c:forEach>
					</c:if>

					<c:if test="${page+1<=pages}">
						<li class="page-item">
							<form action="${pageContext.request.contextPath}/Lottery"  method="post">
								<input name="action" value="goToPage" type="hidden" /> 
								<input name="page" value="${page+1}" type="hidden" />
								<input name="region" value="${region}" type="hidden" />
								<input name="colOrder" value="${colOrder}" type="hidden" />
								<input name="order" value="${order}" type="hidden" />
								<button type="submit" class="btn btn-outline-primary"
									title="Chuyển đến trang ${page+1}">
									<span aria-hidden="true">&rsaquo;</span>
								</button>
							</form>
						</li>
						<li class="page-item">
							<form action="${pageContext.request.contextPath}/Lottery" method="post">
								<input name="action" value="goToPage" type="hidden" /> 
								<input name="page" value="${pages}" type="hidden" />
								<input name="region" value="${region}" type="hidden" />
								<input name="colOrder" value="${colOrder}" type="hidden" />
								<input name="order" value="${order}" type="hidden" />
								<button type="submit" class="btn btn-outline-primary"
									title="Chuyển đến trang ${pages}">
									<span aria-hidden="true">&raquo;</span>
								</button>
							</form>
						</li>
					</c:if>
				</ul>
				
				<form class="col-12 col-sm-2 d-flex justify-content-center"
					action="${pageContext.request.contextPath}/Lottery" method="post">
					<input type="hidden" name="action" value="goToPage" />
					<input name="region" value="${region}" type="hidden" />
					<input name="colOrder" value="${colOrder}" type="hidden" />
					<input name="order" value="${order}" type="hidden" />
					<select  name="page" class="form-select form-select-sm p-0 m-0"
						aria-label="Default select">
						<c:forEach var="i"  begin="1" end="${pages}" step="1">
							<c:if test='${i==page}'>
								<option class="p-0 m-0" value="${i}" selected="selected">Trang: ${i}</option>
							</c:if>
							<c:if test='${i!=page}'>
								<option class="p-0 m-0" value="${i}" >Trang: ${i}</option>
							</c:if>								
						</c:forEach>
					</select>
					<button class="btn btn-primary py-1" type="submit"
						title="Đi đến trang">
						Đi
					</button>
				</form>
				
			</nav>
		</c:if>
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
