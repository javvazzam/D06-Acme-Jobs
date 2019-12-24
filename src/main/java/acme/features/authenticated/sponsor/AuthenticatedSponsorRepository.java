
package acme.features.authenticated.sponsor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.customization.Customization;
import acme.entities.roles.Sponsor;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedSponsorRepository extends AbstractRepository {

	@Query("select s from Sponsor s where s.userAccount.id = ?1")
	Sponsor findOneSponsorByUserAccountId(int id);

	@Query("select ua from UserAccount ua where ua.id = ?1")
	UserAccount findOneUserAccountById(int id);

	@Query("select c from Customization c")
	Customization findCustomization();
}
