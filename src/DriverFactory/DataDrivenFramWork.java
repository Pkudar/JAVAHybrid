package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import CommonFunLibrary.ERP_Functions;
import Utilities.ExcelFileUtil;

public class DataDrivenFramWork {

	WebDriver driver;
	@BeforeTest
	public void setup()
	{
		String launch=ERP_Functions.launchApp("http://webapp.qedge.com");
		System.out.println(launch);
		String login= ERP_Functions.verifyLogin("admin", "master");
		System.out.println(login);
	}
	@Test
	public void supplierCre() throws Throwable
	{
		ExcelFileUtil xl=new ExcelFileUtil();
		int rc=xl.rowCount("Suppiler");
		int cc=xl.colCount("Suppiler");
		System.out.println(rc+ " "+cc);
		//print all coloumn  value
		for(int i=1;i<=rc;i++)
		{
			String sname=xl.getCellData("Suppiler", i, 0);
			String address=xl.getCellData("Suppiler", i, 1);
			String city=xl.getCellData("Suppiler", i, 2);
			String country=xl.getCellData("Suppiler", i, 3);
			String cperson=xl.getCellData("Suppiler", i, 4);
			String pnumber=xl.getCellData("Suppiler", i, 5);
			String mail=xl.getCellData("Suppiler", i, 6);
			String mnumber=xl.getCellData("Suppiler", i, 7);
			String notes=xl.getCellData("Suppiler", i, 8);
			String Result=ERP_Functions.verifySupplier(sname, address, city, country, cperson, pnumber, mail, mnumber, notes);
			xl.setCellData("Suppiler", i, 9,Result);
		}
		
	}
	@AfterTest
	public void tearDown()
	{
		ERP_Functions.closeapp();
	}
}
