/* globals Chart:false */
// viết cái hàm truyền tham số( sluongj người truy cập theo 1 tuần, 3 tháng) vào data
(() => {
	"use strict";

	// Graphs
	const ctx = document.getElementById("myChart");
	// eslint-disable-next-line no-unused-vars
	const myChart = new Chart(ctx, {
		type: "line",
		data: {
			labels: [
				"Thứ Hai",
				"Thứ Ba",
				"Thứ Tư",
				"Thứ Năm",
				"Thứ Sáu",
				"Thứ Bảy",
				"Chủ Nhật",
			],
			datasets: [
				{
					data: [153, 213, 184, 240, 234, 200, 120, 0],
					lineTension: 0,
					backgroundColor: "transparent",
					borderColor: "#007bff",
					borderWidth: 4,
					pointBackgroundColor: "#007bff",
				},
			],
		},
		options: {
			plugins: {
				legend: {
					display: false,
				},
				tooltip: {
					boxPadding: 3,
				},
			},
		},
	});
})();
