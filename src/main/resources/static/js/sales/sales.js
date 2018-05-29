var ct = 0;
var deleteItemId;

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
    articleNo.classList.add("sales-form-control");
    articleNo.placeholder = "Article No"
    articleNo.id = "article" + ct;
    document.getElementById((ct).toString()).appendChild(articleNo);

    //Creating a div
    var div1 = document.createElement('div');
    div1.id = ct + "-2";
    div1.classList.add("form-group");
    div1.classList.add("col-md-2");
    document.getElementById(rowDiv.id).appendChild(div1);
    //barcode
    var barcode = document.createElement('input');
    barcode.setAttribute("type", "text");
    barcode.classList.add("form-control");
    barcode.classList.add("sales-form-control");
    barcode.placeholder = "Barcode";
    barcode.id = "barcode" + ct;
    barcode.onkeyup = function () {
        getDetails(rowDiv.id)
    };
    document.getElementById((ct + "-2").toString()).appendChild(barcode);


    //Creating a div
    var div1 = document.createElement('div');
    div1.id = ct + "-3";
    div1.classList.add("form-group");
    div1.classList.add("col-md-2");
    document.getElementById(rowDiv.id).appendChild(div1);
    //description
    var description = document.createElement('input');
    description.setAttribute("type", "text");
    description.classList.add("form-control");
    description.classList.add("sales-form-control");
    description.placeholder = "Desc";
    description.id = "description" + ct;
    document.getElementById((ct + "-3").toString()).appendChild(description);

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
    unitPrice.classList.add("sales-form-control");
    unitPrice.placeholder = "Unit price";
    unitPrice.id = "unitPrice" + ct;
    unitPrice.onkeyup = function () {
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
    quantity.classList.add("sales-form-control");
    quantity.placeholder = "Quantity";
    quantity.id = "quantity" + ct;
    quantity.onkeyup = function () { calculatePrice(rowDiv.id) };
    document.getElementById((ct + "-7").toString()).appendChild(
        quantity);

    //Creating a div
    var div1 = document.createElement('div');
    div1.id = ct + "-8";
    div1.classList.add("form-group");
    div1.classList.add("col-md-2");
    document.getElementById(rowDiv.id).appendChild(div1);
    //total Price
    var totalPrice = document.createElement('input');
    totalPrice.setAttribute("type", "text");
    totalPrice.classList.add("form-control");
    totalPrice.classList.add("sales-form-control");
    totalPrice.placeholder = "Total Price";
    totalPrice.id = "totalPrice" + ct;
    document.getElementById((ct + "-8").toString()).appendChild(
        totalPrice);

    //Creating a div
    var div1 = document.createElement('div');
    div1.id = ct + "-9";
    div1.classList.add("form-group");
    div1.classList.add("col-md-1");
    document.getElementById(rowDiv.id).appendChild(div1);
    //discount percentage
    var discountPercentage = document.createElement('input');
    discountPercentage.setAttribute("type", "text");
    discountPercentage.disabled = true;
    discountPercentage.classList.add("form-control");
    discountPercentage.classList.add("sales-form-control");
    discountPercentage.placeholder = "Discount";
    discountPercentage.id = "discountPercentage" + ct;
    document.getElementById((ct + "-9").toString()).appendChild(
        discountPercentage);


    //Creating a div
    var div1 = document.createElement('div');
    div1.id = ct + "-10";
    div1.classList.add("form-group");
    div1.classList.add("col-md-1");
    document.getElementById(rowDiv.id).appendChild(div1);
    // total
    var total = document.createElement('input');
    total.setAttribute("type", "text");
    total.classList.add("form-control");
    total.classList.add("sales-form-control");
    total.placeholder = "Total";
    total.id = "total" + ct;
    document.getElementById((ct + "-10").toString()).appendChild(
        total);


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
    del.innerHTML = '<i class="fa fa-trash" aria-hidden="true"></i>'
    del.innerText = "Delete";
    del.id = "del" + ct;
    del.onclick = function () {
        onDelete(rowDiv.id)
    };
    document.getElementById((ct + "-11").toString()).appendChild(del);
}

function makeList() {
    var list = [];
    let c = ct;
    let count = 0;
    let price = [];
    let totalPrice = 0;

    for (var i = 1; i <= c; i++) {

        if (document.getElementById((i).toString()) != null) {

            var model = {
                articleNo: document.getElementById("article" + i).value,
                barcode: document.getElementById("barcode" + i).value,
                unitPrice: Number(document.getElementById("unitPrice"
                    + i).value),
                quantity: Number(document.getElementById("quantity"
                    + i).value),
                totalPrice: Number(document
                    .getElementById("totalPrice" + i).value),
                discountPercentage: Number(document
                    .getElementById("discountPercentage" + i).value),
                netPrice: Number(document
                    .getElementById("total" + i).value)
            }; homeNav
            totalPrice = totalPrice + Number(document.getElementById("total" + i).value);
            list[count] = model;
            count++;
        }
    }
    document.getElementById("netAmount").innerHTML = totalPrice;
}

function calculatePrice(id) {

    let index = id.indexOf("-");
    let num = id.substring(index + 1);


    console.log("working");
    let unitPrice = document.getElementById("unitPrice" + num).value;
    let quantity = document.getElementById("quantity" + num).value;
    let discount = document.getElementById("discountPercentage" + num).value;

    if (unitPrice != null) {

        unitPrice = Number(unitPrice);
    }
    if (quantity != null) {
        quantity = Number(quantity);
    }

    if ((unitPrice != null || unitPrice !== 0.00) && (quantity != null || quantity !== 0.00)) {
        let total = unitPrice * quantity
        let discountAmount = total * (discount/100); 
        let net = total - discountAmount;
        
        document.getElementById("totalPrice" + num).value = total;
        document.getElementById("total" + num).value = net;
    }

    makeList();
}

function onDelete(ct) {

    $("#deleteModal").modal();
    deleteItemId = ct;
}

function onConfirm() {
    var elem = document.getElementById(deleteItemId);
    elem.parentNode.removeChild(elem);
    makeList();
}


function getDetails(id) {

    let index = id.indexOf("-");
    let num = id.substring(index + 1);
    let barcode = document.getElementById("barcode" + num).value;
    let articleNo = barcode.substring(0, 7);

    if (barcode.length === 7) {

        var data = JSON.stringify(articleNo);
        var searchResult = $.ajax({
            type: 'POST',
            url: "http://localhost:4200/POS/getProductDetails",
            data: data,
            contentType: "application/json",
            success: function (resultData, textStatus, xhr) {
                console.log("Successfully Sent");
                //  window.location = xhr.getResponseHeader("Location");
                if (xhr.status === 200) {

                    document.getElementById('article' + num).value = resultData.articleNo;
                    document.getElementById('description' + num).value = resultData.description;
                    document.getElementById('unitPrice' + num).value = resultData.unitPrice;
                    document.getElementById('discountPercentage' + num).value = resultData.discount;
                }
            },
            error: function (resultData, textStatus, xhr) {

                if (textStatus === "error" || resultData === 400) {
                    $("#orderSaveUnsuccessModal").modal();
                }
            },

            failure: function (resultData) {
                console.log("Error Occured");
            }
        });
    }
}

function printInvoice() {
    var list = [];
    let c = ct;
    let count = 0;
    let price = [];
    let totalPrice = 0;


    if (document.getElementById("netAmount").innerHTML === "Total Amount" || document.getElementById("netAmount").innerHTML === "0") {

        $("#noItemModal").modal();
        
    } else {
        for (var i = 1; i <= c; i++) {

            if (document.getElementById((i).toString()) != null) {

                var model = {
                    articleNo: document.getElementById("article" + i).value,
                    barcode: document.getElementById("barcode" + i).value,
                    description: document.getElementById("description" + i).value,
                    unitPrice: Number(document.getElementById("unitPrice"
                        + i).value),
                    quantity: Number(document.getElementById("quantity"
                        + i).value),
                    totalAmount: Number(document
                        .getElementById("totalPrice" + i).value),
                    discount: Number(document
                        .getElementById("discountPercentage" + i).value),
                    total: Number(document
                        .getElementById("total" + i).value)
                };
                totalPrice = totalPrice + Number(document.getElementById("total" + i).value);
                list[count] = model;
                count++;
            }
        }


        var data = JSON.stringify(list);
        var searchResult = $.ajax({
            type: 'POST',
            url: "http://localhost:4200/POS/sales",
            data: data,
            contentType: "application/json",
            success: function (resultData, textStatus, xhr) {
                console.log("Successfully Sent");
                //  window.location = xhr.getResponseHeader("Location");
                if (xhr.status === 201) {
                    $("#modal").modal();
                    var link = xhr.getResponseHeader("Location");
                    var win = window.open(link, '_blank');

                    // $("#orderSaveUnsuccessModal").modal();
                    document.getElementById("modalParagraph").innerHTML = "Invoice generated";

                    win.focus();
                }
            },
            error: function (resultData, textStatus, xhr) {

                if (textStatus === "error" || resultData === 400) {
                    $("#orderSaveUnsuccessModal").modal();
                }
            },

            failure: function (resultData) {
                console.log("Error Occured");
            }
        });
        console.log(list);
    }
}


function calculateReturn() {

    var paidAmount = document.getElementById("paid").value;
    var totalAmount = Number(document.getElementById("netAmount").innerHTML);
    var returnAmount;

    returnAmount = paidAmount - totalAmount;
    document.getElementById("returnAmount").value = returnAmount;
}

window.onload = function () {

    document.getElementById("homeNav").classList.add('active');
}

function loadSalesPage() {
    location.reload();
}