
<ul>
    <li>
        <h3>
             <g:link class="wrench" controller="admin">Admin</g:link>
        </h3>
        <h3>
            <a href="#" class="app_form">Settings</a>
        </h3>
        <ul>
            <li>
                <g:link class="app_form_add" controller="configSetting" action="list">New Setting</g:link>
            </li>
            <g:if test="${configSettingInstance}"
            <li>
            <g:link class="app_form_edit" 
            name="editSettingLink"
            controller="configSetting" 
            action="edit" id="${configSettingInstance.id}">Edit Setting</g:link>
            </li>
            </g:if>
        </ul>
    </li>
</ul>
