package com.stage.projet.config;

import com.stage.projet.service.auth.ApplicationUserDetailsService;
import com.stage.projet.utils.JwUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.persistence.Column;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class ApplicationRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwUtil jwUtil;

    @Autowired
    private ApplicationUserDetailsService userDetailsService;

    public ApplicationRequestFilter(JwUtil jwUtil, ApplicationUserDetailsService userDetailsService) {
        this.jwUtil = jwUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {


        final String authHeader=request.getHeader("Authorization");

        String username=null;
        String jwt=null;

        if(authHeader!=null && authHeader.startsWith("Bearer ")){
            jwt=authHeader.substring(7);
            username=jwUtil.extractUsername(jwt);

        }

        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails=userDetailsService.loadUserByUsername(username);
            if(jwUtil.validateToken(jwt,userDetails)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(
                        userDetails,null,userDetails.getAuthorities()
                );
                usernamePasswordAuthenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);



            }
        }


        chain.doFilter(request,response);

    }

}
