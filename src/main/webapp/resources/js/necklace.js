$(document).ready(function () {
    var table0 = $("#stones").DataTable();
    var table = $("#necklace").DataTable();

    $("#search").click(function () {

        var rangeValue = $('input[name="find"]:checked').val();
        var minValue = $("#min").val();
        var maxValue = $("#max").val();
        $.ajax(location.href, {
            type: "post",
            url: "http://localhost:8080/necklace",
            data: {method: "search", range: rangeValue, min: minValue, max: maxValue},
            cache: false,
            success: function (response) {
//response.children('td')

                $("html").html(response);
                $("#necklace").DataTable()
                updateCW()

            },
            error: function () {
                alert('\t\tError\nYou need fill in min and max')
            }
        });
    });

    function updateCW() {

        $.ajax(location.href, {
            type: "post",
            url: "http://localhost:8080/necklace",
            data: {method: "updateCost"},
            cache: false,

            success: function (result) {
                if (result == 0) {

                   table.clear().draw();

                }

                $("#cost").replaceWith("<label id = \"cost\">Цена ожерелья:" + result + " </label>");

            },
            error: function () {
                alert("Mistake in cost");
            }
        });


        $.ajax(location.href, {
            type: "post",
            url: "http://localhost:8080/necklace",
            data: {method: "updateWeight"},
            cache: false,

            success: function (result) {


                $("#weight").replaceWith("<label id = \"weight\">Вес ожерелья:" + result + " </label>");
            },
            error: function () {
                alert("Mistake in weight");
            }
        });


    }

    function deleteStone() {
        var self = $(this);
        var necklaceId = $('#necklace-id').text();
        var stoneId = self.attr('data-stone-id');
        var table = $('#necklace').DataTable();
        //var cost = $('#cost').text();
        $.ajax(location.href, {
            type: "post",
            url: "http://localhost:8080/necklace",
            data: {method: "delete", necklace: necklaceId, stone: stoneId},
            cache: false,
            success: function () {

                (self.closest('td')).parent().remove();
                updateCW();

            },
            error: function () {
                alert("\t\tError\nCon't delete stone from necklace");
            }
        });
    };

    $(".necklace-add").click(function () {
        var self = $(this);
        //var id = self.parent('td').parent('tr').find('td:first').text();
        var necklaceId = $('#necklace-id').text();
        var stoneId = self.attr('data-stone-id');

        var s = "#stones tr#row-stone-id-" + stoneId;
        var raw = $("#stones tr#row-stone-id-" + stoneId);
        var type = $('td:nth-child(2)', raw).text();
        var name = $('td:nth-child(3)', raw).text();
        var cost = $('td:nth-child(4)', raw).text();
        var weight = $('td:nth-child(5)', raw).text();
        var table = $('#necklace').DataTable();
        //var btn = "<button class='necklace-delete' data-stone-id=\'" + ${stone.id} + "\'>Delete from necklace</button>";
        //var btn = "<button class='necklace-delete' data-stone-id='${stone.id}'>Delete from necklace</button>";
        var btn = "<button class='necklace-delete' data-stone-id=" + stoneId + ">Delete from necklace</button>";
        /*        var btn =" <button class='necklace-delete' data-stone-id= ";
         btn+=$(stone.id);
         btn += ">Delete from necklace</button>";*/
        $.ajax(location.href, {
            type: "post",
            url: "http://localhost:8080/necklace",
            data: {method: "add", necklace: necklaceId, stone: stoneId},
            cache: false,
            success: function () {


                //var btn = "<button class='necklace-delete' data-stone-id='" + ${stone.id} +"'>Delete from necklace</button>";
                table.row.add([type, name, cost, weight, btn]).draw(false);
                $(".necklace-delete").click(deleteStone);
                updateCW();
            },
            error: function () {
                alert("\t\tError\nCon't add stone to necklace");
            }
        });

    });


    $(".necklace-delete").click(deleteStone);


});



