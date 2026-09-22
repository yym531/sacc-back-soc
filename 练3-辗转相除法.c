#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
int main()
{
	int m = 24, n = 18, r;
	do
	{
		r = m % n;
		m = n;
		n = r;
	}while (n);
	printf("%d\n", m);
	return 0;
}