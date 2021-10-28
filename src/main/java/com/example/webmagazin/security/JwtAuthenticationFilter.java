package com.example.webmagazin.security;

import com.example.webmagazin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    JwtTokenProvider tokenProvider;
    @Autowired
    UserService userService;


    public String getJwtFromRequest(HttpServletRequest httpServletRequest){
         String token=httpServletRequest.getHeader("Authorization");
         if (StringUtils.hasText(token) && token.substring(0,6).equals("Bearer")){
             return token.substring(6);
         }
         return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        try{
            String jwt=getJwtFromRequest(httpServletRequest);
            if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)){
                String userId=tokenProvider.getUserIdFromJWT(jwt);
                UserDetails userDetails=userService.loadUserByUserId(UUID.fromString(userId));
                UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        }
        catch (Exception ex){
            logger.error("Could not set user authentication in security context",ex);

        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
