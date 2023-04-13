// 게시글 작성 폼 유효성 검사
function validateForm() {
  const title = $('#title');
  const content = $('#content');
  const category = $('[name="categories"]');

  if (title.val().trim() === '') {
    alert('제목을 입력해주세요.');
    title.focus();
    return false;
  }

  if (content.val().trim() === '') {
    alert('내용을 입력해주세요.');
    content.focus();
    return false;
  }

  if (category.val() === 'Please select a category') {
    alert('카테고리를 선택해주세요.');
    category.focus();
    return false;
  }

  return true;
}


$(document).ready(function () {
  $('[type="reset"]').on('click', function (e) {
    if (!confirm('정말 초기화를 하시겠습니까 ?')) {
      e.preventDefault();
    }
  });
});
