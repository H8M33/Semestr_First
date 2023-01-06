package model.service;

public class EmailConfirmService {
    public static String confirm(String code, String entered, long id){
        if (entered.equals("")){
            return "Please fill in all required fields";
        }
        if (code.equals(entered)){
            return UserService.activateUser(id);
        }
        return "Wrong code";
    }
}
