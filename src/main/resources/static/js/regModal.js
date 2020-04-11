const passwordCheck = $("#passwordCheck");
const password = $("#password");
let pwCheck = false;


function checkEmail() {
    const email = $("#email").val()
    let flag = false;
    $.ajax({
        type: 'get',
        url: '/checkEmail/' + email,
        async: false, //동기로 만들어준다.
        success: function (result) {
            if (result === 0 && pwCheck === true) {
                flag = true;
            } else if (pwCheck === false) {
                flag = false;
            } else {
                $("#failId").html('<span class="text-danger">*이미 사용중인 email입니다.</span>')
                flag = false;
            }
        }
    })
    return flag;
}

passwordCheck.on("propertychange change keyup paste input", function () {
    if (password.val() !== passwordCheck.val()) {
        $("#failPassword").html('<span class="text-danger">*비밀번호를 확인해주세요.</span>')
        pwCheck = false;
    } else {
        $("#failPassword").html('')
        pwCheck = true;
        //val() html() 내용없으면 가져오는거
    }
})