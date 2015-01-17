package com.nazi.controller;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.nazi.App;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
public abstract class BaseWebTest {
	protected MockMvc mockMvc;

	@Autowired
	private WebApplicationContext applicationContext;

	@Autowired
	private FilterChainProxy springSecurityFilterChain;

	protected static MockHttpSession loginAsUser() {
		MockHttpSession mockHttpSession = new MockHttpSession();
		mockHttpSession.setAttribute(SPRING_SECURITY_CONTEXT_KEY,
				new MockSecurityContext(
						new UsernamePasswordAuthenticationToken(
								"nazila.akbari87@gmail.com", "12345678")));
		return mockHttpSession;
	}

	@Before
	public void init() {
		mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext)
				.addFilter(springSecurityFilterChain).build();
	}
	
	public static class MockSecurityContext implements SecurityContext {

        private static final long serialVersionUID = -1386535243513362694L;

        private Authentication authentication;

        public MockSecurityContext(Authentication authentication) {
            this.authentication = authentication;
        }
	
	@Override
    public Authentication getAuthentication() {
        return this.authentication;
    }

    @Override
    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }
	}
}
