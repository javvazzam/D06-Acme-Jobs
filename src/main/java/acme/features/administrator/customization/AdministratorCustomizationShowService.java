
package acme.features.administrator.customization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customization.Customization;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorCustomizationShowService implements AbstractShowService<Administrator, Customization> {

	@Autowired
	AdministratorCustomizationRepository repository;


	@Override
	public boolean authorise(final Request<Customization> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Customization> request, final Customization entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "spam", "threshold");
	}

	@Override
	public Customization findOne(final Request<Customization> request) {
		assert request != null;

		Customization result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

}
