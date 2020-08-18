package com.leapfinance.assesment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.leapfinance.assesment.service.LoginService;
import com.leapfinance.assesment.vo.BaseResponseVO;
import com.leapfinance.assesment.vo.UserVO;

@RestController
public class AssesmentController {
	
	@Autowired
	private LoginService loginService;
	

	@RequestMapping("/hai")
	public String hello() {
		return "Hai";
	}
	
	@PostMapping("/getUserDetails")
	public ResponseEntity<BaseResponseVO> validateLoginDetails(@RequestBody UserVO userVO) {
		BaseResponseVO baseResponseVO = loginService.validateLoginAPI(userVO);
		return ResponseEntity.ok().body(baseResponseVO);
	}
	
	
	
	@RequestMapping (value = "/saveContentAPI", method = RequestMethod.POST)
	public ResponseEntity<BaseResponseVO> redirectToExternalUrl(@RequestBody UserVO userVO)  {
	    BaseResponseVO saveContentAPI = loginService.saveContentAPI(userVO);
	    return ResponseEntity.ok().body(saveContentAPI);
	}
	
	@RequestMapping(value="/fetchAllUrlDataByUser", method = RequestMethod.POST)
	public ResponseEntity<BaseResponseVO> fetchAllUrlDataByUser(@RequestBody UserVO userVO){
		BaseResponseVO fetchAllAPIData = loginService.fetchAllUrlDataByUser(userVO);
	    return ResponseEntity.ok().body(fetchAllAPIData);
	}
	
	
}
