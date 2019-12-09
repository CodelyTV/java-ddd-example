package tv.codely.shared.infrastructure.spring;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;

public final class BasicHttpAuthMiddleware implements Filter {
    private final HashMap<String, String> validUsers = new HashMap<String, String>() {{
        put("javi", "barbitas");
        put("rafa", "pelazo");
    }};

    @Override
    public void doFilter(
        ServletRequest request,
        ServletResponse response,
        FilterChain chain
    ) throws IOException, ServletException {
        String authorizationHeader = ((HttpServletRequest) request).getHeader("authorization");

        if (hasIntroducedCredentials(authorizationHeader)) {
            authenticate(authorizationHeader, chain, request, response);
        } else {
            askForCredentials(response);
        }
    }

    private boolean hasIntroducedCredentials(String authorizationHeader) {
        return null != authorizationHeader;
    }

    private void authenticate(
        String authorizationHeader,
        FilterChain chain,
        ServletRequest request,
        ServletResponse response
    ) throws IOException, ServletException {
        String[] auth = decodeAuth(authorizationHeader);
        String   user = auth[0];
        String   pass = auth[1];

        if (isValid(user, pass)) {
            request.setAttribute("authentication_username", user);
            chain.doFilter(request, response);
        } else {
            setInvalidCredentials(response);
        }
    }

    private boolean isValid(String user, String pass) {
        return validUsers.containsKey(user) && validUsers.get(user).equals(pass);
    }

    private String[] decodeAuth(String authString) {
        return new String(Base64.getDecoder().decode(authString.split("\\s+")[1])).split(":");
    }

    private void setInvalidCredentials(ServletResponse response) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.reset();
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }

    private void askForCredentials(ServletResponse response) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.reset();
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.setHeader("WWW-Authenticate", "Basic realm=\"CodelyTV\"");
    }
}
