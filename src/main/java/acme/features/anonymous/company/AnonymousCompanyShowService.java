
package acme.features.anonymous.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.companies.Company;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractShowService;

@Service
public class AnonymousCompanyShowService implements AbstractShowService<Anonymous, Company> {

	@Autowired
	private AnonymousCompanyRepository repository;


	@Override
	public boolean authorise(final Request<Company> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Company> request, final Company entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "name", "sector", "ceo", "activities", "url", "phone", "email", "stars");
	}

	@Override
	public Company findOne(final Request<Company> request) {
		assert request != null;

		Company result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

}
