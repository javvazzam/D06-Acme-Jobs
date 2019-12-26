/*
 * ConsumerAcmeRequestRepository.java
 *
 * Copyright (c) 2019 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.thread;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.customization.Customization;
import acme.entities.participants.Participant;
import acme.entities.threads.Thread;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedThreadRepository extends AbstractRepository {

	@Query("select t from Thread t where t.id = ?1")
	Thread findOneThreadById(int id);

	@Query("select p.user from Participant p where p.thread.id = ?1")
	List<Authenticated> findManyAuthenticatedByThreadId(int id);

	/*
	 * @Query("select t from Thread t where t.deadline >= current_date()")
	 * Collection<Thread> findManyAll();
	 */
	@Query("select p from Participant p where p.user.id = ?1")
	Collection<Participant> findManyByUserId(int id);

	@Query("select t from Thread t where exists (select p from Participant p where p.user.id = ?1 and p.thread.id=t.id)")
	Collection<Thread> findManyThreadsInParticipantByUserId(int id);
	//select t from Thread t where exists (select p from Participant p where p.user.id = 8 and p.thread.id=t.id);

	@Query("select a from Authenticated a where a.userAccount.id = ?1")
	Authenticated findOneAuthenticatedByUserAccountId(int id);

	@Query("select c from Customization c")
	Customization findCustomization();
}
