#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
int main()
{
	int i ,j, s = 0;
	for (i = 1;i <= 2; i++)
	{									//大括号去掉就不是循环嵌套了
		s = s + 1;
	for (j = 1;j <= 2; j++)
		s = s + 1;
	}
	printf("### i=%d, j=%d, s=%d ###\n", i, j, s);
	return 0;
}