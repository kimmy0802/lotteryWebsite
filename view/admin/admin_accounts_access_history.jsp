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
<!-- JS -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/account.js"></script>

<title>Lịch sử người dùng truy cập</title>
</head>

<body>

	<!---------------------- navbar---------------------------------->
	<c:import url="/view/website/dashboard.jsp"></c:import>
	<!------------------------------------------ACCESS HISTORY------------------------------------------>

	<main class="col-md-9 mx-auto col-lg-10 mt-4" style="min-height: 100vh;">
		<!--  				<canvas class="my-4 w-100" id="myChart" width="900" height="380"></canvas>
-->
		<h2>Lịch sử Người dùng truy cập</h2>
		<div id="contextAccounts" class="table-responsive small">
			<table class="table table-striped table-sm">
				<thead>
					<tr>
						<th scope="col">STT</th>
						<th scope="col">Tài khoản truy cập</th>
						<th scope="col">Ngày truy cập</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="i" value="${page*10-10}"></c:set>
					<c:forEach var="result" items="${historyAccess}">
						<tr onmouseover="addColor(this)" onmouseout="removeColor(this)">
							<td><c:set var="i" value="${i+1}"></c:set>${i}</td>
							<td>${result.username}</td>
							<td>${result.date_access}</td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</div>
		<!--  pagination - phân trang  -->
		<c:if test="${pages==0}">
			<p style="color: red;">Không có kết quả.
			<p>
		</c:if>
		<c:if test="${pages!=0}">
			<!--  pagination - phân trang  -->
			<nav aria-label="Page navigation  ">
				<ul id="pagination" class="pagination d-flex justify-content-center">
					<!-- previous pages -->
					<c:if test="${page>1}">
						<li class="page-item">
							<button type="submit" class="btn btn-outline-primary"
								title="Chuyển đến trang ${1}"
								onclick="doAction(${1});changePage(${1})">
								<span aria-hidden="true">&laquo;</span>
							</button>
						</li>
					</c:if>
					<c:if test="${page>1}">
						<li class="page-item">
							<button type="submit" class="btn btn-outline-primary"
								title="Chuyển đến trang ${page-1}"
								onclick="doAction(${page-1});changePage(${page-1})">
								<span aria-hidden="true">&lsaquo;</span>
							</button>
						</li>
					</c:if>
					<c:if test="${page<4}">
						<c:forEach var="i" begin="1" end="${page-1}" step="1">
							<li class="page-item">

								<button type="submit" class="btn btn-outline-primary"
									title="Chuyển đến trang ${i}"
									onclick="doAction(${i});changePage(${i})">${i}</button>
							</li>
						</c:forEach>
					</c:if>
					<c:if test="${page>3}">
						<c:forEach var="i" begin="${page-3}" end="${page-1}" step="1">
							<li class="page-item">
								<button type="submit" class="btn btn-outline-primary"
									title="Chuyển đến trang ${i}"
									onclick="doAction(${i});changePage(${i})">${i}</button>
							</li>
						</c:forEach>
					</c:if>
					<!-- current page -->
					<li class="page-item ">
						<button type="submit" class="btn  btn-primary active"
							title="Chuyển đến trang ${page}"
							onclick="doAction(${page});changePage(${page})">${page}</button>
					</li>

					<!-- next pages -->
					<c:if test="${page<pages}">
						<c:forEach var="i" begin="${page+1}" end="${page+3}" step="1">
							<c:if test="${i<=pages}">
								<li class="page-item">
									<button type="submit" class="btn btn-outline-primary"
										title="Chuyển đến trang ${i}"
										onclick="doAction(${i});changePage(${i})">${i}</button>
								</li>
							</c:if>
						</c:forEach>
					</c:if>

					<c:if test="${page+1<=pages}">
						<li class="page-item">
							<button type="submit" class="btn btn-outline-primary"
								title="Chuyển đến trang ${page+1}"
								onclick="doAction(${page+1});changePage(${page+1})">
								<span aria-hidden="true">&rsaquo;</span>
							</button>
						</li>
					</c:if>
					<c:if test="${page+1<=pages}">
						<li class="page-item">
							<button type="submit" class="btn btn-outline-primary"
								title="Chuyển đến trang ${pages}"
								onclick="doAction(${pages});changePage(${pages})">
								<span aria-hidden="true">&raquo;</span>
							</button>
						</li>
					</c:if>
				</ul>
			</nav>

			<%--
			<nav aria-label="Page navigation  ">
				<ul class="pagination d-flex justify-content-center">
					<!-- previous pages -->
					<c:if test="${page>1}">
						<li class="page-item">
							<form method="post"
								action="${pageContext.request.contextPath}/HistoryAccountsAccess">
								<input name="action" value="goToPage" type="hidden" /> <input
									name="page" value="1" type="hidden" />
								<button type="submit" class="btn btn-outline-primary"
									title="Chuyển đến trang 1">
									<span aria-hidden="true">&laquo;</span>
								</button>
							</form>
						</li>
					</c:if>
					<c:if test="${page>1}">
						<li class="page-item">
							<form method="post"
								action="${pageContext.request.contextPath}/HistoryAccountsAccess">
								<input name="action" value="goToPage" type="hidden" /> <input
									name="page" value="${page-1}" type="hidden" />
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
								<form method="post"
									action="${pageContext.request.contextPath}/HistoryAccountsAccess">
									<input name="action" value="goToPage" type="hidden" /> <input
										name="page" value="${i}" type="hidden" />
									<button type="submit" class="btn-outline-primary btn"
										title="Chuyển đến trang ${i}">${i}</button>
								</form>
							</li>
						</c:forEach>
					</c:if>
					<c:if test="${page>3}">
						<c:forEach var="i" begin="${page-3}" end="${page-1}" step="1">
							<li class="page-item">
								<form method="post"
									action="${pageContext.request.contextPath}/HistoryAccountsAccess">
									<input name="action" value="goToPage" type="hidden" /> <input
										name="page" value="${i}" type="hidden" />
									<button type="submit" class="btn btn-outline-primary"
										title="Chuyển đến trang ${i}">${i}</button>
								</form>
							</li>
						</c:forEach>
					</c:if>
					<!-- current page -->
					<li class="page-item ">
						<form method="post"
							action="${pageContext.request.contextPath}/HistoryAccountsAccess">
							<input name="action" value="goToPage" type="hidden" /> <input
								name="page" value="${page}" type="hidden" />
							<button type="submit" class="btn btn-primary active "
								title="Trang hiện tại: trang ${page}">${page}</button>
						</form>
					</li>

					<!-- next pages -->
					<c:if test="${page<pages}">
						<c:forEach var="i" begin="${page+1}" end="${page+3}" step="1">
							<c:if test="${i<=pages}">
								<li class="page-item">
									<form method="post"
										action="${pageContext.request.contextPath}/HistoryAccountsAccess">
										<input name="action" value="goToPage" type="hidden" /> <input
											name="page" value="${i}" type="hidden" />
										<button type="submit" class="btn btn-outline-primary"
											title="Chuyển đến trang ${i}">${i}</button>
									</form>
								</li>
							</c:if>
						</c:forEach>
					</c:if>

					<c:if test="${page+1<=pages}">
						<li class="page-item">
							<form method="post"
								action="${pageContext.request.contextPath}/HistoryAccountsAccess">
								<input name="action" value="goToPage" type="hidden" /> <input
									name="page" value="${page+1}" type="hidden" />
								<button type="submit" class="btn btn-outline-primary"
									title="Chuyển đến trang ${page+1}">
									<span aria-hidden="true"> &rsaquo;</span>
								</button>
							</form>
						</li>
					</c:if>
					<c:if test="${page+1<=pages}">
						<li class="page-item">
							<form method="post"
								action="${pageContext.request.contextPath}/HistoryAccountsAccess">
								<input name="action" value="goToPage" type="hidden" /> <input
									name="page" value="${pages}" type="hidden" />
								<button type="submit" class="btn btn-outline-primary"
									title="Chuyển đến trang ${pages}">
									<span aria-hidden="true">&raquo;</span>
								</button>
							</form>
						</li>
					</c:if>
				</ul>
			</nav>			
			 --%>
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
		  url: "/LotteryWeb/HistoryAccountsAccessController?action=changePage",
		  type: "post", //send it through get method
		  data: { 
		    page:p
		  },
		  success: function(response) {
			var row=document.getElementById("pagination")
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
		  url: "/LotteryWeb/HistoryAccountsAccessController?action=loadAccounts",
		  type: "post", //send it through get method
		  data: { 
		    page:p
		  },
		  success: function(response) {
			var row=document.getElementById("contextAccounts")
			row.innerHTML=response;
		  },
		  error: function(xhr) {
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
