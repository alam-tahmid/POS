
$("#updatePasswordBtn").click(function () {


	var oldPassword = document.getElementById("oldPassword").value;
	var newPassword = document.getElementById("newPassword").value;


	var password = {
		oldPassword: oldPassword,
		newPassword: newPassword
	};
	console.log(password);

	var data = JSON.stringify(password);

	var searchResult = $.ajax({
		type: 'POST',
		url: LOCATION_URL + "/updatePassword",
		data: data,
		contentType: "application/json",
		success: function (resultData) {
			console.log("Successfully Sent");
			//  window.location = xhr.getResponseHeader("Location");\

			setTimeout(function () {

				document.getElementById("success").style.display = 'block';
			}, 3000);
			window.location.replace(LOCATION_URL + "/home");

		},
		failure: function (resultData) {
			console.log("Error Occured");
		}
	});
});