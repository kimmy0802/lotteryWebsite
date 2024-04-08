"use strict";
// add/remove color for element when mouse moves it
function addColor(x){
			 x.classList.add("table-success");
		 }
function removeColor(x){
			 x.classList.remove("table-success");
		 }

// show/hidden buttons:  delete and edit
function showButton(id) {
	var s = "no_" + id;
	const showElement = document.getElementById(s);
	if (showElement.classList.contains("d-none")) {
		showElement.classList.remove("d-none");
	}
}

function hiddenButton(id) {
	var s = "no_" + id;
	const hiddenElement = document.getElementById(s);
	if (!hiddenElement.classList.contains("d-none")) {
		hiddenElement.classList.add("d-none");
	}
}

// update 
function updateProvince(provinceID){
	window.location.href = "Provinces?action=updateProvince&provinceID=" + provinceID;
}
// ---------------------DeleteAccountsController--------------------------
// alert can't delete 
function alertCantDelete() {
	alert("Vui lòng chọn Nhà đài để xóa");
}

// confirm delete a account 
function confirmDeleteProvince(provinceid,region,dateOfWeekd) {	
		var option = confirm("chọn 'ok' để xóa  Nhà đài ?");
		if (option === true) {
			window.location.href = "Provinces?action=deleteProvince&provinceID=" +provinceid+"&region="+region+"&dateOfWeek="+dateOfWeekd;
		
	}
}
// confirm delete  accounts
function confirmDeleteProvinces() {
	var option = confirm("chọn 'ok' để xóa  Nhà đài đã chọn?");
	if (option === true) {
		window.location.href = "Provinces?action=deleteProvinces";
	}
}

//show messenger when delete
function alertDeleteFail() {
	alert("Xóa Nhà đài không thành công!");
	//window.location.href = "AccountsList?action=accountsList";
}

function alertDeleteSuccess() {
	alert("Đã Nhà đài xóa thành công!");
	//window.location.href = "Provinces";
}


// alert result when update  province
function updateSuccess(provinceID) {
	alert("Cập nhật Nhà đài thành công!");
	//window.location.href = "UpdateAccount?action=updateAccount&provinceID=" + provinceID;;
}
function updateFail(provinceID) {
	alert("Cập nhật Nhà đài Thất bại!");
	//window.location.href = "UpdateAccount?action=updateAccount";
}

// alert result when update  province
function createSuccess(provinceID) {
	alert("Thêm Nhà đài mới thành công!");
	//window.location.href = "UpdateAccount?action=updateAccount&provinceID=" + provinceID;;
}
function createFail(provinceID) {
	alert("Thêm Nhà đài mới Thất bại!");
	//window.location.href = "UpdateAccount?action=updateAccount";
}
	