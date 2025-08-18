package com.isc.api.service;

public interface EmailService 
{    
	void sendAssignmentEmailWithReport(String emailTo, byte[] report, String employeeName, String serialNumber, String categoryName);

}
