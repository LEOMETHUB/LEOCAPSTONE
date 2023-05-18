package org.match_service.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {


    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        Optional<String> token = Optional.ofNullable(jwtService.getToken());
        if (token.isPresent()) {
            Boolean valid = jwtService.validateToken(token.get());
            if(Boolean.TRUE.equals(valid)) {
                filterChain.doFilter(request, response);
            }else {
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
            }

        }else {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }


    }

}