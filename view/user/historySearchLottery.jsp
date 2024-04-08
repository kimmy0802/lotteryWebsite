<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<html>
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
	src="${pageContext.request.contextPath}/js/account.js"></script>
	
<title>Lịch sử dò vé số</title>
</head>
<body>
	<!---------------------- navbar---------------------------------->
	<c:import url="/view/website/dashboard.jsp"></c:import>
	<!------------------------------------------ACCESS HISTORY------------------------------------------>

	<main class="col-md-9 mx-auto col-lg-10 px-md-4" style="min-height: 100vh;">
		<h1 class="h2">Lịch sử các số đã dò</h1>
		<div id="historySearchLottery" class="table-responsive small">
			<table class="table table-striped table-sm">
				<thead>
					<tr>
						<th scope="col">STT</th>
						<th scope="col">Ngày - tháng - năm</th>
						<th scope="col">Tỉnh</th>
						<th scope="col">Số đã dò</th>
						<th scope="col">Kết quả đã dò</th>
						<th scope="col">Thời gian dò</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="i" value="${page*10-10}"></c:set>
					<c:forEach var="result" items="${historySearch}">
						<tr class="" title="click để đến trang Dò vé số" onmouseover="addColor(this)" onmouseout="removeColor(this)"  onclick='goToSearchLotteryPage(${result.province_id},"${result.lottery_date}","${result.lottery_number}")'>
							<td><c:set var="i" value="${i+1}"></c:set>${i}</td>
							<td>${result.lottery_date}</td>
							<td><c:forEach var="p" items="${provinces}">
									<c:if test="${p.province_id==result.province_id}">${p.province}</c:if>
								</c:forEach></td>
							<td>${result.lottery_number}</td>
							<td>${result.resultSearch}</td>
							<td>${result.search_time}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<!--  pagination - phân trang  -->
		<c:if test="${pages==0}">
			<p style="color: red;">Không có kết quả.</p>
		</c:if>
		<c:if test="${pages!=0}">
			<!--  pagination - phân trang  -->
			<nav aria-label="Page navigation  ">
				<ul id="pagination" class="pagination d-flex justify-content-center">
					<!-- previous pages -->
					<c:if test="${page>1}">
						<li class="page-item">
							<button type="submit" class="btn btn-outline-primary"
								title="Chuyển đến trang 1" onclick="changePage(1); doAction(1)">
								<span aria-hidden="true">&laquo;</span>
							</button>
						</li>
					</c:if>
					<c:if test="${page>1}">
						<li class="page-item">
							<button type="submit" class="btn btn-outline-primary"
								title="Chuyển đến trang ${page-1}"
								onclick="changePage(${page-1});doAction((${page-1})">
								<span aria-hidden="true">&lsaquo;</span>
							</button>
						</li>
					</c:if>
					<c:if test="${page<4}">
						<c:forEach var="i" begin="1" end="${page-1}" step="1">
							<li class="page-item">

								<button type="submit" class="btn btn-outline-primary"
									title="Chuyển đến trang ${i}"
									onclick="changePage(${i});doAction(${i})">${i}</button>
							</li>
						</c:forEach>
					</c:if>
					<c:if test="${page>3}">
						<c:forEach var="i" begin="${page-3}" end="${page-1}" step="1">
							<li class="page-item">
								<button type="submit" class="btn btn-outline-primary"
									title="Chuyển đến trang ${i}"
									onclick="changePage(${i});doAction(${i})">${i}</button>
							</li>
						</c:forEach>
					</c:if>
					<!-- current page -->
					<li class="page-item ">
						<button type="submit" class="btn  btn-primary active"
							title="Chuyển đến trang ${page}"
							onclick="changePage(${page});doAction(${page})">${page}</button>
					</li>

					<!-- next pages -->
					<c:if test="${page<pages}">
						<c:forEach var="i" begin="${page+1}" end="${page+3}" step="1">
							<c:if test="${i<=pages}">
								<li class="page-item">
									<button type="submit" class="btn btn-outline-primary"
										title="Chuyển đến trang ${i}"
										onclick="changePage(${i});doAction(${i})">${i}</button>
								</li>
							</c:if>
						</c:forEach>
					</c:if>

					<c:if test="${page+1<=pages}">
						<li class="page-item">
							<button type="submit" class="btn btn-outline-primary"
								title="Chuyển đến trang ${page+1}"
								onclick="changePage(${page+1});doAction(${page+1})">
								<span aria-hidden="true">&rsaquo;</span>
							</button>
						</li>
					</c:if>
					<c:if test="${page+1<=pages}">
						<li class="page-item">
							<button type="submit" class="btn btn-outline-primary"
								title="Chuyển đến trang ${pages}"
								onclick="changePage(${pages});doAction(${pages})">
								<span aria-hidden="true">&raquo;</span>
							</button>
						</li>
					</c:if>
				</ul>
			</nav>
		</c:if>
	</main>
	
	<!-- FOOTER -->
	<div  style="height: 55px;"></div>
	<c:import url="/view/website/footer.jsp"></c:import>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
	<script>	
		 
	
		function changePage(pa){
			var  p = pa;
			$.ajax({
			  url: "/LotteryWeb/HistorySearchLotteryController?action=changePage",
			  type: "post", //send it through get method
			  data: { 
			    page:p
			  },
			  success: function(response) {
				var row=document.getElementById("pagination");
				row.innerHTML=response;
			  },
			  error: function(xhr) {
			    //Do Something to handle error
			  }
			});
			
		}
		function doAction(pa){
			var  p = pa;
			$.ajax({
			  url: "/LotteryWeb/HistorySearchLotteryController?action=loadHistorySearchLottery",
			  type: "post", //send it through get method
			  data: { 
			    page:p
			  },
			  success: function(response) {
				var row=document.getElementById("historySearchLottery");
				row.innerHTML=response;
			  },
			  error: function(xhr) {
			    //Do Something to handle error
			  }
			});
			
		}
		function goToSearchLotteryPage(province_id,lottery_date,numberSearch){
			window.location.href= "SearchLotteryController?action=getResultSearch&date=" + lottery_date + "&provinceID=" + province_id+"&numberSearch="+numberSearch;			
		}
	</script>


	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
		crossorigin="anonymous"></script>

	<!--  
	<script
		src="https://cdn.jsdelivr.net/npm/chart.js@4.2.1/dist/chart.umd.min.js"
		integrity="sha384-gdQErvCNWvHQZj6XZM0dNsAoY4v+j5P1XDpNkcM3HJG1Yx04ecqIHk7+4VBOCHOG"
		crossorigin="anonymous"></script>
	<script src="${pageContext.request.contextPath}/js/chart.js"></script>
	
	-->
</body>
</html>
