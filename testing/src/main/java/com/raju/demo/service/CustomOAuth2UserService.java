package com.raju.demo.service;

import com.raju.demo.oauth.CustomOAuth2User;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user =  super.loadUser(userRequest);
        CustomOAuth2User loggedInUser = new CustomOAuth2User(user);
        System.out.println(loggedInUser.getAttributes());
        System.out.println(loggedInUser.getAuthorities());
        System.out.println(loggedInUser.getEmail());
        System.out.println(loggedInUser.getName());
        return new com.raju.demo.oauth.CustomOAuth2User(user);
    }
}
