package com.unbound.interview.ms.rest;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.unbound.interview.ms.model.RequestContext;
import com.unbound.interview.ms.model.ResponseContext;
import com.unbound.interview.ms.rest.exceptions.ExecutionException;

@RestController
@RequestMapping("/rsa")
public class RSAController { 
	private static final Logger logger = LogManager.getLogger(RSAController.class);
	
	@PostMapping("")
	@ResponseBody
	public ResponseContext generate() throws ExecutionException {
		logger.info("RSAController generate : ");
		
		try {
			String keyId = RSAGenerator.generateKeyId(true);

			logger.info("RSAController generate completed : " + keyId);
			
			return new ResponseContext(keyId, null);
		} catch (Exception e) {
			logger.error("API generate faild with", e);
			
			throw new ExecutionException("API generate faild", e);
		}
	}
	
	@DeleteMapping("/{keyId}")
	public void delete(@PathVariable String keyId) throws ExecutionException {
		logger.info("RSAController delete : " + keyId);
		
		try {
			RSAGenerator.deleteKeyId(keyId);
			
			logger.info("RSAController delete completed : " + keyId);
		} catch (Exception e) {
			logger.error("API delete faild with", e);
			
			throw new ExecutionException("API delete faild", e);
		}
	}

	@PutMapping("/{keyId}")
	@ResponseBody
	public ResponseContext signOn(@PathVariable String keyId, @RequestBody() String data) throws ExecutionException {
		logger.info("RSAController signOn : " + data);
		
		try {
			String signature = RSAGenerator.signOn(keyId, data);
					
			logger.info("RSAController signOn completed : " + signature);
			
			return new ResponseContext(null, signature);
		} catch (Exception e) {
			logger.error("API signOn faild with", e);
			
			throw new ExecutionException("API signOn faild", e);
		}
	}

	@PostMapping("/{keyId}")
	@ResponseBody
	public ResponseContext verify(@PathVariable String keyId, @RequestBody() RequestContext requestContext) throws ExecutionException {
		logger.info("RSAController verify : " + requestContext);
		
		try {
			boolean isValid = RSAGenerator.verify(keyId, requestContext.getData(), requestContext.getSignature());
			
			logger.info("RSAController verify completed : " + isValid);
			
			return new ResponseContext(isValid);
		} catch (Exception e) {
			logger.error("API verify faild with", e);
			
			throw new ExecutionException("API verify faild", e);
		}
	}

	@GetMapping("")
	@ResponseBody
	public ResponseContext list() throws ExecutionException {
		logger.info("RSAController list : ");
		
		try {
			List<String> keyIds = RSAGenerator.getAll();
			
			logger.info("RSAController signOn completed : " + keyIds);
			
			return new ResponseContext(keyIds);
		} catch (Exception e) {
			logger.error("API list faild with", e);
			
			throw new ExecutionException("API list faild", e);
		}
	}
}
