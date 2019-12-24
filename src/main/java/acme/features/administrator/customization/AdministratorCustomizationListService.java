
package acme.features.administrator.customization;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customization.Customization;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorCustomizationListService implements AbstractListService<Administrator, Customization> {

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
	public Collection<Customization> findMany(final Request<Customization> request) {
		assert request != null;
		Collection<Customization> result;
		result = this.repository.findMany();
		return result;
	}

}
