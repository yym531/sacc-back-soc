#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
int main()
{
	int x, y;
	printf("input x:\n");
	/* 第一种 
	if (x > 0)
		y = 1;
	else  ///x<=0
		if (x ==0)
			y = 0;
		else  //x<0
			y = -1;
	printf("x=%d,y=%d\n", x, y);
	*/
	/* 第二种 
	if (x>=0)
		if (x>0)
			y=1;
		else  //x==0
			y=0;
	else //x<0
		y=-1;
	printf("x=%d,y=%d\n", x, y);
	*/
	/* 第三种(最不建议) 
	if (x > 0) y = 1;
	if (x == 0) y = 0;
	if (x < 0) y = -1;
	printf("x=%d,y=%d\n", x, y);
	*/
	/* 第四种 */
	y = 1;
	if (x == 0) y = 0;//直接对x赋值
	if (x < 0) y = -1;
	printf("x=%d,y=%d\n", x, y);

	return 0;
}
