$(document).ready(function () {
    $('tr[data-href]').on("click", function () {
        window.location.href = $(this).data("href");
    });
});

// 이벤트 핸들러
$(document).ready(function() {
    $(".hoverable-row").on("click", function() {
        window.location = $(this).data("href");
    });
});
