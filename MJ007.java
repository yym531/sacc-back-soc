public class MJ007 {
    public static void main(String[] args) {
        boolean isLogin = true;
        String accountName = "yym";
        String password = "1234";
        PrepareAccount(accountName);
        String checkResult = checkAccount(isLogin,password);
        if (checkResult.equals("登录检验通过")) {
            String secret = secretPassword(password);
            SaveAccount(accountName);
            Welcome(accountName);
        }
        else
            System.out.println(checkResult);

    }
    static void PrepareAccount(String accountname){
        System.out.println("已准备账号：" + accountname);
    }
    static String checkAccount(boolean isLogin, String title){
        if (!isLogin) {
            return "请先登录";
        }

        if (title.equals("")) {
            return "密码不能为空";
        }

        return "登录检验通过";
    }
    static void SaveAccount(String accountname){
        System.out.println("账号" + accountname + "已保存成功！");
    }
    static void Welcome(String accountname){
        System.out.println("Hello World! 慢脚账号" + accountname + "创建完成");
    }
    static String secretPassword(String password){
        String Secret = ("SB" + password);
        return Secret;
    }
}
