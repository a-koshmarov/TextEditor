package bl.Managers;

import bl.User;
import dal.dao.DAO;
import dal.dto.UserDTO;
import utility.HashGenerator;
import utility.Logger;

public class AuthorizationWizard {

    private final DAO<UserDTO> userDAO;

    public AuthorizationWizard(DAO<UserDTO> userDAO){
        this.userDAO = userDAO;
    }

    public User registerUser(String userName, String pass, int position) {
        String encrypted = HashGenerator.getHash(pass);
        User user = new User(userName, encrypted, position);

        Logger.getInstance().log(() -> userDAO.add(user.getUserDTO()));

        System.out.println("User added successfully");

        return user;
    }

    public User logInUser(String userName, String pass) {
        String encrypted = HashGenerator.getHash(pass);
        UserDTO userDTO;
        User user = new User(userName, encrypted);

        userDTO = Logger.getInstance().logWithReturn(
                () -> userDAO.get(user.getUserDTO())
        );

        if (userDTO == null) {
            return null;
        }
        return new User(userDTO);
    }
}
