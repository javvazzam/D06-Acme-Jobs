
package acme.features.administrator.company;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.companies.Company;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorCompanyListService implements AbstractListService<Administrator, Company> {

	@Autowired
	AdministratorCompanyRepository repository;


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
		request.unbind(entity, model, "name", "sector", "url");
	}

	@Override
	public Collection<Company> findMany(final Request<Company> request) {
		assert request != null;
		Collection<Company> result;
		result = this.repository.findManyCompanies();
		return result;
	}

}
