/*
 * AdministratorAuthorizedUpdateService.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.authorized;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Authorized;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Administrator;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorAuthorizedUpdateService implements AbstractUpdateService<Administrator, Authorized> {

	// Internal state ---------------------------------------------------------

	@Autowired
	private AdministratorAuthorizedRepository repository;


	// AbstractUpdateService<Administrator, Authorized> interface -----------------

	@Override
	public boolean authorise(final Request<Authorized> request) {
		assert request != null;

		return true;
	}

	@Override
	public void validate(final Request<Authorized> request, final Authorized entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		//Comprueba que status es "Accepted" o "Pending"
		Boolean statusCorrect = request.getModel().getAttribute("status").equals("Accepted") || request.getModel().getAttribute("status").equals("Pending");
		errors.state(request, statusCorrect, "status", "administrator.Authorized.statusNotCorrect");
	}

	@Override
	public void bind(final Request<Authorized> request, final Authorized entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Authorized> request, final Authorized entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		if (entity.isAccepted()) {
			model.setAttribute("status", "Accepted");
		} else {
			model.setAttribute("status", "Pending");
		}

		request.unbind(entity, model, "body");
	}

	@Override
	public Authorized findOne(final Request<Authorized> request) {
		assert request != null;

		Authorized result;
		int AuthorizedId;

		AuthorizedId = request.getModel().getInteger("id");
		result = this.repository.findOneAuthorizedById(AuthorizedId);

		return result;
	}

	@Override
	public void update(final Request<Authorized> request, final Authorized entity) {
		assert request != null;
		assert entity != null;

		if (request.getModel().getAttribute("status").equals("Accepted")) {
			entity.setAccepted(true);
		} else {
			entity.setAccepted(false);
		}

		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<Authorized> request, final Response<Authorized> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
