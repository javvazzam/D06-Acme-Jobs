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
<jstl:if test="${command != 'create' }">
	<acme:form-textbox code="auditor.audit.form.label.title" path="title"/>
	<jstl:if test="${status=='Published'}">
		<acme:form-select code="auditor.audit.form.label.status" path="status">
			<acme:form-option code="auditor.audit.form.label.status1" value="Published" selected="true"/>
			<acme:form-option code="auditor.audit.form.label.status0" value="Draft" />
		</acme:form-select>
	</jstl:if>
	<jstl:if test="${status=='Draft'}">
		<acme:form-select code="auditor.audit.form.label.status" path="status">
			<acme:form-option code="auditor.audit.form.label.status1" value="Published"/>
			<acme:form-option code="auditor.audit.form.label.status0" value="Draft" selected="true"/>
		</acme:form-select>
	</jstl:if>
	<acme:form-moment code="auditor.audit.form.label.moment" path="moment" readonly="true" />
	<acme:form-textarea code="auditor.audit.form.label.body" path="body"/>
	<acme:form-textbox code="auditor.audit.form.label.auditor" path="associatedAuditor" readonly="true"/>
	<acme:form-textbox code="auditor.audit.form.label.associatedJob" path="associatedJob" readonly="true"/>
	
	<acme:form-submit test="${command== 'show' && !isPublished}" code="auditor.audit.form.button.update"
		action="/auditor/audit/update" />
		
	<acme:form-submit test="${command== 'update' && !isPublished}" code="auditor.audit.form.button.update"
		action="/auditor/audit/update" />
		
</jstl:if>

<jstl:if test="${command == 'create' }">
	<acme:form-textbox code="auditor.audit.form.label.title" path="title"/>
	
	<acme:form-select code="auditor.audit.form.label.status" path="status">
	<acme:form-option code="auditor.audit.form.label.status1" value="Published"/>
	<acme:form-option code="auditor.audit.form.label.status0" value="Draft"/>
	</acme:form-select>
	<acme:form-textarea code="auditor.audit.form.label.body" path="body"/>
</jstl:if>
	<acme:form-hidden path="direccionAudit"/>
	<acme:form-hidden path="isPublished"/>
	<acme:form-submit test="${command=='create'}" code="auditor.audit.form.button.create" action="${direccionAudit}" />
  	<acme:form-return code="auditor.audit.form.button.return"/>
  	
</acme:form>
