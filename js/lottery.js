"use strict";

//------------------lottery_list.jsp	: ADMIN-----------------------
//update lottery
function updateLottery(lotteryID){
	window.location.href="UpdateLotteries?action=updateLotteryGet&lotteryID="+lotteryID;
}
// confirm delete a Lottery
function confirmDeleteLottery(id,page){
	var option=confirm("chọn 'ok' để xóa mục đã chọn?");
	if(option===true){
		window.location.href="DeleteLotteries?action=deleteLottery&lotteryId="+id+"&page="+page;
	}
}
// confirm delete  Lotteries
function confirmDeleteLotteries(){
	var option=confirm("chọn 'ok' để các xóa mục đã chọn?");
	if(option===true){
		window.location.href="DeleteLotteries?action=deleteLotteries";
	}
}
// alert can't delete
function alertCantDelete(){
	 alert("Vui lòng chọn mục để xóa");
 }
//alert result  when delete lottery
function alertDeleteFail(){	
	alert("Xóa không thành công!");
	//window.location.href="LotteriesList?action=lotteriesList";
}
function alertDeleteSuccess(page){	
	alert("Đã xóa thành công!");
	window.location.href="LotteriesList?action=lotteriesList&page="+page;
}
// show/hidden buttons:  delete and edit
function showButton(id){
	var s="no_"+id;
	const showElement=document.getElementById(s);
	if(showElement.classList.contains("d-none")){
		showElement.classList.remove("d-none");
	}	
}
function hiddenButton(id){
	var s="no_"+id;
	const hiddenElement=document.getElementById(s);
	if(!hiddenElement.classList.contains("d-none")){
		hiddenElement.classList.add("d-none");
	}
}

///----------------------------admin_lotteries_update.jsp:   ADMIN------------------
// is a number?
function isTrueNumber(n, x) {
	if (x === null) return false;
	var y = n.match(/[0-9]/gi);
	if (y === null) return false;

	if (y.length !== n.length || n.length !== x) return false;

	return true;
}
// check form input of lottery number 
function checkInput(prize) {
	var lotteryNumber = document.getElementById(prize);
	var lotteryNumberMess = document.getElementById(prize + "Mess");
	var inputRegion = document.getElementById("selectRegion");
	var resultCheckInput = document.getElementById("resultCheckInput");
	var x = 6;
	var number = lotteryNumber.value;
	number = number.replaceAll("-", " ");
	number = number.replaceAll(" ", "");
	if (resultCheckInput.classList.contains("inputTrue"))
		resultCheckInput.classList.remove("inputTrue");
	if (number.length == 0) {
		lotteryNumberMess.innerHTML = "Vui lòng không để trống.";
		resultCheckInput.innerHTML = "false";
		return;
	} else {
		lotteryNumberMess.innerHTML = "";
		if (!isNaN(number)) {
			lotteryNumberMess.innerHTML = "";
		} else {
			lotteryNumberMess.innerHTML = "các số trúng thưởng chỉ gồm các chữ số và cách nhau bởi dấu cách";
			resultCheckInput.innerHTML = "false";
			return;
		}
	}

	if (inputRegion.value === "Miền Bắc") {
		if (prize === "special_prize" || prize === "first_prize" || prize === "third_prize" || prize === "second_prize") x = 5;
		else if (prize === "fourth_prize" || prize === "fifth_prize") x = 4;
		else if (prize === "sixth_prize") x = 3; else x = 2;
	} else {
		if (prize === "special_prize") x = 6;
		else if (prize === "first_prize" || prize === "third_prize" || prize === "second_prize" || prize === "fourth_prize") x = 5;
		else if (prize === "sixth_prize" || prize === "fifth_prize") x = 4;
		else if (prize === "seventh_prize") x = 3; else x = 2;

	}
	var numberOfPrize = 1;
	if (inputRegion.value === "Miền Bắc") {
		if (prize === "special_prize" || prize === "first_prize") numberOfPrize = 1;
		else if (prize === "second_prize") numberOfPrize = 2;
		else if (prize === "third_prize" || prize === "fifth_prize") numberOfPrize = 6;
		else if (prize === "fourth_prize" || prize === "seventh_prize") numberOfPrize = 4;
		else numberOfPrize = 3;
	} else {
		if (prize === "special_prize" || prize === "first_prize" || prize === "second_prize" || prize === "fifth_prize" || prize === "seventh_prize" || prize === "eighth_prize") numberOfPrize = 1;
		else if (prize === "third_prize") numberOfPrize = 2;
		else if (prize === "fourth_prize") numberOfPrize = 7;
		else numberOfPrize = 3;
	}

	var namePrize = "Giải đặc biệt";
	if (prize === "first_prize") namePrize = "Giải Nhất"; else
		if (prize === "second_prize") namePrize = "Giải Nhì"; else
			if (prize === "third_prize") namePrize = "Giải Ba"; else
				if (prize === "fourth_prize") namePrize = "Giải Tư"; else
					if (prize === "fifth_prize") namePrize = "Giải Năm"; else
						if (prize === "sixth_prize") namePrize = "Giải Sáu"; else
							if (prize === "seventh_prize") namePrize = "Giải Bảy"; else
								if (prize === "eighth_prize") namePrize = "Giải Tám";
	number = lotteryNumber.value;
	number = number.replaceAll("  ", " ");
	number = number.trim();
	number = number;
	var a = number.split(" ");
	for (let i = 0; i < a.length; i++) {
		if (a[i] !== null && !isTrueNumber(a[i], x)) {
			lotteryNumber.style.borderColor = "red";
			lotteryNumberMess.innerHTML = "Số vừa nhập không hợp lệ; value: " + a[i] + ". " + namePrize + " gồm " + x + " chữ số";
			resultCheckInput.innerHTML = "false";
			return;
		}
	}
	if (a.length != numberOfPrize) {
		lotteryNumber.style.borderColor = "red";
		lotteryNumberMess.innerHTML = namePrize + " chỉ gồm " + numberOfPrize + " giải.";
		resultCheckInput.innerHTML = "false";
		return;
	}

	lotteryNumber.style.borderColor = "#dee2e6";
	lotteryNumberMess.innerHTML = "";
	resultCheckInput.innerHTML = "true";
	resultCheckInput.classList.add("inputTrue");
}

// ----------------------searchLottery.jsp   : WEBSITE -----------------------
function checkLotterySearch(x) {			
			var numberSearch = document.getElementById("numberSearch").value;
			var numberSearchMess=document.getElementById("numberSearchMess");
			if(numberSearch===null){
				x.style.borderBlockColor="red";
				numberSearchMess.innerHTML="Số cần dò không đước để trống";
				return;
			}
			var regex = /^\d*$/;
			if(!regex.test(numberSearch)){
				x.style.borderBlockColor="red";
				numberSearchMess.innerHTML="Số cần dò chỉ gồm ký tự số.";
				return;
			}
			regex = /^\d{6}$/;
			if(!regex.test(numberSearch)){
				x.style.borderBlockColor="red";
				numberSearchMess.innerHTML="Số cần dò gồm 6 ký tự.";
				return;
			}
			
			x.style.borderBlockColor="green";
			numberSearchMess.innerHTML="";		
}


