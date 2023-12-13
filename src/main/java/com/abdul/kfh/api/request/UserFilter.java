package com.abdul.kfh.api.request;

public class UserFilter {
	public long userid;
	public String status;
	public String gender;
	public String username;
	public String name;
	@Override
	public String toString() {
		return "UserFilter [userid=" + userid + ", status=" + status + ", gender=" + gender + ", username=" + username
				+ ", name=" + name + "]";
	}
	
	

}
