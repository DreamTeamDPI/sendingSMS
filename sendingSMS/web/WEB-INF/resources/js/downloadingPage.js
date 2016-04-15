/**
 * Created by SemmEs on 15.04.2016.
 */

window.onload = function () {
    
    var control = $(".sendingSMSControl").val();
    var n = false;

    if(!n)
        func();

    function a() {
        toastr.info('Смс отправлена');
    }



    function func() {
        if (control == "1") {
            a();
        } else {
        }
        n = true;
    }

    $('#dataBeginFilter').datepicker({
        language: "ru",
        multidate: false,
        forceParse: false,
        autoclose: true,
        todayHighlight: true,
        toggleActive: true
    });
    $('#dataEndFilter').datepicker({
        language: "ru",
        multidate: false,
        forceParse: false,
        autoclose: true,
        todayHighlight: true,
        toggleActive: true
    });
    $('#dataBeginPaymentFilter').datepicker({
        language: "ru",
        multidate: false,
        forceParse: false,
        autoclose: true,
        todayHighlight: true,
        toggleActive: true
    });
    $('#dataEndPaymentFilter').datepicker({
        language: "ru",
        multidate: false,
        forceParse: false,
        autoclose: true,
        todayHighlight: true,
        toggleActive: true
    });
};
function sendOneSMS(){
    var x = document.forms["sendOneSmsName"]["phone"].value;
    var y = document.forms["sendOneSmsName"]["text"].value;
    console.log(x + "   "  + y);
    if (x == null || x == "" || y == null || y == "" ) {
        toastr.warning("Все поля должны быть заполнены!")
        return false;
    }
}
function sendFileToServer(){
    var x = document.forms["sendFileName"]["file"].value;
    console.log(x );
    if (x == null || x == "") {
        toastr.warning("Прикрепите файл!")
        return false;
    }
}