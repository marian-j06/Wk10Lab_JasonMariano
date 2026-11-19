package application;

import java.sql.SQLException;
import java.time.LocalDate;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {

		EmploymentFormDao employmentFormDao = new EmploymentFormDao();

		primaryStage.setTitle("Employment Application");

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Text sceneTitle = new Text("Employment Application");
		sceneTitle.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
		grid.add(sceneTitle, 0, 0, 2, 1);

		// Personal Information 
		Text personalHeader = new Text("Personal Information");
		personalHeader.setFont(Font.font("Tahoma", FontWeight.BOLD, 14));
		grid.add(personalHeader, 0, 1, 2, 1);

		Label fullNameLabel = new Label("Full Name:");
		grid.add(fullNameLabel, 0, 2);
		TextField fullNameTextField = new TextField();
		grid.add(fullNameTextField, 1, 2);

		Label addressLabel = new Label("Current Address:");
		grid.add(addressLabel, 0, 3);
		TextField addressTextField = new TextField();
		grid.add(addressTextField, 1, 3);

		Label contactLabel = new Label("Contact Number:");
		grid.add(contactLabel, 0, 4);
		TextField contactTextField = new TextField();
		grid.add(contactTextField, 1, 4);

		Label emailLabel = new Label("Email Address:");
		grid.add(emailLabel, 0, 5);
		TextField emailTextField = new TextField();
		grid.add(emailTextField, 1, 5);

		Label educationLabel = new Label("Highest Educational Attainment:");
		grid.add(educationLabel, 0, 6);
		TextField educationTextField = new TextField();
		grid.add(educationTextField, 1, 6);

		// Gender Radio Button
		Label genderLabel = new Label("Gender:");
		grid.add(genderLabel, 0, 7);
		ToggleGroup genderGroup = new ToggleGroup();
		RadioButton maleRadio = new RadioButton("Male");
		RadioButton femaleRadio = new RadioButton("Female");
		RadioButton otherRadio = new RadioButton("Other");
		maleRadio.setToggleGroup(genderGroup);
		femaleRadio.setToggleGroup(genderGroup);
		otherRadio.setToggleGroup(genderGroup);
		HBox genderBox = new HBox(10);
		genderBox.getChildren().addAll(maleRadio, femaleRadio, otherRadio);
		grid.add(genderBox, 1, 7);

		// Employment Eligibility 
		Text eligibilityHeader = new Text("Employment Eligibility");
		eligibilityHeader.setFont(Font.font("Tahoma", FontWeight.BOLD, 14));
		grid.add(eligibilityHeader, 0, 8, 2, 1);

		Label dateLabel = new Label("Date Available:");
		grid.add(dateLabel, 0, 9);
		DatePicker datePicker = new DatePicker();
		grid.add(datePicker, 1, 9);

		Label positionLabel = new Label("Desired Position:");
		grid.add(positionLabel, 0, 10);
		TextField positionTextField = new TextField();
		grid.add(positionTextField, 1, 10);

		Label salaryLabel = new Label("Desired Salary:");
		grid.add(salaryLabel, 0, 11);
		TextField salaryTextField = new TextField();
		grid.add(salaryTextField, 1, 11);

		// Legally authorized radio button
		Label authorizedLabel = new Label("Legally authorized to work in the country?");
		grid.add(authorizedLabel, 0, 12);
		ToggleGroup authorizedGroup = new ToggleGroup();
		RadioButton authorizedYesRadio = new RadioButton("Yes");
		RadioButton authorizedNoRadio = new RadioButton("No");
		authorizedYesRadio.setToggleGroup(authorizedGroup);
		authorizedNoRadio.setToggleGroup(authorizedGroup);
		HBox authorizedBox = new HBox(10);
		authorizedBox.getChildren().addAll(authorizedYesRadio, authorizedNoRadio);
		grid.add(authorizedBox, 1, 12);

		// Relatives working radio button
		Label relativesLabel = new Label("Relatives working for our company?");
		grid.add(relativesLabel, 0, 13);
		ToggleGroup relativesGroup = new ToggleGroup();
		RadioButton relativesYesRadio = new RadioButton("Yes");
		RadioButton relativesNoRadio = new RadioButton("No");
		relativesYesRadio.setToggleGroup(relativesGroup);
		relativesNoRadio.setToggleGroup(relativesGroup);
		HBox relativesBox = new HBox(10);
		relativesBox.getChildren().addAll(relativesYesRadio, relativesNoRadio);
		grid.add(relativesBox, 1, 13);

		Label explainLabel = new Label("If yes, please explain further:");
		grid.add(explainLabel, 0, 14);
		TextField explainTextField = new TextField();
		grid.add(explainTextField, 1, 14);

		// Signature
		Text agreementText = new Text("By submitting this application, you agree to adhere to company policies\n"
				+ "and provide accurate information throughout the employment process.");
		grid.add(agreementText, 0, 15, 2, 1);

		Label signatureLabel = new Label("Applicant's Signature:");
		grid.add(signatureLabel, 0, 16);
		
		//Submit Button 
		Button submitButton = new Button("Sign & Submit");
		grid.add(submitButton, 1, 16);

		submitButton.setOnAction(actionEvent -> {
			// Read all the values from the form
			String fullName = fullNameTextField.getText().trim();
			String address = addressTextField.getText().trim();
			String contact = contactTextField.getText().trim();
			String email = emailTextField.getText().trim();
			String education = educationTextField.getText().trim();
			LocalDate dateAvailable = datePicker.getValue();
			String position = positionTextField.getText().trim();
			String salary = salaryTextField.getText().trim();
			String explanation = explainTextField.getText().trim();

			// Check that every required field is filled in and every radio question has an answer
			if (StringPool.BLANK.equals(fullName) || StringPool.BLANK.equals(address)
					|| StringPool.BLANK.equals(contact) || StringPool.BLANK.equals(email)
					|| StringPool.BLANK.equals(education) || StringPool.BLANK.equals(position)
					|| StringPool.BLANK.equals(salary)
					|| genderGroup.getSelectedToggle() == null || dateAvailable == null
					|| authorizedGroup.getSelectedToggle() == null
					|| relativesGroup.getSelectedToggle() == null) {
				this.alert("Error", "Please complete fields!", AlertType.ERROR);
			} else if (relativesYesRadio.isSelected() && StringPool.BLANK.equals(explanation)) {
				this.alert("Error", "Please explain your relatives working for the company.", AlertType.ERROR);
			} else {
				
				// Get the text of the selected gender radio button
				RadioButton selectedGender = (RadioButton) genderGroup.getSelectedToggle();

				// Build the EmploymentForm object
				EmploymentForm form = new EmploymentForm();
				form.setFullName(fullName);
				form.setCurrentAddress(address);
				form.setContactNumber(contact);
				form.setEmailAddress(email);
				form.setHighestEducation(education);
				form.setGender(selectedGender.getText());
				form.setDateAvailable(dateAvailable);
				form.setDesiredPosition(position);
				form.setDesiredSalary(salary);
				form.setAuthorizedToWork(authorizedYesRadio.isSelected());
				form.setHasRelatives(relativesYesRadio.isSelected());
				form.setRelativesExplanation(explanation);
				form.setSigned(true);

				try {
					employmentFormDao.saveEmploymentForm(form);
					this.alert("Submit", "Application submitted!", AlertType.INFORMATION);
				} catch (SQLException ex) {
					System.out.println(ex.getMessage());
				}
			}
		});

		Scene scene = new Scene(grid);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void alert(String title, String message, AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);

		alert.showAndWait();
	}

	public static void main(String[] args) {
		launch(args);
	}
}