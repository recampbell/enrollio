<ul>
    <!-- Main admin menu -->
    <li>
        <h3>
            <g:link class="wrench" controller="admin">Admin</g:link>
        </h3>
    </li>
    <!-- Users -->
    <li>
        <h3>
            <g:link class="group" controller="shiroUser" action="list">Users</g:link>
        </h3>
        <ul>
            <li>
                <g:link class="useradd" controller="shiroUser" action="create">Create
                User</g:link>
            </li>
            <g:if test="${shiroUserInstance?.id}"
                <li>
                <g:link class="useredit" 
                name="editUserLink"
                controller="shiroUser" 
                action="edit" params="[username : shiroUserInstance.username ]">Edit User</g:link>
                </li>
            </g:if>
        </ul>
    </li>
    <!-- Settings -->
    <li>
        <h3>
            <g:link class="app_form" controller="configSetting" 
                   action="list">Settings</g:link>
        </h3>
        <ul>
            <g:if test="${configSettingInstance?.id}"
                <li>
                <g:link class="app_form_edit" 
                name="editSettingLink"
                controller="configSetting" 
                action="edit" id="${configSettingInstance.id}">Edit Setting</g:link>
                </li>
            </g:if>
        </ul>
        </li>
        <li>
        <h3>
            <g:link class="group" controller="configSetting" action="testDataRequest">Test Data</g:link>
        </h3>
        <h3>
            <g:link class="group" controller="configSetting" action="loadDataRequest">Import Data</g:link>
        </h3>
    </li>
    <li>
    <h3><a href="" class="zoom">Search settings</a> </h3>
        <ul>
                <li>
                <g:link class="zoom_in" controller="admin" action="startMirroring">Start Mirroring</g:link> 
                </li>
                <li>
                <g:link class="zoom_out" controller="admin" action="startMirroring">Stop Mirroring</g:link> 
                </li>
        </ul>
        </li>
        <li>
        <h3>
            <g:link class="group" controller="configSetting" action="testDataRequest">Test Data</g:link>
        </h3>
        <h3>
            <g:link class="group" controller="configSetting" action="loadDataRequest">Import Data</g:link>
        </h3>
    </li>
</ul>
