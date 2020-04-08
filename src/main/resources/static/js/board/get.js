const move = $("#move");

const operForm = $("#operForm");

move.on("click", function (e) {
    operForm.attr("action", "/board/modify").submit();
})