/**
 * MJ008 —— 账号模板（类）
 * 文件名必须与 public 类名一致，所以这里的类名跟着文件名走。
 * 类内部该怎么设计跟文件名无关，字段、方法照旧。
 *
 * 一个"模板"说清楚两件事：
 *   1. 一个账号身上有哪些字段（数据）
 *   2. 一个账号能做什么（行为）
 *
 * 当前文件同时扮演"模板"和"测试入口"两个角色，一个文件就能跑。
 */
public class MJ008 {

    // ---------- 字段：账号身上挂着的数据 ----------
    // private 表示"只有 MJ008 自己能碰"，外面想看只能走方法
    private String username;         // 账号名（登录用，唯一）
    private String nickname;         // 昵称（展示用）
    private String passwordDigest;   // 密码摘要，不是原文
    private String status;           // 账号状态
    private int serialNo;            // 创建序号：它是第几个账号
    private String createdAt;        // 创建时间

    // ---------- 构造器：new Account(...) 的时候被调用 ----------
    public MJ008(String username, String nickname, String rawPassword, int serialNo, String createdAt) {
        this.username = username;
        this.nickname = nickname;
        // 关键一步：明文密码只在传进来的这一瞬间存在，存进去的是摘要
        this.passwordDigest = digest(rawPassword);
        this.status = "ACTIVE";
        this.serialNo = serialNo;
        this.createdAt = createdAt;
    }

    /**
     * 把任意长度的密码压成一串固定长度的"摘要"。
     * 这里用的是一个简化的算法，你能看懂它的每一步：
     * 从 0 出发，每读一个字符就把当前值乘 31 再加上字符编码，取余防止越滚越大。
     * 真实项目里这一步要换成 BCrypt / SHA-256 加盐，原理一样：算得出来，反推不回去。
     */
    private static String digest(String rawPassword) {
        long hash = 0;
        for (int i = 0; i < rawPassword.length(); i++) {
            hash = (hash * 31 + rawPassword.charAt(i)) % 1000000007L;
        }
        return String.format("%08X", hash);   // 转成 8 位十六进制
    }

    // ---------- 行为 ----------

    /** 校验密码：拿用户这次输入的明文再算一遍摘要，比对存的那份。全程不出现原文。 */
    public boolean checkPassword(String rawPassword) {
        return passwordDigest.equals(digest(rawPassword));
    }

    /** 封号 / 解冻 */
    public void setStatus(String status) {
        this.status = status;
    }

    /** 统一负责"怎么显示"，所有字段一次性成套输出，看得出属于同一个账号 */
    public void printInfo() {
        System.out.println("+---------------- 账号 #" + serialNo + " ----------------+");
        System.out.println("  账号名   : " + username);
        System.out.println("  昵称     : " + nickname);
        System.out.println("  密码摘要 : " + passwordDigest + "   (sha-like)");
        System.out.println("  明文密码 : ********   (不输出)");
        System.out.println("  账号状态 : " + status);
        System.out.println("  创建序号 : " + serialNo);
        System.out.println("  创建时间 : " + createdAt);
        System.out.println("+--------------------------------------------+");
    }

    // ---------- 入口：造一个实例试试 ----------
    public static void main(String[] args) {
        // template -> instance：照着 Account 模板填进去一份具体的值，才叫"一个账号"
        MJ008 mine = new MJ008("yangyiming", "翊铭", "guitar@2026", 1, "2026-09-14 23:10");
        mine.printInfo();

        System.out.println("密码 'guitar@2026' 对不对？ " + mine.checkPassword("guitar@2026"));
        System.out.println("密码 'wrong123'   对不对？ " + mine.checkPassword("wrong123"));

        mine.setStatus("DISABLED");
        System.out.println("封号后再看一眼：");
        mine.printInfo();
    }
}