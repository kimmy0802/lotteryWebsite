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

<title>Thêm Vé số mới</title>
</head>
<body>

	<!---------------------- navbar---------------------------------->
	<c:import url="/view/website/dashboard.jsp"></c:import>
	<!--------------------------------------------CREATE NEW LOTTERY-------------------------------------------->
	<main
		class="row justify-content-center mx-auto mt-5 " style="min-height: 100vh;">
		<div id="selectedContext" class="col-12 col-lg-8 row  px-5 mb-3 justify-content-center">
		<h2 class="col-12  text-center" >Thêm số trúng thưởng mới</h2>
				<div class="form-floating col-12 col-md-7">
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
				<div class="form-floating col-12 col-md-7 ">
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

				<div class="form-floating col-12 col-md-7">
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
			</div>
		<div class="col-12 col-md-8 row justify-content-center ">
				<div class="col-5 justify-content-center">
					<button name="submit" type="submit" onclick="createLottery()"
						class="btn btn-primary me-5">Thêm</button>
				</div>
				
			</div>		
	</main>
	
	<!-- FOOTER -->
	<div  style="height: 55px;"></div>
	<c:import url="/view/website/footer.jsp"></c:import>
	
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
	<script >
	function createLottery(){
		var selectRegion = document.getElementById("selectRegion").value;
		var selectDate = document.getElementById("selectDateLottery").value;
		var selectProvince = document.getElementById("selectProvince").value;
		$.ajax({
		  url: "/LotteryWeb/CreateLotteriesController?action=create",
		  type: "post", //send it through get method
		  data: { 
			  region:selectRegion,
			  date:selectDate,
			  provinceID:selectProvince
		  },
		  success: function(response) {
			  alert(response);
		  },
		  error: function(xhr) {
		    //Do Something to handle error
		  }
		});
		
	}
	function changeRDP() {
		var selectRegion = document.getElementById("selectRegion").value;
		var selectDate = document.getElementById("selectDateLottery").value;
		var selectProvince = document.getElementById("selectProvince").value;
		
		$.ajax({
			  url: "/LotteryWeb/CreateLotteriesController?action=changeRDP",
			  type: "post", //send it through get method
			  data: { 
				  region:selectRegion,
				  date:selectDate,
				  provinceID:selectProvince
				  
			  },
			  success: function(response) {
				var row=document.getElementById("selectedContext");
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
