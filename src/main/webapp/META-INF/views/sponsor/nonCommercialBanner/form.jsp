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
	<acme:form-url code="sponsor.nonCommercialBanner.form.label.picture" path="picture"/>
	<acme:form-textbox code="sponsor.nonCommercialBanner.form.label.slogan" path="slogan"/>
	<acme:form-url code="sponsor.nonCommercialBanner.form.label.url" path="url"/>
	<acme:form-textbox code="sponsor.nonCommercialBanner.form.label.jingle" path="jingle"/>
	
	<acme:form-submit test="${command == 'create'}" code="sponsor.nonCommercialBanner.form.button.create"
		action="/sponsor/non-commercial-banner/create"/>
	<acme:form-submit test="${command == 'show' || 'update'}" code="sponsor.nonCommercialBanner.form.button.update"
		action="/sponsor/non-commercial-banner/update"/>
	<acme:form-submit test="${command == 'show' || 'delete'}" code="sponsor.nonCommercialBanner.form.button.delete"
		action="/sponsor/non-commercial-banner/delete"/>
	
  	<acme:form-return code="sponsor.nonCommercialBanner.form.button.return"/>
</acme:form>
