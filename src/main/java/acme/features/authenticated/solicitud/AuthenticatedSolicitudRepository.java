/*
 * AuthenticatedAcmeRequestRepository.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.solicitud;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.solicitudes.Solicitud;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedSolicitudRepository extends AbstractRepository {

	@Query("select ac from Solicitud ac where ac.id = ?1")
	Solicitud findOneById(int id);

	@Query("select ac from Solicitud ac where ac.deadline >= current_date()")
	Collection<Solicitud> findManyAll();

}
