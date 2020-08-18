package com.leapfinance.assesment.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.leapfinance.assesment.model.LeapFinanceAsessment;
import com.leapfinance.assesment.model.SaveUrlContent;
import com.leapfinance.assesment.repository.LeapFinanceRepository;
import com.leapfinance.assesment.repository.SaveUrlContentRepository;
import com.leapfinance.assesment.vo.BaseResponseVO;
import com.leapfinance.assesment.vo.UserVO;

@Service
public class LoginService {
	
	@Autowired
	private LeapFinanceRepository leapFinanceRepo;
	
	@Autowired
	private SaveUrlContentRepository saveUrlRepo;
	
	private BaseResponseVO baseResponseVO = BaseResponseVO.getInstance();
	
	

	public BaseResponseVO validateLoginAPI(UserVO userVO) {
		LeapFinanceAsessment userDtls = leapFinanceRepo.findLoginDetails(userVO.getUserId(), userVO.getPassword());
		LeapFinanceAsessment leapFinanceAssesment = new LeapFinanceAsessment();
		if(userDtls != null) {
			leapFinanceAssesment.setUserId(userVO.getUserId());
			leapFinanceAssesment.setPassword(userDtls.getPassword());
			leapFinanceAssesment.setLoggedInTime(LocalDateTime.now());
			leapFinanceAssesment.setLoggedInMessage("Successfully Logged in..");
			leapFinanceRepo.save(leapFinanceAssesment);
			baseResponseVO.setResponseCode(HttpStatus.OK.value());
			baseResponseVO.setResponseMessage("SUCCESS");
			baseResponseVO.setData(leapFinanceAssesment);
		}else {
			baseResponseVO.setResponseCode(HttpStatus.BAD_REQUEST.value());
			baseResponseVO.setResponseMessage("INVALID CREDENTIALS");
		}
		return baseResponseVO;
	}



	public BaseResponseVO saveContentAPI(UserVO userVO) {
		LeapFinanceAsessment userDtls = leapFinanceRepo.findLoginDetails(userVO.getUserId(), userVO.getPassword());
		SaveUrlContent saveUrlContent = new SaveUrlContent();
		if(userDtls != null) {
			userDtls.setLoggedInTime(LocalDateTime.now());
			userDtls.setLoggedInMessage("Successfully Logged in..");
			leapFinanceRepo.save(userDtls);
			URL urlForRequest;
			try {
				urlForRequest = new URL(userVO.getUrl());
				String readLine = null;
			    HttpURLConnection conection = (HttpURLConnection) urlForRequest.openConnection();
			    int responseCode = conection.getResponseCode();
			    if (responseCode == HttpURLConnection.HTTP_OK ||
			    		responseCode == HttpURLConnection.HTTP_MOVED_PERM ||
			    		responseCode == HttpURLConnection.HTTP_SEE_OTHER ||
			    		responseCode == HttpURLConnection.HTTP_MOVED_TEMP) {
			        BufferedReader in = new BufferedReader(
			            new InputStreamReader(conection.getInputStream()));
			        StringBuffer response = new StringBuffer();
			        while ((readLine = in .readLine()) != null) {
			            response.append(readLine);
			        } in .close();
			        // print result
			        System.out.println("JSON String Result " + response.toString());
			        saveUrlContent.setUrl(userVO.getUrl());
			        saveUrlContent.setUrlContent(response.toString());
			        saveUrlContent.setLeapFinanceAssesment(userDtls);
			        saveUrlRepo.save(saveUrlContent);
			        baseResponseVO.setResponseCode(HttpStatus.OK.value());
			        baseResponseVO.setResponseMessage("SUCCESS");
			        baseResponseVO.setData(saveUrlContent);
			        
			       
			    } else {
			        System.out.println("URL NOT WORKED");
			    }
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			baseResponseVO.setResponseCode(HttpStatus.BAD_REQUEST.value());
	        baseResponseVO.setResponseMessage("INVALID CREDENTIALS");
	    }
		return baseResponseVO;
	}



	public BaseResponseVO fetchAllUrlDataByUser(UserVO userVO) {
		List<SaveUrlContent> saveUrlContent; 
		UserVO usrData;
		List<UserVO> usrDataList = new ArrayList<>();
 		
		LeapFinanceAsessment userDtls = leapFinanceRepo.findLoginDetails(userVO.getUserId(), userVO.getPassword());
		if(userDtls != null) {
			userDtls.setLoggedInTime(LocalDateTime.now());
			userDtls.setLoggedInMessage("Successfully Logged in..");
			leapFinanceRepo.save(userDtls);
			saveUrlContent = new ArrayList<>();
			saveUrlContent = saveUrlRepo.findByUserId(userDtls.getUserId());
			for(SaveUrlContent urlData: saveUrlContent) {
				usrData =  new UserVO();
				usrData.setSaveUrlId(urlData.getId());
				usrData.setUserId(urlData.getLeapFinanceAssesment().getUserId());
				usrData.setUrl(urlData.getUrl());
				usrDataList.add(usrData);
			}
			baseResponseVO.setResponseCode(HttpStatus.OK.value());
			baseResponseVO.setResponseMessage("SUCCESS");
			baseResponseVO.setData(usrDataList);
		}else {
			baseResponseVO.setResponseCode(HttpStatus.BAD_REQUEST.value());
			baseResponseVO.setResponseMessage("INVALID CREDENTIALS");
		}
		return baseResponseVO;
	}
	
	

	
}
