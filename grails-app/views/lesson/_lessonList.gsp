<div class="rightnow">
    <h3 class="reallynow">
        <span>${courseInstance} Lessons</span>
        <br />
    </h3>
    <table>
        <thead>
            <g:sortableColumn property="name" title="Name" />
            <g:sortableColumn property="description" title="Description" />
            <g:sortableColumn property="sequence" title="Sequence" />
        </thead>
        <tbody>
            <g:each in="${courseInstance.lessons}" status="i"
            var="lessonInstance">
            <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                <td>
                    <g:link name="lessonLink${lessonInstance.id}" 
                    controller="lesson"
                    action="show" 
                    id="${lessonInstance.id}">${fieldValue(bean:lessonInstance, field:'name')}</g:link>
                </td>
                <td>${fieldValue(bean:lessonInstance,
                    field:'description')}</td>
                <td>${fieldValue(bean:lessonInstance,
                    field:'sequence')}</td>
            </tr>
            </g:each>
        </tbody>
    </table>
</div>
