
$("#saveConversionValueBtn").click(function (){


    var conversionDate = document.getElementById("conversionRateDate").value;
    var rate = document.getElementById("conversionRate").value;

    
    var saveRate = {
        date: conversionDate,
        value: rate
    };
   // console.log(saveRate);

   var data = JSON.stringify(saveRate);

   saveRate = JSON.stringify(saveRate);
  // console.log(data);

    var searchResult = $.ajax({
        type: 'POST',
        url:LOCATION_URL+"/conversionRate",
        data: saveRate,
        contentType: "application/json",
        success: function (resultData, textStatus, xhr) {
            console.log("Successfully Sent");
          //  window.location = xhr.getResponseHeader("Location");\
          if(xhr.status === 201){
            $("#conversionRateChangeSuccessModal").modal();
          }
          //window.location.replace(LOCATION_URL+"/home");

        },
        failure: function(resultData, textStatus, xhr){
            console.log("Error Occured");
        }
    });
});

function redirectToHome(){

    window.location.replace(LOCATION_URL+"/home");
}