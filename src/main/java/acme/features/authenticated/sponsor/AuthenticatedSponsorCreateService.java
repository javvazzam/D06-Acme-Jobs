
package acme.features.authenticated.sponsor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Sponsor;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.entities.UserAccount;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedSponsorCreateService implements AbstractCreateService<Authenticated, Sponsor> {

	@Autowired
	private AuthenticatedSponsorRepository repository;


	@Override
	public boolean authorise(final Request<Sponsor> request) {
		assert request != null;

		Sponsor result;
		Principal principal;
		int userAccountId;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();

		result = this.repository.findOneSponsorByUserAccountId(userAccountId);

		return result == null;
	}

	@Override
	public void bind(final Request<Sponsor> request, final Sponsor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Sponsor> request, final Sponsor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "organization", "creditCard");

	}

	@Override
	public Sponsor instantiate(final Request<Sponsor> request) {
		assert request != null;

		Sponsor result;
		Principal principal;
		int userAccountId;
		UserAccount userAccount;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		userAccount = this.repository.findOneUserAccountById(userAccountId);

		result = new Sponsor();
		result.setUserAccount(userAccount);

		return result;
	}

	@Override
	public void validate(final Request<Sponsor> request, final Sponsor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		//errors.state(request, withOutSpaces, "organization", "authenticated.sponsor.organization");
		/*
		 * String organization = entity.getOrganization();
		 * String[] organizationArray = organization.split(" ");
		 *
		 * Customization customisation = this.repository.findCustomization();
		 *
		 * String spamWords = customisation.getSpam();
		 * String[] spamArray = spamWords.split(",");
		 * Double threshold = customisation.getThreshold();
		 *
		 * List<String> spamList = IntStream.range(0, spamArray.length).boxed().map(x -> spamArray[x].trim()).collect(Collectors.toList());
		 *
		 * Integer numSpamOrganization = (int) IntStream.range(0, organizationArray.length).boxed().map(x -> organizationArray[x].trim()).filter(i -> spamList.contains(i)).count();
		 *
		 * if (organizationArray.length != 0) {
		 * boolean isFreeOfSpamOrganization = 100 * numSpamOrganization / organizationArray.length < threshold;
		 * errors.state(request, isFreeOfSpamOrganization, "organization", "authenticated.sponsor.spamWords");
		 * }
		 */
	}

	@Override
	public void create(final Request<Sponsor> request, final Sponsor entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

	@Override
	public void onSuccess(final Request<Sponsor> request, final Response<Sponsor> response) {

		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}

	}

}
