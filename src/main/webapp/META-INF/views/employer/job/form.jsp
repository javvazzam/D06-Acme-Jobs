<%--
- form.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it i
s not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>

	<acme:form-textbox code="employer.job.form.label.referenceNumber" path="referenceNumber" placeholder="EEEE-JJJJ" />
	<acme:form-textbox code="employer.job.form.label.title" path="title" />
	<acme:form-moment code="employer.job.form.label.deadline" path="deadline" />
	<acme:form-double code="employer.job.form.label.salary" path="salary" />
	<acme:form-textbox code="employer.job.form.label.moreInfo" path="moreInfo" />

	<acme:form-textbox code="employer.job.form.label.description" path="description" />
	<jstl:if test="${command == 'show' || command == 'update'}">
		<jstl:if test="${status=='Published'}">
			<acme:form-select code="employer.job.form.label.status" path="status">
				<acme:form-option code="employer.job.form.label.status1" value="Published" selected="true"/>
				<acme:form-option code="employer.job.form.label.status0" value="Draft"/>
			</acme:form-select>
		</jstl:if>
		
		<jstl:if test="${status=='Draft'}">
			<acme:form-select code="employer.job.form.label.status" path="status">
				<acme:form-option code="employer.job.form.label.status0" value="Draft" selected="true"/>
				<acme:form-option code="employer.job.form.label.status1" value="Published"/>
			</acme:form-select>
		</jstl:if>
	</jstl:if>

	<jstl:if test="${command != 'update' && command != 'create'}">
		<acme:form-return code="employer.job.form.button.duties" action="${duties}" />
		<jstl:if test="${result=='true'}">
			<acme:form-return code="employer.job.form.button.createDuties" action="${jobCreateDuty}" />
		</jstl:if>
		<acme:form-return code="employer.job.form.button.auditList" action="${auditList}" />
	</jstl:if>


	<acme:form-submit test="${command == 'create'}" code="employer.job.form.button.create" action="/employer/job/create" />

	<acme:form-hidden path="result"/>
	<acme:form-hidden path="iAmPrincipal"/>

	<jstl:if test="${result=='true'}">
		<acme:form-submit test="${command == 'show' || command == 'update' || command == 'delete'}" code="employer.job.form.button.update"
			action="/employer/job/update" />
	</jstl:if>

	<jstl:if test="${iAmPrincipal=='true' && jobapplied}">
		<acme:form-submit test="${command == 'show' || command == 'update' || command == 'delete'}" code="employer.job.form.button.delete"
			action="/employer/job/delete" />
	</jstl:if>

	<acme:form-return code="employer.job.form.button.return" />
</acme:form>
