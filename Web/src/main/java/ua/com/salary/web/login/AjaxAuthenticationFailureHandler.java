package ua.com.salary.web.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public class AjaxAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private static final Logger LOG = LoggerFactory.getLogger(AjaxAuthenticationFailureHandler.class);

    @Autowired
    private MappingJackson2HttpMessageConverter mJsonConverter;

    @Autowired
    private MessageSource mMessageSource;

    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {
        Map<String, Object> responseMap = new LinkedHashMap<>();
        responseMap.put("success", false);
        responseMap.put("msg", findErrorMessage(exception.getMessage()));
        writeJson(responseMap, response);
    }

    private void writeJson(Object jsonBean, HttpServletResponse response) {
        MediaType jsonMimeType = MediaType.APPLICATION_JSON;
        if (this.mJsonConverter.canWrite(jsonBean.getClass(), jsonMimeType)) {
            try {
                this.mJsonConverter.write(jsonBean, jsonMimeType, new ServletServerHttpResponse(response));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            LOG.info("json Converter cant write class " + jsonBean.getClass());
        }
    }

    private String findErrorMessage(String exceptionMessage) {
        Locale locale = LocaleContextHolder.getLocale();
        exceptionMessage = exceptionMessage.replaceAll(" ", ".").toLowerCase().trim();
        String message;
        try {
            message = this.mMessageSource.getMessage(exceptionMessage, null, locale);
        } catch (Exception exc) {
            exc.getStackTrace();
            message = this.mMessageSource.getMessage("error.login", null, locale);
        }
        return message;
    }

}