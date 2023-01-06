package model.service;

import jakarta.servlet.http.HttpServletRequest;
import model.entity.User;
import model.repository.user.UserRepository;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

public class UserService {


    public static String signUp(String username, String password, String email, InputStream fileContent) {
        if (email.equals("") || username.equals("")|| password.equals("")) {
            return "Please fill in all required fields";
        }
        return UserRepository.createUser(username,password,email, fileContent);
    }


    public static String signIn(String email, String password, HttpServletRequest request){
        if (email.equals("")||password.equals("")){
            return "Please fill in all required fields";
        }
        return UserRepository.getUser(email, password, request);
    }

    public static String signInByID(Long id, HttpServletRequest request){
        return UserRepository.getUser(id, request);
    }

    public static long getIDByUsername(String username){
        return UserRepository.getIDByUsername(username);
    }
    public static long getIDByEmail(String email){
        return UserRepository.getIDByEmail(email);
    }

    public static String updateUserEmail(long id, String email){
        if (email.equals("")) {
            return "Please fill in all required fields";
        }
        return UserRepository.updateUserEmail(id, email);
    }

    public static String updateUserPassword(long id, String password){
        if (password.equals("")){
            return "Please fill in all required fields";
        }
        return UserRepository.updateUserPassword(id, password.hashCode());
    }

    public static String getUsername(long user_id) {
        return UserRepository.getUsername(user_id);
    }

    public static boolean existUser(String id){
        try {
            long l = Long.parseLong(id);
            return UserRepository.existsUser(l);
        }
        catch (NumberFormatException e){
            return false;
        }
    }
    public static boolean existUser(Long id){
        return UserRepository.existsUser(id);
    }

    public static boolean existUsername(String username){
        return UserRepository.existsUsername(username);
    }

    public static String activateUser(long id){
        return UserRepository.activateUser(id);
    }

    public static String updateUserStatus(long id, int status){return UserRepository.updateUserStatus(id, status);}

    public static int getUserStatus(String id){
        return UserRepository.getUserStatus(Long.parseLong(id));
    }
    public static int getUserStatus(long id){
        return UserRepository.getUserStatus(id);
    }

    public static User getUserById(String id){
        return UserRepository.getUser(Long.parseLong(id));
    }

    public static void updateUserName(String username, Long id) {

    }

    public static List<User> getUserList() {
        return UserRepository.getUserList();
    }

    public static void deleteUsers(LinkedList<User> list) {
        UserRepository.deleteUsers(list);
    }
}
