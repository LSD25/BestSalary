package ua.com.salary.web.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomLoginUrlAuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {

    private static final Logger LOG = LoggerFactory.getLogger(CustomLoginUrlAuthenticationEntryPoint.class);

    private static final String HTTP = "http";

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        String redirectUrl = null;
        if (isUseForward()) {
            if (isForceHttps() && HTTP == request.getScheme().intern()) {
                redirectUrl = buildHttpsRedirectUrlForRequest(request);
            }
            if (redirectUrl == null) {
                String loginForm = determineUrlToUseForThisRequest(request, response, authException);
                LOG.debug("Server side forward to: " + loginForm);
                request.getRequestDispatcher(loginForm).forward(request, response);
                return;
            }
        } else {
            redirectUrl = buildRedirectUrlToLoginPage(request, response, authException);
        }
        new DefaultRedirectStrategy().sendRedirect(request, response, redirectUrl + "?redirectTo=" + request.getServletPath());
    }
}
