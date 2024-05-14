package com.management.rms.controller;


import static com.management.rms.Constants.RmsConstants.USER_BASE_URL;
import static com.management.rms.Constants.RmsConstants.APPLICANT_REGISTRATION;
import static com.management.rms.Constants.RmsConstants.GET_JOB;
import static com.management.rms.Constants.RmsConstants.LIST_JOBS;
import static com.management.rms.Constants.RmsConstants.ADMIN;
import static com.management.rms.Constants.RmsConstants.APPLICANT;
import static com.management.rms.Constants.RmsConstants.APPLICANT_LOGIN;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.management.rms.model.RspnsVO;
import com.management.rms.model.UserVO;
import com.management.rms.service.Services;

import ch.qos.logback.classic.Logger;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Validated
@Api(value = USER_BASE_URL, tags = { "This is the controller for the Eastmantax Services for Applications" })
@RestController
@RequestMapping(USER_BASE_URL)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ApplicantController {
	
private static final Logger logger = (Logger) LoggerFactory.getLogger(ApplicantController.class);
	
	@Autowired
	private Services services;
	
	
	@ApiOperation(value = "Register A admin", notes = "This service allows to add Register a new user")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping(value =  APPLICANT_REGISTRATION, consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RspnsVO> AdminRegister(
			@RequestBody UserVO user,
			@RequestParam String email,
			@RequestParam String password_h)throws Exception
	{
		final String methodName = "AdminRegistration()";
		logger.info("{}: This service is adding a Admin Registration" , methodName);
		RspnsVO rspnsVO = new RspnsVO();
		try {
				rspnsVO.setResponseCode(1000);
				rspnsVO.setResponseMsg("GOOD");
				rspnsVO.setResponse(services.UserRegister(user, email, password_h));	
		}catch(Exception e)
		{
			logger.error("email:" + email  + ":errorMessage:" + e.getMessage());
			rspnsVO.setResponse(e.getMessage());
			rspnsVO.setResponseCode(1010);
			rspnsVO.setResponseMsg(null);
			return new ResponseEntity<RspnsVO>(rspnsVO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		logger.info("Response : {}", rspnsVO.toString());
		return new ResponseEntity<RspnsVO>(rspnsVO, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Login A  Client user", notes = "This service allows to login a client user")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping(value =  APPLICANT_LOGIN, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RspnsVO> login( 
				@RequestParam String email,
				@RequestParam String password)throws Exception
	{
		final String methodName = "ClientUserLogin()";
		logger.info("{}: This service is for login Client user" , methodName);
		RspnsVO rspnsVO = new RspnsVO();
		try {
				rspnsVO.setResponseCode(1000);
				rspnsVO.setResponseMsg("GOOD");
				rspnsVO.setResponse(services.Userlogin(email,password));	
		}catch(Exception e)
		{
			logger.error("email:" + email  + ":errorMessage:" + e.getMessage());
			rspnsVO.setResponse(e.getMessage());
			rspnsVO.setResponseCode(1010);
			rspnsVO.setResponseMsg(null);
			return new ResponseEntity<RspnsVO>(rspnsVO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		logger.info("Response : {}", rspnsVO.toString());
		return new ResponseEntity<RspnsVO>(rspnsVO, HttpStatus.OK);
	}
	
	@ApiOperation(value = "list jobs", notes = "This is listing job . ")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping(value = LIST_JOBS, produces = MediaType.APPLICATION_JSON_VALUE)		
	public ResponseEntity<RspnsVO> listJobsApplicant(
			@RequestHeader(name="authorisation") String email ) throws Exception
	{
		final String methodName = "listJobsServices()";
		logger.info("{}: List Prduct Services", methodName);
		
		RspnsVO rspnsVO = new RspnsVO();
		String userType = null;

		try {

			userType = services.getUserType(email);
			if(APPLICANT.equals(userType)) {
				rspnsVO.setResponseCode(1000);
				rspnsVO.setResponse(services.listJobsApplicant());
				rspnsVO.setResponseMsg("GOOD");
			} else {
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
			}
		} 
		catch(Exception e) {
			logger.error("email:" + email + ":errorMessage:" + e.getMessage());
			rspnsVO.setResponse("SERVER SIDE ERROR");
			rspnsVO.setResponseCode(1010);
			rspnsVO.setResponseMsg(null);
			return new ResponseEntity<RspnsVO>(rspnsVO, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		logger.info("Response : {}", rspnsVO.toString());
		return new ResponseEntity<RspnsVO>(rspnsVO, HttpStatus.OK);

		
	}
	
	@ApiOperation(value = "Get Job", notes = "This is get product service for client.")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping(value = GET_JOB, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RspnsVO> getJob(
			@RequestHeader(name="authorisation") String email,
			@RequestParam String jobID) throws Exception
		{
				final String methodName = "getProduct()";
				logger.info("{}: Get product Service", methodName);
	
				RspnsVO rspnsVO = new RspnsVO();
				String userType = null;

				try {

					userType = services.getUserType(email);
					if(ADMIN.equals(userType)) {
						rspnsVO.setResponseCode(1000);
						rspnsVO.setResponse(services.getJobApplicant(jobID));
						rspnsVO.setResponseMsg("GOOD");
					} else {
						throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
					}
				} 
				catch(Exception e) {
					logger.error("email:" + email + ":errorMessage:" + e.getMessage());
					rspnsVO.setResponse("SERVER SIDE ERROR");
					rspnsVO.setResponseCode(1010);
					rspnsVO.setResponseMsg(null);
					return new ResponseEntity<RspnsVO>(rspnsVO, HttpStatus.INTERNAL_SERVER_ERROR);
				}
				logger.info("Response : {}", rspnsVO.toString());
				return new ResponseEntity<RspnsVO>(rspnsVO, HttpStatus.OK);
		}
	

}
