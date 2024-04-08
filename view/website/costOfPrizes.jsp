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
	
<!-- Style css-->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" />
	
<!-- Icons-->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" />

<title>Trị giá Giải thưởng</title>
</head>
<body>
	<!---------------------- navbar---------------------------------->
	<c:import url="/view/website/dashboard.jsp"></c:import>
	<!---------------------------------- LOTTERY DETAIL------------------------------------------>
	<main class="row mx-auto mt-3 px-3 px-sm-5" style="min-height: 100vh;">
		<h2>Danh sách giá trị các giải theo Miền</h2>
		<div class="col table-responsive small text-start">
			<table
				class="table table-striped  table-sm text-start border border-3 border-dark">
				<thead class="table-danger">
					<tr class="align-top border ">
						<th class="border " scope="col-1">Giải</th>
						<c:forEach var="result" items="${prizes}">
							<th class="border text-center item-lottery position-relative"
								scope="col">
								<p>${result.region}</p>
							</th>
						</c:forEach>
					</tr>
				</thead>
				<tbody class="table-group-divider">
					<tr>
						<th class="border col-1" scope="col">ĐB</th>
						<c:forEach var="result" items="${prizes}">
							<td class="border text-center"><c:out
									value="${result.special_prize}"></c:out></td>
						</c:forEach>
					</tr>
					<tr>
						<th class="border col-1" scope="col">1</th>
						<c:forEach var="result" items="${prizes}">
							<td class="border text-center"><c:out
									value="${result.first_prize}"></c:out></td>
						</c:forEach>
					</tr>
					<tr>
						<th class="border col-1" scope="col">2</th>
						<c:forEach var="result" items="${prizes}">
							<td class="border text-center"><c:out
									value="${result.second_prize}"></c:out></td>
						</c:forEach>
					</tr>
					<tr>
						<th class="border col-1" scope="col">3</th>
						<c:forEach var="result" items="${prizes}">
							<td class="border text-center"><c:out
									value="${result.third_prize}"></c:out></td>
						</c:forEach>
					</tr>
					<tr>
						<th class="border col-1" scope="col">4</th>
						<c:forEach var="result" items="${prizes}">
							<td class="border text-center"><c:out
									value="${result.fourth_prize}"></c:out></td>
						</c:forEach>
					</tr>
					<tr>
						<th class="border col-1" scope="col">5</th>
						<c:forEach var="result" items="${prizes}">
							<td class="border text-center"><c:out
									value="${result.fifth_prize}"></c:out></td>
						</c:forEach>
					</tr>
					<tr>
						<th class="border col-1" scope="col">6</th>
						<c:forEach var="result" items="${prizes}">
							<td class="border text-center"><c:out
									value="${result.sixth_prize}"></c:out></td>
						</c:forEach>
					</tr>
					<tr>
						<th class="border col-1" scope="col">7</th>
						<c:forEach var="result" items="${prizes}">
							<td class="border text-center"><c:out
									value="${result.seventh_prize}"></c:out></td>
						</c:forEach>
					</tr>
					<tr>
						<th class="border col-1" scope="col">8</th>
						<c:forEach var="result" items="${prizes}">
							<td class="border text-center"><c:out
									value="${result.eighth_prize}"></c:out></td>
						</c:forEach>
					</tr>
				</tbody>
			</table>
		</div>
	</main>

	<!-- FOOTER -->
	<div style="height:55px;"></div>
	<c:import url="/view/website/footer.jsp"></c:import>
	
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
		crossorigin="anonymous"></script>

</body>
</html>