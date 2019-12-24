
package acme.features.sponsor.commercialBanner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.commercialBanners.CommercialBanner;
import acme.entities.customization.Customization;
import acme.entities.roles.Sponsor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class SponsorCommercialBannerUpdateService implements AbstractUpdateService<Sponsor, CommercialBanner> {

	@Autowired
	private SponsorCommercialBannerRepository repository;


	@Override
	public boolean authorise(final Request<CommercialBanner> request) {

		assert request != null;

		boolean result;
		int commercialBannerId;
		CommercialBanner commercialBanner;
		Sponsor sponsor;
		Principal principal;

		commercialBannerId = request.getModel().getInteger("id");
		commercialBanner = this.repository.findOneCommercialBannerById(commercialBannerId);
		sponsor = commercialBanner.getSponsor();
		principal = request.getPrincipal();
		result = sponsor.getUserAccount().getId() == principal.getAccountId();

		return result;
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
	public CommercialBanner findOne(final Request<CommercialBanner> request) {
		assert request != null;

		CommercialBanner result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneCommercialBannerById(id);

		return result;
	}

	@Override
	public void validate(final Request<CommercialBanner> request, final CommercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Sponsor sponsor;
		Principal principal;
		int userAccountId;
		String creditCard;

		// primero vamos a encontrar el Sponsor y vamos  agregarlo a dicho CommercialBanner
		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		sponsor = this.repository.findOneSponsorByUserAccountId(userAccountId);

		// ahora vamos a extraer su tarjeta de crédito e insertarla en la nueva propiedad
		creditCard = sponsor.getCreditCard();
		// el error se indica en el atributo de la creditCard, diciendo que debe de añadir una tarjeta de crédito a su perfil
		errors.state(request, creditCard != null, "creditCard", "sponsor.CommercialBanner.creditCardNotRegistered");

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
			errors.state(request, isFreeOfSpamSlogan, "slogan", "sponsor.CommercialBanner.spamWords");
		}
	}

	@Override
	public void update(final Request<CommercialBanner> request, final CommercialBanner entity) {
		assert request != null;
		assert entity != null;

		Sponsor sponsor;
		Principal principal;
		int userAccountId;

		// primero vamos a encontrar el Sponsor
		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		sponsor = this.repository.findOneSponsorByUserAccountId(userAccountId);

		// ahora inicializamos sus propiedades
		entity.setSponsor(sponsor);

		this.repository.save(entity);

	}

}
