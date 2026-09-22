public class MJ012 {
    public String userName;
    public String nickName;
    public String password;
    public MJ012(String userName,String password,String nickName) {
        this.userName = userName;
        this.password = password;
        this.nickName = userName;
    }
    public static void checkuserName (String username){
        if(username.isEmpty()){
            System.out.println("账号不能为空");
        }
        else if (username.contains(" ")){
            String cleanUserName = username.trim();
            System.out.println("Hallo world! 欢迎" + cleanUserName);
        }
        else{
            System.out.println("Hallo world! 欢迎" + username);
        }
    }
    public static void checkPassword (String userName,String password){
        if(password.contains(userName)){
            System.out.println("账号存在安全风险！");
        }
    }

    public static void changeusername(String nickName) {
        if (nickName.contains("退款")) {
            String newnickName = nickName.replace("退款","**");
            System.out.println(newnickName);
        }
    }
    public static void main(String[] args) {
    MJ012 account = new MJ012("杨翊铭","114514","yym");
    checkPassword(account.userName,account.password);
    checkuserName(account.userName);
    }
}
