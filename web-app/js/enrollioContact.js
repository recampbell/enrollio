 $(document).ready(function(){
      // Show date picker for the "Signup Date" that shown on the
      // new student form.  (It's rendered in the createStudent.gsp template below)
         $('#signupDate').datepicker({changeYear:true});
      $("select.multiselect").multiSelect({
          showHeader : false,
          noneSelectedText : 'Select Interests',
           selectedList:2   // selectedList shows the names of the selected interests!
      });

      // toggle students' starred status, depending on students current starred status                
      // We have to use a <span></span> to hold the image, because we
      // replace the contents of the span with the result of this POST.
      // if we used only an image w/o a parent <span> it doesn't work
      $(".star").click(function(){
        $(this).load('${createLink(controller:"student",
                                action:"toggleStar")}',
            { 'starred' : $(this).children('img').attr('starred'), 'id' : $(this).attr("starId") });
        });

      $(".editNote").click(function() {
          var noteId = $( this ).attr( 'editNoteId' );
          var noteElement=$( "[noteId=" + noteId + "]");
          var noteText = noteElement.text().trim();
          
          noteElement.html(
              '<input noteTextId="' + noteId + '" ' +
              'value="' + noteText + '" />'
          );

          // register an event handler to trap ENTER key
          // which will send the updated note
          $('[noteTextId=' + noteId + ']').keydown(function(event) {
            if (event.keyCode == '13') {
                $("[[saveNoteId=" + noteId + "]").click();
                event.preventDefault();
             }
          });

          toggleNoteLinks(noteId);
          
          // Don't actually trigger the href
          return false;
          
      });

      $(".saveNote").click(function() {
          var noteId = $( this ).attr( 'saveNoteId' );
          var noteElement=$( "[noteTextId=" + noteId + "]");
          var noteText = noteElement.val().trim();
          toggleNoteLinks(noteId);

          $.ajax({
              url:'${createLink(controller:"contact", action:"updateNote")}',
              data:  { 'noteText' : noteText, 'id' : noteId },
              success: function(msg) {
                  $("[noteDiv=" + noteId + "]").replaceWith(msg)
              }});
          // Don't actually trigger the href
          return false;
      });

      $(".cancelNote").click(function() {
          var noteId = $( this ).attr( 'cancelNoteId' );
          var noteText = $( "[origNoteText=" + noteId + "]").val().trim();
          
          $( "[noteId=" + noteId + "]").text(noteText);

          toggleNoteLinks(noteId);

          // Don't actually trigger the href
          return false;
      });

      function toggleNoteLinks(noteId) {
          $("[editNoteId=" + noteId + "]").toggle();
          $("[cancelNoteId=" + noteId + "]").toggle();
          $("[saveNoteId=" + noteId + "]").toggle();
      }

});
function updateInterests() {
    // Collect the labels of each of the selected Interests
    // and put them into the "Interests" section of the newStudentForm
    // from http://groups.google.com/group/jquery-en/browse_thread/thread/6bbc26e14a59526c
    var selectedInterestLabels = 
        $('input[name=interestInCourse]:checked').map(function() {
            return $(this).attr("courseName")
    }).get();
    $('#interestNames').val(selectedInterestLabels.join(", "))
}
