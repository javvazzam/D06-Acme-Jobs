
package acme.features.administrator.commercialBanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.commercialBanners.CommercialBanner;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorCommercialBannerShowService implements AbstractShowService<Administrator, CommercialBanner> {

	@Autowired
	AdministratorCommercialBannerRepository repository;


	@Override
	public boolean authorise(final Request<CommercialBanner> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<CommercialBanner> request, final CommercialBanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "picture", "slogan", "url", "creditCard");
	}

	@Override
	public CommercialBanner findOne(final Request<CommercialBanner> request) {
		assert request != null;
		CommercialBanner result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

}
