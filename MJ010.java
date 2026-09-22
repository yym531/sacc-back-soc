public class MJ010 {
    public String userName;
    private String password;
    private String status;
    public MJ010(String userName,String password,String status){
        this.userName=userName;
        this.status=status;
        this.password=password;
    }
    public String getPassword(String password) {
        long hash = 0;
        for (int i = 0; i < password.length(); i++) {
            hash = (hash * 31 + password.charAt(i)) % 1000000007L;
        }
        return String.format("%08X", hash);
    }
    public String Login() {
        if (this.status.equals("blocked")) {
            return ("账号被锁定，无法登录！");
        }
        else {
            return ("登录成功！");
            }
    }
    public static void main(String[] args) {

    }
    }
