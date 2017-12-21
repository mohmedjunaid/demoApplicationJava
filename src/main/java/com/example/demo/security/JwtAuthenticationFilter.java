package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.entity.AuthInfo;
import com.example.demo.service.AuthTokenService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by babu.kannar@indianic.com on 10/12/2016.
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private AuthTokenService authTokenService;

    @Value("${profile.image.name}")
    private String profileImageName;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        String urlAuthToken = request.getParameter("auth_token");
        boolean skipOriginCheck = false;

        String token = null;
        if(header != null && header.startsWith("Bearer ")) {
            token = header.substring(7);
        }

        if(token == null) {
            if(StringUtils.hasText(urlAuthToken)
                    && request.getMethod().equals(RequestMethod.GET.toString())
                    && (request.getRequestURL().toString().contains(profileImageName) || request.getRequestURL().toString().contains("userAlbum"))){
                token = urlAuthToken;
                skipOriginCheck = true;
            }
        }

        if(!skipOriginCheck && !StringUtils.hasText(request.getHeader("Origin")) &&
                !StringUtils.hasText(response.getHeader("Access-Control-Allow-Origin"))) {
            skipOriginCheck = true;
        }

        if(!request.getMethod().equals("OPTIONS")
                && token != null
                && SecurityContextHolder.getContext().getAuthentication() == null) {
            AuthInfo authInfo = authTokenService.getAuthToken(token, true);
            if (authInfo != null && (skipOriginCheck || authInfo.getOrigin().equals(request.getHeader("Origin")))) {
                int userId = authInfo.getUserId();
                User userDetails = new User(userId+"", token, AuthorityUtils.commaSeparatedStringToAuthorityList(authInfo.getRole().toString()));
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, token, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        chain.doFilter(request, response);
    }

}