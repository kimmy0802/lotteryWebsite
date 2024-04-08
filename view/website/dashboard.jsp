<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
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

<meta charset="UTF-8">
<title>Menu</title>
</head>
<body>
	<main class="sticky-top">
		<nav
			class="navbar navbar-expand-lg navbar-danger bg-danger text-light "
			style="--bs-white-rgb: 248, 246, 240; --bs-danger: #dc3545; --bs-navbar-active-color: #dc3344">
			<div class="container-fluid">
				<a class="navbar-brand  text-light bg-danger"
					style="box-shadow: none;"
					href="${pageContext.request.contextPath}/index.jsp"> XỔ SỐ KIẾN
					THIẾT THẦN TÀI </a>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>

				<div class="collapse navbar-collapse " id="navbarSupportedContent">
					<ul class="navbar-nav me-auto mb-2 mb-lg-0">
						<li class="nav-item"><a class="nav-link active  text-light"
							aria-current="page"
							href="${pageContext.request.contextPath}/index.jsp">Trang chủ</a></li>
						<!-- lottery website -->
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle  text-light" href=""
							id="navbarDropdown1" role="button" data-bs-toggle="dropdown"
							aria-expanded="false">Vé số </a>
							<ul id="" class="dropdown-menu bg-danger"
								aria-labelledby="navbarDropdown1">
								<li class=""><a
									class="dropdown-item d-flex align-items-center gap-2 text-light"
									style="-bs-nav-link-hover-color: #DADBDD; - -bs-nav-link-color: #FFFFFF;"
									href="${pageContext.request.contextPath}/Lottery"> <i
										class="bi bi-list"></i> Danh sách theo Miền
								</a></li>
								<li class=""><a
									class="dropdown-item d-flex align-items-center gap-2 text-light"
									style="-bs-nav-link-hover-color: #DADBDD; - -bs-nav-link-color: #FFFFFF;"
									href="${pageContext.request.contextPath}/LotteryOfProvince?action=lotteryOfProvince">
										<i class="bi bi-list"></i> Danh sách theo Nhà đài
								</a></li>
								<li class=""><a
									class="dropdown-item d-flex align-items-center gap-2 text-light"
									style="-bs-nav-link-hover-color: #DADBDD; - -bs-nav-link-color: #FFFFFF;"
									href="${pageContext.request.contextPath}/LotteryOfDate"> <i
										class="bi bi-list"></i> Danh sách theo Ngày mở thưởng
								</a></li>
								<li class=""><a
									class="dropdown-item d-flex align-items-center gap-2 text-light"
									style="-bs-nav-link-hover-color: #DADBDD; - -bs-nav-link-color: #FFFFFF;"
									href="${pageContext.request.contextPath}/SearchLottery"> <i
										class="bi bi-search"></i> Dò vé số
								</a></li>

							</ul></li>

						<!-- inifo website -->
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle  text-light" href="#"
							id="navbarDropdown2" role="button" data-bs-toggle="dropdown"
							aria-expanded="false">Thông tin </a>
							<ul class="dropdown-menu bg-danger"
								aria-labelledby="navbarDropdown2">
								<li class=""><a
									class="dropdown-item d-flex align-items-center gap-2 text-light"
									style="-bs-nav-link-hover-color: #DADBDD; - -bs-nav-link-color: #FFFFFF;"
									href="${pageContext.request.contextPath}/view/website/gioithieu.jsp">
										<i class="bi bi-list"></i> Giới thiệu
								</a></li>
								<li class=""><a
									class="dropdown-item d-flex align-items-center gap-2 text-light"
									style="-bs-nav-link-hover-color: #DADBDD; - -bs-nav-link-color: #FFFFFF;"
									href="${pageContext.request.contextPath}/view/website/lich_mo_thuong.jsp">
										<i class="bi bi-list"></i> Lịch mở thưởng
								</a></li>
								<li class=""><a
									class="dropdown-item d-flex align-items-center gap-2 text-light"
									style="-bs-nav-link-hover-color: #DADBDD; - -bs-nav-link-color: #FFFFFF;"
									href="${pageContext.request.contextPath}/InformationController?action=xsmb">
										<i class="bi bi-list"></i> Xổ số Miền Bắc
								</a></li>
								<li class=""><a
									class="dropdown-item d-flex align-items-center gap-2 text-light"
									style="-bs-nav-link-hover-color: #DADBDD; - -bs-nav-link-color: #FFFFFF;"
									href="${pageContext.request.contextPath}/InformationController?action=xsmt">
										<i class="bi bi-file-earmark-plus"></i> Xổ số Miền Trung
								</a></li>
								<li class=""><a
									class="dropdown-item d-flex align-items-center gap-2 text-light"
									style="-bs-nav-link-hover-color: #DADBDD; - -bs-nav-link-color: #FFFFFF;"
									href="${pageContext.request.contextPath}/InformationController?action=xsmn">
										<i class="bi bi-graph-up"></i> Xổ số Miền Nam
								</a></li>
								<li class=""><a
									class="dropdown-item d-flex align-items-center gap-2 text-light"
									style="-bs-nav-link-hover-color: #DADBDD; - -bs-nav-link-color: #FFFFFF;"
									href="${pageContext.request.contextPath}/InformationController?action=ttlt">
										<i class="bi bi-graph-up"></i> Thủ tục lãnh thưởng
								</a></li>
								<li class=""><a
									class="dropdown-item d-flex align-items-center gap-2 text-light"
									style="-bs-nav-link-hover-color: #DADBDD; - -bs-nav-link-color: #FFFFFF;"
									href="${pageContext.request.contextPath}/InformationController?action=qdlt">
										<i class="bi bi-graph-up"></i> Quy định lãnh thưởng
								</a></li>
								<li class=""><a
									class="dropdown-item d-flex align-items-center gap-2 text-light"
									style="-bs-nav-link-hover-color: #DADBDD; - -bs-nav-link-color: #FFFFFF;"
									href="${pageContext.request.contextPath}/InformationController?action=diachilanhthuong">
										<i class="bi bi-graph-up"></i> Địa chỉ lãnh thưởng
								</a></li>
							</ul></li>

						<c:if test="${sessionScope.account!=null}">
							
							<!--  admin and boss -->
							<c:if test='${sessionScope.account.part!="user"}'>
								<!--  Quản lý người dùng -->
								<li class="nav-item dropdown"><a
									class="nav-link dropdown-toggle  text-light" href="#"
									id="navbarDropdown3" role="button" data-bs-toggle="dropdown"
									aria-expanded="false">Quản lý Người dùng </a>
									<ul class="dropdown-menu bg-danger"
										aria-labelledby="navbarDropdown3">
										<li class=""><a
											class="dropdown-item d-flex align-items-center gap-2 text-light"
											style="-bs-nav-link-hover-color: #DADBDD; - -bs-nav-link-color: #FFFFFF;"
											href="${pageContext.request.contextPath}/AccountsList"> <i
												class="bi bi-list"></i> Danh sách
										</a></li>
										<li class=""><a
											class="dropdown-item d-flex align-items-center gap-2 text-light"
											style="-bs-nav-link-hover-color: #DADBDD; - -bs-nav-link-color: #FFFFFF;"
											href="${pageContext.request.contextPath}/HistoryAccountsAccess">
												<i class="bi bi-graph-up"></i> Lịch sử người dùng truy cập
										</a></li>
										<li class=""><a
											class="dropdown-item d-flex align-items-center gap-2 text-light"
											style="-bs-nav-link-hover-color: #DADBDD; - -bs-nav-link-color: #FFFFFF;"
											href="${pageContext.request.contextPath}/ResetAccountForUser">
												<i class="bi bi-arrow-clockwise"></i> Khôi phục tài khoản
												người dùng
										</a></li>
									</ul></li>
								<!--  Quản lý vé số -->
								<li class="nav-item dropdown"><a
									class="nav-link dropdown-toggle  text-light" href="#"
									id="navbarDropdown4" role="button" data-bs-toggle="dropdown"
									aria-expanded="false">Quản lý Vé số </a>
									<ul class="dropdown-menu bg-danger"
										aria-labelledby="navbarDropdown4">
										<li class=""><a
											class="dropdown-item d-flex align-items-center gap-2 text-light"
											style="-bs-nav-link-hover-color: #DADBDD; - -bs-nav-link-color: #FFFFFF;"
											href="${pageContext.request.contextPath}/LotteriesList">
												<i class="bi bi-list"></i> Danh sách
										</a></li>
										<li class=""><a
											class="dropdown-item d-flex align-items-center gap-2 text-light"
											style="-bs-nav-link-hover-color: #DADBDD; - -bs-nav-link-color: #FFFFFF;"
											href="${pageContext.request.contextPath}/UpdateLotteries">
												<i class="bi bi-pencil-square"></i> Cập nhật
										</a></li>
										<li class=""><a
											class="dropdown-item d-flex align-items-center gap-2 text-light"
											style="-bs-nav-link-hover-color: #DADBDD; - -bs-nav-link-color: #FFFFFF;"
											href="${pageContext.request.contextPath}/CreateLotteries">
												<i class="bi bi-file-earmark-plus"></i> Thêm
										</a></li>

									</ul></li>

								<!--  Quản lý Nhà đài -->
								<li class="nav-item dropdown"><a
									class="nav-link dropdown-toggle  text-light" href="#"
									id="navbarDropdown4" role="button" data-bs-toggle="dropdown"
									aria-expanded="false">Quản lý Nhà đài </a>
									<ul class="dropdown-menu bg-danger"
										aria-labelledby="navbarDropdown4">
										<li class=""><a
											class="dropdown-item d-flex align-items-center gap-2 text-light"
											style="-bs-nav-link-hover-color: #DADBDD; - -bs-nav-link-color: #FFFFFF;"
											href="${pageContext.request.contextPath}/Provinces"> <i
												class="bi bi-list"></i> Danh sách
										</a></li>
										<li class=""><a
											class="dropdown-item d-flex align-items-center gap-2 text-light"
											style="-bs-nav-link-hover-color: #DADBDD; - -bs-nav-link-color: #FFFFFF;"
											href="${pageContext.request.contextPath}/Provinces?action=createProvince">
												<i class="bi bi-file-earmark-plus"></i> Thêm
										</a></li>
									</ul></li>
							</c:if>


							<!--  admin - boss - user -->
							<li class="nav-item dropdown ms-2"><a
								class="nav-link dropdown-toggle text-light" href="#"
								id="navbarDropdown5" role="button" data-bs-toggle="dropdown"
								aria-expanded="false">Hello ${sessionScope.account.fullname}
							</a>
								<ul class="dropdown-menu bg-danger"
									aria-labelledby="navbarDropdown5">
									<li class=""><a
										class="dropdown-item d-flex align-items-center gap-2 text-light"
										style="-bs-nav-link-hover-color: #DADBDD; - -bs-nav-link-color: #FFFFFF;"
										href="${pageContext.request.contextPath}/UpdateAccount?action=updateAccount">
											<i class="bi bi-pencil-square "></i> Cập nhật Thông tin
									</a></li>
									<li class=""><a
										class="dropdown-item d-flex align-items-center gap-2 text-light"
										style="-bs-nav-link-hover-color: #DADBDD; - -bs-nav-link-color: #FFFFFF;"
										href="${pageContext.request.contextPath}/UpdateAccount?action=changePassword">
											<i class="bi bi-key"></i> Thay đổi Mật khẩu
									</a></li>
									<li class=""><a
										class="dropdown-item d-flex align-items-center gap-2 text-light"
										style="-bs-nav-link-hover-color: #DADBDD; - -bs-nav-link-color: #FFFFFF;"
										href="${pageContext.request.contextPath}/HistorySearchLotteryController">
											<i class="bi bi-graph-up"></i> Lịch sử dò vé số
									</a></li>
								</ul></li>

							<li class="nav-item"><a
								class="nav-link d-flex align-items-center gap-2  text-light"
								style="-bs-nav-link-hover-color: #DADBDD; - -bs-nav-link-color: #FFFFFF;"
								href="${pageContext.request.contextPath}/Logout">Đăng xuất </a></li>

						</c:if>
						<c:if test="${sessionScope.account==null}">
							<li class="nav-item "><a
								class="nav-link d-flex align-items-center gap-2  text-light"
								style="-bs-nav-link-hover-color: #DADBDD; - -bs-nav-link-color: #FFFFFF;"
								href="${pageContext.request.contextPath}/LoginWithSystemAccountController">Đăng
									Nhập </a></li>
							<li class="nav-item "><a
								class="nav-link d-flex align-items-center gap-2  text-light"
								style="-bs-nav-link-hover-color: #DADBDD; - -bs-nav-link-color: #FFFFFF;"
								href="${pageContext.request.contextPath}/Register">Đăng Ký </a></li>

						</c:if>
					</ul>
					<form class="d-flex pt-3"
						action="${pageContext.request.contextPath}/Lottery" method="post">
						<input type="hidden" name="action" value="searchLotteries" />
						<c:if test="${searchLotteries==null}">
							<input id="searchLotteries" type="search"
								class="form-control form-control-dark"
								placeholder="Nhập thông tin vé số cần tìm..."
								name="searchLotteries" />
							<c:set var="page" value="1" scope="session"></c:set>
						</c:if>
						<c:if test="${searchLotteries!=null}">
							<input id="searchLotteries" type="search"
								class="form-control form-control-dark" name="searchLotteries"
								value="${searchLotteries}">
						</c:if>
						<button class="btn btn-outline-light" type="submit"
							title="Tìm kiếm">
							<i class="bi bi-search"></i>
						</button>
					</form>

				</div>
			</div>
		</nav>

	</main>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
		crossorigin="anonymous"></script>
</body>
</html>