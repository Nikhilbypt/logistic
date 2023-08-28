package com.logistic.config;

import com.logistic.config.JwtUtils;
import com.logistic.config.ValidateToken;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private ValidateToken validateToken;
//
//    @Autowired
//    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestTokenHeader = request.getHeader("authorization");
        List<String> rolesInToken = new ArrayList<>();
        String userName = null;
        String jwtToken = null;

        if(requestTokenHeader != null) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                userName = jwtUtils.extractUsername(jwtToken);
                rolesInToken = jwtUtils.getRolesFromToken(jwtToken);
            }catch (IllegalArgumentException e) {
                System.out.println("unable to get jwt token");
            } catch (ExpiredJwtException e) {
                System.out.println("jwt token has expired");
//                response.getWriter().write("Access forbidden");
//                response.setContentType("text/plain");
//                response.setCharacterEncoding("UTF-8");
//                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//                e.printStackTrace();
            }
        }else{
            System.out.println("jwt token does not start with bearer");
        }
        if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            String jwtId = validateToken.validateToken(userName);
            if(jwtUtils.validateToken(jwtToken, jwtId)){
                final List<GrantedAuthority> roles = new ArrayList<>();
                rolesInToken.stream().forEach(role -> {
                    roles.add(new SimpleGrantedAuthority(role));
                });
                List<GrantedAuthority> authorities = Collections.unmodifiableList(roles);
//          UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(userName);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userName , null , authorities);
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}

