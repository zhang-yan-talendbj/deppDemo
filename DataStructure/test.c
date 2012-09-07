/*
 * List
 *
 *  Created on: Jun 14, 2012
 *      Author: bsnpbag
 */

#include <stdio.h>
#define LOWER 0
#define UPPER 200
#define STEP 20

void fahr2Celsius() {
	float lower, upper, step, fahr, celsius;
	lower = 0;
	upper = 200;
	step = 20;
	fahr = lower;
	while (fahr < upper) {
		celsius = 5 * (fahr - 32) / 9;
		printf("%3.0f\t %6.1f\n", fahr, celsius);
		fahr = fahr + step;
	}
}

void fahr2CelsiusFor() {
	float fahr;
	for (fahr = LOWER; fahr < UPPER; fahr += STEP) {
		float celsius = 5 * (fahr - 32) / 9;
		printf("%3.0f\t %6.1f\n", fahr, celsius);
	}
}

void testPoint() {
	//	fahr2CelsiusFor();
	//	fahr2Celsius();
	int x = 1, y = 2;
	swap(&x, &y);
	printf("%d\t %d\n", x, y);
}


swap(int *px, int *py) {
	*px = *px + *py;
	*py = *px - *py;
	*px = *px - *py;
}

