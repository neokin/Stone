$(document).ready(function() {
    var a = true
    $("#necklace").DataTable();


    $("#flip").click(function(){
        $.ajax({type: "get",
            url: "http://localhost:8080/necklace",
            //data: {range: rangeValue, min: minValue, max: maxValue},
            cache: false,
            success: function(){

              /*  alert("clicked");*/
         $("#panel").slideToggle("fast")
                if(a) {
                    $("#flip").text("Свернуть");
                    a = false
                }
                else {
                    $("#flip").text("Развернуть");
                    a = true
                }

            },
            error:function(){
                alert('\t\tError Flip');
            }
        });
    });


    $("#search").click(function() {
        //var rangeValue = $('input:radio[name=range]:checked').val();
        var rangeValue = $('input[name="find"]:checked').val();
        var minValue = $("#min").val();
        var maxValue = $("#max").val();
        $.ajax({type: "post",
                url: "http://localhost:8080/necklace",
                data: {range: rangeValue, min: minValue, max: maxValue},
                cache: false,
                success: function(response){

                    $("html").html(response)
                    $("#necklace").DataTable()

                },
                error:function(){
                    alert('\t\tError\nYou need fill in min and max')
                }
        });
    });




} );



