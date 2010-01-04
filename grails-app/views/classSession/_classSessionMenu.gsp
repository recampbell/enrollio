<ul>
    <li>
        <h3>
            <a href="#" class="calendar">Class Session</a>
        </h3>
        <ul>
            <!-- TODO Refactor this into a taglib or template -->
            <li>
                <script type="text/javascript">function
                submit_graduationCertificate(link) { link.parentNode._format.value =
                link.title; link.parentNode.submit(); return false; }</script>
                <g:form name="graduationCertificate" class="jasperReport"
                action="gradCerts">
                    <input type="hidden" name="_format" value="PDF" />
                    <!-- Name shown on top of PDF report -->
                    <input type="hidden" name="_name" value="Graduation Certificates" />
                    <input type="hidden" name="_file" value="graduationCertificate" />
                    <input type="hidden" name="id" value="${classSessionInstance.id}" />
                    <!-- TODO The &nbsp; is a kludge find CSS way to justify image
                                        and text so it looks o.k. -->
                    <a name="gradCertsLink" href="#" class="graduation_cap" title="PDF"
                    onClick="return submit_graduationCertificate(this)">&#160;&#160;Grad.
                    Certificates</a>
                </g:form>
            </li>
            <li>
                <g:link class="application_list" action="attendanceSheet"
                id="${classSessionInstance.id}">&#160;Attendance Sheet</g:link>
            </li>
        </ul>
    </li>
</ul>
