package com.testing;

import com.utility.Xls_Reader;

public class DDTTesting 
{
	public static void main(String[] args) {
		Xls_Reader excel=new Xls_Reader("./src/main/java/resources/LoginCredentials.xlsx");	
		
		String res=excel.getCellData("Login", 0, 1);
		System.out.println(res);
	}
}
