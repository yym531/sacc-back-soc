public class MJ009 {
        String userName;
        String password;
        String status;
    public MJ009(String userName, String password, String status){
        this.userName = userName;
        this.password = password;
        this.status = status;
    }
    public static void main(String[] args) {
        MJ009 account1=new MJ009("yym","114514","blocked");
        MJ009 account2=new MJ009("gyc","182030","blocked");
        MJ009 account3=new MJ009("wfy","615372","blocked");

        MJ009[] accounts = {account1,account2,account3};
        accounts[1].status = ("active");
        for (int i = 0;i < accounts.length;i++){
            System.out.println("账号名：" + accounts[i].userName);
            System.out.println("状态：" + accounts[i].status);
        }
    }

}
