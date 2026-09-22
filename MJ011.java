import java.util.ArrayList;
import java.util.List;

/**
 * MJ011 —— 把 MJ008 里"一个类干所有活"拆成一套协作结构
 *
 * 先想一个问题：MJ008 中 Account 自己就会算摘要（那个 private digest 方法）。
 * 那为什么这次要把它单独拎出来？
 *
 * 因为"算摘要"和"这个账号叫什么名字"根本没关系。
 * 哪天要换成 BCrypt，你只想改一个地方，不该去动账号类。
 * 同理，账号该不该被存进列表、该不该打印 Hello World!，也不是账号自己该操心的事。
 *
 * 于是拆成五块，每块只回答一个问题：
 *
 *   Account            只装数据：我是谁、我的摘要是多少、我什么时候建的
 *   PasswordHasher     只做算数：明文 -> 摘要
 *   AccountRepository  只管仓储：把账号收进内存列表，能按账号名找回来
 *   Notifier           只管说话：生成并输出 Hello World!
 *   MJ011 (main)       只管组队：按顺序把上面四个叫来干活，自己不拧螺丝
 *
 * 一个 .java 文件里可以有多个类，但只能有一个 public，且 public 类名必须等于文件名。
 * 所以这里只有 MJ011 是 public，其余四个是"包内可见"的搭档。
 */
public class MJ011 {

    // ==================== 小目标 5：主流程 ====================
    public static void main(String[] args) {
        // ---- 组队：项目经理先把手下的三个组件叫齐 ----
        // 注意这里只 new 了三个"能干活的人"，Account 等会儿再按需要造
        PasswordHasher hasher = new PasswordHasher();
        AccountRepository repository = new AccountRepository();
        Notifier notifier = new Notifier();

        // ---- 开工第一件事：让通知组件说话 ----
        notifier.sayHello();

        // ---- 注册两个账号：每一步都是"派活"，主流程自己不算摘要、不碰列表 ----
        register(repository, hasher, "yangyiming", "翊铭", "guitar@2026", "2026-09-22 21:50");
        register(repository, hasher, "linwan", "晚晚", "hello123", "2026-09-22 21:51");
        register(repository, hasher, "yangyiming", "重名的人", "abc456", "2026-09-22 21:52");

        System.out.println("\n--- 仓库里的全部账号（共 " + repository.size() + " 个）---");
        repository.printAll();

        // ---- 登录校验：照样是"找谁干活"，主流程不自己比字符串 ----
        System.out.println("\n--- 试着登录 ---");
        checkLogin(repository, hasher, "yangyiming", "guitar@2026");
        checkLogin(repository, hasher, "yangyiming", "wrong123");
        checkLogin(repository, hasher, "nobody", "whatever");
    }

    /**
     * 主流程的"派活清单"之一：注册一个账号。
     * 三行代码对应三个组件，主流程只是把它们串起来，真正的活都在组件里。
     */
    private static void register(AccountRepository repository, PasswordHasher hasher,
                                 String username, String nickname, String rawPassword, String createdAt) {
        String digest = hasher.hash(rawPassword);                       // 找加密组件：明文 -> 摘要
        Account account = new Account(username, nickname, digest,       // 造数据结构：只装数据
                repository.nextSerialNo(), createdAt);
        repository.save(account);                                        // 找仓库：存起来
    }

    /** 主流程的"派活清单"之二：校验一次登录。查账号找仓库，比密码找加密组件。 */
    private static void checkLogin(AccountRepository repository, PasswordHasher hasher,
                                   String username, String rawPassword) {
        Account account = repository.findByUsername(username);
        if (account == null) {
            System.out.println("  [登录] " + username + " -> 查无此账号");
            return;
        }
        boolean ok = hasher.matches(rawPassword, account.getPasswordDigest());
        System.out.println("  [登录] " + username + " / " + rawPassword + " -> " + (ok ? "通过" : "密码错误"));
    }
}

// ==================== 小目标 1：账号数据结构 ====================
/**
 * 只负责保存账号信息。
 * 判断标准很简单：把 hash()、save()、println("Hello") 都拿走之后，
 * 这个类剩下的全是"这个账号长什么样"——那就对了。
 */
class Account {

    // ---------- 字段：账号身上挂着的数据 ----------
    // private = 只有 Account 自己能改，外面想看只能走 getter，好处是别人改不坏
    private String username;         // 账号名（登录用，唯一）
    private String nickname;         // 昵称（展示用）
    private String passwordDigest;   // 密码摘要，绝不是原文
    private String status;           // 账号状态
    private int serialNo;            // 创建序号：它是第几个账号
    private String createdAt;        // 创建时间

    /**
     * 构造器。注意第三个参数已经是摘要了——明文密码走到这里之前就被换掉了，
     * Account 从头到尾没见过密码原文，也没能力去算摘要。
     */
    public Account(String username, String nickname, String passwordDigest, int serialNo, String createdAt) {
        this.username = username;
        this.nickname = nickname;
        this.passwordDigest = passwordDigest;
        this.status = "ACTIVE";
        this.serialNo = serialNo;
        this.createdAt = createdAt;
    }

    // ---------- getter：让外面能读，但不能直接改 ----------
    public String getUsername() { return username; }
    public String getNickname() { return nickname; }
    public String getPasswordDigest() { return passwordDigest; }
    public String getStatus() { return status; }
    public int getSerialNo() { return serialNo; }
    public String getCreatedAt() { return createdAt; }

    /** 封号 / 解冻：状态是账号自己的事，所以这个改动留给 Account 自己把关 */
    public void setStatus(String status) { this.status = status; }

    /** 统一负责"怎么显示"，所有字段成套输出，看得出它们属于同一个账号 */
    public void printInfo() {
        System.out.println("+---------------- 账号 #" + serialNo + " ----------------+");
        System.out.println("  账号名   : " + username);
        System.out.println("  昵称     : " + nickname);
        System.out.println("  密码摘要 : " + passwordDigest + "   (sha-like)");
        System.out.println("  明文密码 : ********   (不保存、不输出)");
        System.out.println("  账号状态 : " + status);
        System.out.println("  创建序号 : " + serialNo);
        System.out.println("  创建时间 : " + createdAt);
        System.out.println("+--------------------------------------------+");
    }
}

// ==================== 小目标 2：密码加密组件 ====================
/**
 * 只负责一件事：把明文变成摘要，以及拿明文去比对已有摘要。
 * 它不认识 Account，也不知道账号存哪儿——所以它可以被单独换掉、单独测试。
 */
class PasswordHasher {

    /**
     * 把任意长度的密码压成一串固定长度的摘要。
     * 算法跟 MJ008 里那个一样，你能看懂每一步：
     *   从 0 出发，每读一个字符就把当前值乘 31 再加上字符编码，取余防止越滚越大。
     * 真实项目里这一步换成 BCrypt / SHA-256 加盐，原理相同：正向算得出，反向推不回。
     */
    public String hash(String rawPassword) {
        long h = 0;
        for (int i = 0; i < rawPassword.length(); i++) {
            h = (h * 31 + rawPassword.charAt(i)) % 1000000007L;
        }
        return String.format("%08X", h);   // 转成 8 位十六进制
    }

    /** 校验：拿本次输入的明文再算一遍摘要，跟存的那份比。全程不出现原文。 */
    public boolean matches(String rawPassword, String storedDigest) {
        return hash(rawPassword).equals(storedDigest);
    }
}

// ==================== 小目标 3：账号仓库 ====================
/**
 * 只负责保存账号。这里用内存列表模拟，换成文件或数据库时，
 * 只要把 save / findByUsername 里的几行换掉，外面的代码一行都不用改。
 */
class AccountRepository {

    private List<Account> accounts = new ArrayList<>();   // 内存列表，就是那个"仓库"

    /** 下一个序号是多少：仓库最清楚自己已经存了几个，所以这个编号由它发 */
    public int nextSerialNo() {
        return accounts.size() + 1;
    }

    /** 存一个账号进去。账号名重复就拒绝，保证"账号名唯一"这条规矩只用写一次。 */
    public void save(Account account) {
        if (findByUsername(account.getUsername()) != null) {
            System.out.println("  [仓库] " + account.getUsername() + " 已存在，拒绝重复保存");
            return;
        }
        accounts.add(account);
        System.out.println("  [仓库] 已保存 " + account.getUsername() + "，当前共 " + accounts.size() + " 个");
    }

    /** 按账号名把账号找回来；找不到就返回 null（调用方负责判断） */
    public Account findByUsername(String username) {
        for (Account a : accounts) {
            if (a.getUsername().equals(username)) {
                return a;
            }
        }
        return null;
    }

    public int size() {
        return accounts.size();
    }

    /** 展示交给 Account 自己，仓库只负责把人一个个叫出来 */
    public void printAll() {
        for (Account a : accounts) {
            a.printInfo();
        }
    }
}

// ==================== 小目标 4：通知组件 ====================
/**
 * 只负责生成 / 输出 Hello World!。
 * 这里刻意拆成"生成"和"输出"两个动作：
 * 哪天要改成发短信、写日志，只要换 send()，buildMessage() 不用动。
 */
class Notifier {

    // static final = 常量：全大写命名，值不可改，全类共用一份
    private static final String HELLO = "Hello World!";

    /** 生成：只负责把这句话造出来，不关心谁来用 */
    public String buildMessage() {
        return HELLO;
    }

    /** 输出：只负责把话送出去，不关心话是怎么来的 */
    public void send(String message) {
        System.out.println(message);
    }

    /** 一步到位：最常用的那个动作 */
    public void sayHello() {
        send(buildMessage());
    }
}
