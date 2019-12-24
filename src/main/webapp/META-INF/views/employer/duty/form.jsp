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
		<acme:form-textbox code="employer.duty.form.label.title" path="title" />
		<acme:form-textarea code="employer.duty.form.label.description" path="description" />
		<acme:form-moment code="employer.duty.form.label.timeAmount" path="timeAmount" placeholder="0-100" />
		
	<acme:form-hidden path="result"/>
	<acme:form-hidden path="iAmPrincipal"/>
		<jstl:if test="${iAmPrincipal=='true'}">
			<jstl:if test="${notFinalMode=='true'}">
				<acme:form-submit test="${command== 'show'}" code="employer.duty.form.button.update" action="/employer/duty/update" />

				<acme:form-submit test="${command== 'update'}" code="employer.duty.form.button.update" action="/employer/duty/update" />

				<acme:form-submit test="${command== 'show'}" code="employer.duty.form.button.delete" action="/employer/duty/delete" />

				<acme:form-submit test="${command== 'delete'}" code="employer.duty.form.button.delete" action="/employer/duty/delete" />
			</jstl:if>
		</jstl:if>
	</jstl:if>
	<acme:form-hidden path="direccionDuty" />
	<jstl:if test="${command == 'create' }">
		<acme:form-textarea code="employer.duty.form.label.title" path="title" />
		<acme:form-textarea code="employer.duty.form.label.description" path="description" />
		<acme:form-textbox code="employer.duty.form.label.timeAmount" path="timeAmount" placeholder="0-100" />
		<acme:form-submit code="employer.duty.form.button.create" action="${direccionDuty}" />
	</jstl:if>


	<acme:form-return code="employer.job.form.button.return" />
</acme:form>
