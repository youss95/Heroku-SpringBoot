package com.ksy.server.config.jwt;



interface JwtProps {
		
		String secret = "비밀키";
		String auth = "Bearer ";
		String header = "Authorization";
		int expiration_time = 3000000;
	}

	

