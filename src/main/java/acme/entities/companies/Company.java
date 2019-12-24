
package acme.entities.companies;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = {
	@Index(columnList = "stars"), @Index(columnList = "sector")
})
public class Company extends DomainEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				name;

	@NotNull
	private Boolean				incorporated;

	@NotBlank
	private String				sector;

	@NotBlank
	private String				ceo;

	@NotBlank
	private String				activities;

	@URL
	@NotBlank
	private String				url;

	// Example of regular exp: +999 (9999) 99999999
	@NotBlank
	@Pattern(regexp = "^(\\+([1-9]{1}[0-9]{0,2})\\s)?(\\(([1-9]{1}[0-9]{0,3}|0)\\)\\s)?([0-9]{6,10})$")
	private String				phone;

	@NotBlank
	@Email
	private String				email;

	@Range(min = 0, max = 5, message = "The stars must be in range 0 to 5")
	@Digits(integer = 1, fraction = 1, message = "Stars must contain only one integer and one decimal number")
	private Double				stars;

}
