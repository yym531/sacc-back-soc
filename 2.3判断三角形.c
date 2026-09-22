#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <math.h>
#include <stdlib.h>
int main()
{
	double a, b, c, t;
	printf("Enter three edgrs of a traingle:");
	scanf("%lf%lf%lf", &a, &b ,&c);
	if (a>b)  //a<=b
	{
		t=a;
		a=b;
		b=t;
	}
	if (a>c)  //a<=c
	{
		t=a;
		a=c;
		c=t;
	}
	if (b>c)  //b<=c
	{
		t=b;
		b=c;
		c=t;
	}


	if (a <= 0 || b <= 0 ||c <= 0)     /*输入合法性判别*/
	{
		printf("Error input!\n");
	}
	else  //三边都是正数
	{
		if (((a + b) > c) && a + c > b && b + c > a) //任意两边和大于第三边
		{
			if (fabs(a * a + b * b - c * c) <= 1E-2
				|| fabs(b * b + c * c - a * a) <= 1E-2
				|| fabs(a * a + c * c - b * b) <= 1E-2)
			{
				/*直角三角形*/
				printf("%if, %if, %if is a right traingle.\n", a, b, c);
			}
			else
			{
				/*普通三角形*/
				printf("%if, %if, %if is a ordinary traingle.\n", a, b, c);
			}
		}
		else	/*不能构成三角形*/
		{
			printf("%if, %if, %if can't make a traingle.\n", a, b, c);
		}
	}
	return 0;
	system("pause");
}