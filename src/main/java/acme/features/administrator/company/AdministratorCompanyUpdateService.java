
package acme.features.administrator.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.companies.Company;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorCompanyUpdateService implements AbstractUpdateService<Administrator, Company> {

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
	}

	@Override
	public void validate(final Request<Company> request, final Company entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public Company findOne(final Request<Company> request) {
		assert request != null;

		Company result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneCompanyById(id);

		return result;
	}

	@Override
	public void update(final Request<Company> request, final Company entity) {
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
