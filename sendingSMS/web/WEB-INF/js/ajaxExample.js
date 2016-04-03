//Delete Row in UserList
var delId;
function clickme(id) {
    delId = id;
    console.log("groupId" + id);
    $('#myModal').modal('show');
}
function deleteRow(id) {
    var row = document.getElementById(id);
    row.parentNode.removeChild(row);
}
function delUser() {
    var url = "UserList/del";
    console.log("groupId" + delId + "  " + url);
    $.get(url, {id: delId}, function (resp) {
    }).done(function () {
            console.log("success" + delId);
            deleteRow(delId);
        })
        .fail(function () {
            console.log("fail" + delId);
        });

    $('#myModal').modal('hide');

}
///adUSer
function addUser() {
    $('#myModalBox').modal('hide');
    //var formData = $("#addForm").serializeObject();
    //setTimeout(5000);
    //var form = $("#addForm").serializeArray();
    var url = "UserList/add";
    //var form = JSON.stringify($("#addForm").serializeJSON());
    console.log();

    var form = $("#addForm").serialize();
//    var formData = JSON.stringify(jQuery('#addForm').serializeObject());
//    var formjs = JSON.stringify(form);
    //console.log(form.firstName);
    console.log(form);
    $.get(url, form, function () {
        })
        .done(function (resp) {
            console.log("success " + resp);
        })
        .fail(function () {
            console.log("fail");
        });
}


function edit(t) {
    $('#myModalBox').modal('show');
    var url = "UserList/getUserById";
    console.log(t);
    $.get(url, {id: t}, function (resp) {

        })
        .done(function (resp) {
            console.log("success " + resp);
            $("#idUser").val(resp.idUser);
            $("#lastName").val(resp.lastName);
            $("#firstName").val(resp.firstName);
            $("#login").val(resp.roleidRole);

        })
        .fail(function (resp) {
            console.log(resp);
            console.log("fail");

        });


}

function sort() {
    var num = $("#numRole").val();
    var url = "UserList/sort";
    console.log(num);
    console.log(url);
    $.get(url, {i: num}, function (resp) {
        })
        .done(function (resp) {
            var $response = $(resp);


            var table = $response.filter('.simple-little-table');
            var list = $response.filter('.bot-navigation');
            var size = $response.filter("#secondStepInfoTab");

            console.log(size.text());

            $(".simple-little-table").remove();
            $(".bot-navigation").remove();
            $(".marh").append(table);
            $(".marh").append(list);

            $("#secondStepInfoTab").remove();
            var ul = $("#firstStepInfoTab");

            //  console.log(ul);
            ul.after(size);


        })
        .fail(function (resp) {
            console.log("fail");
        });
}

function findAllName(href) {

    console.log(href);
    var url = "";
    var name = $("#findName").val();
    document.location.href = "http://localhost:8080/UserList/ex?page=1&name=" + name;

}

window.onload = function () {

    var table = document.getElementById('tableForSort');
    var numColOfSort = 1;
    var directOfSort = 1;
    var lastEl;

    function sortTable(colNum, attribute, direct) {
        var tbody = table.getElementsByTagName('tbody')[0];
        //console.log(tbody);
        //console.log(tbody.rows);
        var rowsArray = [].slice.call(tbody.rows);
        var compare;


        console.log(attribute);

        switch (attribute) {
            case "string":
                compare = function (rowA, rowB) {
                    return rowA.cells[colNum].innerHTML > rowB.cells[colNum].innerHTML ? 1 * direct : -1 * direct;
                };
                break;
            case "number":
                compare = function (rowA, rowB) {
                    return rowA.cells[colNum].innerHTML * direct - rowB.cells[colNum].innerHTML * direct;
                };
                break;
            default:
                return;
        }


        rowsArray.sort(compare);


        table.removeChild(tbody);

        for (var i = 0; i < rowsArray.length; i++)
            tbody.appendChild(rowsArray[i])

        table.appendChild(tbody);


    }

    table.onclick = function (e) {
        console.log("click");
        if (e.target.tagName != 'TH')return;

        console.log(e.target);



        if (e.target.cellIndex != numColOfSort) {
            numColOfSort = e.target.cellIndex;
            directOfSort = 1;
            if(lastEl!=null)lastEl.innerHTML = lastEl.textContent.split(" ")[0];
            e.target.innerHTML = e.target.textContent.split(" ")[0] + " ↑";
            lastEl = e.target;
        } else {
            directOfSort *= -1;
            if (directOfSort == 1)e.target.innerHTML = e.target.textContent.split(" ")[0] + " ↑";
            else e.target.innerHTML = e.target.textContent.split(" ")[0] + " ↓";

        }


        sortTable(e.target.cellIndex, e.target.getAttribute('data-type'), directOfSort);
    };

};
