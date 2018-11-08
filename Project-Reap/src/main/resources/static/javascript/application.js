
// $("#signin").on('click',function (){
//     alert("User Registered Successfully");
// });

// function test(){
//     alert("User Registered Successfully");
// };

// $('#submitForm').on('hidden.bs.modal', function () {
//     $(this).find('form').trigger('reset');
// });

// $('#login').on('hidden.bs.modal', function (e) {
//     $(this)
//         .find("input,textarea,select")
//         .val('')
//         .end()
//         .find("input[type=checkbox], input[type=radio]")
//         .prop("checked", "")
//         .end();
// })

// $(document).ready(function() {
//     $('#login').on('hidden', function() {
//         clear();
//     });
// });




// $('#login').modal({
//     backdrop:true
// })


$(document).ready(function()
{
    $('#mod_cls').on('click', function () {
        $('#signInForm').trigger("reset");
        console.log($('#signInForm'));
    })
});

function SaveForm() {
    var data=$("#registeration").serialize();
    $.ajax({
        type:"post",
        data:data,
        url:"/index",
        success:function (result) {
            alert(result);
            console.log("request from server",data);
        }
    })
}