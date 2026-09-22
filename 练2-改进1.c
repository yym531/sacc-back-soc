#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#define PI 3.14
int main()
{
	double radius,perimeter,area;	//定义三个变量
	do
	{
		printf("input radius:\n");
		scanf("%lf", &radius);
	}	while (!(radius > 0));	//半径大于0
		perimeter =  PI * 2 * radius;	//计算周长
		area = PI * radius * radius;	//计算面积
		printf("radius=%lf, perimeter=%lf, area=%lf\n", radius, perimeter, area);//输出
	
		return 0;
}