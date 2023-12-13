package com.abdul.kfh.api.response;

import java.util.List;

public class JwtResponse {
  private String token;
  private String type = "Bearer";
  private Long id;
  private String username;
  private String name;
  private String gender;
  private String status;
  private List<String> roles;

  public JwtResponse(String accessToken, Long id, String username, String name, String gender, String status, List<String> roles) {
    this.token = accessToken;
    this.id = id;
    this.username = username;
    this.name = name;
    this.gender = gender;
    this.status = status;
    this.roles = roles;
  }

  public String getAccessToken() {
    return token;
  }

  public void setAccessToken(String accessToken) {
    this.token = accessToken;
  }

  public String getTokenType() {
    return type;
  }

  public void setTokenType(String tokenType) {
    this.type = tokenType;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

 

  public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public List<String> getRoles() {
    return roles;
  }

public String getToken() {
	return token;
}

public void setToken(String token) {
	this.token = token;
}

public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}

public String getGender() {
	return gender;
}

public void setGender(String gender) {
	this.gender = gender;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public void setRoles(List<String> roles) {
	this.roles = roles;
}
  
  
  
}
