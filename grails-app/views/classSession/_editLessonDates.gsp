<table>
   <g:each var="lessonDate" in="${lessonDates}" status="i">
   <tr class="prop">
       <td valign="top">
           
           <input type="text" id="lesson_${lessonDate.lesson.id}" 
                            name="lesson_${lessonDate.lesson.id}"
                            value="${lessonDate?.lessonDate}"
                            class="lessonDate"></input>
           
       </td>
       <td valign="top" class="name">
           <label for="lessonDate">${lessonDate.lesson.name}</label>
       </td>
   </tr>
</g:each>
</table>
