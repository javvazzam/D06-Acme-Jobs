
package acme.features.administrator.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.companies.Company;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorCompanyShowService implements AbstractShowService<Administrator, Company> {

	@Autowired
	private AdministratorCompanyRepository repository;


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
		request.unbind(entity, model, "incorporated", "sector", "ceo", "activities", "url", "phone", "email", "stars"); // No usamos la propiedad "name" ya que se inicializa después para especificar el tipo de empresa

		String cadena = entity.getName();
		Integer len = cadena.length();
		String subcadena = cadena.substring(0, len - 4);
		model.setAttribute("name", subcadena);
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

}
