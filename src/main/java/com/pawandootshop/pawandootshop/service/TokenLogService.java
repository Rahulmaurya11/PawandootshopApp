
package com.pawandootshop.pawandootshop.service;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pawandootshop.pawandootshop.enums.LinkType;
import com.pawandootshop.pawandootshop.enums.Purpose;
import com.pawandootshop.pawandootshop.model.TokenLog;
import com.pawandootshop.pawandootshop.repository.TokenLogRepository;

@Service
public class TokenLogService {

	@Autowired
	TokenLogRepository tokenLogRepository;

	public String generateToken(Long id, String username) {

		String token = UUID.randomUUID().toString();

		LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(2);

		// Call method to add token log to database
		addlog(token, id, username, expiryTime);

		return token;

	}

	/*
	 * public boolean isValidToken(String token) { Optional<TokenLog>
	 * tokenLogOptional = tokenLogRepository.findBytoken(token);
	 * 
	 * if (tokenLogOptional.isPresent()) { TokenLog tokenLog =
	 * tokenLogOptional.get();
	 * 
	 * 
	 * if (tokenLog.isValid())
	 * 
	 * { LocalDateTime expiryTime = tokenLog.getExpiryTime(); // Check if the token
	 * is not expired return
	 * 
	 * return isTokenExpired(expiryTime);
	 * 
	 * }
	 * 
	 * } // Token not found or invalid return false;
	 * 
	 * return false;
	 * 
	 * }
	 * 
	 */

	/*
	 * private boolean isTokenExpired(LocalDateTime expiryTime)
	 * 
	 * {
	 * 
	 * return expiryTime != null && expiryTime.isBefore(LocalDateTime.now());
	 * 
	 * }
	 * 
	 */

	public TokenLog addlog(String token, Long id, String username, LocalDateTime expiryTime) {

		TokenLog t1 = new TokenLog();
		t1.setLinkId(id);
		t1.setLinkType(LinkType.CUSTOMER);
		t1.setToken(token);
		t1.setCreatedAt(LocalDateTime.now());
		t1.setValid(true);
		t1.setPurpose(Purpose.LOGIN);
		t1.setUserName(username);
		t1.setExpiryTime(expiryTime);
		// Set expiry time

		return tokenLogRepository.save(t1);
	}

	public TokenLog updateTokenLog(Integer id, TokenLog tokenLog) {
		TokenLog existingStaff = tokenLogRepository.findById(id).orElse(null);
		existingStaff.setUserName(tokenLog.getUserName());
		existingStaff.setToken(tokenLog.getToken());
		existingStaff.setPurpose(tokenLog.getPurpose());
		existingStaff.setLinkType(tokenLog.getLinkType());
		existingStaff.setIp(tokenLog.getIp());
		existingStaff.setLinkId(tokenLog.getLinkId());
		existingStaff.setAttempt(tokenLog.getAttempt());

		return tokenLogRepository.save(existingStaff);

	}

	public List<TokenLog> getTokenLog() {

		return (List<TokenLog>) tokenLogRepository.findAll();

	}

	public Optional<TokenLog> getTokenLogById(Integer id)

	{

		return tokenLogRepository.findById(id);

	}

	// token input validation

	public List<String> validate(TokenLog tokenLog) {

		List<String> error = new ArrayList<>();

		if (tokenLog.getUserName() == null) {
			error.add("TokenLog Username can not be empty");
		}

		if (tokenLog.getToken() == null) {
			error.add("Token can not be empty");
		}

		if (tokenLog.getPurpose() == null) {
			error.add("Purpose can not be empty");
		}

		if (tokenLog.getLinkType() == null) {
			error.add("LinkType can not be empty");
		}

		if (tokenLog.getLinkId() == 0) {
			error.add("LinkId can not be empty");
		}

		if (tokenLog.getAttempt() == 0) {
			error.add("Attempt can not be empty");
		}

		return error;
	}

	public boolean deleteTokenLog(Integer id) {

		boolean exists = tokenLogRepository.existsById(id);

		if (exists) {

			tokenLogRepository.deleteById(id);

			return true;

		}

		else

		{

			return false;

		}

	}

}
