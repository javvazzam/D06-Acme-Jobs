
package acme.entities.jobs;

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

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.roles.Employer;
import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

@Table(indexes = {
	@Index(columnList = "deadline"), @Index(columnList = "finalMode")
})

public class Job extends DomainEntity {

	// Serialisation identifier
	private static final long	serialVersionUID	= 1L;
	//Attributes
	//“EEEE-JJJJ”, where “EEEE” is a four-letter string that helps recognise the employer and “JJJJ” is a four-letter string that helps recognise the job.
	@Column(unique = true)
	@Length(min = 5, max = 10)
	@NotBlank
	//@Pattern(regexp = "^([a-zA-Z]{4}[-][a-zA-Z]{4})?$")
	private String				referenceNumber;

	@NotBlank
	private String				title;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date				deadline;

	@NotNull
	@Valid
	private Money				salary;

	@URL
	private String				moreInfo;

	private String				description;

	@NotNull
	private boolean				finalMode;

	//Derived attributes

	//Relationships

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Employer			employer;

	/*
	 * @Valid
	 *
	 * @OneToMany(mappedBy = "job")
	 * private Collection<Audit> audits;
	 */
}
