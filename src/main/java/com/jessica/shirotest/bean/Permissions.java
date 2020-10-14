package com.jessica.shirotest.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Permissions {
    private String id;
    private String permissionsName;
	public Permissions(String id, String permissionsName) {
		super();
		this.id = id;
		this.permissionsName = permissionsName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPermissionsName() {
		return permissionsName;
	}
	public void setPermissionsName(String permissionsName) {
		this.permissionsName = permissionsName;
	}
    
}
