function ajaxPostSearchConfig(){
    var data = {
        pageLimit : $("#pageLimit").val(),
        passatLimit :  $("#passatLimit").val(),
        dateLimit :  $("#dateLimit").val()
    }
    $.ajax({
        type : "POST",
        contentType : "application/json",
        url : "/search",
        data : JSON.stringify(data),
        dataType : 'json',
        success : function(result) {
            $("#submitButton").text("submit");
            if(result.validated) {
                passats = result.passats;
                drawChart()
                $("#saveSearchResultButton").prop("disabled", false);
            } else {
                $.each(result.errorMessages, function (key, value) {
                    $('input[name=' + key + ']').after('<span class="text-danger">' + value + '</span>');
                    if(key === "nothingFound") {
                        $('#chart_div').before('<span class="text-danger">' + value + '</span>');
                    }
                });
            }
        },
        error : function(e) {
            $("#submitButton").text("submit");
            console.log("ERROR: ", e);
        }
    });
}

function ajaxPostSaveSearchResult(){
    $.ajax({
        type : "POST",
        contentType : "application/json",
        url : "/saveSearchResult",
        data : JSON.stringify(passats),
        dataType : 'json',
        success : function(result) {
            $("#saveSearchResultButton").after($("<span>", {"class": "text-success"}).text("Save " + result + " passats"));
        },
        error : function(e) {
            console.log("ERROR: ", e);
        }
    });
}

function ajaxPostGetPassatsFromBase(){
    var data = {
        startDate : $("#startDate").val(),
        endDate :  $("#endDate").val(),
    }
    $.ajax({
        type : "POST",
        contentType : "application/json",
        url : "/getPassatsFromBase",
        data : JSON.stringify(data),
        dataType : 'json',
        success : function(result) {
            passats = result;
            $("#getPassatsFromBaseButton").text("submit")
                .after($("<span>", {"class": "text-success"}).text("Find " + result.length + " passats"));
            drawChart()
        },
        error : function(e) {
            $("#getPassatsFromBaseButton").text("submit");
            console.log("ERROR: ", e);
        }
    });
}