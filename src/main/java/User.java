public class User {

    Integer id;
    String name;
    String surName;
    String nickName;
    String email;
    String cellPhone;
    String password;


    public User() {
    }

    public User(Integer id, String name, String surName, String nickName, String email, String cellPhone) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.nickName = nickName;
        this.email = email;
        this.cellPhone = cellPhone;
    }

    public User(Integer id, String name, String surName, String nickName, String email, String cellPhone, String password) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.nickName = nickName;
        this.email = email;
        this.cellPhone = cellPhone;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }
}
