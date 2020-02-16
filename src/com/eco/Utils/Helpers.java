package com.eco.Utils;

public class Helpers {
	public static boolean LoginValidation(String email,String Password) {
		if(!email.matches("^\\w+@\\w+\\.\\w+{2,}$") || email.equals("")|| email==null) {
			return false;
		}
		else if(Password.length()<3 || Password.equals("")|| Password==null) {
			return false;
		}
		return true;
	}
}
