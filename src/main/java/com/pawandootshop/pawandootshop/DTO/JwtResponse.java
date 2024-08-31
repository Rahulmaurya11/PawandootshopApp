package com.pawandootshop.pawandootshop.DTO;

	import java.util.Date;

import lombok.Builder;
	import lombok.Data;

	@Data
	@Builder
	public class JwtResponse {

		private String jwttoken;
		
		private String username;
		
		private Date Expiration;

		public Date getExpiration() {
			return Expiration;
		}

		public void setExpiration(Date expiration) {
			Expiration = expiration;
		}

		public String getJwttoken() {
			return jwttoken;
		}

		public void setJwttoken(String jwttoken) {
			this.jwttoken = jwttoken;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public JwtResponse(String jwttoken, String username) {
			super();
			this.jwttoken = jwttoken;
			this.username = username;
		}

		
		


}
