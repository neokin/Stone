$(document).ready(function() {
    $("#necklace").DataTable();

    $("#search").click(function() {
        //var rangeValue = $('input:radio[name=range]:checked').val();
        var rangeValue = $('input[name="find"]:checked').val();
        var minValue = $("#min").val();
        var maxValue = $("#max").val();
        $.ajax({type: "post",
                url: "/necklace",
                data: {range: rangeValue, min: minValue, max: maxValue},
                cache: false,
                success: function(response){
                    //$('html').replaceWith(response);
                    //$('#somediv').text(responseText);
                    //alert("success")
                   // alert(response)
                    //$('#necklace').replaceWith(data);

                    //Рабочий вариант!!!
                   // $('html').html(response)
                    //alert(response)

                    //$('').clearAttributes()
                    //$('html').html(response)
                    $("html").html(response)
                    $("#necklace").DataTable()

                },
                error:function(){
                    alert('\t\tError\nYou need fill in min and max');
                }
        });
    });
} );