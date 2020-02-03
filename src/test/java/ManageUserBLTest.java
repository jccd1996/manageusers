import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;


@RunWith(JUnitParamsRunner.class)
public class ManageUserBLTest {

    private ManageUserBL manageUserBL;

    @Mock
    IUserConfigRepository userConfigRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        manageUserBL = new ManageUserBL(userConfigRepository);
    }

    @Test
    @Parameters({
            "12345678, false",
            "1qwA, false",
            "asdasdasdaq, false",
            "AAAAAAAAAAA, false",
            "w3Unpocodet0d0, true",

    })
    public void methodValidatePasswordShouldReturnExpectedWhenPasswordIsTheParam(String password, boolean expected) {
        Mockito.when(userConfigRepository.passwordRegExp())
                .thenReturn("^(?=\\w*\\d)(?=\\w*[A-Z])(?=\\w*[a-z])\\S{8,16}$");
        boolean result = manageUserBL.validatePassword(password);
        Mockito.verify(userConfigRepository, Mockito.times(1)).passwordRegExp();
        Assert.assertEquals(expected, result);
    }

    @Test
    @Parameters({
            "correo.oc, false",
            "asdfasd@, false",
            "pruebacorreo_1234, false",
            "jccd1996@hotmail.com, true",
            "juancamilo@gmail.co, true",
            ", false",

    })
    public void methodValidateEmailShouldReturnExpectedWhenEmailIsTheParam(String email, boolean expected) {
        Mockito.when(userConfigRepository.emailRegExp())
                .thenReturn("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        boolean result = manageUserBL.validateEmail(email);
        Mockito.verify(userConfigRepository, Mockito.times(1)).emailRegExp();
        Assert.assertEquals(expected, result);
    }

    @Test
    @Parameters({
            "número, false",
            "un_dos_3@, false",
            "   , false",
            "123456789, true",
            "3209296233, true",

    })
    public void methodValidateCelShouldReturnExpectedWhenCelIsTheParam(String cel, boolean expected) {
        Mockito.when(userConfigRepository.celRegExp())
                .thenReturn("\\d*");
        boolean result = manageUserBL.validateCelNumber(cel);
        Mockito.verify(userConfigRepository, Mockito.times(1)).celRegExp();
        Assert.assertEquals(expected, result);
    }

    @Test
    @Parameters({
            "123, false",
            "un_dos_3@, false",
            "   , false",
            "SoloLetras, true",
            "minusculas, true",
            "MAYUSCULAS, true",
            "ContíldesOCaracteres, false",
            "con Espacioss, false",

    })
    public void methodValidateNickNamehouldReturnExpectedWhenNickNamesTheParam(String nickName, boolean expected) {
        Mockito.when(userConfigRepository.nickNameRegExp())
                .thenReturn("[a-zA-Z]*");
        boolean result = manageUserBL.validateNickName(nickName);
        Mockito.verify(userConfigRepository, Mockito.times(1)).nickNameRegExp();
        Assert.assertEquals(expected, result);
    }

    @Test
    @Parameters({
            "11111, false",
            "...., false",
            "   , false",
            "SoloLetras, true",
            "minusculas, true",
            "MAYUSCULAS, true",
            "ContíldesOCaracteres, false",
            "con Espacioss, true",
    })
    public void methodValidateNamehouldReturnExpectedWhenNamesTheParam(String name, boolean expected) {
        Mockito.when(userConfigRepository.nameRegExp())
                .thenReturn("[a-zA-Z\\s]*");
        boolean result = manageUserBL.validateName(name);
        Mockito.verify(userConfigRepository, Mockito.times(1)).nameRegExp();
        Assert.assertEquals(expected, result);
    }

    @Test
    @Parameters({
            "11111, false",
            "...., false",
            "   , false",
            "SoloLetras, true",
            "minusculas, true",
            "MAYUSCULAS, true",
            "ContíldesOCaracteres, false",
            "con Espacioss, true",
    })
    public void methodValidateSurnameShouldReturnExpectedWhenSurnameTheParam(String surName, boolean expected) {
        Mockito.when(userConfigRepository.nameRegExp())
                .thenReturn("[a-zA-Z\\s]*");
        boolean result = manageUserBL.validateSurname(surName);
        Mockito.verify(userConfigRepository, Mockito.times(1)).nameRegExp();
        Assert.assertEquals(expected, result);
    }

    @Test
    @Parameters(method = "usersValues")
    public void methodGetUsersShouldReturnExpectedWhenUsersIsTheParam(ArrayList<User> usersExpected) {
        ArrayList<User> usersMocks = new ArrayList<>();
        usersMocks.addAll(usersExpected);

        Mockito.when(userConfigRepository.getUsers())
                .thenReturn(usersExpected);

        ArrayList<User> usersResult = manageUserBL.getUsers();
        Mockito.verify(userConfigRepository, Mockito.times(1)).getUsers();

        for (int i = 0; i < usersExpected.size(); i++) {
            Assert.assertEquals(usersExpected.get(i), usersResult.get(i));
        }
    }

    private Object[] usersValues() {
        ArrayList<User> users = new ArrayList<User>();
        users.add(new User(1, "Camilo", "Cubillos", "jccd",
                "jccd1996@hotmail.com", "3209296233"));
        users.add(new User(2, "Juan", "Diaz", "jdl",
                "jdl@gmail.co", "3144254360"));
        users.add(new User(3, "Andres", "Matoma", "amma",
                "ammm@yahoo.es", "3103355224"));
        users.add(new User(4, "Camila", "Gomez", "cg",
                "cgga@hotmail.es", "0382673924"));
        users.add(new User(5, "Tobi", "Pacheco", "tb",
                "tobias@sasd.es", "0382658484"));
        return new Object[]{users};
    }

    @Test
    @Parameters(method = "userValue")
    public void methodGetUserByIdShouldReturnExpectedWhenUserIsTheParam(Integer id, User user) {
        User userMock = new User(1, "Camilo", "Cubillos", "jccd",
                "jccd1996@", "3209296233");
        Mockito.when(userConfigRepository.getUserById(id))
                .thenReturn(userMock);

        User userResult = manageUserBL.getUserById(1);

        Mockito.verify(userConfigRepository, Mockito.times(1)).getUserById(1);
        Assert.assertEquals(userResult.id, user.id);
    }

    private Object[] userValue() {
        return new Object[]{
                new Object[]{1, new User(1, "Camilo", "Cubillos", "jccd",
                        "jccd1996@", "3209296233")}
        };
    }

    @Test
    @Parameters(method = "saveUserValue")
    public void methodSaveUserShouldReturnBadMessageWhenUserHasTheCorrectConditions(User user, String expected) {
        Mockito.when(userConfigRepository.passwordRegExp())
                .thenReturn("^(?=\\w*\\d)(?=\\w*[A-Z])(?=\\w*[a-z])\\S{8,16}$");
        Mockito.when(userConfigRepository.emailRegExp())
                .thenReturn("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Mockito.when(userConfigRepository.celRegExp())
                .thenReturn("\\d*");
        Mockito.when(userConfigRepository.nickNameRegExp())
                .thenReturn("[a-zA-Z]*");
        Mockito.when(userConfigRepository.nameRegExp())
                .thenReturn("[a-zA-Z\\s]*");

        String result = manageUserBL.saveUser(user);
        Assert.assertEquals(expected, result);
    }

    private Object[] saveUserValue() {
        return new Object[]{
                new Object[]{new User(1, "Camilo", "Cubillos", "jccd",
                        "jccd1996@hotmail.com", "3209296233", "w3cooL0as1456"), "Usuario guardado correctamente"},
                new Object[]{new User(1, "Camilo", "Cubillos", "jccd",
                        "jccd1996", "3209296233", "w3cooL0as1456"), "Correo no valido"},
                new Object[]{new User(1, "Camilo", "Cubillos", "jccd",
                        "jccd1996@hotmail.com", "3asd", "w3cooL0as1456"), "Celular no valido"},
                new Object[]{new User(1, "Camilo", "Cubillos", "jccd",
                        "jccd1996@hotmail.com", "3209296233", "admin0000"), "Contraseña no valida"},
                new Object[]{new User(1, "Camilo", "Cubillos", "jccd 1996",
                        "jccd1996@hotmail.com", "3209296233", "w3cooL0as1456"), "Nickname no valido"},
                new Object[]{new User(1, "   ", "Cubillos", "jccd",
                        "jccd1996@hotmail.com", "3209296233", "w3cooL0as1456"), "Nombre no valido"},
                new Object[]{new User(1, "Camilo", "  ", "jccd",
                        "jccd1996@hotmail.com", "3209296233", "w3cooL0as1456"), "Apellido no valido"},
        };
    }
}