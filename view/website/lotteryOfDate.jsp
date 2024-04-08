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
	<main class="col-md-9 mx-1 m-md-auto  col-lg-10 px-md-4"
		style="min-height: 100vh;">
		<div class="mb-3">
			<h2 class="col col-lg-5 pt-3">Danh sách Số trúng thưởng theo
				Ngày</h2>
		</div>

		<!-- --------Sort  -->
		<div class="justify-content-between mb-4">
			<form class="row justify-content-start"
				action="${pageContext.request.contextPath}/LotteryOfDate"
				method="post">
				<div id="formSearch" class="col-10 row justify-content-start">
					<div class="col col-md-3 ">
						<input name="date" type="date" class="form-control"
							id="date" value="${date}" onchange="changeRDP()" />
					</div>
					<div class="col col-md-3">
						<select name="region" id="region" class="form-select"
							onchange="changeRDP()" aria-label="Default select ">

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
					<div class="col col-md-3">
						<select name="province" id="province" class="form-select"
							onchange="changeRDP()" aria-label="Default select ">

							<c:forEach var="p" items="${provinces}">
								<c:if test='${p==province}'>
									<option value="${p}" selected="selected">${province}</option>
								</c:if>
								<c:if test='${p!=province}'>
									<option value="${p}">${p}</option>
								</c:if>
							</c:forEach>

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
		<c:if test="${lotteryShow==null}">
			<p style="color: red;">Không có kết quả.</p>
		</c:if>
		<c:if test="${lotteryShow!=null}">
			<!-- display lotteries list -->
			<div id="contextLottery" class="table-responsive small text-start">
				<table
					class="table table-striped  table-sm text-start border border-3 border-dark">
					<thead class="table-danger">
						<tr class="align-top border ">
							<th class="border " scope="col">Giải</th>
							<c:forEach var="result" items="${lotteryShow}">
								<th class="border text-center item-lottery position-relative"
									scope="col">
									<p>${result.dayOfWeek} - ${result.date}</p>
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

			</div>

			</c:if>
	</main>

	<!-- FOOTER -->
	<div style="height: 55px;"></div>
	<c:import url="/view/website/footer.jsp"></c:import>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
	<script>
	function changeRDP() {
		var r= document.getElementById("region").value;
		var pro=document.getElementById("province").value;
		var d = document.getElementById("date").value;
		$.ajax({
			url : "/LotteryWeb/LotteryOfDate?action=changeRDP",
			type : "post", //send it through get method
			data : {
				province:pro,
			    region:r,
			    date:d
			},
			success : function(response) {
				var row = document.getElementById("formSearch")
				row.innerHTML = response;
			},
			error : function(xhr) {
				//Do Something to handle error
			}
		});
	}			
	</script>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
		crossorigin="anonymous"></script>
</body>
</html>
