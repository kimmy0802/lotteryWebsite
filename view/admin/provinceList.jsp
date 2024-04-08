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
	src="${pageContext.request.contextPath}/js/province.js"></script>
<title>Danh sách Nhà đài</title>
</head>
<body
	onload='<c:if test="${isError==1}">alertDeleteFail()</c:if><c:if test="${isError==0}">alertDeleteSuccess()</c:if>'>

	<!---------------------- navbar---------------------------------->
	<c:import url="/view/website/dashboard.jsp"></c:import>
	<!---------------------------------- LOTTERY DETAIL------------------------------------------>
	<main class="col-md-9 m-auto  col-lg-10 px-md-4"
		style="min-height: 100vh;">
		<div class="mb-3">
			<h2 class="col col-lg-5 pt-3">Danh sách Nhà đài</h2>
		</div>

		<!-- --------Sort  -->
		<div class="row justify-content-between mb-4">
			<form class="row col-12 col-sm-6 col-lg-8"
				action="${pageContext.request.contextPath}/Provinces" method="post">
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
					<select name="dateOfWeek" id="dateOfWeek" class="form-select m-0"
						aria-label="Default select ">
						<c:forEach var="r" items="${dateOfWeeks}">
							<c:if test='${r==dateOfWeek}'>
								<option value="${r}" selected="selected">${r}</option>
							</c:if>
							<c:if test='${r!=dateOfWeek}'>
								<option value="${r}">${r}</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
				<div class="col-1">
					<input name="action" value="filter" type="hidden" />
					<button class="btn btn-danger" type="submit">
						<i class="bi bi-funnel"></i>
					</button>
				</div>

			</form>

			<!--   delete button-->
			<div id="deleteButton"
				class="col-10 col-sm-6  col-lg-4 row  m-0 p-0 mt-2 justify-content-center">
				<c:if test="${sessionScope.deleteProvincesList.size()==0}">
					<div class="col-4 ">
						<button class=" btn btn-danger pt-1 pb-1 ps-3 pe-3" type="submit"
							title="Xóa các mục đã chọn" onclick="alertCantDelete()">Xóa</button>
					</div>

				</c:if>
				<c:if test="${sessionScope.deleteProvincesList.size()!=0}">
					<div class="col-4 col-sm-2 me-1 ">
						<button class=" btn btn-danger pt-1 pb-1 ps-3 pe-3" type="submit"
							title="Xóa các mục đã chọn" onclick="confirmDeleteProvinces()">Xóa</button>
					</div>
					<div class="col-7 col-sm-9 ">
						<button class=" btn btn-primary ms-3 py-1 px-3" type="submit"
							title="Xóa các mục đã chọn" onclick="unSelectedList()">Bỏ
							chọn tất cả</button>
					</div>
				</c:if>
			</div>
		</div>

		<!--  show result -->
		<div id="contextProvinces" class="table-responsive small text-start">
			<table class="table table-striped table-sm text-start">
				<thead>
					<tr class="align-top">
						<th scope="col">STT</th>
						<th scope="col">Ngày mở thưởng</th>
						<th scope="col">Nhà đài</th>
						<th scope="col">Miền</th>
						<th class="text-center" scope="col">Chọn</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="j" value="${0}"></c:set>
					<c:forEach var="result" items="${provinces}">
						<tr onmouseover="showButton(${j});addColor(this)"
							onmouseout="hiddenButton(${j});removeColor(this)">
							<td class="item-account position-relative"
								id="stt${result.province_id}">
								<div
									class="show-button d-none bg-white position-absolute badge top-50 start-0 h-50"
									id="no_${j}">
									<button type="submit" class="btn btn-outline-none "
										title="	Xóa"
										onclick='confirmDeleteProvince(${result.province_id},"${region}","${dateOfWeek}")'>
										<i class="bi bi-trash"></i>
									</button>
									<button type="submit" class="btn btn-outline-none "
										title="Chỉnh sửa Nhà đài"
										onclick="updateProvince(${result.province_id})">
										<i class="bi bi-pencil-square"></i>
									</button>
								</div>
								<div>
									<c:out value="${j+1}"></c:out>
									<c:set var="j" value="${j+1}" />
								</div>
							</td>
							<td height="60px;" class=""><c:out
									value="${result.dateOpen}"></c:out></td>
							<td><c:out value="${result.province}"></c:out></td>
							<td><c:out value="${result.region}"></c:out></td>
							
							<td class="text-center">
								<c:set var="isChecked" value="false"></c:set>
								<c:forEach var="i" items="${deleteProvincesList}">
									<c:if test="${i==result.province_id }">
										<c:set var="isChecked" value="true"></c:set>
									</c:if>
								</c:forEach> <c:if test="${isChecked==true}">
									<input class="form-check-input deleteList"
										id="d_${result.province_id}" type="checkbox" name="delete"
										checked
										onchange="deleteProvince(${result.province_id})" />
								</c:if> <c:if test="${isChecked!=true}">
									<input class="form-check-input deleteList"
										id="d_${result.province_id}" type="checkbox" name="delete"
										onchange="deleteProvince(${result.province_id})" />
								</c:if>
							</td>
						</tr>

					</c:forEach>
				</tbody>
			</table>
		</div>


	</main>

	<!-- FOOTER -->
	<div style="height: 55px;"></div>
	<c:import url="/view/website/footer.jsp"></c:import>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
	<script>
	
	function doAction(){
		var d = document.getElementById("dateOfWeek").value;
		var r= document.getElementById("region").value;

		$.ajax({
		  url: "/LotteryWeb/Provinces?action=loadProvince",
		  type: "post", //send it through get method
		  data: { 
			  dateOfWeek: d,
			  region:r
		  },
		  success: function(response) {
			var row=document.getElementById("contextProvinces")
			row.innerHTML=response;
		  },
		  error: function(xhr) {
		    //Do Something to handle error
		  }
		});
		
	}
	function deleteProvince(province_id){
		
		var deleteId=document.getElementById("d_"+province_id);		
		$.ajax({
			  url: "/LotteryWeb/Provinces?action=addDelete",
			  type: "post", //send it through get method
			  data: { 
				province_id:province_id, 
			    isDelete:(deleteId.checked===true)?"add":"remove",
			  },
			  success: function(response) {
				 	var row=document.getElementById("deleteButton");
					row.innerHTML=response;				  
				  	doAction();
			  },
			  error: function(xhr) {
				  doAction();
			    //Do Something to handle error
			  }
			});
		}
	
	function unSelectedList(){
		$.ajax({
			  url: "/LotteryWeb/Provinces?action=removeAllDeleteList",
			  type: "post", //send it through get method
			  success: function(response) {
				  var row=document.getElementById("deleteButton");
				  row.innerHTML=response;				  
				  doAction();
			  },
			  error: function(xhr) {
				  doAction();
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
