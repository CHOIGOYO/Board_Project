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

// 댓글 submit 버튼을 눌렀을 때
$(document).ready(function() {
    $('#submitComments').on('click', submitComment);
  });
  
  function submitComment() {
    const commentInput = $('input[name="comment"]');
    const commentText = commentInput.val().trim();
    const userId = $('#user-id').val();
    const boardId = $('#board-id').val();
  
    if (commentText === '') {
      alert('댓글을 입력해주세요.');
      commentInput.focus();
      return;
    }
  
    // 서버에 댓글 데이터 전송
    $.ajax({
        type: 'POST',
        url: '/comment/save',
        contentType: 'application/json', // JSON 형식으로 전송
        data: JSON.stringify({ // JSON 문자열로 변환
            memberId: userId,
            boardId: boardId,
            content: commentText
        }),
        success: function(response) {
            commentInput.val('');
        },
        error: function(error) {
            alert(`오류: ${error.responseText}`);
        }
    });

  }
  
