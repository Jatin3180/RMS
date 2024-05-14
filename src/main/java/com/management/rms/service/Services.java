package com.management.rms.service;

import java.util.ArrayList;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.management.rms.Dao.Dao;
import com.management.rms.model.JobVO;
import com.management.rms.model.ListJobs;
import com.management.rms.model.ListUsers;
import com.management.rms.model.UserVO;

import ch.qos.logback.classic.Logger;

@Component
@Service
public class Services {
	
	private static final Logger logger = (Logger) LoggerFactory.getLogger(Services.class);

	@Autowired
	private Dao dao;
	
	
	public String AdminRegister(UserVO user, String email, String password_h)throws Exception {
		try {
			 BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			 
			 String Password_hash = passwordEncoder.encode(password_h);			
			return dao.AdminRegister(user, email,Password_hash);
		} catch (Exception e) {
			logger.error("ERROR IN EO - ClientUserRegistration");
			logger.error(e.getMessage());
			throw e;
		}
	}


	public String login(String email, String password) throws Exception {
		StringBuilder responseBuilder=new StringBuilder("ERROR ADDING NEW REGISTRATION USER");
		try {
			String response = dao.login(email, password);
			responseBuilder = new StringBuilder(response);
	
			
		} catch (Exception e) 
		{
			logger.error("ERROR IN BO - Login()");
			logger.error(e.getMessage());
			throw e;
		}
		return responseBuilder.toString();
	}


	public String getUserType(String email) {
		// TODO Auto-generated method stub
		return dao.getUserType(email);
	}


	public String createJobOpening(JobVO job, String email) throws Exception {
		StringBuilder responseBuilder=new StringBuilder("ERROR ADDING NEW PRODUCT");
		try {
			
			// Adds client data to db
			StringBuilder response = dao.createJobOpening(job, email);
			responseBuilder = new StringBuilder(response);
			
		} catch (Exception e) 
		{
			logger.error("ERROR IN BO - Add product()");
			logger.error(e.getMessage());
			throw e;
		}
		return responseBuilder.toString();
	}


	public String UserRegister(UserVO user, String email, String password_h) throws Exception {
		try {
			 BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			 
			 String Password_hash = passwordEncoder.encode(password_h);			
			return dao.UserRegister(user, email,Password_hash);
		} catch (Exception e) {
			logger.error("ERROR IN EO - ClientUserRegistration");
			logger.error(e.getMessage());
			throw e;
		}
	}


	public String Userlogin(String email, String password) throws Exception {
		StringBuilder responseBuilder=new StringBuilder("ERROR ADDING NEW REGISTRATION USER");
		try {
			String response = dao.Userlogin(email, password);
			responseBuilder = new StringBuilder(response);
	
			
		} catch (Exception e) 
		{
			logger.error("ERROR IN BO - Login()");
			logger.error(e.getMessage());
			throw e;
		}
		return responseBuilder.toString();
	}


	public String listJobs(String email) {
		ArrayList<ListJobs> listProduct;
		String response = null;
		try {
			listProduct = dao.listJobs(email) ;
			ObjectMapper objmapper = new ObjectMapper();
			response = objmapper.writeValueAsString(listProduct);
//			System.out.print(response);
		} catch (Exception e) {
			logger.info("EXCEPTION IN EO - LIST JOBS: {}" + e.getMessage(), e);
		}
		return response;
	}


	public String listUsers(String usertype) {
		ArrayList<ListUsers> listProduct;
		String response = null;
		try {
			listProduct = dao.listUsers(usertype) ;
			ObjectMapper objmapper = new ObjectMapper();
			response = objmapper.writeValueAsString(listProduct);
//			System.out.print(response);
		} catch (Exception e) {
			logger.info("EXCEPTION IN EO - LIST USERS: {}" + e.getMessage(), e);
		}
		return response;
	}


	public String listJobsApplicant() {
		ArrayList<ListJobs> listProduct;
		String response = null;
		try {
			listProduct = dao.listJobsApplicant() ;
			ObjectMapper objmapper = new ObjectMapper();
			response = objmapper.writeValueAsString(listProduct);
//			System.out.print(response);
		} catch (Exception e) {
			logger.info("EXCEPTION IN EO - LIST JOBS: {}" + e.getMessage(), e);
		}
		return response;
	}


	public String getJob(String email, String jobID) {
		try {
			JobVO job = dao.getJob(email,jobID);
			ObjectMapper objectMapper = new ObjectMapper();
			String values = objectMapper.writeValueAsString(job);
			return values;
		}catch(Exception e) {
			logger.error("ERROR IN BO - GET Job Service :{} ", e.getMessage());
		}
		
		return null;
	}


	public String getJobApplicant(String jobID) {
		try {
			JobVO job = dao.getJobApplicant(jobID);
			ObjectMapper objectMapper = new ObjectMapper();
			String values = objectMapper.writeValueAsString(job);
			return values;
		}catch(Exception e) {
			logger.error("ERROR IN BO - GET Job Service :{} ", e.getMessage());
		}
		
		return null;
	}
}
