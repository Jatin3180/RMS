package com.management.rms.model;

public class UserVO {
	
	private String Name;
	
	private String Email;
	
	private String Address;
	
	private String UserType;
	
	private String PasswordHash;
	
	private String ProfileHeadline;
	
	ProfileVO profile;

	@Override
	public String toString() {
		return "UserVO [Name=" + Name + ", Email=" + Email + ", Address=" + Address + ", UserType=" + UserType
				+ ", PasswordHash=" + PasswordHash + ", ProfileHeadline=" + ProfileHeadline + ", profile=" + profile
				+ "]";
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getUserType() {
		return UserType;
	}

	public void setUserType(String userType) {
		UserType = userType;
	}

	public String getPasswordHash() {
		return PasswordHash;
	}

	public void setPasswordHash(String passwordHash) {
		PasswordHash = passwordHash;
	}

	public String getProfileHeadline() {
		return ProfileHeadline;
	}

	public void setProfileHeadline(String profileHeadline) {
		ProfileHeadline = profileHeadline;
	}

	public ProfileVO getProfile() {
		return profile;
	}

	public void setProfile(ProfileVO profile) {
		this.profile = profile;
	}
	
	

}
