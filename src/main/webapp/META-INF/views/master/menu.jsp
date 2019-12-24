<%--
- menu.jsp
-
- Copyright (c) 2019 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java" import="acme.framework.helpers.PrincipalHelper,acme.entities.roles.Provider,acme.entities.roles.Consumer"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:menu-bar code="master.menu.home">
	<acme:menu-left>
		<!-- 
		<acme:menu-option code="master.menu.anonymous" access="isAnonymous()">
			<acme:menu-suboption code="master.menu.anonymous.favourite-link" action="http://www.example.com/"/>
			<acme:menu-suboption code="master.menu.anonymous.juanogtir-favourite-link" action="https://www.marca.com/"/>
			<acme:menu-suboption code="master.menu.anonymous.edubotdom-favourite-link" action="https://www.xda-developers.com/"/>
			<acme:menu-suboption code="master.menu.anonymous.javvazzam-favourite-link" action="https://www.atptour.com/"/>
			<acme:menu-suboption code="master.menu.anonymous.danaremar-favourite-link" action="https://www.minecraft.net/"/>
			<acme:menu-suboption code="master.menu.anonymous.seraguoro-favourite-link" action="https://www.youtube.com/watch?v=73GI6uS_4Ic"/>
		</acme:menu-option>
		 -->
		<!--Anonymous's Selector-->
		<acme:menu-option code="master.menu.selector.anonymous" access="isAnonymous()">
			<!-- Investor records -->
			<acme:menu-suboption code="master.menu.anonymous.investor" action="/anonymous/investor/list" access="isAnonymous()" />
			<acme:menu-suboption code="master.menu.anonymous.top_investors" action="/anonymous/top_investors/list" access="isAnonymous()" />
			<!-- Announcements -->
			<acme:menu-suboption code="master.menu.anonymous.announcements" action="/anonymous/announcement/list" access="isAnonymous()" />
			<!--Company records-->
			<acme:menu-suboption code="master.menu.anonymous.companies" action="/anonymous/company/list" access="isAnonymous()" />
			<!--Top company records-->
			<acme:menu-suboption code="master.menu.anonymous.top_companies" action="/anonymous/top_company/list" access="isAnonymous()" />
		</acme:menu-option>

		<!--Authenticated's Selector-->
		<acme:menu-option code="master.menu.selector.authenticated" access="isAuthenticated()">
			<!-- Requests & offers -->
			<acme:menu-suboption code="master.menu.authenticated.solicitud" action="/authenticated/solicitud/list" access="isAuthenticated()" />
			<acme:menu-suboption code="master.menu.authenticated.offer" action="/authenticated/offer/list" access="isAuthenticated()" />
			<!-- Investor records -->
			<acme:menu-suboption code="master.menu.authenticated.investor" action="/authenticated/investor/list" access="isAuthenticated()" />
			<!--Challenge-->
			<acme:menu-suboption code="master.menu.authenticated.challenge" action="/authenticated/challenge/list" access="isAuthenticated()" />
			<!-- Announcements -->
			<acme:menu-suboption code="master.menu.authenticated.announcements" action="/authenticated/announcement/list"
				access="isAuthenticated()" />
			<!--Company records-->
			<acme:menu-suboption code="master.menu.authenticated.companies" action="/authenticated/company/list" access="isAuthenticated()" />
			<!-- Jobs -->
			<acme:menu-suboption code="master.menu.authenticated.jobs" action="/authenticated/job/list" access="isAuthenticated()" />
			<!--Threads-->
			<acme:menu-suboption code="master.menu.authenticated.threads.list_mine" action="/authenticated/thread/list_mine"
				access="isAuthenticated()" />
			<acme:menu-suboption code="master.menu.authenticated.threads.create" action="/authenticated/thread/create"
				access="isAuthenticated()" />

		</acme:menu-option>

		<!--Administrator's Selector-->
		<acme:menu-option code="master.menu.selector.administrator" access="hasRole('Administrator')">
			<!-- Dashboard -->
			<acme:menu-suboption code="master.menu.administrator.dashboard" action="/administrator/dashboard/show"
				access="hasRole('Administrator')" />
			<!-- Announcements -->
			<acme:menu-suboption code="master.menu.administrator.list_announcements" action="/administrator/announcement/list"
				access="hasRole('Administrator')" />
			<acme:menu-suboption code="master.menu.administrator.create_announcements" action="/administrator/announcement/create"
				access="hasRole('Administrator')" />
			<!-- Spam -->
			<acme:menu-suboption code="master.menu.administrator.customization" action="/administrator/customization/list"
				access="hasRole('Administrator')" />
			<!--Challenge-->
			<acme:menu-suboption code="master.menu.administrator.list_challenge" action="/administrator/challenge/list"
				access="hasRole('Administrator')" />
			<acme:menu-suboption code="master.menu.administrator.create_challenge" action="/administrator/challenge/create"
				access="hasRole('Administrator')" />
			<!--Company records-->
			<acme:menu-suboption code="master.menu.administrator.list_companies" action="/administrator/company/list"
				access="hasRole('Administrator')" />
			<acme:menu-suboption code="master.menu.administrator.create_companies" action="/administrator/company/create"
				access="hasRole('Administrator')" />
			<!-- Investor records -->
			<acme:menu-suboption code="master.menu.administrator.list_investor" action="/administrator/investor/list"
				access="hasRole('Administrator')" />
			<acme:menu-suboption code="master.menu.administrator.create_investor" action="/administrator/investor/create"
				access="hasRole('Administrator')" />
			<!-- Commercial Banner -->
			<acme:menu-suboption code="master.menu.administrator.list_commercialBanner" action="/administrator/commercial-banner/list"
				access="hasRole('Administrator')" />
			<acme:menu-suboption code="master.menu.administrator.create_commercialBanner" action="/administrator/commercial-banner/create"
				access="hasRole('Administrator')" />
			<!-- Non-Commercial Banner -->
			<acme:menu-suboption code="master.menu.administrator.list_nonCommercialBanner" action="/administrator/non-commercial-banner/list"
				access="hasRole('Administrator')" />
			<acme:menu-suboption code="master.menu.administrator.create_nonCommercialBanner"
				action="/administrator/non-commercial-banner/create" access="hasRole('Administrator')" />
			<!-- Auditors' request -->
			<acme:menu-suboption code="master.menu.administrator.list_auditor_request" action="/administrator/auditor/list"
				access="hasRole('Administrator')" />

		</acme:menu-option>

		<acme:menu-option code="master.menu.administrator" access="hasRole('Administrator')">
			<acme:menu-suboption code="master.menu.administrator.user-accounts" action="/administrator/user-account/list" />
			<acme:menu-separator />
			<acme:menu-suboption code="master.menu.administrator.shutdown" action="/master/shutdown" />
		</acme:menu-option>

		<!--Consumer's Selector-->
		<acme:menu-option code="master.menu.selector.consumer" access="hasRole('Consumer')">
			<acme:menu-suboption code="master.menu.consumer.create_offer" action="/consumer/offer/create" access="hasRole('Consumer')" />
		</acme:menu-option>

		<!--Provider's Selector-->
		<acme:menu-option code="master.menu.selector.provider" access="hasRole('Provider')">
			<!--<acme:menu-suboption code="master.menu.provider.favourite-link" action="http://www.example.com/" />
			<acme:menu-suboption code="master.menu.provider.list_solicitud" action="/provider/solicitud/list" />-->
			<acme:menu-suboption code="master.menu.provider.create_solicitud" action="/provider/solicitud/create" />
		</acme:menu-option>

		<!--Employer's Selector-->
		<acme:menu-option code="master.menu.selector.employer" access="hasRole('Employer')">
			<acme:menu-suboption code="master.menu.employer.job.list_mine" action="/employer/job/list_mine" />
			<acme:menu-suboption code="master.menu.employer.application.list_mine" action="/employer/application/list_mine" />
			<acme:menu-suboption code="master.menu.employer.application.list_mine_reference"
				action="/employer/application/list_mine_reference" />
			<acme:menu-suboption code="master.menu.employer.application.list_mine_status" action="/employer/application/list_mine_status" />
			<acme:menu-suboption code="master.menu.employer.application.list_mine_moment" action="/employer/application/list_mine_moment" />
			<acme:menu-suboption code="master.menu.employer.job.create_job" action="/employer/job/create" />
		</acme:menu-option>

		<!--Worker's Selector-->
		<acme:menu-option code="master.menu.selector.worker" access="hasRole('Worker')">
			<acme:menu-suboption code="master.menu.worker.application.list_mine" action="/worker/application/list_mine" />
			<acme:menu-suboption code="master.menu.worker.job.list" action="/worker/job/list" />
		</acme:menu-option>

		<!--Worker's Selector-->
		<acme:menu-option code="master.menu.selector.sponsor" access="hasRole('Sponsor')">
			<acme:menu-suboption code="master.menu.sponsor.comercial_banner.create" action="/sponsor/commercial-banner/create" />
			<acme:menu-suboption code="master.menu.sponsor.comercial_banner.list_mine" action="/sponsor/commercial-banner/list_mine" />
			<acme:menu-suboption code="master.menu.sponsor.non_comercial_banner.create" action="/sponsor/non-commercial-banner/create" />
			<acme:menu-suboption code="master.menu.sponsor.non_comercial_banner.list_mine" action="/sponsor/non-commercial-banner/list_mine" />
		</acme:menu-option>

		<!--Auditor's Selector-->
		<acme:menu-option code="master.menu.selector.auditor" access="hasRole('Auditor')">
			<acme:menu-suboption code="master.menu.auditor.audit.list_mine" action="/auditor/job/list_mine" />
			<acme:menu-suboption code="master.menu.auditor.audit.list_not_mine" action="/auditor/job/list_not_mine" />
		</acme:menu-option>

	</acme:menu-left>

	<acme:menu-right>
		<acme:menu-option code="master.menu.sign-up" action="/anonymous/user-account/create" access="isAnonymous()" />
		<acme:menu-option code="master.menu.sign-in" action="/master/sign-in" access="isAnonymous()" />

		<acme:menu-option code="master.menu.user-account" access="isAuthenticated()">
			<acme:menu-suboption code="master.menu.user-account.general-data" action="/authenticated/user-account/update" />
			<acme:menu-suboption code="master.menu.user-account.become-provider" action="/authenticated/provider/create"
				access="!hasRole('Provider')" />
			<acme:menu-suboption code="master.menu.user-account.provider" action="/authenticated/provider/update"
				access="hasRole('Provider')" />
			<acme:menu-suboption code="master.menu.user-account.become-consumer" action="/authenticated/consumer/create"
				access="!hasRole('Consumer')" />
			<acme:menu-suboption code="master.menu.user-account.consumer" action="/authenticated/consumer/update"
				access="hasRole('Consumer')" />
			<acme:menu-suboption code="master.menu.user-account.become-employer" action="/authenticated/employer/create"
				access="!hasRole('Employer')" />
			<acme:menu-suboption code="master.menu.user-account.employer" action="/authenticated/employer/update"
				access="hasRole('Employer')" />
			<acme:menu-suboption code="master.menu.user-account.become-worker" action="/authenticated/worker/create"
				access="!hasRole('Worker')" />
			<acme:menu-suboption code="master.menu.user-account.worker" action="/authenticated/worker/update" access="hasRole('Worker')" />
			<acme:menu-suboption code="master.menu.user-account.become-sponsor" action="/authenticated/sponsor/create"
				access="!hasRole('Sponsor')" />
			<acme:menu-suboption code="master.menu.user-account.sponsor" action="/authenticated/sponsor/update" access="hasRole('Sponsor')" />
			<acme:menu-suboption code="master.menu.user-account.become-auditor" action="/authenticated/auditor/create"
				access="!hasRole('Auditor')" />
			<acme:menu-suboption code="master.menu.user-account.auditor" action="/authenticated/auditor/update" access="hasRole('Auditor')" />

		</acme:menu-option>

		<acme:menu-option code="master.menu.sign-out" action="/master/sign-out" access="isAuthenticated()" />
	</acme:menu-right>
</acme:menu-bar>
