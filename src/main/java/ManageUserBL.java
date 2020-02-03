import java.util.ArrayList;

public class ManageUserBL {
    private IUserConfigRepository userConfigRepository;

    public ManageUserBL(IUserConfigRepository userConfigRepository){
        this.userConfigRepository = userConfigRepository;
    }

    public boolean validatePassword(String password) {
        String regExp = userConfigRepository.passwordRegExp();
        return password.matches(regExp);
    }

    public boolean validateEmail(String email) {
        String regExp = userConfigRepository.emailRegExp();
        return email.matches(regExp);
    }

    public boolean validateCelNumber(String cel) {
        String regExp = userConfigRepository.celRegExp();
        return isTextBlank(cel) && cel.matches(regExp);
    }

    public boolean validateNickName(String nickName) {
        String regExp = userConfigRepository.nickNameRegExp();
        return isTextBlank(nickName) && nickName.matches(regExp);
    }

    public boolean validateName(String name) {
        String regExp = userConfigRepository.nameRegExp();
        return isTextBlank(name) && name.matches(regExp);
    }

    public boolean validateSurname(String surName) {
        String regExp = userConfigRepository.nameRegExp();
        return isTextBlank(surName) && surName.matches(regExp);
    }

    private boolean isTextBlank(String text){
        return !text.trim().equals("");
    }

    public User getUserById(Integer id){
        User user = userConfigRepository.getUserById(id);
        return user;
    }

    public ArrayList<User> getUsers() {
        ArrayList<User> users = userConfigRepository.getUsers();
        return users;
    }

    public String saveUser(User user) {
        if(!validatePassword(user.password)){
            return "Contraseña no valida";
        }
        if(!validateEmail(user.email)){
            return "Correo no valido";
        }
        if(!validateCelNumber(user.cellPhone)){
            return "Celular no valido";
        }
        if(!validateNickName(user.nickName)){
            return "Nickname no valido";
        }
        if(!validateName(user.name)){
            return "Nombre no valido";
        }
        if(!validateSurname(user.surName)){
            return "Apellido no valido";
        }
        userConfigRepository.saveUser(user);
        return "Usuario guardado correctamente";
    }
}
