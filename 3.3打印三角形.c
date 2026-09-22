#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
int main()
{	
	int i, j, n;
	do
	{
		printf("input lines:\n");
		scanf("%d",&n);
	}
	while(!(n>=4 && n<=30));
	for(i=1;i<=n;i++);		//�б仯
	 {
		 for (j = 1; j<= n - i; j++);
			printf(" ");
		 for(j = 1;j <= 2 * i - 1;j++);
			printf("*");
		 printf("\n");
	 }
	return 0;
}