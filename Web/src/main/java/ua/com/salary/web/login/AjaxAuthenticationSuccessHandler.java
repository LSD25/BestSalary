package ua.com.salary.web.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

public class AjaxAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private static final Logger LOG = LoggerFactory.getLogger(AjaxAuthenticationSuccessHandler.class);

    @Autowired
    private MappingJackson2HttpMessageConverter mJsonConverter;

    @Autowired
    private MessageSource mMessageSource;

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
            throws IOException, ServletException {
        super.onAuthenticationSuccess(request, response, auth);
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
        String message = this.mMessageSource.getMessage(exceptionMessage, null, locale);
        if (StringUtils.isEmpty(message)) {
            message = this.mMessageSource.getMessage("error.login", null, locale);
        }
        return message;
    }

}