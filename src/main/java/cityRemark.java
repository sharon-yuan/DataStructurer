import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.Suirui.DataStructurer.Util.FileIO;

public class cityRemark {
public static void main(String[]args){
	try {
		exe();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
	public static void exe() throws Exception {
		String resultString = "";
		ArrayList<String> cityList = FileIO.getLinesArray("CityInfo.txt");
		HashMap<String, ArrayList<String>> countyMap = new HashMap<>();
		HashMap<String, ArrayList<String>> cityMap = new HashMap<>();
		// HashMap<String, ArrayList<String>>stateMap=new HashMap<>();
		for (int i = 0; i < cityList.size(); i++) {
			String[] locations = cityList.get(i).split(" ");

			// county
			if (countyMap.containsKey(locations[0])) {
				ArrayList<String> tmpcountyVal = countyMap.get(locations[0]);
				tmpcountyVal.add(locations[1] + " " + locations[2]);
				countyMap.put(locations[0], tmpcountyVal);
			} else {

				ArrayList<String> tmpcountyVal = new ArrayList<>();
				tmpcountyVal.add(locations[1] + " " + locations[2]);
				countyMap.put(locations[0], tmpcountyVal);
			}

			// city
			if (cityMap.containsKey(locations[1])) {
				ArrayList<String> tmpcityVal = countyMap.get(locations[0]);
				if (tmpcityVal.contains(locations[2]))
					continue;
				else {
					tmpcityVal.add(locations[2]);
				}
				cityMap.put(locations[1], tmpcityVal);
			} else {
				ArrayList<String> tmpcityVal = new ArrayList<>();
				tmpcityVal.add(locations[2]);
				cityMap.put(locations[1], tmpcityVal);
			}

		}
		InputStream inp = new FileInputStream(new File("C:/Users/wangsy/Desktop/city.xlsx"));
		Workbook wb = WorkbookFactory.create(inp);

		// 获取第一张表
		Sheet st = wb.getSheetAt(0);
		// 遍历第一张表的所有行
		for (int i = 0; i <= st.getLastRowNum(); i++) {
			// 第一行一般为title不读取
			ArrayList<String> rowList = new ArrayList<>();
			Row row = st.getRow(i); // 获取第一行数据
			// 遍历第一行所有的列(单元格)

			if (row != null) {

				String[] locationList = new String[3];
				String oriString = "";
				int flag = 0;
				for (int j = 0; j < 3; j++) {
					if (row.getCell(j) != null) {
						locationList[j] = row.getCell(j).toString();
						oriString += row.getCell(j).toString() + " ";
					} else {
						flag += (Math.pow(2, j));
						oriString += "null ";
					}
				}
				System.out.println("oriString:" + oriString);
				if (flag != 0) {
					System.out.println("flag!=0");
					// 存在值为null
					if (locationList[1] == null) {
						// city==null
						System.out.println("city==null");
						if (countyMap.get(locationList[0]) != null) {
							/// city==null & county!=null
							if (countyMap.get(locationList[0]).size() == 1) {
								// county!=null & countylistsize==1
								/*
								 * for(int jjj=0;jjj<locationList.length;jjj++)
								 * System.out.print("--"+locationList[jjj]+" ");
								 * System.err.println('\n'+locationList[0]+"-->"
								 * +countyMap.get(locationList[0])+'\n');
								 */
								System.out.println("county对应唯一city");
								String tmpResultString =  locationList[0] + " "
										+ countyMap.get(locationList[0]).get(0) + '\n';
								resultString += tmpResultString;
								System.out.print(oriString + "====>" +tmpResultString);

							} else {
								// city==null & county!=null & countylistsize!=1
								System.out.println("county对应 " + countyMap.get(locationList[0]).size() + "个city");
								System.out.println(countyMap.get(locationList[0]));
								ArrayList<String> valArray = countyMap.get(locationList[0]);
								boolean findTheOne = false;
								if (locationList[2] != null) {
									//city==null & county!=null & countylistsize!=1 state !=null
									for (String tmpstring : valArray) {
										if (tmpstring.contains(locationList[2])) {

											System.out.println("size:" + countyMap.get(locationList[0]).size());
											System.out.println(locationList[0] + "-->" + countyMap.get(locationList[0]));
											System.err.println(tmpstring);
											findTheOne = true;

											String tmpResultString = locationList[0] + " "
													+ tmpstring + " " + '\n';
											resultString += tmpResultString;
											System.out.println(oriString + "====>" + tmpResultString);
											/*
											 * for(int tempI=i;tempI<3;tempI++)
											 * resultString+=" ";
											 */

											break;
										}

									}
									if (!findTheOne) {
									System.err.println("bad location array"+oriString);
									System.out.println(valArray);
									//throw new Exception();
									resultString += oriString+'\n';
									}
								} else {
									//city==null & county!=null & countylistsize!=1 state ==null
									ArrayList<String> tmpcityList = countyMap.get(locationList[0]);
									if (tmpcityList != null) {
										if (tmpcityList.size() >= 1) {
											String tmpResultString =locationList[1] + " " + tmpcityList.get(0) + '\n';
											resultString += tmpResultString;
											System.out.println( oriString + "====>"+tmpResultString);
										}
									} else {
										System.err.println("bad city name!"+oriString);
										throw new Exception();
									}

								}

							}
						}
						else{
							String tmpResultString =  oriString+ '\n';
							
							resultString += tmpResultString;
							System.out.println(oriString + "line 174====>" + tmpResultString);
						}
					} else {
						// city !=null
						if(locationList[2]!=null){
						String tmpResultString =oriString + '\n';
						resultString += tmpResultString;
						System.out.println( oriString + "====>" + tmpResultString);}
						else{
							ArrayList<String>stateArray=cityMap.get(locationList[1]);
							System.out.println(locationList[1]);
							System.out.println(stateArray);
							String tmpResultString =  locationList[0]+" "+stateArray.get(0) + '\n';
							
							resultString += tmpResultString;
							System.out.println(oriString + "line 174====>" + tmpResultString);
							
						}
					}
				} else {
					// flag==0
					String tmpResultString = oriString + '\n';
					resultString += tmpResultString;
					System.out.println(oriString + "====>" +tmpResultString);
				}

			} else {
				// row ==null
				resultString += '\n';
			}

		}

		FileIO.saveintoFile("E:/data/china/resultCity.txt", resultString);
	}

}
