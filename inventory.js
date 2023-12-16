google.charts.load('current', {packages: ['corechart', 'bar']});

google.charts.setOnLoadCallback(callAvailableProductsAPI);

function callAvailableProductsAPI() {
    $.ajax({
        url: "AvailableProductsAPI",
        type: "POST",
        data: "{}",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (msg) {
            formatJsonAccordingToGoogleChart(msg)
        },
        error: function(){
            console.log("error occurred while making ajax call;")
        }
    });
}


//This method will parse json data and build datatable required by google api to plot the bar chart.
function formatJsonAccordingToGoogleChart(msg)
{
    var newMsg = JSON.stringify(msg);
    //alert(newMsg);
    //[{"productName":"Basic Plan","productPrice":"22.99","numberOfAvailableProducts":"9","productDiscount":"22.99","manufacturerRebate":"0"},{"productName":"LG FitnessWatch","productPrice":"399.99","numberOfAvailableProducts":"10","productDiscount":"399.99","manufacturerRebate":"0"},{"productName":"LG Headphone","productPrice":"20.99","numberOfAvailableProducts":"10","productDiscount":"20.99","manufacturerRebate":"0"},{"productName":"LG Laptop","productPrice":"689.99","numberOfAvailableProducts":"10","productDiscount":"689.99","manufacturerRebate":"0"},{"productName":"LG Phone","productPrice":"389.99","numberOfAvailableProducts":"10","productDiscount":"389.99","manufacturerRebate":"100"},{"productName":"LG SmartWatch","productPrice":"289.99","numberOfAvailableProducts":"10","productDiscount":"289.99","manufacturerRebate":"0"},{"productName":"LG SoundSystem","productPrice":"72.99","numberOfAvailableProducts":"10","productDiscount":"72.99","manufacturerRebate":"0"},{"productName":"LG Television","productPrice":"87.99","numberOfAvailableProducts":"10","productDiscount":"87.99","manufacturerRebate":"0"},{"productName":"LG VoiceAssistant","productPrice":"199.99","numberOfAvailableProducts":"10","productDiscount":"199.99","manufacturerRebate":"0"},{"productName":"Microsoft FitnessWatch","productPrice":"40.99","numberOfAvailableProducts":"10","productDiscount":"40.99","manufacturerRebate":"0"},{"productName":"Microsoft Headphone","productPrice":"149.99","numberOfAvailableProducts":"10","productDiscount":"149.99","manufacturerRebate":"0"},{"productName":"Microsoft Laptop","productPrice":"849.99","numberOfAvailableProducts":"10","productDiscount":"849.99","manufacturerRebate":"51"},{"productName":"Microsoft Phone","productPrice":"589.99","numberOfAvailableProducts":"10","productDiscount":"589.99","manufacturerRebate":"0"},{"productName":"Microsoft SmartWatch","productPrice":"389.99","numberOfAvailableProducts":"10","productDiscount":"389.99","manufacturerRebate":"0"},{"productName":"Microsoft SoundSystem","productPrice":"50.99","numberOfAvailableProducts":"10","productDiscount":"50.99","manufacturerRebate":"0"},{"productName":"Microsoft Television","productPrice":"150.49","numberOfAvailableProducts":"9","productDiscount":"150.49","manufacturerRebate":"10"},{"productName":"Microsoft VoiceAssistant","productPrice":"150.99","numberOfAvailableProducts":"10","productDiscount":"150.99","manufacturerRebate":"0"},{"productName":"Onida FitnessWatch","productPrice":"289.99","numberOfAvailableProducts":"10","productDiscount":"289.99","manufacturerRebate":"13"},{"productName":"Onida Headphone","productPrice":"89.99","numberOfAvailableProducts":"10","productDiscount":"89.99","manufacturerRebate":"0"},{"productName":"Onida Laptop","productPrice":"489.99","numberOfAvailableProducts":"10","productDiscount":"489.99","manufacturerRebate":"0"},{"productName":"Onida Phone","productPrice":"300.99","numberOfAvailableProducts":"10","productDiscount":"300.99","manufacturerRebate":"11"},{"productName":"Onida SmartWatch","productPrice":"149.99","numberOfAvailableProducts":"10","productDiscount":"149.99","manufacturerRebate":"0"},{"productName":"Onida SoundSystem","productPrice":"59.99","numberOfAvailableProducts":"10","productDiscount":"59.99","manufacturerRebate":"10"},{"productName":"Onida Television","productPrice":"75.99","numberOfAvailableProducts":"10","productDiscount":"75.99","manufacturerRebate":"0"},{"productName":"Onida VoiceAssistant","productPrice":"149.99","numberOfAvailableProducts":"10","productDiscount":"149.99","manufacturerRebate":"0"},{"productName":"Premium Plan","productPrice":"32.99","numberOfAvailableProducts":"10","productDiscount":"32.99","manufacturerRebate":"0"},{"productName":"Samsung FitnessWatch","productPrice":"499.99","numberOfAvailableProducts":"10","productDiscount":"499.99","manufacturerRebate":"0"},{"productName":"Samsung Headphone ","productPrice":"89.99","numberOfAvailableProducts":"10","productDiscount":"89.99","manufacturerRebate":"12"},{"productName":"Samsung Laptop","productPrice":"520.99","numberOfAvailableProducts":"10","productDiscount":"520.99","manufacturerRebate":"0"},{"productName":"Samsung Phone","productPrice":"489.99","numberOfAvailableProducts":"10","productDiscount":"489.99","manufacturerRebate":"0"},{"productName":"Samsung SmartWatch","productPrice":"280.99","numberOfAvailableProducts":"10","productDiscount":"280.99","manufacturerRebate":"10"},{"productName":"Samsung SoundSystem","productPrice":"200.99","numberOfAvailableProducts":"10","productDiscount":"200.99","manufacturerRebate":"0"},{"productName":"Samsung Television","productPrice":"99.99","numberOfAvailableProducts":"10","productDiscount":"99.99","manufacturerRebate":"5"},{"productName":"Samsung VoiceAssistant","productPrice":"50.99","numberOfAvailableProducts":"10","productDiscount":"50.99","manufacturerRebate":"1"},{"productName":"Sony FitnessWatch","productPrice":"289.99","numberOfAvailableProducts":"10","productDiscount":"289.99","manufacturerRebate":"11"},{"productName":"Sony Headphone","productPrice":"189.99","numberOfAvailableProducts":"10","productDiscount":"189.99","manufacturerRebate":"0"},{"productName":"Sony Laptop","productPrice":"549.99","numberOfAvailableProducts":"10","productDiscount":"549.99","manufacturerRebate":"0"},{"productName":"Sony Phone","productPrice":"189.99","numberOfAvailableProducts":"10","productDiscount":"189.99","manufacturerRebate":"13"},{"productName":"Sony SmartWatch","productPrice":"389.99","numberOfAvailableProducts":"10","productDiscount":"389.99","manufacturerRebate":"13"},{"productName":"Sony SoundSystem","productPrice":"112.99","numberOfAvailableProducts":"10","productDiscount":"112.99","manufacturerRebate":"0"},{"productName":"Sony Television","productPrice":"119.99","numberOfAvailableProducts":"10","productDiscount":"119.99","manufacturerRebate":"0"},{"productName":"Sony VoiceAssistant","productPrice":"149.99","numberOfAvailableProducts":"10","productDiscount":"149.99","manufacturerRebate":"0"},{"productName":"Ultimate Plan","productPrice":"42.99","numberOfAvailableProducts":"10","productDiscount":"42.99","manufacturerRebate":"2"}]
    var parsedData = $.parseJSON(newMsg);

    var data = new Array();
    var productNameArr = new Array();
    var availableProductsArr = new Array();
    productNameArr.push("Product Name");
    productNameArr.push("Number of available products");

    var newArray = [];
    newArray.push(productNameArr);

    for(var i=0; i < parsedData.length; i++) {
        var productName = parsedData[i]["productName"];
        var availableProducts = Number.parseInt(parsedData[i]["numberOfAvailableProducts"]);
        var array = [].concat([productName, availableProducts]);
        newArray.push(array);
    }
    console.log("New Array: ", newArray);
    drawChart(newArray, productNameArr);
}

//Plot the chart using 2d array and product names as subtitles;
function drawChart(data, productNameArr)
{
    var productNames = "";
    /*for(var i=0; i<productNameArr.length; i++)
    {
        productNames += productNameArr[i] + ",";
    }*/

    //Invoke google's built in method to get data table object required by google.
    //var chartData = google.visualization.arrayToDataTable(data);

    /*var chartData = google.visualization.arrayToDataTable([
        ["Product Name", "Number of available products"],
        ["Basic Plan",9],
        ["LG FitnessWatch",10],
        ["LG Headphone",4],
        ["LG Laptop",7],
        ["LG Phone",5],
        ["LG SmartWatch",8],
        ["LG SoundSystem",2],
        ["LG Television",10],
        ["LG VoiceAssistant",3],
        ["Microsoft FitnessWatch",10],
        ["Microsoft Headphone",5],
        ["Microsoft Laptop",10],
        ["Microsoft Phone",7],
        ["Microsoft SmartWatch",10],
        ["Microsoft SoundSystem",9],
        ["Microsoft Television",9],
        ["Microsoft VoiceAssistant",10],
        ["Onida FitnessWatch",2],
        ["Onida Headphone",10],
        ["Onida Laptop",10],
        ["Onida Phone",1],
        ["Onida SmartWatch",10],
        ["Onida SoundSystem",10],
        ["Onida Television",3],
        ["Onida VoiceAssistant",10],
        ["Premium Plan",10],
        ["Samsung FitnessWatch",10],
        ["Samsung Headphone ",7],
        ["Samsung Laptop",10],
        ["Samsung Phone",10],
        ["Samsung SmartWatch",3],
        ["Samsung SoundSystem",10],
        ["Samsung Television",10],
        ["Samsung VoiceAssistant",10],
        ["Sony FitnessWatch",10],
        ["Sony Headphone",10],
        ["Sony Laptop",6],
        ["Sony Phone",10],
        ["Sony SmartWatch",10],
        ["Sony SoundSystem",6],
        ["Sony Television",10],
        ["Sony VoiceAssistant",10],
        ["Ultimate Plan",7]
    ]);*/
    /*
    
    */

    console.log(data);
    var chartData = google.visualization.arrayToDataTable(data);

   var options = {
    //'width':600,
    'height':650,
    chart: {
            title: 'Total Product Sales',
            //subtitle: productNames,
        },
        bars: 'horizontal' // Required for Material Bar Charts.
    };

    var chart = new google.visualization.BarChart(document.getElementById('chartDivNumberOfAvailableProducts'));
    chart.draw(chartData, options);
}