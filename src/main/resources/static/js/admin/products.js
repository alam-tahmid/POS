$(document).ready(function () {
    $('#productTable').DataTable({
        "order":[[1,"desc"]] 
    });
});

function getProductDetails(e) {

    var id;
    var link;
    var win;
    if (!e) {
        var e = window.event;     // Get the window event
    }

    e.cancelBubble = true; // IE Stop propagation

    if (e.stopPropagation) {
        e.stopPropagation();  // Other Broswers
    }
    //console.log('td clicked');
    id = e.currentTarget.id;
    link = LOCATION_URL + "/getProduct/" + id;
    win = window.open(link, '_blank');
    win.focus();
}; 