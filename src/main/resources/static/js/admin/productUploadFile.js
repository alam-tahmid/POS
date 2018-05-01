// bind the on-change event for the input element (triggered when a file
// is chosen)
$(document).ready(function () {
	$("#upload-file-input").on("change", uploadFile);
});

/**
 * Upload the file sending it via Ajax at the Spring Boot server.
 */
function uploadFile() {
	$
		.ajax({
			url: LOCATION_URL + "/fileUpload",
			type: "POST",
			data: new FormData($("#upload-file-form")[0]),
			enctype: 'multipart/form-data',
			processData: false,
			contentType: false,
			cache: false,
			success: function (resultData, textStatus, xhr) {
				console.log("Successfully Sent");
				//  window.location = xhr.getResponseHeader("Location");
				if (xhr.status === 201) {

					$("#modal").modal();
					document.getElementById("modalParagraph").innerHTML = "Order Uploaded Successfully";
				}
			},
			error: function (resultData, textStatus, xhr) {

				if (resultData.status === 400) {
					$("#modal").modal();
					document.getElementById("modalParagraph").innerHTML = resultData.responseText;
				}
			},

			failure: function (resultData) {
				console.log("Error Occured");
			}
		});
} // function uploadFile

function loadFileUploadPage(){
	location.reload();
}