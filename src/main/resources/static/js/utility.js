function setDestinationCountry(selectedOption) {

	console.log(selectedOption.value);
	document.getElementById('destinationCountry').value = selectedOption[selectedOption.selectedIndex].text;
	getRate();

}

$("#packageQuantity").keyup(function() {
	var quantity = document.getElementById('packageQuantity').value;
	console.log(quantity);
	// alert(quantity);

});

$("#receivingCountryRate").keyup(function() {
	var country = document.getElementById('receivingCountryRate').value;
	document.getElementById('destinationCountry').value = country;

});

window.onload = function() {

	var date = GetFormattedDate();
	document.getElementById('date').value = date;
	var countryList = [];
	var alpha3Code = [];
	// code goes here
	$.ajax({
		url : "https://restcountries.eu/rest/v2/all",
		type : 'GET',
		success : function(result) {
			console.log(result);
			$.each(result, function(i, item) {
				// console.log(i+" "+item.name);
				// var element = document.getElementById('senderCountryList');
				// element.value = item.name;
				countryList.push(item.name);
				alpha3Code.push(item.alpha3Code);
			});
			console.log(countryList);
			// console.log(flagUrl);
			var senderCountryList = document
					.getElementById('senderCountryList');
			var receivingCountryList = document
					.getElementById('receivingCountryRate');

			for (var i = 0; i < countryList.length; i++) {

				var senderCountry = document.createElement('option');
				var receivingCountry = document.createElement('option');
				senderCountry.innerHTML = countryList[i];
				senderCountry.value = alpha3Code[i];
				receivingCountry.innerHTML = countryList[i];
				receivingCountry.value = alpha3Code[i];
				senderCountryList.appendChild(senderCountry);
				receivingCountryList.appendChild(receivingCountry);
			}

		}
	});

	$.ajax({
		//url : "http://localhost:8080/demo/getConversionRate",
		url: LOCATION_URL+"/getConversionRate",
		type : 'GET',
		success : function(result) {
			var convRate = JSON.parse(result)["value"];
			document.getElementById("convRate").value = convRate;
		}
	});
};

function GetFormattedDate() {
	var todayTime = new Date();
	var month = todayTime.getMonth() + 1;
	var day = todayTime.getDate();
	var year = todayTime.getFullYear();
	
	if(day <= 9){
		day = "0"+day;
	}
	
	if (month <= 9) {
		return year + "-0" + month + "-" + day;
	} else {
		return year + "-" + month + "-" + day;
	}
}

// $("#discPercent").keyup(function() {

// 	var value = document.getElementById("discPercent").value;
// 	var rate = document.getElementById("rate").value;
// 	if (value != null || value != 0) {

// 		var val = rate * (value / 100);
// 		document.getElementById("discount").value = val;
// 		val = rate - val;
// 		document.getElementById("totalAmount").value = val;
// 	}
// });

// $("#discount").keyup(function() {

// 	var value = document.getElementById("discount").value;
// 	var rate = document.getElementById("rate").value;
// 	if (value != null || value != 0) {

// 		var calculatedDiscPercent = (value * 100) / rate;
// 		document.getElementById("discPercent").value = calculatedDiscPercent;

// 		var val = rate - value;
// 		document.getElementById("totalAmount").value = val;
// 	}
// });
