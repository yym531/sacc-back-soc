public class MJ006 {
    static String validateAccount ( boolean legalAccount, boolean legalPassword){
    if (!legalAccount) {
        return "非合法账号";
    }
    if (!legalPassword) {
        return "非合法密码";
    }


    return "登录成功";//任务一
}


    static void encryptPassword (String password){
        System.out.println("SB" + password);//任务二
    }


    static void buildWelcomeMessage (String message) {
        System.out.println("Hallo world! 账号" + message + "诞生了！");
    }//任务三

    static void Welcome (String thing){
        System.out.println("欢迎" + thing + "来到慢脚！");
    }

public static void main(String[] args) {

    String result = validateAccount(true, true);
    System.out.println(result);
    String Account = ("001");
    buildWelcomeMessage(Account);
    Welcome(Account);
    }
}