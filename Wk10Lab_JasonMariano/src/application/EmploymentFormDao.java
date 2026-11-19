package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmploymentFormDao {

	/*
	 * Insert method - submits/writes the form values to the DB
	 * using a PreparedStatement.
	 */

	public int saveEmploymentForm(EmploymentForm employment) throws SQLException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = Database.getDBConnection();
			connection.setAutoCommit(false);

			String query = "INSERT INTO employment_form(full_name, current_address, contact_number, "
					+ "email_address, highest_education, gender, date_available, desired_position, "
					+ "desired_salary, authorized_to_work, has_relatives, relatives_explanation, is_signed) "
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			int counter = 1;
			statement.setString(counter++, employment.getFullName());
			statement.setString(counter++, employment.getCurrentAddress());
			statement.setString(counter++, employment.getContactNumber());
			statement.setString(counter++, employment.getEmailAddress());
			statement.setString(counter++, employment.getHighestEducation());
			statement.setString(counter++, employment.getGender());
			// Convert the LocalDate from the DatePicker into a SQL date
			statement.setDate(counter++, java.sql.Date.valueOf(employment.getDateAvailable()));
			statement.setString(counter++, employment.getDesiredPosition());
			statement.setString(counter++, employment.getDesiredSalary());
			statement.setBoolean(counter++, employment.isAuthorizedToWork());
			statement.setBoolean(counter++, employment.isHasRelatives());
			statement.setString(counter++, employment.getRelativesExplanation());
			statement.setBoolean(counter++, employment.isSigned());

			statement.executeUpdate();
			connection.commit();

			resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				return resultSet.getInt(1);
			}
		} catch (SQLException exception) {
		
			System.out.println("Insert failed: " + exception.getMessage());
			if (null != connection) {
				connection.rollback();
			}
		} finally {
			if (null != resultSet) {
				resultSet.close();
			}

			if (null != statement) {
				statement.close();
			}

			if (null != connection) {
				connection.close();
			}
		}

		return 0;
	}

}