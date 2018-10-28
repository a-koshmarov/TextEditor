package BL;

import DAL.DAO.UserDAO;
import DAL.DTO.UserDTO;
import Utility.HashGenerator;

public class AuthorizationWizard {
    public static boolean registerUser(String userName, String pass){
        String encrypted = HashGenerator.getHash(pass);
        boolean result = UserDAO.addUser(userName, encrypted);

        if(result) {
            System.out.println("User added successfully");
        }
        return result;
    }

    public static User logInUser(String userName, String pass){
        String encrypted = HashGenerator.getHash(pass);
        UserDTO userDTO = UserDAO.getUser(userName, encrypted);
        if (userDTO == null){
            return null;
        }
        return new User(userDTO);
    }
}
