package BL;

import DAL.DAO.DAO;
import DAL.DTO.UserDTO;
import Utility.HashGenerator;
import Utility.Logger;

public class AuthorizationWizard {

    private final DAO<UserDTO> userDAO;

    public AuthorizationWizard(DAO<UserDTO> userDAO){
        this.userDAO = userDAO;
    }

    public void registerUser(String userName, String pass) {
        String encrypted = HashGenerator.getHash(pass);

        Logger.log(() -> userDAO.add(new UserDTO(userName, encrypted)));

        System.out.println("User added successfully");
    }

    public User logInUser(String userName, String pass) {
        String encrypted = HashGenerator.getHash(pass);
        UserDTO userDTO;

        userDTO = Logger.logWithReturn(
                () -> userDAO.get(new UserDTO(userName, encrypted))
        );
        if (userDTO == null) {
            return null;
        }
        return new User(userDTO);
    }
}
