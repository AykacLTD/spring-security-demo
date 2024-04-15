package com.aykacltd.sec.demo;

import com.aykacltd.sec.demo.controller.MainController;
import com.aykacltd.sec.demo.service.CustomUserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MainControllerTest {

    @Mock
    private CustomUserDetailsService customUserDetailsService;

    @Mock
    private PasswordEncoder passwordEncoder;

    private MainController mainController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mainController = new MainController(customUserDetailsService, passwordEncoder);
    }

    @Test
    public void testLogin() {
        String result = mainController.login();
        assertEquals("login", result);
    }

    @Test
    public void testDoLoginSuccess() {
        Map<String, String> user = new HashMap<>();
        user.put("username", "test");
        user.put("password", "test");

        UserDetails userDetails = mock(UserDetails.class);
        when(customUserDetailsService.loadUserByUsername("test")).thenReturn(userDetails);
        when(passwordEncoder.matches("test", userDetails.getPassword())).thenReturn(true);

        String result = mainController.dologfin(user);
        assertEquals("success", result);
    }

    @Test
    public void testDoLoginError() {
        Map<String, String> user = new HashMap<>();
        user.put("username", "test");
        user.put("password", "test");

        UserDetails userDetails = mock(UserDetails.class);
        when(customUserDetailsService.loadUserByUsername("test")).thenReturn(userDetails);
        when(passwordEncoder.matches("test", userDetails.getPassword())).thenReturn(false);

        String result = mainController.dologfin(user);
        assertEquals("error", result);
    }

    @Test
    public void testRegister() {
        Map<String, String> user = new HashMap<>();
        user.put("username", "test");
        user.put("password", "test");
        user.put("role", "USER");

        String result = mainController.user(user);
        assertEquals("home", result);
    }
}