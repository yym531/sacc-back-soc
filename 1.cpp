#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
int main()
{
	int length, width, perimeter,area;
	printf("input length and width:\n");
	scanf("%d%d", &length, &width);
	if (length>0 &&	 width>0)
	{
		perimeter = 2 * (length + width);
		area=length*width;
		printf("length=%d, width=%d perimeter=%d, area=%d\n", length, width, perimeter , area);
	}
	else //length<=0 , width<=0
		printf("error input!\n");
	return 0;