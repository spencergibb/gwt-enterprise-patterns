package us.gibb.dev.gwt.login;

import us.gibb.dev.gwt.command.Command;

public class LoginCommand implements Command<Login> {
    private static final long serialVersionUID = 4881753067481464647L;

    private String requestUri;

    public LoginCommand(String requestUri) {
        super();
        this.requestUri = requestUri;
    }

    public String getRequestUri() {
        return requestUri;
    }
    
}
