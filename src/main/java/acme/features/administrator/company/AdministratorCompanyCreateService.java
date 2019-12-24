
package acme.features.administrator.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.companies.Company;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorCompanyCreateService implements AbstractCreateService<Administrator, Company> {

	// Internal state ----------------------------------------------

	@Autowired
	private AdministratorCompanyRepository repository;


	//AbstractUpdateService<Administrator,Company> interface -------

	@Override
	public boolean authorise(final Request<Company> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Company> request, final Company entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Company> request, final Company entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "name", "incorporated", "sector", "ceo", "activities", "url", "phone", "email", "stars");

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("incorporated", "false");
		} else {
			request.transfer(model, "incorporated");
		}
	}

	@Override
	public Company instantiate(final Request<Company> request) {
		Company result;

		result = new Company();

		return result;
	}

	@Override
	public void validate(final Request<Company> request, final Company entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void create(final Request<Company> request, final Company entity) {
		// BUSCA QUE EL CHECKBOX SE HAYA SELECCIONADO O NO
		Boolean isIncorporated;
		isIncorporated = request.getModel().getBoolean("incorporated");
		if (isIncorporated) {
			String cadena = entity.getName();
			cadena += " Inc";
			entity.setName(cadena);
		} else {
			String cadena = entity.getName();
			cadena += " LLC";
			entity.setName(cadena);
		}

		this.repository.save(entity);
	}

}
