
package acme.features.administrator.investor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.investors.Investor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorInvestorListService implements AbstractListService<Administrator, Investor> {

	// Internal state ---------------------------------------------------------

	@Autowired
	AdministratorInvestorRepository repository;


	@Override
	public boolean authorise(final Request<Investor> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Investor> request, final Investor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "sector", "statement", "stars");
	}

	@Override
	public Collection<Investor> findMany(final Request<Investor> request) {
		assert request != null;

		Collection<Investor> result;

		result = this.repository.findManyAll();
		return result;
	}
}
