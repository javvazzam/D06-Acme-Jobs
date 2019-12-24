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

<link rel="stylesheet" href="libraries/acme/css/challenge.css" />

<acme:form>
	
	<acme:form-textbox code="administrator.auditor.form.label.firm" path="firm" readonly="true"/>
	<acme:form-textarea code="administrator.auditor.form.label.statement" path="statement" readonly="true" />
	<jstl:if test="${status=='Accepted'}">
	<acme:form-select code="administrator.auditor.form.label.status" path="status">
		<acme:form-option code="administrator.auditor.form.label.status1" value="Accepted" selected="true"/>
		<acme:form-option code="administrator.auditor.form.label.status0" value="Pending"/>
	</acme:form-select>
	</jstl:if>
	<jstl:if test="${status=='Pending'}">
	<acme:form-select code="administrator.auditor.form.label.status" path="status">
		<acme:form-option code="administrator.auditor.form.label.status1" value="Accepted"/>
		<acme:form-option code="administrator.auditor.form.label.status0" value="Pending" selected="true"/>
	</acme:form-select>
	</jstl:if>
	<acme:form-textarea code="administrator.auditor.form.label.body" path="body" readonly="true" />
	
	<acme:form-submit test="${command== 'show'}" code="administrator.auditor.form.button.update"
		action="/administrator/auditor/update" />
		
	<acme:form-submit test="${command == 'update'}" code="administrator.auditor.form.button.update"
		action="/administrator/auditor/update"/>
		
	<acme:form-return code="administrator.auditor.form.button.return" />
</acme:form>
