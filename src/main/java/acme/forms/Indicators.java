
package acme.forms;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Indicators implements Serializable {

	private static final long	serialVersionUID	= 1L;

	// List Attributes ----------------------------------------------------------
	Double						averageJobsPerEmployer;
	Double						averageApplicationsPerEmployer;
	Double						averageApplicationsPerWorker;

	// Chart Attributes ---------------------------------------------------------

	//List<String>				status;

	// Ratio of applications grouped by status
	Double						ratioAcceptedApplications;
	Double						ratioPendingApplications;
	Double						ratioRejectedApplications;

	// Ratio of jobs grouped by status
	Double						ratioPublishedJobs;
	Double						ratioDraftJobs;

}
