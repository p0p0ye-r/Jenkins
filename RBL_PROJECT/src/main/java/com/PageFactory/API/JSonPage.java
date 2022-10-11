package com.PageFactory.API;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.generics.Pojo;

public class JSonPage {

	private Pojo objPojo;

	public JSonPage(Pojo pojo) {
		objPojo = pojo;
	}

	/**
	 * @author : Vikash Thakur
	 * @throws IOException
	 * @Date of Creation : 27-08-2022
	 */
	@SuppressWarnings("resource")
	public void readRequiredDataFromJsonInputFile(String requestid) throws IOException {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\ApiJsonData\\GenrateOtpJsonTestData.json";
		File fileToBeModified = new File(filePath);
		String oldContent = "";
		String abc = "";
		BufferedReader reader = null;
		String strCurrentLine = "";
		FileWriter writer = null;
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		while ((strCurrentLine = br.readLine()) != null) {
			if (strCurrentLine.contains("requestId")) {
				String[] strAr = strCurrentLine.split("requestId");
				abc = strAr[1].trim().substring(4);
			}
		}
		try {
			reader = new BufferedReader(new FileReader(fileToBeModified));
			String line = reader.readLine();
			while (line != null) {
				oldContent = oldContent + line + System.lineSeparator();
				line = reader.readLine();
			}
			String newContent = oldContent.replaceAll(abc, requestid + "\",");
			writer = new FileWriter(fileToBeModified);
			writer.write(newContent);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	@SuppressWarnings("resource")
	public void readRequiredDataFromValidateOTPJsonInputFile(String otp) throws IOException {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\ApiJsonData\\ValidateOtpJsonTestData.json";
		File fileToBeModified = new File(filePath);
		String oldContent = "";
		String abc = "";
		BufferedReader reader = null;
		String strCurrentLine = "";
		FileWriter writer = null;
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		while ((strCurrentLine = br.readLine()) != null) {
			if (strCurrentLine.contains("otp")) {
				String[] strAr = strCurrentLine.split("otp");
				abc = strAr[1].trim().substring(3);
			}
		}
		try {
			reader = new BufferedReader(new FileReader(fileToBeModified));
			String line = reader.readLine();
			while (line != null) {
				oldContent = oldContent + line + System.lineSeparator();
				line = reader.readLine();
			}
			String newContent = oldContent.replaceAll(abc, otp + "\",");
			writer = new FileWriter(fileToBeModified);
			writer.write(newContent);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("resource")
	public void readRequiredDataFromValidateUUIDJsonInputFile(String UUID) throws IOException {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\ApiJsonData\\ValidateOtpJsonTestData.json";
		File fileToBeModified = new File(filePath);
		String oldContent = "";
		String abc = "";
		BufferedReader reader = null;
		String strCurrentLine = "";
		FileWriter writer = null;
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		while ((strCurrentLine = br.readLine()) != null) {
			if (strCurrentLine.contains("RequestUUID")) {
				String[] strAr = strCurrentLine.split("RequestUUID");
				abc = strAr[1].trim().substring(4);
			}
		}
		try {
			reader = new BufferedReader(new FileReader(fileToBeModified));
			String line = reader.readLine();
			while (line != null) {
				oldContent = oldContent + line + System.lineSeparator();
				line = reader.readLine();
			}
			String newContent = oldContent.replaceAll(abc, UUID + "\",");
			writer = new FileWriter(fileToBeModified);
			writer.write(newContent);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("resource")
	public void readRequiredDataFromValidateRequestIdJsonInputFile(String requestId) throws IOException {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\ApiJsonData\\ValidateOtpJsonTestData.json";
		File fileToBeModified = new File(filePath);
		String oldContent = "";
		String abc = "";
		BufferedReader reader = null;
		String strCurrentLine = "";
		FileWriter writer = null;
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		while ((strCurrentLine = br.readLine()) != null) {
			if (strCurrentLine.contains("requestId")) {
				String[] strAr = strCurrentLine.split("requestId");
				abc = strAr[1].trim().substring(3);
			}
		}
		try {
			reader = new BufferedReader(new FileReader(fileToBeModified));
			String line = reader.readLine();
			while (line != null) {
				oldContent = oldContent + line + System.lineSeparator();
				line = reader.readLine();
			}
			String newContent = oldContent.replaceAll(abc, requestId + "\",");
			writer = new FileWriter(fileToBeModified);
			writer.write(newContent);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * @author : Vikash Thakur
	 * @throws IOException
	 * @Date of Creation : 27-08-2022
	 */
	@SuppressWarnings("resource")
	public void readRequiredDataFromJsonGETTOKENInputFile(String RequestUUID) throws IOException {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\ApiJsonData\\GetOtpJsonTestData.json";
		File fileToBeModified = new File(filePath);
		String oldContent = "";
		String abc = "";
		BufferedReader reader = null;
		String strCurrentLine = "";
		FileWriter writer = null;
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		while ((strCurrentLine = br.readLine()) != null) {
			if (strCurrentLine.contains("RequestUUID")) {
				String[] strAr = strCurrentLine.split("RequestUUID");
				abc = strAr[1].trim().substring(3);
			}
		}
		try {
			reader = new BufferedReader(new FileReader(fileToBeModified));
			String line = reader.readLine();
			while (line != null) {
				oldContent = oldContent + line + System.lineSeparator();
				line = reader.readLine();
			}
			String newContent = oldContent.replaceAll(abc, RequestUUID + "\",");
			writer = new FileWriter(fileToBeModified);
			writer.write(newContent);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * @author : Vikash Thakur
	 * @throws IOException
	 * @Date of Creation : 27-08-2022
	 */
	@SuppressWarnings("resource")
	public void readRequiredDataFromJsonRequestUUIDInputFile(String RequestUUID) throws IOException {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\ApiJsonData\\GenrateOtpJsonTestData.json";
		File fileToBeModified = new File(filePath);
		String oldContent = "";
		String abc = "";
		BufferedReader reader = null;
		String strCurrentLine = "";
		FileWriter writer = null;
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		while ((strCurrentLine = br.readLine()) != null) {
			if (strCurrentLine.contains("RequestUUID")) {
				String[] strAr = strCurrentLine.split("RequestUUID");
				abc = strAr[1].trim().substring(4);
			}
		}
		try {
			reader = new BufferedReader(new FileReader(fileToBeModified));
			String line = reader.readLine();
			while (line != null) {
				oldContent = oldContent + line + System.lineSeparator();
				line = reader.readLine();
			}
			String newContent = oldContent.replaceAll(abc, RequestUUID + "\",");
			writer = new FileWriter(fileToBeModified);
			writer.write(newContent);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * @author : Vikash Thakur
	 * @throws IOException
	 * @Date of Creation : 27-08-2022
	 */
	@SuppressWarnings("resource")
	public void readRequiredDataFromGenrateMOBILENOJsonInputFile(String mobileno) throws IOException {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\ApiJsonData\\GenrateOtpJsonTestData.json";
		File fileToBeModified = new File(filePath);
		String oldContent = "";
		String abc = "";
		BufferedReader reader = null;
		String strCurrentLine = "";
		FileWriter writer = null;
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		while ((strCurrentLine = br.readLine()) != null) {
			if (strCurrentLine.contains("mobileno")) {
				String[] strAr = strCurrentLine.split("mobileno");
				abc = strAr[1].trim().substring(4);
			}
		}
		try {
			reader = new BufferedReader(new FileReader(fileToBeModified));
			String line = reader.readLine();
			while (line != null) {
				oldContent = oldContent + line + System.lineSeparator();
				line = reader.readLine();
			}
			String newContent = oldContent.replaceAll(abc, mobileno + "\",");
			writer = new FileWriter(fileToBeModified);
			writer.write(newContent);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * @author : Vikash Thakur
	 * @throws IOException
	 * @Date of Creation : 27-08-2022
	 */
	@SuppressWarnings("resource")
	public void readRequiredDataFromGenrateEMAILJsonInputFile(String email) throws IOException {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\ApiJsonData\\GenrateOtpJsonTestData.json";
		File fileToBeModified = new File(filePath);
		String oldContent = "";
		String abc = "";
		BufferedReader reader = null;
		String strCurrentLine = "";
		FileWriter writer = null;
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		while ((strCurrentLine = br.readLine()) != null) {
			if (strCurrentLine.contains("email")) {
				String[] strAr = strCurrentLine.split("email");
				abc = strAr[1].trim().substring(4);
			}
		}
		try {
			reader = new BufferedReader(new FileReader(fileToBeModified));
			String line = reader.readLine();
			while (line != null) {
				oldContent = oldContent + line + System.lineSeparator();
				line = reader.readLine();
			}
			String newContent = oldContent.replaceAll(abc, email + "\",");
			writer = new FileWriter(fileToBeModified);
			writer.write(newContent);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	//
	
	/**
	 * @author : vinayak 
	 * @throws IOException
	 * @Date of Creation : 09-08-2022
	 */
	@SuppressWarnings("resource")
	public void readRequiredDataFromGenrateChannelIdJsonInputFile(String channelId) throws IOException {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\ApiJsonData\\GenrateOtpJsonTestData.json";
		File fileToBeModified = new File(filePath);
		String oldContent = "";
		String abc = "";
		BufferedReader reader = null;
		String strCurrentLine = "";
		FileWriter writer = null;
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		while ((strCurrentLine = br.readLine()) != null) {
			if (strCurrentLine.contains("channelId")) {
				String[] strAr = strCurrentLine.split("channelId");
				abc = strAr[1].trim().substring(4);
			}
		}
		try {
			reader = new BufferedReader(new FileReader(fileToBeModified));
			String line = reader.readLine();
			while (line != null) {
				oldContent = oldContent + line + System.lineSeparator();
				line = reader.readLine();
			}
			String newContent = oldContent.replaceAll(abc, channelId + "\",");
			writer = new FileWriter(fileToBeModified);
			writer.write(newContent);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @author : vinayak 
	 * @throws IOException
	 * @Date of Creation : 09-08-2022
	 */
	@SuppressWarnings("resource")
	public void readRequiredDataFromGenrateDeliveryFlagJsonInputFile(String DeliveryFlag) throws IOException {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\ApiJsonData\\GenrateOtpJsonTestData.json";
		File fileToBeModified = new File(filePath);
		String oldContent = "";
		String abc = "";
		BufferedReader reader = null;
		String strCurrentLine = "";
		FileWriter writer = null;
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		while ((strCurrentLine = br.readLine()) != null) {
			System.out.println("strCurrentLine :"+strCurrentLine);
			if (strCurrentLine.contains("deliveryFlag")) {
				String[] strAr = strCurrentLine.split("deliveryFlag");
				abc = strAr[1].trim().substring(4);
			}
		}
		try {
			reader = new BufferedReader(new FileReader(fileToBeModified));
			String line = reader.readLine();
			while (line != null) {
				oldContent = oldContent + line + System.lineSeparator();
				line = reader.readLine();
			}
			String newContent = oldContent.replaceAll(abc, DeliveryFlag + "\""+",");
			writer = new FileWriter(fileToBeModified);
			writer.write(newContent);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	/**
	 * @author : vinayak 
	 * @throws IOException
	 * @Date of Creation : 09-08-2022
	 */
	@SuppressWarnings("resource")
	public void readRequiredDataFromGenrateSERVICETYPEJsonInputFile(String serviceType) throws IOException {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\ApiJsonData\\GenrateOtpJsonTestData.json";
		File fileToBeModified = new File(filePath);
		String oldContent = "";
		String abc = "";
		BufferedReader reader = null;
		String strCurrentLine = "";
		FileWriter writer = null;
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		while ((strCurrentLine = br.readLine()) != null) {
			System.out.println("strCurrentLine :"+strCurrentLine);
			if (strCurrentLine.contains("serviceType")) {
				String[] strAr = strCurrentLine.split("serviceType");
				abc = strAr[1].trim().substring(4);
			}
		}
		try {
			reader = new BufferedReader(new FileReader(fileToBeModified));
			String line = reader.readLine();
			while (line != null) {
				oldContent = oldContent + line + System.lineSeparator();
				line = reader.readLine();
			}
			String newContent = oldContent.replaceAll(abc, serviceType + "\""+",");
			writer = new FileWriter(fileToBeModified);
			writer.write(newContent);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	/**
	 * @author : vinayak 
	 * @throws IOException
	 * @Date of Creation : 09-08-2022
	 */
	@SuppressWarnings("resource")
	public void readRequiredDataFromGenrateISNRIJsonInputFile(String IsNRI) throws IOException {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\ApiJsonData\\GenrateOtpJsonTestData.json";
		File fileToBeModified = new File(filePath);
		String oldContent = "";
		String abc = "";
		BufferedReader reader = null;
		String strCurrentLine = "";
		FileWriter writer = null;
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		while ((strCurrentLine = br.readLine()) != null) {
			if (strCurrentLine.contains("IsNRI")) {
				String[] strAr = strCurrentLine.split("IsNRI");
				abc = strAr[1].trim().substring(4);
			}
		}
		try {
			reader = new BufferedReader(new FileReader(fileToBeModified));
			String line = reader.readLine();
			while (line != null) {
				oldContent = oldContent + line + System.lineSeparator();
				line = reader.readLine();
			}
			String newContent = oldContent.replaceAll(abc, IsNRI + "\""+",");
			writer = new FileWriter(fileToBeModified);
			writer.write(newContent);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * @author : vinayak 
	 * @throws IOException
	 * @Date of Creation : 09-09-2022
	 */
	@SuppressWarnings("resource")
	public void readRequiredDataFromGenrateSERVICEREQUESTIDJsonInputFile(String serviceRequestId) throws IOException {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\ApiJsonData\\GenrateOtpJsonTestData.json";
		File fileToBeModified = new File(filePath);
		String oldContent = "";
		String abc = "";
		BufferedReader reader = null;
		String strCurrentLine = "";
		FileWriter writer = null;
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		while ((strCurrentLine = br.readLine()) != null) {
			if (strCurrentLine.contains("ServiceRequestId")) {
				String[] strAr = strCurrentLine.split("ServiceRequestId");
				abc = strAr[1].trim().substring(4);
			}
		}
		try {
			reader = new BufferedReader(new FileReader(fileToBeModified));
			String line = reader.readLine();
			while (line != null) {
				oldContent = oldContent + line + System.lineSeparator();
				line = reader.readLine();
			}
			String newContent = oldContent.replaceAll(abc, serviceRequestId + "\",");
			writer = new FileWriter(fileToBeModified);
			writer.write(newContent);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @author : vinayak 
	 * @throws IOException
	 * @Date of Creation : 09-09-2022
	 */
	@SuppressWarnings("resource")
	public void readRequiredDataFromGenrateSERVICEREQUESTVERSIONJsonInputFile(String serviceRequestVersion) throws IOException {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\ApiJsonData\\GenrateOtpJsonTestData.json";
		File fileToBeModified = new File(filePath);
		String oldContent = "";
		String abc = "";
		BufferedReader reader = null;
		String strCurrentLine = "";
		FileWriter writer = null;
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		while ((strCurrentLine = br.readLine()) != null) {
			if (strCurrentLine.contains("ServiceRequestVersion")) {
				String[] strAr = strCurrentLine.split("ServiceRequestVersion");
				abc = strAr[1].trim().substring(4);
			}
		}
		try {
			reader = new BufferedReader(new FileReader(fileToBeModified));
			String line = reader.readLine();
			while (line != null) {
				oldContent = oldContent + line + System.lineSeparator();
				line = reader.readLine();
			}
			String newContent = oldContent.replaceAll(abc, serviceRequestVersion + "\",");
			writer = new FileWriter(fileToBeModified);
			writer.write(newContent);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @author : vinayak 
	 * @throws IOException
	 * @Date of Creation : 09-09-2022
	 */
	@SuppressWarnings("resource")
	public void readRequiredDataFromGenrateMESSAGEKEYCHANNELIDsonInputFile(String msgKeyChannelId) throws IOException {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\ApiJsonData\\GenrateOtpJsonTestData.json";
		File fileToBeModified = new File(filePath);
		String oldContent = "";
		String abc = "";
		BufferedReader reader = null;
		String strCurrentLine = "";
		FileWriter writer = null;
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		while ((strCurrentLine = br.readLine()) != null) {
			if (strCurrentLine.contains("ChannelId")) {
				String[] strAr = strCurrentLine.split("ChannelId");
				abc = strAr[1].trim().substring(4);
			}
		}
		try {
			reader = new BufferedReader(new FileReader(fileToBeModified));
			String line = reader.readLine();
			while (line != null) {
				oldContent = oldContent + line + System.lineSeparator();
				line = reader.readLine();
			}
			String newContent = oldContent.replaceAll(abc, msgKeyChannelId + "\",");
			writer = new FileWriter(fileToBeModified);
			writer.write(newContent);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * @author : vinayak 
	 * @throws IOException
	 * @Date of Creation : 09-09-2022
	 */
	@SuppressWarnings("resource")
	public void readRequiredDataFromGenratePASSWORDTOKENUSERIDsonInputFile(String userId) throws IOException {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\ApiJsonData\\GenrateOtpJsonTestData.json";
		File fileToBeModified = new File(filePath);
		String oldContent = "";
		String abc = "";
		BufferedReader reader = null;
		String strCurrentLine = "";
		FileWriter writer = null;
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		while ((strCurrentLine = br.readLine()) != null) {
			if (strCurrentLine.contains("UserId")) {
				String[] strAr = strCurrentLine.split("UserId");
				abc = strAr[1].trim().substring(4);
			}
		}
		try {
			reader = new BufferedReader(new FileReader(fileToBeModified));
			String line = reader.readLine();
			while (line != null) {
				oldContent = oldContent + line + System.lineSeparator();
				line = reader.readLine();
			}
			String newContent = oldContent.replaceAll(abc, userId+ "\"");
			writer = new FileWriter(fileToBeModified);
			writer.write(newContent);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	/**
	 * @author : vinayak 
	 * @throws IOException
	 * @Date of Creation : 09-09-2022
	 */
	@SuppressWarnings("resource")
	public void readRequiredDataFromGenrateBANKIDsonInputFile(String bankId) throws IOException {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\ApiJsonData\\GenrateOtpJsonTestData.json";
		File fileToBeModified = new File(filePath);
		String oldContent = "";
		String abc = "";
		BufferedReader reader = null;
		String strCurrentLine = "";
		FileWriter writer = null;
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		while ((strCurrentLine = br.readLine()) != null) {
			if (strCurrentLine.contains("BankId")) {
				String[] strAr = strCurrentLine.split("BankId");
				abc = strAr[1].trim().substring(4);
			}
		}
		try {
			reader = new BufferedReader(new FileReader(fileToBeModified));
			String line = reader.readLine();
			while (line != null) {
				oldContent = oldContent + line + System.lineSeparator();
				line = reader.readLine();
			}
			String newContent = oldContent.replaceAll(abc, bankId+ "\",");
			writer = new FileWriter(fileToBeModified);
			writer.write(newContent);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	/**
	 * @author : vinayak 
	 * @throws IOException
	 * @Date of Creation : 09-09-2022
	 */
	@SuppressWarnings("resource")
	public void readRequiredDataFromGenrateOPTIONALFIELD1sonInputFile(String optionalField1) throws IOException {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\ApiJsonData\\GenrateOtpJsonTestData.json";
		File fileToBeModified = new File(filePath);
		String oldContent = "";
		String abc = "";
		BufferedReader reader = null;
		String strCurrentLine = "";
		FileWriter writer = null;
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		while ((strCurrentLine = br.readLine()) != null) {
			if (strCurrentLine.contains("optionalField1")) {
				String[] strAr = strCurrentLine.split("optionalField1");
				abc = strAr[1].trim().substring(4);
			}
		}
		try {
			reader = new BufferedReader(new FileReader(fileToBeModified));
			String line = reader.readLine();
			while (line != null) {
				oldContent = oldContent + line + System.lineSeparator();
				line = reader.readLine();
			}
			String newContent = oldContent.replaceAll(abc, optionalField1+ "\",");
			writer = new FileWriter(fileToBeModified);
			writer.write(newContent);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @author : vinayak 
	 * @throws IOException
	 * @Date of Creation : 09-09-2022
	 */
	@SuppressWarnings("resource")
	public void readRequiredDataFromGenrateOPTIONALFIELD2sonInputFile(String optionalField2) throws IOException {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\ApiJsonData\\GenrateOtpJsonTestData.json";
		File fileToBeModified = new File(filePath);
		String oldContent = "";
		String abc = "";
		BufferedReader reader = null;
		String strCurrentLine = "";
		FileWriter writer = null;
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		while ((strCurrentLine = br.readLine()) != null) {
			if (strCurrentLine.contains("optionalField2")) {
				String[] strAr = strCurrentLine.split("optionalField2");
				abc = strAr[1].trim().substring(4);
			}
		}
		try {
			reader = new BufferedReader(new FileReader(fileToBeModified));
			String line = reader.readLine();
			while (line != null) {
				oldContent = oldContent + line + System.lineSeparator();
				line = reader.readLine();
			}
			String newContent = oldContent.replaceAll(abc, optionalField2+ "\",");
			writer = new FileWriter(fileToBeModified);
			writer.write(newContent);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * @author : vinayak 
	 * @throws IOException
	 * @Date of Creation : 09-09-2022
	 */
	@SuppressWarnings("resource")
	public void readRequiredDataFromGenrateOPTIONALFIELD3sonInputFile(String optionalField3) throws IOException {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\ApiJsonData\\GenrateOtpJsonTestData.json";
		File fileToBeModified = new File(filePath);
		String oldContent = "";
		String abc = "";
		BufferedReader reader = null;
		String strCurrentLine = "";
		FileWriter writer = null;
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		while ((strCurrentLine = br.readLine()) != null) {
			if (strCurrentLine.contains("optionalField3")) {
				String[] strAr = strCurrentLine.split("optionalField3");
				abc = strAr[1].trim().substring(4);
			}
		}
		try {
			reader = new BufferedReader(new FileReader(fileToBeModified));
			String line = reader.readLine();
			while (line != null) {
				oldContent = oldContent + line + System.lineSeparator();
				line = reader.readLine();
			}
			String newContent = oldContent.replaceAll(abc, optionalField3+ "\",");
			writer = new FileWriter(fileToBeModified);
			writer.write(newContent);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
	
	/**
	 * @author : vinayak 
	 * @throws IOException
	 * @Date of Creation : 09-09-2022
	 */
	@SuppressWarnings("resource")
	public void readRequiredDataFromTransactionRefIDJsonInputFile(String transactionRefID) throws IOException {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\ApiJsonData\\GenrateOtpJsonTestData.json";
		File fileToBeModified = new File(filePath);
		String oldContent = "";
		String abc = "";
		BufferedReader reader = null;
		String strCurrentLine = "";
		FileWriter writer = null;
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		while ((strCurrentLine = br.readLine()) != null) {
			if (strCurrentLine.contains("TransactionRefID")) {
				String[] strAr = strCurrentLine.split("TransactionRefID");
				abc = strAr[1].trim().substring(4);
			}
		}
		try {
			reader = new BufferedReader(new FileReader(fileToBeModified));
			String line = reader.readLine();
			while (line != null) {
				oldContent = oldContent + line + System.lineSeparator();
				line = reader.readLine();
			}
			String newContent = oldContent.replaceAll(abc, transactionRefID+ "\"");
			writer = new FileWriter(fileToBeModified);
			writer.write(newContent);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}