package com.librarySpring.librarySpring.Security;

import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class GraphQLSecurityInterceptor implements WebGraphQlInterceptor {

    private final UserDetailsService userDetailsService;

    public GraphQLSecurityInterceptor(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Mono<WebGraphQlResponse> intercept(WebGraphQlRequest request, Chain chain) {
        // Extract the Authorization header
        List<String> authHeaders = request.getHeaders().getOrEmpty("Authorization");

        if (!authHeaders.isEmpty()) {
            String authHeader = authHeaders.get(0);
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                String token = authHeader.substring(7);
                String username = null;

                try {
                    username = JwtUtil.getClaims(token).getSubject();

                    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                        // Load user details including authorities
                        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                        if (JwtUtil.isTokenValid(token)) {
                            UsernamePasswordAuthenticationToken authentication =
                                    new UsernamePasswordAuthenticationToken(
                                            userDetails,
                                            null,
                                            userDetails.getAuthorities()
                                    );

                            SecurityContextHolder.getContext().setAuthentication(authentication);
                        }
                    }
                } catch (Exception e) {
                    // Token is invalid, just continue with the chain
                    // Authentication will remain null
                }
            }
        }

        // Continue processing the request
        return chain.next(request);
    }
}