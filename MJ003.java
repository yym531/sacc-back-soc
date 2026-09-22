public class MJ003 {
    public static void main(String[] args) {
        String AccountName = "yym";
        String Nickname = "xy";
        String Password = "0531";
        int LoginDays = 7;
        boolean IsNewUser = true;//任务一

        int[] LoginNumbers = {100, 233, 666, 1024, 2048, 4096, 8192};
        System.out.println(LoginNumbers[0]);
        System.out.println(LoginNumbers[6]);
        int Numbers = LoginNumbers.length;
        System.out.println(Numbers);//任务二

        LoginNumbers[2] = 114514;
        System.out.println(LoginNumbers[2]);//任务三

        System.out.println(AccountName);
        System.out.println(Nickname);
        System.out.println(Password);
        System.out.println(IsNewUser);//任务四
    }
}
