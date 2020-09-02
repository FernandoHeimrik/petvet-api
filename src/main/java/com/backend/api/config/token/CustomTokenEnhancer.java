package com.backend.api.config.token;

import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import com.backend.api.security.UserSystem;
import com.backend.api.view.UserView;

public class CustomTokenEnhancer implements TokenEnhancer {
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		UserSystem userSystem = (UserSystem) authentication.getPrincipal();
		
		UserView userView = modelMapper.map(userSystem.getUser(), UserView.class);

		Map<String, Object> addInfo = new HashMap<>();
		addInfo.put("user", userView);

		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(addInfo);
		return accessToken;
	}
}
