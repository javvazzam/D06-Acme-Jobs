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

<acme:form readonly="true">
	<acme:form-textbox code="anonymous.top_company_records.form.label.company_name" path="company_name"/>
	<acme:form-textbox code="anonymous.top_company_records.form.label.sector" path="sector"/>
	<acme:form-textbox code="anonymous.top_company_records.form.label.ceo_name" path="ceo_name"/>
	<acme:form-textarea code="anonymous.top_company_records.form.label.activities" path="activities"/>
	<acme:form-url code="anonymous.top_company_records.form.label.website" path="website"/>
	<acme:form-textbox code="anonymous.top_company_records.form.label.contact_phone" path="contact_phone"/>
	<acme:form-textbox code="anonymous.top_company_records.form.label.contact_email" path="contact_email"/>
	<acme:form-double code="anonymous.top_company_records.form.label.stars" path="stars"/>
	
  	<acme:form-return code="anonymous.top_company_records.form.button.return"/>
</acme:form>
