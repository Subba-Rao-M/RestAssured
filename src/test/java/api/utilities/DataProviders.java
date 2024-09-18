package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {


	@DataProvider(name="Data")
	public String[][] getAllData() throws IOException
	{
		String path=System.getProperty("user.dir")+"//src//test/resources//testdata//TestData1.xlsx";
		XLUtility xl=new XLUtility(path);
	
		int rownum=xl.getRowCount("Sheet1");	
		int colcount=xl.getCellCount("Sheet1",1); // in 2nd row as index starts from 0
		
		String apidata[][]=new String[rownum][colcount]; // 2 dimention array to store the data
		
		for(int i=1;i<=rownum;i++) // stating from row 2
		{		
			for(int j=0;j<colcount;j++) // column 1
			{
				apidata[i-1][j]= xl.getCellData("Sheet1",i, j);  //array index starts from 0
			}
		}
	
		return apidata;
	}
	
	@DataProvider(name="UserNames")
	public String[] getUserNames() throws IOException
	{
		String path=System.getProperty("user.dir")+"//src//test/resources//testdata//TestData1.xlsx";
		XLUtility xl=new XLUtility(path);
	
		int rownum=xl.getRowCount("Sheet1");	
			
		String apidata[]=new String[rownum];
		
		for(int i=1;i<=rownum;i++)
		{		
			apidata[i-1]= xl.getCellData("Sheet1",i, 1);  // to get column 2 data and 1 field of array
			
		}
	
		return apidata;
	}
	
}