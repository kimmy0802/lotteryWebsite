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
<body onload='<c:if test="${isError==1}">alertDeleteFail()</c:if><c:if test="${isError==0}">alertDeleteSuccess(${page})</c:if>'>

	<!---------------------- navbar---------------------------------->
	<c:import url="/view/website/dashboard.jsp"></c:import>
	<!---------------------------------- LOTTERY DETAIL------------------------------------------>
	<main class="col-md-9 m-auto  col-lg-10 px-md-4" style="min-height: 100vh;">
		<div class="mb-3">
			<h2 class="col col-lg-5 pt-3">Danh sách Số trúng thưởng</h2>
		</div>

		<!-- --------Sort  -->
		<div class="row justify-content-between mb-4">
			<div class="col-12 col-sm-6 col-lg-8 row me-0 m-0">
				<div class="col-3 col-sm-4 m-0 p-0">
					<select name="region" id="region" class="form-select"
						onchange="doAction(1);changePage(1)" aria-label="Default select ">

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
					<select name="colOrder" id="colOrder" class="form-select m-0"
						onchange="doAction(1);changePage(1)" aria-label="Default select ">
						<c:if test='${colOrder=="province"}'>
							<option value="province" selected="selected">Nhà đài</option>
							<option value="lottery_date">Ngày</option>
						</c:if>
						<c:if test='${colOrder=="lottery_date"}'>
							<option value="lottery_date" selected="selected">Ngày</option>
							<option value="province">Nhà đài</option>
						</c:if>

					</select>
				</div>
				<div class="col-3 col-sm-3 m-0 p-0">
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
				<c:if test="${sessionScope.deleteLotteriesList.size()==0}">
					<div class="col-4 ">
						<button class=" btn btn-danger pt-1 pb-1 ps-3 pe-3" type="submit"
							title="Xóa các mục đã chọn" onclick="alertCantDelete()">Xóa</button>
					</div>

				</c:if>
				<c:if test="${sessionScope.deleteLotteriesList.size()!=0}">
					<div class="col-4 col-sm-2 me-1 ">
						<button class=" btn btn-danger pt-1 pb-1 ps-3 pe-3" type="submit"
							title="Xóa các mục đã chọn" onclick="confirmDeleteLotteries()">Xóa</button>
					</div>
					<div class="col-7 col-sm-9 ">
						<button class=" btn btn-primary ms-3 py-1 px-3" type="submit"
							title="Xóa các mục đã chọn" onclick="unSelectedList(${page})">Bỏ
							chọn tất cả</button>
					</div>
				</c:if>
			</div>
		</div>


		<!--  show result -->
		<c:if test="${pages==0}">
			<p style="color: red;">Không có kết quả.</p>
		</c:if>
		<c:if test="${pages!=0}">
			<!-- display lotteries list -->
			<div id="contextLottery" class="table-responsive small text-start">
				<c:if test='${region!="Tất cả"}'>
					<table
						class="table table-striped  table-sm text-start border border-3 border-dark">
						<thead class="table-danger">
							<tr class="align-top border ">
								<th class="border " scope="col">Giải</th>
								<c:forEach var="result" items="${lotteryShow}">
									<th onmouseover="showButton(${result.lottery.lottery_id})"
											onmouseout="hiddenButton(${result.lottery.lottery_id})"
											class="border text-center item-lottery position-relative"
											scope="col">
											<p>${result.dayOfWeek} - ${result.date} - ${result.region}</p>
											<p>${result.province}
												<c:set var="isChecked" value="false"></c:set>
												<c:forEach var="i" items="${deleteLotteriesList}">
													<c:if test="${i==result.lottery.lottery_id}">
														<c:set var="isChecked" value="true"></c:set>
													</c:if>
												</c:forEach>
												<c:if test="${isChecked==true}">
													<input class="form-check-input deleteList"
														id="delete${result.lottery.lottery_id}" type="checkbox"
														name="delete" checked
														onchange="deleteLottery(${result.lottery.lottery_id},${page})" />
												</c:if>
												<c:if test="${isChecked!=true}">
													<input class="form-check-input deleteList"
														id="delete${result.lottery.lottery_id}" type="checkbox"
														name="delete"
														onchange="deleteLottery(${result.lottery.lottery_id},${page})" />
												</c:if>
											</p>
											<div
												class="show-button d-none bg-white position-absolute badge top-50 start-0 h-50"
												id="no_${result.lottery.lottery_id}">
												<button type="submit" class="btn  btn-outline-none"
													title="Xóa"
													onclick="confirmDeleteLottery(${result.lottery.lottery_id},${page})">
													<i class="bi bi-trash"></i>
												</button>
												<button type="submit" class="btn btn-outline-none"
													title="Cập nhật"
													onclick='updateLottery(${result.lottery.lottery_id})'>
													<i class="bi bi-pencil-square"></i>
												</button>
											</div>
										</th>
								</c:forEach>
							</tr>
						</thead>
						<tbody class="table-group-divider">
							<c:set var="j" value="${page*3-3}"></c:set>
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
				</c:if>
				<!-- hien thi 3 mien---------------------------- -->
				<c:if test='${region=="Tất cả"}'>
					<c:set var="d" value="0"></c:set>
					<c:if test="${lotteryShowMB.size()>0}">
						<c:set var="d" value="${d+1}"></c:set>
						<p>${d}. Miền Bắc:</p>
						<table
							class="table table-striped  table-sm text-start border border-3 border-dark">
							<thead class="table-danger">
								<tr class="align-top border ">
									<th class="border " scope="col">Giải</th>
									<c:forEach var="result" items="${lotteryShowMB}">
										<th onmouseover="showButton(${result.lottery.lottery_id})"
												onmouseout="hiddenButton(${result.lottery.lottery_id})"
												class="border text-center item-lottery position-relative"
												scope="col">
												<p>${result.dayOfWeek} - ${result.date} - ${result.region}</p>
												<p>${result.province}
													<c:set var="isChecked" value="false"></c:set>
													<c:forEach var="i" items="${deleteLotteriesList}">
														<c:if test="${i==result.lottery.lottery_id}">
															<c:set var="isChecked" value="true"></c:set>
														</c:if>
													</c:forEach>
													<c:if test="${isChecked==true}">
														<input class="form-check-input deleteList"
															id="delete${result.lottery.lottery_id}" type="checkbox"
															name="delete" checked
															onchange="deleteLottery(${result.lottery.lottery_id},${page})" />
													</c:if>
													<c:if test="${isChecked!=true}">
														<input class="form-check-input deleteList"
															id="delete${result.lottery.lottery_id}" type="checkbox"
															name="delete"
															onchange="deleteLottery(${result.lottery.lottery_id},${page})" />
													</c:if>
												</p>
												<div
													class="show-button d-none bg-white position-absolute badge top-50 start-0 h-50"
													id="no_${result.lottery.lottery_id}">
													<button type="submit" class="btn  btn-outline-none"
														title="Xóa"
														onclick="confirmDeleteLottery(${result.lottery.lottery_id},${page})">
														<i class="bi bi-trash"></i>
													</button>
													<button type="submit" class="btn btn-outline-none"
														title="Cập nhật"
														onclick='updateLottery(${result.lottery.lottery_id})'>
														<i class="bi bi-pencil-square"></i>
													</button>
												</div>
											</th>
									</c:forEach>
								</tr>
							</thead>
							<tbody class="table-group-divider">
								<c:set var="j" value="${page*3-3}"></c:set>
								<tr>
									<th class="border col-1" scope="col">ĐB</th>
									<c:forEach var="result" items="${lotteryShowMB}">
										<td class="border text-center"><c:out
												value="${result.lottery.special_prize}"></c:out></td>
									</c:forEach>
								</tr>
								<tr>
									<th class="border col-1" scope="col">1</th>
									<c:forEach var="result" items="${lotteryShowMB}">
										<td class="border text-center"><c:out
												value="${result.lottery.first_prize}"></c:out></td>
									</c:forEach>
								</tr>
								<tr>
									<th class="border col-1" scope="col">2</th>
									<c:forEach var="result" items="${lotteryShowMB}">
										<td class="border text-center"><c:out
												value="${result.lottery.second_prize}"></c:out></td>
									</c:forEach>
								</tr>
								<tr>
									<th class="border col-1" scope="col">3</th>
									<c:forEach var="result" items="${lotteryShowMB}">
										<td class="border text-center"><c:out
												value="${result.lottery.third_prize}"></c:out></td>
									</c:forEach>
								</tr>
								<tr>
									<th class="border col-1" scope="col">4</th>
									<c:forEach var="result" items="${lotteryShowMB}">
										<td class="border text-center"><c:out
												value="${result.lottery.fourth_prize}"></c:out></td>
									</c:forEach>
								</tr>
								<tr>
									<th class="border col-1" scope="col">5</th>
									<c:forEach var="result" items="${lotteryShowMB}">
										<td class="border text-center"><c:out
												value="${result.lottery.fifth_prize}"></c:out></td>
									</c:forEach>
								</tr>
								<tr>
									<th class="border col-1" scope="col">6</th>
									<c:forEach var="result" items="${lotteryShowMB}">
										<td class="border text-center"><c:out
												value="${result.lottery.sixth_prize}"></c:out></td>
									</c:forEach>
								</tr>
								<tr>
									<th class="border col-1" scope="col">7</th>
									<c:forEach var="result" items="${lotteryShowMB}">
										<td class="border text-center"><c:out
												value="${result.lottery.seventh_prize}"></c:out></td>
									</c:forEach>
								</tr>
							</tbody>
						</table>
					</c:if>
					<c:if test="${lotteryShowMT.size()>0}">
						<c:set var="d" value="${d+1}"></c:set>
						<p>${d}. Miền Trung:</p>
						<table
							class="table table-striped  table-sm text-start border border-3 border-dark">
							<thead class="table-danger">
								<tr class="align-top border ">
									<th class="border " scope="col">Giải</th>
									<c:forEach var="result" items="${lotteryShowMT}">
										<th onmouseover="showButton(${result.lottery.lottery_id})"
												onmouseout="hiddenButton(${result.lottery.lottery_id})"
												class="border text-center item-lottery position-relative"
												scope="col">
												<p>${result.dayOfWeek} - ${result.date} - ${result.region}</p>
												<p>${result.province}
													<c:set var="isChecked" value="false"></c:set>
													<c:forEach var="i" items="${deleteLotteriesList}">
														<c:if test="${i==result.lottery.lottery_id}">
															<c:set var="isChecked" value="true"></c:set>
														</c:if>
													</c:forEach>
													<c:if test="${isChecked==true}">
														<input class="form-check-input deleteList"
															id="delete${result.lottery.lottery_id}" type="checkbox"
															name="delete" checked
															onchange="deleteLottery(${result.lottery.lottery_id},${page})" />
													</c:if>
													<c:if test="${isChecked!=true}">
														<input class="form-check-input deleteList"
															id="delete${result.lottery.lottery_id}" type="checkbox"
															name="delete"
															onchange="deleteLottery(${result.lottery.lottery_id},${page})" />
													</c:if>
												</p>
												<div
													class="show-button d-none bg-white position-absolute badge top-50 start-0 h-50"
													id="no_${result.lottery.lottery_id}">
													<button type="submit" class="btn  btn-outline-none"
														title="Xóa"
														onclick="confirmDeleteLottery(${result.lottery.lottery_id},${page})">
														<i class="bi bi-trash"></i>
													</button>
													<button type="submit" class="btn btn-outline-none"
														title="Cập nhật"
														onclick='updateLottery(${result.lottery.lottery_id})'>
														<i class="bi bi-pencil-square"></i>
													</button>
												</div>
											</th>
										
									</c:forEach>
								</tr>
							</thead>
							<tbody class="table-group-divider">
								<c:set var="j" value="${page*3-3}"></c:set>
								<tr>
									<th class="border col-1" scope="col">ĐB</th>
									<c:forEach var="result" items="${lotteryShowMT}">
										<td class="border text-center"><c:out
												value="${result.lottery.special_prize}"></c:out></td>
									</c:forEach>
								</tr>
								<tr>
									<th class="border col-1" scope="col">1</th>
									<c:forEach var="result" items="${lotteryShowMT}">
										<td class="border text-center"><c:out
												value="${result.lottery.first_prize}"></c:out></td>
									</c:forEach>
								</tr>
								<tr>
									<th class="border col-1" scope="col">2</th>
									<c:forEach var="result" items="${lotteryShowMT}">
										<td class="border text-center"><c:out
												value="${result.lottery.second_prize}"></c:out></td>
									</c:forEach>
								</tr>
								<tr>
									<th class="border col-1" scope="col">3</th>
									<c:forEach var="result" items="${lotteryShowMT}">
										<td class="border text-center"><c:out
												value="${result.lottery.third_prize}"></c:out></td>
									</c:forEach>
								</tr>
								<tr>
									<th class="border col-1" scope="col">4</th>
									<c:forEach var="result" items="${lotteryShowMT}">
										<td class="border text-center"><c:out
												value="${result.lottery.fourth_prize}"></c:out></td>
									</c:forEach>
								</tr>
								<tr>
									<th class="border col-1" scope="col">5</th>
									<c:forEach var="result" items="${lotteryShowMT}">
										<td class="border text-center"><c:out
												value="${result.lottery.fifth_prize}"></c:out></td>
									</c:forEach>
								</tr>
								<tr>
									<th class="border col-1" scope="col">6</th>
									<c:forEach var="result" items="${lotteryShowMT}">
										<td class="border text-center"><c:out
												value="${result.lottery.sixth_prize}"></c:out></td>
									</c:forEach>
								</tr>
								<tr>
									<th class="border col-1" scope="col">7</th>
									<c:forEach var="result" items="${lotteryShowMT}">
										<td class="border text-center"><c:out
												value="${result.lottery.seventh_prize}"></c:out></td>
									</c:forEach>
								</tr>
								<tr>
									<th class="border col-1" scope="col">8</th>
									<c:forEach var="result" items="${lotteryShowMT}">
										<td class="border text-center"><c:out
												value="${result.lottery.eighth_prize}"></c:out></td>
									</c:forEach>
								</tr>
							</tbody>
						</table>
					</c:if>
					<c:if test="${lotteryShowMN.size()>0}">
						<c:set var="d" value="${d+1}"></c:set>
						<p>${d}. Miền Nam:</p>
						<table
							class="table table-striped  table-sm text-start border border-3 border-dark">
							<thead class="table-danger">
								<tr class="align-top border ">
									<th class="border " scope="col">Giải</th>
									<c:forEach var="result" items="${lotteryShowMN}">
										<th onmouseover="showButton(${result.lottery.lottery_id})"
											onmouseout="hiddenButton(${result.lottery.lottery_id})"
											class="border text-center item-lottery position-relative"
											scope="col">
											<p>${result.dayOfWeek} - Ngày: ${result.date} - ${result.region}</p>
											<p>${result.province}
												<c:set var="isChecked" value="false"></c:set>
												<c:forEach var="i" items="${deleteLotteriesList}">
													<c:if test="${i==result.lottery.lottery_id}">
														<c:set var="isChecked" value="true"></c:set>
													</c:if>
												</c:forEach>
												<c:if test="${isChecked==true}">
													<input class="form-check-input deleteList"
														id="delete${result.lottery.lottery_id}" type="checkbox"
														name="delete" checked
														onchange="deleteLottery(${result.lottery.lottery_id},${page})" />
												</c:if>
												<c:if test="${isChecked!=true}">
													<input class="form-check-input deleteList"
														id="delete${result.lottery.lottery_id}" type="checkbox"
														name="delete"
														onchange="deleteLottery(${result.lottery.lottery_id},${page})" />
												</c:if>
											</p>
											<div
												class="show-button d-none bg-white position-absolute badge top-50 start-0 h-50"
												id="no_${result.lottery.lottery_id}">
												<button type="submit" class="btn  btn-outline-none"
													title="Xóa"
													onclick="confirmDeleteLottery(${result.lottery.lottery_id},${page})">
													<i class="bi bi-trash"></i>
												</button>
												<button type="submit" class="btn btn-outline-none"
													title="Cập nhật"
													onclick='updateLottery(${result.lottery.lottery_id})'>
													<i class="bi bi-pencil-square"></i>
												</button>
											</div>
										</th>
									</c:forEach>
								</tr>
							</thead>
							<tbody class="table-group-divider">
								<c:set var="j" value="${page*3-3}"></c:set>
								<tr>
									<th class="border col-1" scope="col">ĐB</th>
									<c:forEach var="result" items="${lotteryShowMN}">
										<td class="border text-center"><c:out
												value="${result.lottery.special_prize}"></c:out></td>
									</c:forEach>
								</tr>
								<tr>
									<th class="border col-1" scope="col">1</th>
									<c:forEach var="result" items="${lotteryShowMN}">
										<td class="border text-center"><c:out
												value="${result.lottery.first_prize}"></c:out></td>
									</c:forEach>
								</tr>
								<tr>
									<th class="border col-1" scope="col">2</th>
									<c:forEach var="result" items="${lotteryShowMN}">
										<td class="border text-center"><c:out
												value="${result.lottery.second_prize}"></c:out></td>
									</c:forEach>
								</tr>
								<tr>
									<th class="border col-1" scope="col">3</th>
									<c:forEach var="result" items="${lotteryShowMN}">
										<td class="border text-center"><c:out
												value="${result.lottery.third_prize}"></c:out></td>
									</c:forEach>
								</tr>
								<tr>
									<th class="border col-1" scope="col">4</th>
									<c:forEach var="result" items="${lotteryShowMN}">
										<td class="border text-center"><c:out
												value="${result.lottery.fourth_prize}"></c:out></td>
									</c:forEach>
								</tr>
								<tr>
									<th class="border col-1" scope="col">5</th>
									<c:forEach var="result" items="${lotteryShowMN}">
										<td class="border text-center"><c:out
												value="${result.lottery.fifth_prize}"></c:out></td>
									</c:forEach>
								</tr>
								<tr>
									<th class="border col-1" scope="col">6</th>
									<c:forEach var="result" items="${lotteryShowMN}">
										<td class="border text-center"><c:out
												value="${result.lottery.sixth_prize}"></c:out></td>
									</c:forEach>
								</tr>
								<tr>
									<th class="border col-1" scope="col">7</th>
									<c:forEach var="result" items="${lotteryShowMN}">
										<td class="border text-center"><c:out
												value="${result.lottery.seventh_prize}"></c:out></td>
									</c:forEach>
								</tr>
								<tr>
									<th class="border col-1" scope="col">8</th>
									<c:forEach var="result" items="${lotteryShowMN}">
										<td class="border text-center"><c:out
												value="${result.lottery.eighth_prize}"></c:out></td>
									</c:forEach>
								</tr>
							</tbody>
						</table>
					</c:if>

				</c:if>
			</div>

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
	<div  style="height: 55px;"></div>
	<c:import url="/view/website/footer.jsp"></c:import>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
	<script>
	function changePage(pa){
		var  p = pa;
		var s = document.getElementById("searchLotteries").value;
		var r= document.getElementById("region").value;
		var c = document.getElementById("colOrder").value;
		$.ajax({
		  url: "/LotteryWeb/LotteriesList?action=changePage",
		  type: "post", //send it through get method
		  data: { 
		    page:p, 
		    search: s,
		    region:r,
		    colOrder:c
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
		var s = document.getElementById("searchLotteries").value;
		var r= document.getElementById("region").value;
		var c = document.getElementById("colOrder").value;
		var o = document.getElementById("order").value;

		$.ajax({
		  url: "/LotteryWeb/LotteriesList?action=loadLottery",
		  type: "post", //send it through get method
		  data: { 
		    page:p, 
		    search: s,
		    region:r,
		    colOrder:c,
		    order:o
		  },
		  success: function(response) {
			var row=document.getElementById("contextLottery")
			row.innerHTML=response;
		  },
		  error: function(xhr) {
		    //Do Something to handle error
		  }
		});
		
	}
	function deleteLottery(id,page){
		
		var deleteId=document.getElementById("delete"+id);
		var p=page;
		var i=id;
		
		$.ajax({
			  url: "/LotteryWeb/DeleteLotteriesController?action=addDelete",
			  type: "post", //send it through get method
			  data: { 
			    page:p, 
			    lotteryId:i,
			    isDelete:(deleteId.checked===true)?"add":"remove"
			  },
			  success: function(response) {
				  var row=document.getElementById("deleteButton")
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
			  url: "/LotteryWeb/DeleteLotteriesController?action=removeAllDeleteList",
			  type: "post", //send it through get method
			  data: { 
			    page:p
			  },
			  success: function(response) {
				  var row=document.getElementById("deleteButton")
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
	
	
	</script>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz"
		crossorigin="anonymous"></script>
</body>
</html>
