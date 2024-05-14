package com.management.rms.Constants;

public class RmsConstants {
	
	public static final String ADMIN_BASE_URL = "/admin";
	public static final String USER_BASE_URL = "/user";
	public static final String ADMIN_REGISTRATION = "/registration";
	public static final String APPLICANT_REGISTRATION = "/applicant";
	public static final String LIST_JOBS = "/jobs";
	public static final String LIST_USERS = "/users";
	public static final String APPLICANT = "applicant";
	public static final String ADMIN = "ADMIN";
	public static final String ADMIN_LOGIN = "/adminlogin";
	public static final String APPLICANT_LOGIN = "/applicantlogin";
	public static final String JOB_OPENINGS = "/jobopening";
	public static final String GET_JOB = "/job";
	
	
	
//	queries
	
	public static final String QUERY_ADMIN_REGISTRATION = "INSERT INTO rms.users (email, user_data, created_at, user_type, password_h) VALUES (?, ?, ?, ?, ?);";
	public static final String QUERY_CREATE_JOB = "INSERT INTO rms.job_openings (email, job_data, created_at) VALUES (?, ?, ?);";
	public static final String QUERY_USER_REGISTRATION = "INSERT INTO rms.users (email, user_data, created_at, user_type, password_h) VALUES (?, ?, ?, ?, ?);";
}
