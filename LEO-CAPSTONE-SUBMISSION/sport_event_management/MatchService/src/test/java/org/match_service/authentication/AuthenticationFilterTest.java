package org.match_service.authentication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.lang.NonNull;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class AuthenticationFilterTest {

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private MockFilterChain chain;
    private AuthenticationFilter filter;
    private HttpServlet servlet;

    private List<OncePerRequestFilter> invocations;

    @BeforeEach
    @SuppressWarnings("serial")
    public void setup() {
        this.servlet = new HttpServlet() {
        };
        this.request = new MockHttpServletRequest();
        this.response = new MockHttpServletResponse();
        this.chain = new MockFilterChain();
        this.invocations = new ArrayList<>();
        this.filter = new AuthenticationFilter() {
            @Override
             public void doFilterInternal(@NonNull HttpServletRequest request,
                                          @NonNull  HttpServletResponse response,
                                          @NonNull FilterChain filterChain)
                    throws ServletException, IOException {
                AuthenticationFilterTest.this.invocations.add(this);
                filterChain.doFilter(request, response);
            }
        };
    }

    @Test
    void doFilterOnce() throws ServletException, IOException {
        this.filter.doFilter(this.request, this.response, this.chain);
        assertThat(this.invocations).containsOnly(this.filter);
    }

    @Test
    void doFilterMultiOnlyInvokesOnce() throws ServletException, IOException {
        this.filter.doFilter(this.request, this.response,
                new MockFilterChain(this.servlet, this.filter));
        assertThat(this.invocations).containsOnly(this.filter);
    }

    @Test
    void doFilterOtherSubclassInvoked() throws ServletException, IOException {
        OncePerRequestFilter filter2 = new OncePerRequestFilter() {
            @Override
            protected void doFilterInternal(@NonNull HttpServletRequest request,
                                            @NonNull HttpServletResponse response,
                                            @NonNull FilterChain filterChain)
                    throws ServletException, IOException {
                AuthenticationFilterTest.this.invocations.add(this);
                filterChain.doFilter(request, response);
            }
        };
        this.filter.doFilter(this.request, this.response,
                new MockFilterChain(this.servlet, filter2));

        assertThat(this.invocations).containsOnly(this.filter, filter2);
    }

}