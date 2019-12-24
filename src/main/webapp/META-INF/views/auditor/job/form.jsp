<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="auditor.job.form.label.referenceNumber" path="referenceNumber" placeholder="EEEE-JJJJ"/>
	<acme:form-textbox code="auditor.job.form.label.title" path="title" />
	<acme:form-moment code="auditor.job.form.label.deadline" path="deadline" />
	<acme:form-double code="auditor.job.form.label.salary" path="salary" />
	<acme:form-textbox code="auditor.job.form.label.moreInfo" path="moreInfo" />
	<acme:form-textbox code="auditor.job.form.label.status" path="status" />
	<acme:form-textbox code="auditor.job.form.label.description" path="description" />
	
	<jstl:if test="${command == 'show'}">
		<acme:form-return code="auditor.job.form.button.duties" action="${duties}" />
		<acme:form-return code="auditor.job.form.button.createAudits" action="${jobCreateAudit}" />
		<acme:form-return code="auditor.job.form.button.auditList" action="${auditList}" />
	</jstl:if>
	
	<acme:form-return code="auditor.job.form.button.return" />
</acme:form>
