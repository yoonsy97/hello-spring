package hello.hellospring.controller;

public class MemberForm {

    private String userId;
    private String userpassword;

    private int userlevel;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public int getUserlevel() {
        return userlevel;
    }

    public void setUserlevel(int userlevel) {
        this.userlevel = userlevel;
    }


}
