
package acme.features.administrator.commercialBanner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.commercialBanners.CommercialBanner;
import acme.entities.customization.Customization;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorCommercialBannerCreateService implements AbstractCreateService<Administrator, CommercialBanner> {

	@Autowired
	AdministratorCommercialBannerRepository repository;


	@Override
	public boolean authorise(final Request<CommercialBanner> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<CommercialBanner> request, final CommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<CommercialBanner> request, final CommercialBanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "picture", "slogan", "url", "creditCard");
	}

	@Override
	public CommercialBanner instantiate(final Request<CommercialBanner> request) {
		CommercialBanner result;
		result = new CommercialBanner();
		return result;
	}

	@Override
	public void validate(final Request<CommercialBanner> request, final CommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		String slogan = entity.getSlogan();
		String[] sloganArray = slogan.split(" ");

		Customization customisation = this.repository.findCustomization();

		String spamWords = customisation.getSpam();
		String[] spamArray = spamWords.split(",");
		Double threshold = customisation.getThreshold();

		List<String> spamList = IntStream.range(0, spamArray.length).boxed().map(x -> spamArray[x].trim()).collect(Collectors.toList());

		Integer numSpamSlogan = (int) IntStream.range(0, sloganArray.length).boxed().map(x -> sloganArray[x].trim()).filter(i -> spamList.contains(i)).count();
		boolean isFreeOfSpamSlogan = true;
		if (numSpamSlogan != 0) {
			isFreeOfSpamSlogan = 100 * numSpamSlogan / sloganArray.length < threshold;
			errors.state(request, isFreeOfSpamSlogan, "slogan", "administrator.CommercialBanner.spamWords");
		}
	}

	@Override
	public void create(final Request<CommercialBanner> request, final CommercialBanner entity) {
		this.repository.save(entity);
	}

}
