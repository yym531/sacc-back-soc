#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
int main()
{
	int i ,j, s = 0;
	for (i = 1;i <= 2; i++)
	{
		printf("i=%d:s=%d ********* \n",i,s);
		for (j = 1;j <= 3; j++)
		{
			s = s + 1;
			printf("i=%d, j=%d, s=%d\n", i, j, s);
		}
	}
	printf("### i=%d, j=%d, s=%d ###\n", i, j, s);
	return 0;
}