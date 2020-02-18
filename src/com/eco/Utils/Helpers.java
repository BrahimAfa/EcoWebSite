package com.eco.Utils;

import java.sql.SQLException;
import java.util.List;

import com.eco.Models.Product;

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
	
	public static String getProductAsHtmlTableRow(List<Product> P) {
		StringBuilder str = new StringBuilder();
		P.forEach(p-> 
		str.append(String.format(
				"<tr><th scope='row'>%d</th> <td>%s</td> <td>%d</td></td></tr>", 
				p.getId(),p.getName(),p.getPrice()))
				);
		return str.toString();
	}
}
