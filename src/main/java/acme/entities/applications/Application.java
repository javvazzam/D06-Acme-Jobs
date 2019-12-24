
package acme.entities.applications;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "status asc"), @Index(columnList = "moment asc")
})
public class Application extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	// regex EEEE-JJJJ:WWWW -> where 'E' is the employer, 'J' is the job and 'W' the worker
	@NotBlank
	@Length(min = 5, max = 15)
	//@Pattern(regexp = "^[a-zA-Z]{4}-[a-zA-Z]{4}([:][a-zA-Z]{4})?$")		// this is optional
	@Column(unique = true)
	private String				reference;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	private Date				moment;

	@NotBlank
	@Pattern(regexp = "^(pending)?(accepted)?(rejected)?$")
	private String				status;

	private String				justification;

	@NotBlank
	private String				statement;

	@NotBlank
	private String				skills;

	@NotBlank
	private String				qualifications;

	// Relationships-----------------------------------------------------

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Worker				worker;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Job					job;

}
