package BL;

import DAL.DAO.UserDAO;
import DAL.DTO.UserDTO;
import Utility.HashGenerator;
import Utility.Logger;

import java.sql.SQLException;

public class AuthorizationWizard {

    private static UserDAO userDAO = new UserDAO();

    public static void registerUser(String userName, String pass) {
        String encrypted = HashGenerator.getHash(pass);

        Logger.measureTime(()-> userDAO.add(new UserDTO(userName, encrypted)));

        System.out.println("User added successfully");
    }

    public static User logInUser(String userName, String pass) {
        String encrypted = HashGenerator.getHash(pass);
        UserDTO userDTO;
        try {
            userDTO = userDAO.get(new UserDTO(userName, encrypted));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (userDTO == null) {
            return null;
        }
        return new User(userDTO);
    }
}
