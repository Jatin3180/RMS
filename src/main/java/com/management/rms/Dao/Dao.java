package com.management.rms.Dao;

import static com.management.rms.Constants.RmsConstants.QUERY_ADMIN_REGISTRATION;
import static com.management.rms.Constants.RmsConstants.QUERY_USER_REGISTRATION;
import static com.management.rms.Constants.RmsConstants.ADMIN;
import static com.management.rms.Constants.RmsConstants.QUERY_CREATE_JOB;
import static com.management.rms.Constants.RmsConstants.APPLICANT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.jdbc.core.RowMapper;

import com.management.rms.model.JobVO;
import com.management.rms.model.ListJobs;
import com.management.rms.model.ListUsers;
import com.management.rms.model.UserVO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.sql.DataSource;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;
import jakarta.validation.Valid;

@Component
@Service
public class Dao {
	
	private static final Logger logger = (Logger) LoggerFactory.getLogger(Dao.class);
	
	
    private transient static JdbcTemplate jdbcTemplate;
	
	@Autowired
	private transient DataSource datasource;
	 private JdbcTemplate jdbcTemplateObject;

	
	@Autowired
	public Dao(DataSource dataSource) throws SQLException {
		logger.debug("Inside DAO Constructor");
		this.datasource = dataSource;
		jdbcTemplate = new JdbcTemplate(this.datasource);
	}


	public String AdminRegister(UserVO user, String email, String password_hash)throws Exception {
		StringBuilder response = new StringBuilder("ERROR ADMIN REGISTER");
		try {	
				
				// ObjectMapper to convert object into string
				ObjectMapper objectMapper = new ObjectMapper();
				String value1 = objectMapper.writeValueAsString(user);
			
				
					jdbcTemplate.update(QUERY_ADMIN_REGISTRATION, email, value1, new Timestamp(System.currentTimeMillis()) ,ADMIN,password_hash);
					
					return email;
				
		} catch (Exception e) {
			logger.error("ERROR IN DAO - ADMIN REGISTER");
			logger.error(e.getMessage());
			throw e;
		}
		
	}


	public String login(String email, String password) throws Exception{
		try {
			 String query = "SELECT password_hash from rms.users WHERE email = ?";
			    @SuppressWarnings("deprecation")
				String password_hashed = jdbcTemplate.queryForObject(query, new Object[] { email }, String.class);				    
			        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			        if (passwordEncoder.matches(password, password_hashed)) {
			            return email;	       
			            } else {
			            throw new Exception("Password is incorrect");
			        }					
		}catch (Exception e) {
			logger.error("Exception in DAO- login() :{} ", e.getMessage());
			throw new Exception(e.getMessage());
		}
	}


	@SuppressWarnings("deprecation")
	public String getUserType(String email) {
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForObject("SELECT user_type FROM rms.users WHERE email = ?", new Object[] {email}, String.class);
	}


	public StringBuilder createJobOpening(JobVO job, String email) throws Exception {
		StringBuilder response = new StringBuilder("ERROR ADDING NEW PRODUCT");
		try {		
				// ObjectMapper to convert object into string
				ObjectMapper objectMapper = new ObjectMapper();
				String values = objectMapper.writeValueAsString(job);
				
				jdbcTemplate.update(QUERY_CREATE_JOB, email, values, new Timestamp(System.currentTimeMillis()));
				response = new StringBuilder(String.valueOf(job.getTitle()));
				return response;
			
		} catch (Exception e) {
			logger.error("ERROR IN DAO - AddProduct");
			logger.error(e.getMessage());
			throw e;
		}
		
	}


	public String UserRegister(UserVO user, String email, String password_hash) throws Exception {
		StringBuilder response = new StringBuilder("ERROR ADMIN REGISTER");
		try {	
				
				// ObjectMapper to convert object into string
				ObjectMapper objectMapper = new ObjectMapper();
				String value1 = objectMapper.writeValueAsString(user);
			
				
					jdbcTemplate.update(QUERY_USER_REGISTRATION, email, value1, new Timestamp(System.currentTimeMillis()) ,APPLICANT,password_hash);
					
					return email;
				
		} catch (Exception e) {
			logger.error("ERROR IN DAO - ADMIN REGISTER");
			logger.error(e.getMessage());
			throw e;
		}
		
	}


	public String Userlogin(String email, String password) throws Exception{
		try {
			 String query = "SELECT password_hash from rms.users WHERE email = ?";
			    @SuppressWarnings("deprecation")
				String password_hashed = jdbcTemplate.queryForObject(query, new Object[] { email }, String.class);				    
			        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			        if (passwordEncoder.matches(password, password_hashed)) {
			            return email;	       
			            } else {
			            throw new Exception("Password is incorrect");
			        }					
		}catch (Exception e) {
			logger.error("Exception in DAO- login() :{} ", e.getMessage());
			throw new Exception(e.getMessage());
		}
	}


	@SuppressWarnings("deprecation")
	public ArrayList<ListJobs> listJobs(String email) {
		try {	
			ArrayList<ListJobs> listjob = new ArrayList<>();
			String query="SELECT * FROM rms.job_openings WHERE email = ?";
		     
			
			jdbcTemplate.query(query, new Object[] {email},
					new RowMapper<ArrayList<ListJobs>>() {
						public ArrayList<ListJobs> mapRow(ResultSet rs, int rowNum) throws SQLException {
							logger.info("extractData()....");
							ListJobs req = new ListJobs();
							String json = rs.getString("job_data");
						
							ObjectMapper objectMapper = new ObjectMapper();
							JobVO object;
							try {
								object = objectMapper.readValue(json, JobVO.class);
								req.setJob(object);
								listjob.add(req);
								
								
							} catch (JsonMappingException e) {
								e.printStackTrace();
							} catch (JsonProcessingException e) {
								e.printStackTrace();
							}
							
						
						

							return listjob;
						}
					});
			logger.info("GETTING Services..");
			return listjob;
		} catch (Exception e) {
			logger.error("Exception Occurred while Connecting to database :{} " + e.getMessage(), e);
			
		}
		return new ArrayList<>();

	}



	@SuppressWarnings("deprecation")
	public ArrayList<ListUsers> listUsers(String usertype) {
		try {	
			ArrayList<ListUsers> listuser = new ArrayList<>();
			String query="SELECT * FROM rms.users WHERE user_type = ?";
		     
			
			jdbcTemplate.query(query, new Object[] {usertype},
					new RowMapper<ArrayList<ListUsers>>() {
						public ArrayList<ListUsers> mapRow(ResultSet rs, int rowNum) throws SQLException {
							logger.info("extractData()....");
							ListUsers req = new ListUsers();
							String json = rs.getString("user_data");
						
							ObjectMapper objectMapper = new ObjectMapper();
							UserVO object;
							try {
								object = objectMapper.readValue(json, UserVO.class);
								req.setUser(object);
								listuser.add(req);
								
								
							} catch (JsonMappingException e) {
								e.printStackTrace();
							} catch (JsonProcessingException e) {
								e.printStackTrace();
							}
							
						
						

							return listuser;
						}
					});
			logger.info("GETTING Services..");
			return listuser;
		} catch (Exception e) {
			logger.error("Exception Occurred while Connecting to database :{} " + e.getMessage(), e);
			
		}
		return new ArrayList<>();

	}


	@SuppressWarnings("deprecation")
	public ArrayList<ListJobs> listJobsApplicant() {
		try {	
			ArrayList<ListJobs> listjob = new ArrayList<>();
			String query="SELECT * FROM rms.job_openings";
		     
			
			jdbcTemplate.query(query, new Object[]{},
					new RowMapper<ArrayList<ListJobs>>() {
						public ArrayList<ListJobs> mapRow(ResultSet rs, int rowNum) throws SQLException {
							logger.info("extractData()....");
							ListJobs req = new ListJobs();
							String json = rs.getString("job_data");
						
							ObjectMapper objectMapper = new ObjectMapper();
							JobVO object;
							try {
								object = objectMapper.readValue(json, JobVO.class);
								req.setJob(object);
								listjob.add(req);
								
								
							} catch (JsonMappingException e) {
								e.printStackTrace();
							} catch (JsonProcessingException e) {
								e.printStackTrace();
							}
							return listjob;
						}
					});
			logger.info("GETTING Services..");
			return listjob;
		} catch (Exception e) {
			logger.error("Exception Occurred while Connecting to database :{} " + e.getMessage(), e);
			
		}
		return new ArrayList<>();

	}

	@SuppressWarnings("deprecation")
	public JobVO getJob(String email, String jobID) {
        String exist="SELECT 1 from rms.job_openings where email = ? AND job_id = ? ";
		
		boolean isExists = jdbcTemplate.queryForObject(exist,new Object[] { email, jobID }, boolean.class);
		if(isExists) {
			try {
			    String GET_JOB = "SELECT job_data from rms.job_openings where email = ? AND job_id = ?";
			    String json = jdbcTemplate.queryForObject(GET_JOB, new Object[] { email, jobID}, String.class);
				ObjectMapper objectMapper = new ObjectMapper();
				JobVO jobObject = objectMapper.readValue(json, JobVO.class);
				return jobObject;
			
			}catch (Exception e) {
				logger.error("Exception Occurred while Connecting to database :{} ", e.getMessage());
			}
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	public JobVO getJobApplicant(String jobID) {
        String exist="SELECT 1 from rms.job_openings where job_id = ? ";
		
		boolean isExists = jdbcTemplate.queryForObject(exist,new Object[] {jobID }, boolean.class);
		if(isExists) {
			try {
			    String GET_JOB = "SELECT job_data from rms.job_openings where job_id = ?";
			    String json = jdbcTemplate.queryForObject(GET_JOB, new Object[] { jobID}, String.class);
				ObjectMapper objectMapper = new ObjectMapper();
				JobVO jobObject = objectMapper.readValue(json, JobVO.class);
				return jobObject;
			
			}catch (Exception e) {
				logger.error("Exception Occurred while Connecting to database :{} ", e.getMessage());
			}
		}
		return null;
	}
	


}
