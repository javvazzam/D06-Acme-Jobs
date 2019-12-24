
package acme.features.administrator.customization;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.customization.Customization;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorCustomizationRepository extends AbstractRepository {

	@Query("select s from Customization s")
	Collection<Customization> findMany();

	@Query("select i from Customization i where i.id = ?1")
	Customization findOneById(int id);

}
