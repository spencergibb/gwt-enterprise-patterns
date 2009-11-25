package us.gibb.dev.gwt.server;

import us.gibb.dev.gwt.command.CommandException;
import us.gibb.dev.gwt.login.Login;
import us.gibb.dev.gwt.login.LoginCommand;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class AppengineLoginHandler extends CommandHandler<LoginCommand, Login> {

    @Override
    public Login execute(LoginCommand command) throws CommandException {
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        Login login = new Login();

        if (user != null) {
          login.setLoggedIn(true);
          login.setEmailAddress(user.getEmail());
          login.setNickname(user.getNickname());
          login.setLogoutUrl(userService.createLogoutURL(command.getRequestUri()));
        } else {
          login.setLoggedIn(false);
          login.setLoginUrl(userService.createLoginURL(command.getRequestUri()));
        }
        return login;
    }

}
