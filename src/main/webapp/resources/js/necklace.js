$.fn.dataTable.ext.search.push(
    function (settings, data, dataIndex) {
        var min = parseInt($('#min').val(), 10);
        var max = parseInt($('#max').val(), 10);
        var rangeValue = $('input[name="find"]:checked').val();
        var param;
        if (rangeValue == "cost") {
            param = parseFloat(data[4]) || 0;
        }
        else {
            param = parseFloat(data[3]) || 0;
        }

        if (( isNaN(min) && isNaN(max) ) ||
            ( isNaN(min) && param <= max ) ||
            ( min <= param && isNaN(max) ) ||
            ( min <= param && param <= max )) {
            return true;
        }
        return false;
    }
);

$(document).ready(function () {
    var table0 = $("#stones").DataTable();
    var table = $("#necklace").DataTable();

        $("#search").click(function () {
            table0.draw();
        });

    function updateCW() {

        $.ajax(location.href, {
            type: "post",
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

        $.ajax(location.href, {
            type: "post",
            data: {method: "delete", necklace: necklaceId, stone: stoneId},
            cache: false,
            success: function () {

                (self.closest('td')).parent().remove();
                updateCW();

            },
            error: function () {
                alert("\t\tError\nCan't delete stone from necklace");
            }
        });
    };

    $(".necklace-add").click(function () {
        var self = $(this);

        var necklaceId = $('#necklace-id').text();
        var stoneId = self.attr('data-stone-id');

        var s = "#stones tr#row-stone-id-" + stoneId;
        var raw = $("#stones tr#row-stone-id-" + stoneId);
        var type = $('td:nth-child(2)', raw).text();
        var name = $('td:nth-child(3)', raw).text();
        var cost = $('td:nth-child(4)', raw).text();
        var weight = $('td:nth-child(5)', raw).text();
        var table = $('#necklace').DataTable();

        var btn = "<button class='necklace-delete' data-stone-id=" + stoneId + ">Delete from necklace</button>";

        $.ajax(location.href, {
            type: "post",
            url: "http://localhost:8080/necklace",
            data: {method: "add", necklace: necklaceId, stone: stoneId},
            cache: false,
            success: function () {

                table.row.add([type, name, cost, weight, btn]).draw(false);
                $(".necklace-delete").click(deleteStone);
                updateCW();
            },
            error: function () {
                alert("The stone " + name + " exists in necklace");
            }
        });

    });


    $(".necklace-delete").click(deleteStone);


});




