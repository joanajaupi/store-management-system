package Model;

public class checkInputs {

	public static boolean checkEmail(String email) {
		if (email.matches("[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}")) {
			System.out.println("email matched");
			return true;
		} else {
			return false;

		}
	}

	public static boolean checkPhoneNumbers(String phoneNr) {
		if (phoneNr.matches("06[6-9]\\d{7}") || phoneNr.matches("06[6-9]\\s\\d{3}\\s\\d{4}")) {
			return true;
		} else
			return false;
	}

	public static boolean checkPersonID(String personID) {
		if (personID.matches("[a-zA-Z][0-9]{8}[a-zA-Z]")) {
			return true;
		} else
			return false;
	}

}
