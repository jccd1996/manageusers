import java.util.ArrayList;

public interface IUserConfigRepository {

    String passwordRegExp();
    String emailRegExp();
    String celRegExp();
    String nickNameRegExp();
    String nameRegExp();
    User getUserById(Integer id);
    ArrayList<User> getUsers();
    String saveUser(User userMock);
    ArrayList<Role> getRoles();
}
