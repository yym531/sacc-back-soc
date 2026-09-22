#include <stdio.h>
int main()
{
	int a,b,c;
	printf("input a and b:\n");
	scanf("%d%d",&a,&b);
	if (a>b)
		c=a;
	else
		c=b;
	printf("bigger=%d\n",b);
	return 0;
}
