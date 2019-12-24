
package acme.features.administrator.company;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.companies.Company;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorCompanyRepository extends AbstractRepository {

	@Query("select a from Company a where a.id = ?1")
	Company findOneCompanyById(int id);

	@Query("select a from Company a")
	Collection<Company> findManyCompanies();

}
