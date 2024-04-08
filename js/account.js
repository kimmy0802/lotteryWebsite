"use strict";
// add/remove color for element
function addColor(x) {
	x.classList.add("table-success");
}
function removeColor(x) {
	x.classList.remove("table-success");
}

// show buttons:  delete and edit
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


// --------------------------input-----------------------------------
// show - hidden password
function showPassword(x) {
	var pass = x.previousElementSibling;
	if (x.classList.contains("hiddenPassword")) {
		pass.setAttribute("type", "text");
		x.innerHTML = '<i class="bi bi-eye"></i>';
		x.classList.remove("hiddenPassword");
	} else {
		pass.setAttribute("type", "password");
		x.innerHTML = '<i class="bi bi-eye-slash"></i>';
		x.classList.add("hiddenPassword");
	}
}

// check email 
function checkEmail(x) {
	var regex = /^\w+(?:\.[\w-]+)*@[a-zA-Z0-9]+(?:\.[a-zA-Z]{2,6})*\.[a-zA-Z]{2,6}$/;
	var y = x.value;
	y = y.trim();
	if (!regex.test(y)) {
		x.style.borderBlockColor = "red";
		x.nextElementSibling.textContent = "Email không hợp lệ";
	} else {
		x.style.borderBlockColor = "rgb(133, 133, 133)";
		x.nextElementSibling.textContent = "";
	}
}
// check phonenumber
function checkPhonenumber(x) {
	var regex = /^[0]\d{9}$/;
	var y = x.value;
	y = y.trim();
	if (!regex.test(y)) {
		x.style.borderBlockColor = "red";
		x.nextElementSibling.textContent = "Số điện thoại  không hợp lệ";
	} else {
		x.style.borderBlockColor = "rgb(133, 133, 133)";
		x.nextElementSibling.textContent = "";
	}
}
// checkFullname(this)
function checkFullname(x) {
	var y = x.value;
	y = y.trim();
	if (y.length === 0) {
		x.style.borderBlockColor = "red";
		x.nextElementSibling.textContent = "Họ và tên không để trống.";
	} else {
		x.style.borderBlockColor = "rgb(133, 133, 133)";
		x.nextElementSibling.textContent = "";
	}
}

// check username
function checkUsername(x) {
	var regex = /^\w{6,30}$/;
	var y = x.value;
	y = y.trim();
	if (!regex.test(y)) {
		x.style.borderBlockColor = "red";
		x.nextElementSibling.textContent = "Tên đăng nhập gồm 6-30 ký tự chữ hoặc số.";
	} else {
		x.style.borderBlockColor = "rgb(133, 133, 133)";
		x.nextElementSibling.textContent = "";
	}
}

//------------------admin_accounts_update.jsp--------------------------------------
// alert result update  account from admin
function updateFromAdminSuccess(username) {
	alert("Cập nhật Tài Khoản thành công!");
	window.location.href = "UpdateAccounts?action=updateFromAdmin&username=" + username;
}
function updateFromAdminFail(username) {
	alert("Cập nhật Tài khoản Thất bại!");
	window.location.href = "UpdateAccounts?action=updateFromAdmin&username=" + username;
}
// update account from admin
function updateAccount(usernameUpdate, part, accountPart) {
	if (part === "boss") {
		alert("Không thể cập nhật tài khoản này.");
		return;
	}

	if (accountPart === "boss") {
		window.location.href = "UpdateAccounts?action=updateFromAdmin&username=" + usernameUpdate;
	} else {
		if (part === "admin") {
			alert("Không thể cập nhật tài khoản admin "+usernameUpdate+" này.");
			return;
		} else {
			window.location.href = "UpdateAccounts?action=updateFromAdmin&username=" + usernameUpdate;
		}
	}
}
// ---------------------DeleteAccountsController--------------------------
// alert can't delete 
function alertCantDelete() {
	alert("Vui lòng chọn mục để xóa");
}

// confirm delete a account 
function confirmDeleteAccount(accountPart,username, page, part) {
	if(part==="boss"){
		alert("Không thể xóa tài khoản này!");
		return;
	}
	if(accountPart==="boss"){
		var option = confirm("chọn 'ok' để xóa tài khoản có tên đăng nhập là "+username);
		if (option === true) {
			window.location.href = "DeleteAccounts?action=deleteAccount&usernameDelete=" + username + "&page=" + page + "&part=" + part;
		}
		return;
	}
	if (part === "admin") {
		alert("Không thể xóa Admin!")
	} else {
		var option = confirm("chọn 'ok' để xóa tài khoản có tên đăng nhập là "+username);
		if (option === true) {
			window.location.href = "DeleteAccounts?action=deleteAccount&usernameDelete=" + username + "&page=" + page + "&part=" + part;
		}
	}
}
// confirm delete  accounts
function confirmDeleteAccounts() {
	var option = confirm("chọn 'ok' để các xóa các mục đã chọn?");
	if (option === true) {
		window.location.href = "DeleteAccounts?action=deleteAccounts";
	}
}

//show messenger when delete
function alertDeleteFail() {
	alert("Xóa không thành công!");
	window.location.href = "AccountsList?action=accountsList";
}

function alertDeleteSuccess() {
	alert("Đã xóa thành công!");
	window.location.href = "AccountsList?action=accountsList";
}


//------------------------ResetPasswordFromAdminController---------------------------
// change password
function changePassword(username, part, adminPart) {
	if(part==="boss"){
		alert("Không thể Thay đổi Mật khẩu tài khoản này!")
		return;		
	}
	if (adminPart === "boss") {
		var option = confirm("Thay đổi Mật khẩu tài khoản có tên đăng nhập là: " + username + "?");
		if (option === true) {
			window.location.href = "ResetPasswordFromAdminController?action=resetPassword&username=" + username;
		}
	} else {
		if (part === "admin") {
			alert("Không thể Thay đổi Mật khẩu tài khoản này!")
		} else {
			var option = confirm("Thay đổi Mật khẩu tài khoản có tên đăng nhập là: " + username + "?");
			if (option === true) {
				window.location.href = "ResetPasswordFromAdminController?action=resetPassword&username=" + username;
			}
		}
	}

}
// alert result when reset password from adminin
function resetPasswordFromAdminFail() {
	alert("Thay đổi mật khẩu THẤT BẠI!");
	//window.location.href = "AccountsList";
}
function resetPasswordFromAdminSuccess() {
	alert("Thay đổi mật khẩu thành công!");
	//window.location.href = "AccountsList";
}

// ---------- ----------------------- updateAccount.jsp     USER  --------------------
// alert result when update  account
function updateAccountSuccess() {
	alert("Cập nhật Tài Khoản thành công!");
	window.location.href = "UpdateAccount?action=updateAccount";
}
function updateAccountFail() {
	alert("Cập nhật Tài khoản Thất bại!");
	window.location.href = "UpdateAccount?action=updateAccount";
}
// check input
function checkInput(id) {
	var x = document.getElementById(id);
	var y = document.getElementById(id + "Mess");
	if (id === "fullname") {
		if (x.value.length === 0) {
			x.style.borderBlockColor = "red";
			y.innerText = "Họ và tên không được để trống!"
		} else {
			x.style.borderBlockColor = "green";
			y.innerText = "";
		}
	}
	if (id === "phonenumber") {
		var t = /^[0]\d{9}$/;
		if (t.test(x.value)) {
			x.style.borderBlockColor = "green";
			y.innerText = "";
		} else {
			x.style.borderBlockColor = "red";
			y.innerText = "Số điện thoại không hợp lệ!"
		}
	}

}

//--------------------------- changePassword.jsp  USER-------------------------------
// alert result when user changes password
function changePasswordFail() {
	alert("Thay đổi Mật khẩu Thất bại!");
	//window.location.href="UpdateAccount?action=changePassword";
}
function changePasswordSuccess() {
	alert("Thay đổi Mật khẩu thành công!");
	//window.location.href="UpdateAccount?action=changePassword";
}

// -----------------------forgetPassword.jsp WEBSITE----------------------------
// aler result when user forget Password
function forgetPasswordFail() {
	alert("Cấp mật khẩu mới thất bại!");
	//window.location.href = "Login";
}
function forgetPasswordSuccess() {
	alert("Cấp mật khẩu mới thành công! Mật khẩu mới đã được gửi đến Email của bạn.");
	window.location.href = "Login";
}

// ------------------------register.jsp WEBSITE ----------------------------
function alertCreateFail() {
	alert("Tạo tài khoản không thành công!");
	//window.location.href="Register";
}

function alertCreateSuccess() {
	alert("Tạo Tài khoản mới thành công! Mật khẩu đã được gửi đến email của bạn, vui lòng đăng nhập để sử dụng dịch vụ");
	window.location.href = "Login";
}

// resetAccountForUser.jsp
function resetAccountSuccess() {
	alert("Khôi phục Tài Khoản thành công! Mật khẩu mới được gửi đến email của tài khoản");
}
function resetAccountFail() {
	alert("Khôi phục Tài khoản Thất bại!");
}