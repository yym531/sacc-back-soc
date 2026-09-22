public class MJ005 {
    public static void main(String[] args) {
        // 任务一
        String[] AccountName = {"1", "2", "3", "", "risk_user", "6", "7"};

        int successCount = 0;   // 任务五：成功计数器，放在循环外面

        // 用 AccountName.length 代替写死的 5，数组怎么改都不会越界
        for (int AccountId = 1; AccountId <= AccountName.length; AccountId++) {

            String name = AccountName[AccountId - 1];   // 下标从 0 开始，所以 -1

            // 任务三：空账号名跳过，不计入
            if (name.isEmpty()) {
                System.out.println("第 " + AccountId + " 个账号名为空，跳过");
                continue;
            }

            // 任务四：见到 risk_user 立刻收手
            if (name.equals("risk_user")) {
                System.out.println("第 " + AccountId + " 个是风控账号，停止处理。");
                break;
            }

            // 任务二：正常创建
            System.out.println("正在创建账号 yym00" + name);
            successCount++;
        }

        // 任务五：汇总
        System.out.println("成功处理账号数：" + successCount);
    }
}
