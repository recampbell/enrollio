$(document).ready(function(){
    $('#name').change(function() {
        $('#newLessonNameInSequence').text($(this).val());
    });
});
