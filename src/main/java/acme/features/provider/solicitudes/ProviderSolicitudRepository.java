
package acme.features.provider.solicitudes;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.solicitudes.Solicitud;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ProviderSolicitudRepository extends AbstractRepository {

	@Query("select ac from Solicitud ac where ac.id = ?1")
	Solicitud findOneById(int id);

	@Query("select ac from Solicitud ac where ac.deadline >= current_date()")
	Collection<Solicitud> findManyAll();

	@Query("select t from Solicitud t where t.ticker = ?1")
	Solicitud findOneByTicker(String ticker);

}
