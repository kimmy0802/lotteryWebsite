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

<title>Dò Vé số</title>
</head>
<body>

	<!-- -------------------- navbar-------------------------------- -->
	<c:import url="/view/website/dashboard.jsp"></c:import>
	<!-- -------------------------------- SEARCH LOTTERY ---------------------------------------- -->
	<main class="row justify-content-center mx-auto" >
		<h2 class="col-12 col-md-6 mx-5 my-3">Dò vé số:</h2>
		<form id="formSearch" class="col-12 col-md-6 row mx-5"
			action="${pageContext.request.contextPath}/SearchLotteryController?action=searchLottery"
			method="post">

			<div class="form-floating col-12 col-md-6 mb-4 mb-md-2">
				<select name="region" class="form-select" id="selectRegion"
					onchange="changeRDP()" aria-label="select region">
					<c:forEach var="r" items="${regions}">
						<c:if test="${r==region}">
							<option value="${r}" selected="selected">${r}</option>
						</c:if>
						<c:if test="${r!=region}">
							<option value="${r}">${r}</option>
						</c:if>
					</c:forEach>
				</select> <label for="selectRegion">Chọn Miền</label>
			</div>
			<div class="form-floating col-12 col-md-6 mb-4 mb-md-2">
				<c:if test='${date==""}'>
					<input name="date" type="date" class="form-control"
						id="selectDateLottery" placeholder="Chọn ngày... "
						onchange="changeRDP()" />
				</c:if>
				<c:if test='${date!=""}'>
					<input name="date" type="date" class="form-control"
						id="selectDateLottery" value="${date}" onchange="changeRDP()" />
				</c:if>
				<label for="selectDateLottery">Chọn ngày:</label>
			</div>
			<div class="form-floating col-12 col-md-6 mb-4 mb-md-2">
				<select name="provinceID" class="form-select" id="selectProvince"
					aria-label="select province" onchange="changeRDP()">
					<c:forEach var="p" items="${provinces}">
						<c:if test="${p.province_id==provinceID}">
							<option value="${p.province_id}" selected="selected">${p.province}</option>
						</c:if>
						<c:if test="${p.province_id!=provinceID}">
							<option value="${p.province_id}">${p.province}</option>
						</c:if>
					</c:forEach>
				</select> <label for="selectProvince">Chọn nhà đài</label>
			</div>
			<div id="numberSearchContext"
				class="form-floating col-12 col-md-6 mb-4 mb-md-2 ">
				<input name="numberSearch" type="text" class="form-control"
					id="numberSearch" value="${numberSearch}" oninput="checkLotterySearch(this)"  />
				<label for="numberSearch">Số cần dò:</label>
				<p class="text-danger" id="numberSearchMess">${numberSearchMess}</p>
				
			</div>
			<div class="col-12 col-sm-7 row justify-content-center">
				<div class="col-5">
					<button name="submit" type="submit" value="search"
						class="btn btn-primary ">Dò</button>
				</div>
			</div>
		</form>
		
		<div id="resultSearch" class="col-12 col-md-6 row mx-5 text-center">
		
			<c:choose>
				<%-- prize=-1: lần đầu tải trang  --%>
				<c:when test="${prize==-1}">
				</c:when>
				<%-- prize=10: thực hiện dò vé số nhưng số vừa nhập không hợp lệ  --%>
				<c:when test="${prize==10}">
					<p style="color: red;font-size: 22px;">Số vừa nhập không hợp lệ.</p>
				</c:when>
				<%-- prize=11: Không có kết quả của ngày và  hà đài vừa nhập  --%>
				<c:when test="${prize==11}">
					<p style="color: red;font-size: 22px;">Rất tiếc kết quả xổ số hiện chưa có trên hệ thống</p>
				</c:when>

				<c:otherwise>
					<c:if test="${prize==9}">
						<p style="color: red;font-size: 22px;">Rất tiếc số bạn vừa dò không trúng
							thưởng!</p>
					</c:if>
					<c:if test="${prize!=9}">
						<p style="font-size: 22px; font-weight: 700;">
							Chúc mừng bạn đã trúng <span style="color: red;font-size: 24px; font-weight: 800;">${resultSearchLottery}</span>
							Trị giá <span style="color: red;font-size: 24px; font-weight: 800;">${costOfPrize}</span> ,
							số trúng thưởng: <span style="color: red;font-size: 24px; font-weight: 800;">${numberResult}
							</span>
						</p>
					</c:if>
					<div class="table-responsive small text-start">
						<table
							class="table table-striped  table-sm text-start border border-3 border-dark">
							<thead class="table-danger">
								<tr class="align-top border ">
									<th class="border " scope="col">Giải</th>
									<th class="border text-center item-lottery position-relative"
										scope="col">
										<p>${lotteryShow.dayOfWeek}-${lotteryShow.date}-${lotteryShow.region}</p>
										<p>${lotteryShow.province}</p>
									</th>
								</tr>
							</thead>
							<tbody class="table-group-divider">
								<c:set var="j" value="${page*3-3}"></c:set>
								<tr>
									<th class="border col-1" scope="col">ĐB</th>
									<td class="border text-center"><c:if test="${prize==0}">
											<span style="background-color: #F7FC4E;">${lotteryShow.lottery.special_prize}</span>
										</c:if> <c:if test="${prize!=0}">${lotteryShow.lottery.special_prize}</c:if></td>
								</tr>
								<tr>
									<th class="border col-1" scope="col">1</th>
									<td class="border text-center"><c:if test="${prize==1}">
											<span style="background-color: #F7FC4E;">${lotteryShow.lottery.first_prize}</span>
										</c:if> <c:if test="${prize!=1}">${lotteryShow.lottery.first_prize}</c:if>
									</td>
								</tr>
								<tr>
									<th class="border col-1" scope="col">2</th>
									<td class="border text-center"><c:if test="${prize==2}">
											<span style="background-color: #F7FC4E;">${lotteryShow.lottery.second_prize}</span>
										</c:if> <c:if test="${prize!=2}">${lotteryShow.lottery.second_prize}</c:if>
									</td>
								</tr>
								<tr>
									<th class="border col-1" scope="col">3</th>
									<td class="border text-center"><c:if test="${prize==3}">
											<c:set var="lotteryNumber"
												value="${lotteryShow.lottery.third_prize}"></c:set>
											${fn:substringBefore(lotteryNumber,numberResult)}  																		
											<span style="background-color: #F7FC4E;">${numberResult}</span>
											${fn:substringAfter(lotteryNumber,numberResult)}
										</c:if> <c:if test="${prize!=3}">${lotteryShow.lottery.third_prize}</c:if>

									</td>
								</tr>
								<tr>
									<th class="border col-1" scope="col">4</th>
									<td class="border text-center"><c:if test="${prize==4}">
											<c:set var="lotteryNumber"
												value="${lotteryShow.lottery.fourth_prize}"></c:set>
											${fn:substringBefore(lotteryNumber,numberResult)}  																		
											<span style="background-color: #F7FC4E;">${numberResult}</span>
											${fn:substringAfter(lotteryNumber,numberResult)}
											
										</c:if> <c:if test="${prize!=4}">${lotteryShow.lottery.fourth_prize}</c:if>

									</td>
								</tr>
								<tr>
									<th class="border col-1" scope="col">5</th>
									<td class="border text-center"><c:if test="${prize==5}">
											<c:set var="lotteryNumber"
												value="${lotteryShow.lottery.fifth_prize}"></c:set>
											${fn:substringBefore(lotteryNumber,numberResult)}  																		
											<span style="background-color: #F7FC4E;">${numberResult}</span>
											${fn:substringAfter(lotteryNumber,numberResult)}
											
										</c:if> <c:if test="${prize!=5}">${lotteryShow.lottery.fifth_prize}</c:if>


									</td>
								</tr>
								<tr>
									<th class="border col-1" scope="col">6</th>
									<td class="border text-center"><c:if test="${prize==6}">
											<c:set var="lotteryNumber"
												value="${lotteryShow.lottery.sixth_prize}"></c:set>
											${fn:substringBefore(lotteryNumber,numberResult)}  																		
											<span style="background-color: #F7FC4E;">${numberResult}</span>
											${fn:substringAfter(lotteryNumber,numberResult)}
											</c:if> <c:if test="${prize!=6}">${lotteryShow.lottery.sixth_prize}</c:if>
									</td>
								</tr>
								<tr>
									<th class="border col-1" scope="col">7</th>
									<td class="border text-center"><c:if test="${prize==7}">
											<c:set var="lotteryNumber"
												value="${lotteryShow.lottery.seventh_prize}"></c:set>
											${fn:substringBefore(lotteryNumber,numberResult)}  																		
											<span style="background-color: #F7FC4E;">${numberResult}</span>
											${fn:substringAfter(lotteryNumber,numberResult)}
												</c:if> <c:if test="${prize!=7}">${lotteryShow.lottery.seventh_prize}</c:if>
									</td>
								</tr>
								<tr>
									<th class="border col-1" scope="col">8</th>
									<td class="border text-center"><c:if test="${prize==8}">
											<span style="background-color: #F7FC4E;">${lotteryShow.lottery.eighth_prize}</span>
										</c:if> <c:if test="${prize!=8}">${lotteryShow.lottery.eighth_prize}</c:if>

									</td>
								</tr>
							</tbody>
						</table>
					</div>


				</c:otherwise>
			</c:choose>
		</div>
	</main>

	<!-- FOOTER -->
	<div  style="height: 55px;"></div>
	<c:import url="/view/website/footer.jsp"></c:import>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>

	<script>
		function changeRDP() {
			var selectRegion = document.getElementById("selectRegion").value;
			var selectDate = document.getElementById("selectDateLottery").value;
			var selectProvince = document.getElementById("selectProvince").value;
			var numberSearch = document.getElementById("numberSearch").value;

			$.ajax({
				url : "/LotteryWeb/SearchLotteryController?action=changeRDP",
				type : "post", //send it through get method
				data : {
					numberSearch : numberSearch,
					region : selectRegion,
					date : selectDate,
					provinceID : selectProvince
				},
				success : function(response) {
					var row = document.getElementById("formSearch")
					row.innerHTML = response;
					document.getElementById("resultSearch").innerHTML = "";
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
