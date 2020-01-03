
package acme.forms;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {

	// Serialization identifier ---------------------------------------------

	private static final long	serialVersionUID	= 1L;

	//Attributes ------------------------------------------------------------

	//Numbers
	Integer						totalNumberOfAnnouncements;
	Integer						totalNumberOfCompanyRecords;
	Integer						totalNumberOfInvestorRecords;
	//Reward
	Double						minimumReward;
	Double						maximumReward;
	Double						averageReward;
	Double						standardDesviationReward;
	//Offer
	Double						minimumOffer;
	Double						maximumOffer;
	Double						averageOffer;
	Double						standardDesviationOffer;

	// Chart atts.
	List<String>				companySectors;
	List<Long>					numberCompanys; //nº companys per sector

	List<String>				investorSectors;
	List<Long>					numberInvestors; //nº investors per sector

	// List Attributes -----------------------------------------------------------
	Double						averageJobsPerEmployer;
	Double						averageApplicationsPerEmployer;
	Double						averageApplicationsPerWorker;

	// Chart Attributes ----------------------------------------------------------

	//List<String>				status;

	// Ratio of applications grouped by status -----------------------------------
	Double						ratioAcceptedApplications;
	Double						ratioPendingApplications;
	Double						ratioRejectedApplications;

	// Ratio of jobs grouped by status--------------------------------------------
	Double						ratioPublishedJobs;
	Double						ratioDraftJobs;

	// Application's time series--------------------------------------------------

	//Map<Integer, Date>			timeSeriesPendingApplication;
	List<Integer>				countListTimeSeriesPendingApplication;
	List<String>				dateListTimeSeriesPendingApplication;

	//Map<Integer, Date>			timeSeriesAcceptedApplication;
	List<Integer>				countListTimeSeriesAcceptedApplication;
	List<String>				dateListTimeSeriesAcceptedApplication;

	//Map<Integer, Date>			timeSeriesRejectedApplication;
	List<Integer>				countListTimeSeriesRejectedApplication;
	List<String>				dateListTimeSeriesRejectedApplication;

	// Derived attributes ------------------------------------------------------

	// Relationships -----------------------------------------------------------
}
