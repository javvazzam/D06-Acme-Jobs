
package acme.features.sponsor.commercialBanner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.commercialBanners.CommercialBanner;
import acme.entities.customization.Customization;
import acme.entities.roles.Sponsor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SponsorCommercialBannerRepository extends AbstractRepository {

	@Query("select cb from CommercialBanner cb where cb.id = ?1")
	CommercialBanner findOneCommercialBannerById(int id);

	@Query("select cb from CommercialBanner cb where cb.sponsor.id = ?1")
	Collection<CommercialBanner> findManyBySponsorId(int id);

	@Query("select s from Sponsor s where s.userAccount.id = ?1")
	Sponsor findOneSponsorByUserAccountId(int id);

	@Query("select c from Customization c")
	Customization findCustomization();
}
