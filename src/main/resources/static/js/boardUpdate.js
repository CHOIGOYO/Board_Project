function validateForm() {
    var title = $("#title").val();
    var content = $("#content").val();
    var categories = $("select[name='categories']").val();

    if (title.trim() === "") {
        alert("제목을 작성해주세요.");
        $("#title").focus();
        return false;
    }

    if (content.trim() === "") {
        alert("내용을 입력해주세요.");
        $("#content").focus();
        return false;
    }

    if (categories === "") {
        alert("카테고리를 선택해주세요.");
        $("select[name='categories']").focus();
        return false;
    }

    if (confirm("수정한 내용을 저장하시겠습니까?")) {
        return true;
    } else {
        return false;
    }
}