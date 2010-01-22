$(document).ready(function() {
    $("#sortable").sortable({
        update: function(event, ui) {
            // after an item is dropped, go through each
            // lessonId_, and give it seq. numbers, starting at 100, 
            // and incrementing by 100
            $('input[name^=lessonId_]').each(
                function(i) { $(this).val((i + 1) * 100); }
            );
        }
    });
    $("#sortable").disableSelection();
});
