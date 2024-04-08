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
	src="${pageContext.request.contextPath}/js/lottery.js"></script>

<title>Cập nhật vé số</title>
</head>

<body>

	<!---------------------- navbar---------------------------------->
	<c:import url="/view/website/dashboard.jsp"></c:import>
	<!---------------------- Cập  nhật số trúng thưởng---------------------------------->
	<main class="row justify-content-center mx-auto mt-3 px-5" style="min-height: 100vh;">
		<h2 class="p-0 m-0">Cập nhật số trúng thưởng:</h2>
		<p class="text-danger" id="messError">${messError}</p>
		<div id="updateContext" class="row">
			<div class="col-12 row">
				<div class="col-12 col-md-6 mb-2 form-floating">
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
					<p>
				</div>
				<div class="col-12 col-md-6 mb-2 form-floating">
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
					<p>
				</div>
				<div class="col-12 col-md-6 mb-2 form-floating">
					<select name="province" class="form-select" id="selectProvince"
						aria-label="select province" onchange="changeRDP()">
						<c:forEach var="p" items="${provinces}">
							<c:if test="${p.province==province}">
								<option value="${p.province}" selected="selected">${p.province}</option>
							</c:if>
							<c:if test="${p.province!=province}">
								<option value="${p.province}">${p.province}</option>
							</c:if>
						</c:forEach>
					</select> <label for="selectProvince">Chọn nhà đài</label>
					<p>
				</div>
				<div class="col-12 col-md-6 mb-2 form-floating">
					<input name="special_prize" type="text" class="form-control"
						id="special_prize" value="${special_prize}"
						oninput='checkInput("special_prize")' /> <label
						for="special_prize">Giải đặc biệt</label>
					<p class="text-danger" id="special_prizeMess">${special_prizeMess}</p>
				</div>
				<div class="col-12 col-md-6 mb-2 form-floating">
					<input name="first_prize" type="text" class="form-control"
						id="first_prize" value="${first_prize}"
						oninput='checkInput("first_prize")' /> <label for="first_prize">Giải
						Nhất</label>
					<p class="text-danger" id="first_prizeMess">${first_prizeMess}</p>

				</div>
				<div class="col-12 col-md-6 mb-2 form-floating">
					<input name="second_prize" type="text" class="form-control"
						id="second_prize" value="${second_prize}"
						oninput='checkInput("second_prize")' /> <label for="second_prize">Giải
						Nhì</label>
					<p class="text-danger" id="second_prizeMess">${second_prizeMess}</p>
				</div>
			</div>
			<div class="col-12 row">
				<div class="col-12 col-md-6 mb-2 form-floating">
					<input name="third_prize" type="text" class="form-control"
						id="third_prize" value="${third_prize}"
						oninput='checkInput("third_prize")' /> <label for="third_prize">Giải
						Ba</label>
					<p class="text-danger" id="third_prizeMess">${third_prizeMess}</p>
				</div>
				<div class="col-12 col-md-6 mb-2 form-floating">
					<input name="fourth_prize" type="text" class="form-control"
						id="fourth_prize" value="${fourth_prize}"
						oninput='checkInput("fourth_prize")' /> <label for="fourth_prize">Giải
						Bốn</label>
					<p class="text-danger" id="fourth_prizeMess">${fourth_prizeMess}</p>
				</div>
				<div class="col-12 col-md-6 mb-2 form-floating">
					<input name="fifth_prize" type="text" class="form-control"
						id="fifth_prize" value="${fifth_prize}"
						oninput='checkInput("fifth_prize")' /> <label for="fifth_prize">Giải
						Năm</label>
					<p class="text-danger" id="fifth_prizeMess">${fifth_prizeMess}</p>
				</div>
				<div class="col-12 col-md-6 mb-2 form-floating">
					<input name="sixth_prize" type="text" class="form-control"
						id="sixth_prize" value="${sixth_prize}"
						oninput='checkInput("sixth_prize")' /> <label for="sixth_prize">Giải
						Sáu</label>
					<p class="text-danger" id="sixth_prizeMess">${sixth_prizeMess}</p>
				</div>
				<div class="col-12 col-md-6 mb-2 form-floating">
					<input name="seventh_prize" type="text" class="form-control"
						id="seventh_prize" value="${seventh_prize}"
						oninput='checkInput("seventh_prize")' /> <label
						for="seventh_prize">Giải Bảy</label>
					<p class="text-danger" id="seventh_prizeMess">${seventh_prizeMess}</p>
				</div>

				<c:if test='${region!="Miền Bắc"}'>
					<div class="col-12 col-md-6 mb-2 form-floating">
						<input name="eighth_prize" type="text" class="form-control"
							id="eighth_prize" value="${eighth_prize}"
							oninput='checkInput("eighth_prize")' /> <label
							for="eighth_prize">Giải Tám</label>
						<p class="text-danger" id="eighth_prizeMess">${eighth_prizeMess}</p>
					</div>
				</c:if>
			</div>
		</div>
		<p id="resultCheckInput" class="inputTrue d-none">true</p>
		<div class="row justify-content-center">
			<div class="col-5">
				<button name="submit" type="submit" onclick="updateLottery()"
					class="btn btn-primary">Cập nhật</button>
			</div>
			<div class="col-4">
				<button name="submit" type="submit" onclick="changeRDP()"
					class="btn btn-danger ">Hủy</button>
			</div>
		</div>
	</main>

	<!-- FOOTER -->
	<div  style="height: 55px;"></div>
	<c:import url="/view/website/footer.jsp"></c:import>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
	<script>		
	function changeRDP(){
		var selectRegion = document.getElementById("selectRegion").value;
		var selectDate = document.getElementById("selectDateLottery").value;
		var selectProvince = document.getElementById("selectProvince").value;
		
		$.ajax({
		  url: "/LotteryWeb/UpdateLotteriesController?action=changeRDP",
		  type: "post", //send it through get method
		  data: { 
		    region:selectRegion,
		    date:selectDate,
			province:selectProvince
		  },
		  success: function(response) {
			var row=document.getElementById("updateContext")
			row.innerHTML=response;
		  },
		  error: function(xhr) {
		    //Do Something to handle error
		  }
		});		
	}	
	
	function updateLottery(){
		var resultCheckInput=document.getElementById("resultCheckInput");
		if(!resultCheckInput.classList.contains("inputTrue")){
			alert("Cập nhật thất bại");
			return;
		}
		if(resultCheckInput.classList.contains("inputTrue")){
			var selectRegion = document.getElementById("selectRegion").value;
			var selectDate = document.getElementById("selectDateLottery").value;
			var selectProvince = document.getElementById("selectProvince").value;
			var special_prize=document.getElementById("special_prize").value;
			var first_prize=document.getElementById("first_prize").value;
			var second_prize=document.getElementById("second_prize").value;
			var third_prize=document.getElementById("third_prize").value;
			var fourth_prize=document.getElementById("fourth_prize").value;
			var fifth_prize=document.getElementById("fifth_prize").value;
			var sixth_prize= document.getElementById("sixth_prize").value;
			var seventh_prize=document.getElementById("seventh_prize").value;	
			
			var eighth_prize=null;
			if(selectRegion!=="Miền Bắc"){
				eighth_prize=document.getElementById("eighth_prize").value;
			}
			$.ajax({
			  url: "/LotteryWeb/UpdateLotteriesController?action=update",
			  type: "post", //send it through get method
			  data: { 
			    region:selectRegion,
			    date:selectDate,
				province:selectProvince,
				special_prize:special_prize,
				first_prize:first_prize,
				second_prize:second_prize,
				third_prize:third_prize,
				fourth_prize:fourth_prize,
				fifth_prize:fifth_prize,
				sixth_prize:sixth_prize,
				seventh_prize:seventh_prize,				
				eighth_prize:eighth_prize
			  },
			  success: function(response) {
				  alert(response);
			  },
			  error: function(e) {
				  alert(e);
			  }
			});
			return;
			
		}
				
	}
	</script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
		crossorigin="anonymous"></script>
</body>
</html>
