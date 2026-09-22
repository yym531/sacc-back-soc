#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
using namespace std;

int main() {
    const int DAYS = 7;   // 一周 7 天
    int plays[DAYS];      // 存放每天的新增播放量
    int sum = 0;          // 累计播放量

    // 第一个循环：读入 7 天的数据
    for (int i = 0; i < DAYS; i++) {
        cin >> plays[i];
    }

    // 第二个循环：把 7 天的数据累加起来
    for (int i = 0; i < DAYS; i++) {
        sum += plays[i];
    }

    cout << "累计播放量： " << sum << endl;

    return 0;
}