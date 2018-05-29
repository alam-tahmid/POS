function myCreateFunction() {
    var table = document.getElementById("myTable");
    var row = table.insertRow(3);
    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    cell1.innerHTML = '<input type="text">';
    cell2.innerHTML = "NEW CELL2";
}

function myDeleteFunction() {
    document.getElementById("myTable").deleteRow(0);
}