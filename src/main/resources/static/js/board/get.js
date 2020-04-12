const move = $("#move");
const go = $("#go");
const addBtn = $("#addBtn");
const operForm = $("#operForm");
const boardId = $("#boardId").val();
const page = 1;
const replyUL = $(".chat");
const content = $("#content");
const userId = $("#userId").val();
const sessionUserId = $("#sessionUserId").val();
const sessionName = $("#sessionName").val();

if (userId !== sessionUserId) {
    move.hide();
}


//form 형식 관련
move.on("click", function () {
    alert(userId + "eeeee" + sessionUserId)
    if (userId === sessionUserId) {
        operForm.attr("action", "/board/modify").submit();
    } else {
        alert("너 아님")
    }
});

go.on("click", function () {
    operForm.attr("action", "/board/list").submit();
});


addBtn.on("click", function () {
    const data = {
        replyer: sessionName,
        replyContent: content.val(),
        boardId: boardId,
        userId: sessionUserId
    };
    insert(data);
});

function showList(page) {
    $.ajax({
        type: "get",
        url: "/reply/replyList/" + boardId + "/" + page,
        success: (list) => {
            let str = "";
            if (list == null || list.length == 0) {
                replyUL.html("");
                return;
            }
            for (let i = 0, len = list.length || 0; i < len; i++) {
                if (Number(list[i].userId) === Number(sessionUserId)) {
                    str += "<li class='left clearfix' data-replyId='" + list[i].replyId + "'>";
                    str += " <div><div class='header'><strong class='primary-font'>" + list[i].replyer + "</strong>";
                    str += "<span class='modi' style='margin-left: 15px; font-size: small' onclick='modifyReply(" + list[i].replyId + ")'>[수정</span>][<span class='del' style='font-size: small' onclick='deleteReply(" + list[i].replyId + ")'>삭제]</span >";
                    str += "  <small class='pull-right text-muted mr-3'>" + list[i].replyDate + "</small></div>";
                    str += "<p id='" + list[i].replyId + "'>" + list[i].replyContent + "</p></div></li><hr/>";
                } else {
                    str += "<li class='left clearfix' data-replyId='" + list[i].replyId + "'>";
                    str += " <div><div class='header'><strong class='primary-font'>" + list[i].replyer + "</strong>";
                    str += "  <small class='pull-right text-muted mr-3'>" + list[i].replyDate + "</small></div>";
                    str += "<p id='" + list[i].replyId + "'>" + list[i].replyContent + "</p></div></li><hr/>";
                }
            }

            replyUL.html(str);
        }
    })
}

function deleteReply(replyId) {

    const swalWithBootstrapButtons = Swal.mixin({
        customClass: {
            confirmButton: 'btn btn-success',
            cancelButton: 'btn btn-danger'
        },
        buttonsStyling: false
    })

    swalWithBootstrapButtons.fire({
        title: '삭제하시겠습니까?',
        text: "",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: '삭제하기',
        cancelButtonText: '취소',
        reverseButtons: true
    }).then((result) => {
        if (result.value) {
            $.ajax({
                type: "post",
                url: "/reply/replyDelete/" + replyId,
                success: function () {
                    showList(1)
                    swalWithBootstrapButtons.fire(
                        '삭제하였습니다',
                        '',
                        'success'
                    )
                }
            })
        } else if (
            /* Read more about handling dismissals below */
            result.dismiss === Swal.DismissReason.cancel
        ) {
            swalWithBootstrapButtons.fire(
                '취소하였습니다',
                '',
                'error'
            )
        }
    })

}

//html string으로 집어넣으면 html그대로 찍힘
function modifyReply(replyId) {

    const swalWithBootstrapButtons = Swal.mixin({
        customClass: {
            confirmButton: 'btn btn-success',
            cancelButton: 'btn btn-danger'
        },
        buttonsStyling: false
    })

    const replyContent = $("#" + replyId).html()
    content.val(replyContent)

    $("#btnArea").html('<button id="modifyBtn" class="btn btn-info pull-right" style="margin-top: -40px">수정하기</button>')

    $("#modifyBtn").click(() => {
        swalWithBootstrapButtons.fire({
            title: '수정하시겠습니까?',
            text: "",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: '수정하기',
            cancelButtonText: '취소',
            reverseButtons: true
        }).then((result) => {
            if (result.value) {

                const data = {
                    replyId: replyId,
                    replyContent: content.val()
                }

                $.ajax({
                    type: "post",
                    url: "/reply/replyUpdate",
                    data: data,
                    success: function () {
                        content.val('');
                        $("#btnArea").html('<button id="addBtn" class="btn btn-info pull-right" style="margin-top: -40px">등록하기</button>')
                        showList(1);
                        swalWithBootstrapButtons.fire(
                            '수정하였습니다',
                            '',
                            'success'
                        )
                    }
                })
            } else if (
                /* Read more about handling dismissals below */
                result.dismiss === Swal.DismissReason.cancel
            ) {
                swalWithBootstrapButtons.fire(
                    '취소하였습니다',
                    '',
                    'error'
                )
            }
        })
    })

};

function insert(data) {

    Swal.fire({
        title: '등록하시겠습니까?',
        text: "",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '등록'
    }).then((result) => {
        if (result.value) {
            $.ajax({
                type: "post",
                url: "/reply/replyInsert",
                data: data,
                success: function () {
                    Swal.fire(
                        '등록되었습니다',
                        '',
                        'success'
                    )
                    content.val('');
                    showList(1);
                }
            })

        } else {

        }
    })
}


showList(1);

