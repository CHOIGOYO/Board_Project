// 수정 버튼 클릭 시 실행 
function updateReq(buttonElement) {
    var boardId = $("#board-id").val();
    location.href = "/board/update/" + boardId;
}

// 삭제 버튼을 눌렀을 때 실행 
function deleteReq(buttonElement) {
    var boardId = $("#board-id").val();
    
    if (confirm("정말 삭제하시겠습니까?")) {
        location.href = "/board/delete/" + boardId;
    }
}


