package application;

import java.time.LocalDate;

public class EmploymentForm {

	/*
	 * Class to model the Employment Application entity.
	 * One field for each input on the form.
	 */

	// Personal Information
	private String fullName;
	private String currentAddress;
	private String contactNumber;
	private String emailAddress;
	private String highestEducation;
	private String gender;

	// Employment Eligibility
	private LocalDate dateAvailable;
	private String desiredPosition;
	private String desiredSalary;
	private boolean authorizedToWork;
	private boolean hasRelatives;
	private String relativesExplanation;

	private boolean signed;

	public String getFullName() {
		return fullName;
	}

	public String getCurrentAddress() {
		return currentAddress;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public String getHighestEducation() {
		return highestEducation;
	}

	public String getGender() {
		return gender;
	}

	public LocalDate getDateAvailable() {
		return dateAvailable;
	}

	public String getDesiredPosition() {
		return desiredPosition;
	}

	public String getDesiredSalary() {
		return desiredSalary;
	}

	public boolean isAuthorizedToWork() {
		return authorizedToWork;
	}

	public boolean isHasRelatives() {
		return hasRelatives;
	}

	public String getRelativesExplanation() {
		return relativesExplanation;
	}

	public boolean isSigned() {
		return signed;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public void setHighestEducation(String highestEducation) {
		this.highestEducation = highestEducation;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setDateAvailable(LocalDate dateAvailable) {
		this.dateAvailable = dateAvailable;
	}

	public void setDesiredPosition(String desiredPosition) {
		this.desiredPosition = desiredPosition;
	}

	public void setDesiredSalary(String desiredSalary) {
		this.desiredSalary = desiredSalary;
	}

	public void setAuthorizedToWork(boolean authorizedToWork) {
		this.authorizedToWork = authorizedToWork;
	}

	public void setHasRelatives(boolean hasRelatives) {
		this.hasRelatives = hasRelatives;
	}

	public void setRelativesExplanation(String relativesExplanation) {
		this.relativesExplanation = relativesExplanation;
	}

	public void setSigned(boolean signed) {
		this.signed = signed;
	}

}