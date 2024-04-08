<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

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

<!-- JS -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/account.js"></script>

<title>Danh sách người dùng</title>
</head>

<body
	onload='<c:if test="${isError==1}">alertDeleteFail()</c:if>
<c:if test="${isError==0}">alertDeleteSuccess()</c:if>
<c:if test="${resetPasswordisError==1}">resetPasswordFromAdminFail()</c:if>
<c:if test="${resetPasswordisError==0}">resetPasswordFromAdminSuccess()</c:if>'>

	<!---------------------- navbar---------------------------------->
	<c:import url="/view/website/dashboard.jsp"></c:import>

	<!--                              USERS DETAIL                              -->

	<main class="col-md-9 mx-sm-auto col-lg-10 px-md-4"
		style="min-height: 100vh;">
		<h2 class="pt-3">Danh sách người dùng</h2>
		<div
			class="d-flex justify-content-end flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
			<form class="d-flex col-lg-auto mb-3 mb-lg-0 me-lg-3 pe-3"
				action="${pageContext.request.contextPath}/AccountsList"
				method="post">
				<input type="hidden" name="action" value="searchAccounts" />

				<c:if test="${searchAccounts==null}">
					<input id="searchAccounts" type="search"
						class="col-6 form-control form-control-dark"
						placeholder="Nhập thông tin cần tìm..." name="searchAccounts" />
					<c:set var="page" value="1" scope="session"></c:set>
				</c:if>
				<c:if test="${searchAccounts!=null}">
					<input id="searchAccounts" type="search"
						class="col-6 form-control form-control-dark" name="searchAccounts"
						value="${searchAccounts}">
				</c:if>

				<button class="col-2 btn btn-outline-primary" type="submit"
					title="Tìm kiếm">
					<i class="bi bi-search"></i>
				</button>
			</form>
		</div>

		<!-- --------Sort  -->
		<div class="row justify-content-between mb-4">
			<div class="col-12 col-sm-6 col-lg-8 row me-0 m-0">
				<div class="col-7 col-sm-5 m-0 p-0">
					<select id="colOrder" name="colOrder" class="form-select "
						aria-label="Default select " onchange="doAction(1);changePage(1)">
						<c:forEach var="i" items="${listColOrderMap}">
							<c:if test="${i.key==colOrder}">
								<option value="${i.key}" selected="selected">${i.value}</option>
							</c:if>
							<c:if test="${i.key!=colOrder}">
								<option value="${i.key}">${i.value}</option>
							</c:if>
						</c:forEach>
					</select>
				</div>

				<div class="col-4 col-sm-3 m-0 p-0">
					<select name="order" id="order" class="form-select"
						onchange="doAction(1);changePage(1)" aria-label="Default select">

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
			<!--   delete button-->			
				<div id="deleteButton"
					class="col-10 col-sm-6  col-lg-4 row  m-0 p-0 mt-2 justify-content-center">
					<c:if test="${sessionScope.deleteAccountsList.size()==0}">
						<div class="col-4 ">
							<button class=" btn btn-danger pt-1 pb-1 ps-3 pe-3" type="submit"
								title="Xóa các mục đã chọn" onclick="alertCantDelete()">Xóa</button>
						</div>

					</c:if>
					<c:if test="${sessionScope.deleteAccountsList.size()!=0}">
						<div class="col-4 col-sm-2 me-1 ">
							<button class=" btn btn-danger pt-1 pb-1 ps-3 pe-3" type="submit"
								title="Xóa các mục đã chọn" onclick="confirmDeleteAccounts()">Xóa</button>
						</div>
						<div class="col-7 col-sm-9 ">
							<button class=" btn btn-primary ms-3 py-1 px-3" type="submit"
								title="Xóa các mục đã chọn" onclick="unSelectedList(${page})">Bỏ
								chọn tất cả</button>
						</div>
					</c:if>
				</div>
		</div>

		<!-- display accounts list -->
		<div id="contextAccounts" class="table-responsive small text-start">
			<table class="table table-striped table-sm text-start">
				<thead>
					<tr class="align-top">
						<th scope="col">STT</th>
						<th scope="col">Tên đăng nhập</th>
						<th scope="col">Họ và tên</th>
						<th scope="col">Số điện thoại</th>
						<th scope="col">Email</th>
						<th scope="col">Ngày tham gia</th>
						<th scope="col">Hoạt động</th>
						<th scope="col">Quyền</th>
						<th scope="col">Tài khoản</th>
						<th class="text-center" scope="col">Chọn</th>
					</tr>
				</thead>
				<tbody>
					<c:set var="j" value="${page*10-10}"></c:set>
					<c:forEach var="result" items="${accounts}">
						<tr onmouseover="showButton(${j});addColor(this)"
							onmouseout="hiddenButton(${j});removeColor(this)">

							<td class="item-account position-relative"
								id="stt${result.username}">
								<div
									class="show-button d-none bg-white position-absolute badge top-50 start-0 h-50"
									id="no_${j}">
									<button type="submit" class="btn btn-outline-none "
										title="	Xóa"
										onclick='confirmDeleteAccount("${sessionScope.account.part}","${result.username}",${page},"${result.part}")'>
										<i class="bi bi-trash"></i>
									</button>
									<button type="submit" class="btn btn-outline-none "
										title="Chỉnh sửa Tài khoản người dùng"
										onclick='updateAccount("${result.username}","${result.part}","${sessionScope.account.part}")'>
										<i class="bi bi-pencil-square"></i>
									</button>
									<button type="submit" class="btn btn-outline-none "
										title="Đổi mật khẩu"
										onclick='changePassword("${result.username}","${result.part}","${sessionScope.account.part}")'>
										<i class="bi bi-key"></i>
									</button>
								</div>
								<div>
									<c:out value="${j+1}"></c:out>
									<c:set var="j" value="${j+1}" />
								</div>
							</td>
							<td height="60px;" class=""><c:out
									value="${result.username}"></c:out></td>
							<td><c:out value="${result.fullname}"></c:out></td>
							<td><c:if test='${result.phonenumber!=""}'>
									<c:out value="${fn:substring(result.phonenumber,0,3)}*******"></c:out>
								</c:if></td>
							<td><c:if test='${result.email!=""}'>
									<c:out value="${fn:substring(result.email,0,3)}*******"></c:out>
								</c:if></td>
							<td><c:out value="${result.created_date}"></c:out></td>
							<td><c:if test="${result.activate==0}">
									<c:out value="Offline"></c:out>
								</c:if> <c:if test="${result.activate==1}">
									<c:out value="Online"></c:out>
								</c:if></td>
							<td><c:out value="${result.part}"></c:out></td>
							<td><c:out value="${result.created_by}"></c:out></td>
							<td class="text-center"><c:set var="isChecked" value="false"></c:set>
								<c:forEach var="i" items="${deleteAccountsList}">
									<c:if test="${i==result.username }">
										<c:set var="isChecked" value="true"></c:set>
									</c:if>
								</c:forEach> <c:if test="${isChecked==true}">
									<input class="form-check-input deleteList"
										id="d_${result.username}" type="checkbox" name="delete"
										checked
										onchange='deleteAccount("${sessionScope.account.part}","${result.username}",${page},"${result.part}")' />
								</c:if> <c:if test="${isChecked!=true}">
									<input class="form-check-input deleteList"
										id="d_${result.username}" type="checkbox" name="delete"
										onchange='deleteAccount("${sessionScope.account.part}","${result.username}",${page},"${result.part}")' />
								</c:if></td>
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
		</c:if>
	</main>

	<!-- FOOTER -->
	<div style="height: 55px;"></div>
	<c:import url="/view/website/footer.jsp"></c:import>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
	<script>
	function changePage(pa){
		var  p = pa;
		var s = document.getElementById("searchAccounts").value;
		
		$.ajax({
		  url: "/LotteryWeb/AccountsListController?action=changePage",
		  type: "post", //send it through get method
		  data: { 
		    page:p, 
		    searchAccounts: s
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
		var s = document.getElementById("searchAccounts").value;
		var c = document.getElementById("colOrder").value;
		var o = document.getElementById("order").value;

		$.ajax({
		  url: "/LotteryWeb/AccountsListController?action=loadAccounts",
		  type: "post", //send it through get method
		  data: { 
		    page:p, 
		    searchAccounts: s,
		    colOrder:c,
		    order:o
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
	
	function deleteAccount(accountPart,username, page,part){
		if(part==="boss"){
			document.getElementById("d_"+username).checked=false;
			alert("Không thể chọn tài khoản này!");
			return;
		}
		if(accountPart==="admin" && part==="admin"){
			document.getElementById("d_"+username).checked=false;
			alert("Không thể chọn tài khoản admin ");
			return;
		}
		
		
		var deleteId=document.getElementById("d_"+username);
		var p=page;
		var username=username;
		
		$.ajax({
			  url: "/LotteryWeb/DeleteAccountsController?action=addDelete",
			  type: "post", //send it through get method
			  data: { 
			    page:p, 
			    usernameDelete:username,
			    isDelete:(deleteId.checked===true)?"add":"remove"
			  },
			  success: function(response) {
				  var row=document.getElementById("deleteButton");
					row.innerHTML=response;
				  
				  doAction(page),
				  changePage(page)
				//row.innerHTML=response;
			  },
			  error: function(xhr) {
				  doAction(pa),
				  changePage(pa)
			    //Do Something to handle error
			  }
			});
		}
	
	function unSelectedList(page){
		var p=page;
		
		$.ajax({
			  url: "/LotteryWeb/DeleteAccountsController?action=removeAllDeleteList",
			  type: "post", //send it through get method
			  data: { 
			    page:p
			  },
			  success: function(response) {
				  var row=document.getElementById("deleteButton");
					row.innerHTML=response;
				  
				  doAction(page);
				  changePage(page);
				//row.innerHTML=response;
			  },
			  error: function(xhr) {
				  doAction(pa),
				  changePage(pa)
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
