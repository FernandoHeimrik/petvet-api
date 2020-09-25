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
import com.backend.api.view.VeterinaryView;

public class CustomTokenEnhancer implements TokenEnhancer {

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		UserSystem userSystem = (UserSystem) authentication.getPrincipal();
		Map<String, Object> addInfo = new HashMap<>();
		if (userSystem.getUser() != null) {
			UserView userView = modelMapper.map(userSystem.getUser(), UserView.class);
			addInfo.put("user", userView);
		}
		if (userSystem.getVet() != null) {
			VeterinaryView vetView = modelMapper.map(userSystem.getVet(), VeterinaryView.class);
			addInfo.put("vet", vetView);
		}

		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(addInfo);
		return accessToken;
	}
}
