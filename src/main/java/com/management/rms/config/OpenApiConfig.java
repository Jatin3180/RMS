package com.management.rms.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
	info = @Info(
		title ="Recruitment Management System",
		description ="contains all api for web and app",
		version ="v1"
	),
	servers = {
			@Server(description="LOCAL", url="http://localhost:8000"),
			
			
	}
	
)

public class OpenApiConfig {

}
