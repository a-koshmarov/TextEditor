package client;

import dao.UserEntity;

public class Context {
    private static Context context = new Context();
    private static UserEntity user;

    private Context(){}

    public static Context getInstance(){
        return context;
    }

    public UserEntity getUser(){
        return user;
    }

    public boolean isAuthorized(){
        return user!=null;
    }

    void authorize(String userName, String pass){
        UserManager userManager = new UserManager();
        user = userManager.getUserEntity(userName, pass);
    }

    public void logOut(){
        user = null;
    }
}
