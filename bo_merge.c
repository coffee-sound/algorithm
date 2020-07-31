#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define N 8

/* 1 ~ N までの数値をランダムに生成するわよ */
void generate_data(int data[]) {
    int i, elements = N, element;
    double r;
    int numbers[N];
    for (i = 0; i < N; i++) {
        numbers[i] = i + 1;
    }
    srand((unsigned)time(NULL));
    for (i = 0; i < N; i++) {
        r = (double) rand() / RAND_MAX;
        element = (int)(elements * r);
        data[i] = numbers[element];
        numbers[element] = numbers[elements - 1];
        numbers[elements - 1] = data[i];
        elements--;
    }
}

/* 出力関数 */
void output(int data[], int length) {
    int i;
    for (i = 0; i < length && i < N; i++) {
        printf("%d ", data[i]);
    }
    printf("\n");
}

int main(void) {
    int data[N];
    generate_data(data);
    output(data, N);
    return 0;
}
