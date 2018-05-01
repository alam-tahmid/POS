var ct = 0;

		function addNewField() {

			ct++;

			//Creating a div
			var rowDiv = document.createElement('div');
			rowDiv.id = "row-" + ct;
			rowDiv.classList.add("form-row");
			document.getElementById('newlink').appendChild(rowDiv);

			//Creating a div
			var div1 = document.createElement('div');
			div1.id = ct;
			div1.classList.add("form-group");
			div1.classList.add("col-md-1");
			document.getElementById(rowDiv.id).appendChild(div1);

			// Creating a input box for article no
			var articleNo = document.createElement('input');
			articleNo.setAttribute("type", "text");
			articleNo.classList.add("form-control");
			articleNo.placeholder = "Article No"
			articleNo.id = "article" + ct;
			document.getElementById((ct).toString()).appendChild(articleNo);

			//Creating a div
			var div1 = document.createElement('div');
			div1.id = ct + "-2";
			div1.classList.add("form-group");
			div1.classList.add("col-md-1");
			document.getElementById(rowDiv.id).appendChild(div1);
			//barcode
			var barcode = document.createElement('input');
			barcode.setAttribute("type", "text");
			barcode.classList.add("form-control");
			barcode.placeholder = "Barcode";
			barcode.id = "barcode" + ct;
			document.getElementById((ct + "-2").toString())
					.appendChild(barcode);

			//Creating a div
			var div1 = document.createElement('div');
			div1.id = ct + "-3";
			div1.classList.add("form-group");
			div1.classList.add("col-md-1");
			document.getElementById(rowDiv.id).appendChild(div1);
			//description
			var description = document.createElement('input');
			description.setAttribute("type", "text");
			description.classList.add("form-control");
			description.placeholder = "Description";
			description.id = "description" + ct;
			document.getElementById((ct + "-3").toString()).appendChild(
					description);

			//Creating a div
			var div1 = document.createElement('div');
			div1.id = ct + "-4";
			div1.classList.add("form-group");
			div1.classList.add("col-md-1");
			document.getElementById(rowDiv.id).appendChild(div1);
			//size
			var size = document.createElement('input');
			size.setAttribute("type", "text");
			size.classList.add("form-control");
			size.placeholder = "Size";
			size.id = "size" + ct;
			document.getElementById((ct + "-4").toString()).appendChild(size);

			//Creating a div
			var div1 = document.createElement('div');
			div1.id = ct + "-5";
			div1.classList.add("form-group");
			div1.classList.add("col-md-1");
			document.getElementById(rowDiv.id).appendChild(div1);
			//color
			var color = document.createElement('input');
			color.setAttribute("type", "text");
			color.classList.add("form-control");
			color.placeholder = "Color";
			color.id = "color" + ct;
			document.getElementById((ct + "-5").toString()).appendChild(color);

			//Creating a div
			var div1 = document.createElement('div');
			div1.id = ct + "-6";
			div1.classList.add("form-group");
			div1.classList.add("col-md-1");
			document.getElementById(rowDiv.id).appendChild(div1);
			//unit price
			var unitPrice = document.createElement('input');
			unitPrice.setAttribute("type", "text");
			unitPrice.classList.add("form-control");
			unitPrice.placeholder = "Unit price";
			unitPrice.id = "unitPrice" + ct;
			unitPrice.onkeyup = function(){
				calculatePrice(rowDiv.id)
				};
			document.getElementById((ct + "-6").toString()).appendChild(
					unitPrice);

			//Creating a div
			var div1 = document.createElement('div');
			div1.id = ct + "-7";
			div1.classList.add("form-group");
			div1.classList.add("col-md-1");
			document.getElementById(rowDiv.id).appendChild(div1);
			//quantity
			var quantity = document.createElement('input');
			quantity.setAttribute("type", "text");
			quantity.classList.add("form-control");
			quantity.placeholder = "Quantity";
			quantity.id = "quantity" + ct;
			quantity.onkeyup = function(){calculatePrice(rowDiv.id)};
			document.getElementById((ct + "-7").toString()).appendChild(
					quantity);

			//Creating a div
			var div1 = document.createElement('div');
			div1.id = ct + "-8";
			div1.classList.add("form-group");
			div1.classList.add("col-md-1");
			document.getElementById(rowDiv.id).appendChild(div1);
			//total Price
			var totalPrice = document.createElement('input');
			totalPrice.setAttribute("type", "text");
			totalPrice.classList.add("form-control");
			totalPrice.placeholder = "Total Price";
			totalPrice.id = "totalPrice" + ct;
			document.getElementById((ct + "-8").toString()).appendChild(
					totalPrice);

			//Creating a div
			var div1 = document.createElement('div');
			div1.id = ct + "-9";
			div1.classList.add("form-group");
			div1.classList.add("col-md-2");
			document.getElementById(rowDiv.id).appendChild(div1);
			//discount percentage
			var discountPercentage = document.createElement('input');
			discountPercentage.setAttribute("type", "text");
			discountPercentage.classList.add("form-control");
			discountPercentage.placeholder = "Discount Percentage";
			discountPercentage.id = "discountPercentage" + ct;
			document.getElementById((ct + "-9").toString()).appendChild(
					discountPercentage);

			//Creating a div
			var div1 = document.createElement('div');
			div1.id = ct + "-10";
			div1.classList.add("form-group");
			div1.classList.add("col-md-1");
			document.getElementById(rowDiv.id).appendChild(div1);
			//offer
			var offer = document.createElement('input');
			offer.setAttribute("type", "text");
			offer.classList.add("form-control");
			offer.placeholder = "Offer";
			offer.id = "offer" + ct;
			document.getElementById((ct + "-10").toString()).appendChild(offer);

			//Creating a div
			var div1 = document.createElement('div');
			div1.id = ct + "-11";
			div1.classList.add("form-group");
			div1.classList.add("col-md-1");
			document.getElementById(rowDiv.id).appendChild(div1);
			//delete line
			var del = document.createElement('button');
			del.classList.add("btn");
			del.classList.add("btn-default");
			del.type = "button";
			del.innerText = "Delete";
			del.id = "del" + ct;
			del.onclick = function() {
				onDelete(rowDiv.id)
			};
			document.getElementById((ct + "-11").toString()).appendChild(del);

		}

		function makeList() {
			var list = [];
			let c = ct;
			let count = 0;

			for (var i = 1; i <= c; i++) {

				if (document.getElementById((i).toString()) != null) {

					var model = {
						articleNo : document.getElementById("article" + i).value,
						barcode : document.getElementById("barcode" + i).value,
						description : document
								.getElementById("description" + i).value,
						size : document.getElementById("size" + i).value,
						color : document.getElementById("color" + i).value,
						unitPrice : Number(document.getElementById("unitPrice"
								+ i).value),
						quantity : Number(document.getElementById("quantity"
								+ i).value),
						totalPrice : Number(document
								.getElementById("totalPrice" + i).value),
						discountPercentage : Number(document
								.getElementById("discountPercentage" + i).value),
						offer : document.getElementById("offer" + i).value
					};
					list[count] = model;
					count++;
				}
			}

			var data = JSON.stringify(list);
			var searchResult = $.ajax({
				type : 'POST',
				url : "http://localhost:4200/POS/product",
				data : data,
				contentType : "application/json",
				success : function(resultData, textStatus, xhr) {
					console.log("Successfully Sent");
					//  window.location = xhr.getResponseHeader("Location");
					if (xhr.status === 201) {

						$("#modal").modal();
						document.getElementById("modalParagraph").innerHTML = resultData;
					}
				},
				error : function(resultData, textStatus, xhr) {

					if ( resultData.status === 400) {
						$("#modal").modal();
						document.getElementById("modalParagraph").innerHTML = resultData.responseText;
					}
				},

				failure : function(resultData) {
					console.log("Error Occured");
				}
			});
			console.log(list);
		}
		
		function calculatePrice(id){
			
			let index = id.indexOf("-");
			let num = id.substring(index+1);
			
			
			
			console.log("working");
			let unitPrice = document.getElementById("unitPrice"+num).value;
			let quantity = document.getElementById("quantity"+num).value;
			
			if(unitPrice != null){
				
				unitPrice = Number(unitPrice);
			}
			if(quantity != null){
				quantity = Number(quantity);
			}
			
			if((unitPrice != null || unitPrice !==0.00) && (quantity != null || quantity !==0.00)){
				let total = unitPrice * quantity
				
				document.getElementById("totalPrice"+num).value = total;
			}
		}

		function onDelete(ct) {
			var elem = document.getElementById(ct);
			elem.parentNode.removeChild(elem);
		}

		function loadAddProductPage(){
			location.reload();
		}