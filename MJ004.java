public class MJ004 {
    public static void main(String[] args) {
        String AccountName = "yym";
        String Password = "114514";
        String Status = "safe";
        String LoginDays = "7";
        try {
            int playCount = Integer.parseInt(LoginDays);
        } catch (NumberFormatException e) {
            System.out.println("登录天数格式错误");
        }
        if ("".equals(AccountName)){
            System.out.println("账号名不能为空");
        } else if (Password.length() < 6) {
            System.out.println("密码长度不足");
        } else if ("blocked".equals(Status)){
            System.out.println("账号风险拦截");
        } else{
            System.out.println("账号校验通过");
        }
    }
}
