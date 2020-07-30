#include <iostream>
using namespace std;

#define MAX 5000000     // 配列の要素数
#define SENTINEL 2000000000     // 番兵用の非常に大きな値

int L[MAX/2+2], R[MAX/2+2];
int cnt;        // 比較回数

void merge(int A[], int left, int mid, int right) {        // mergeSortによって得られたソート済みの部分配列を統合する関数
    int n1 = mid - left;        // 左側の配列の大きさ
    int n2 = right - mid;       // 右側の配列の大きさ (rightは部分配列の末尾+1のインデックス)
    
    // L[0...n1]とR[0...n2]なる配列を生成
    for (int i = 0; i < n1; ++i) {
        L[i] = A[left + i];
    }
    for (int i = 0; i < n2; ++i) {
        R[i] = A[mid + i];
    }
    L[n1] = SENTINEL;
    R[n2] = SENTINEL;

    // ソート済み配列を統合する
    int i = 0, j = 0;
    for (int k = left; k < right; ++k) {
        cnt++;
        if (L[i] <= R[j]) {
            A[k] = L[i];
            i++;
        } else {
            A[k] = R[j];
            j++;
        }
    }
}

void mergeSort(int A[], int left, int right) {      // 配列をそれぞれ半分半分に分割する関数
    if (left + 1 < right) {     // 分割した部分配列の要素数が1になるまで分割する
        int mid = (left + right) / 2;
        mergeSort(A, left, mid);        // 左半分を分割
        mergeSort(A, mid, right);       // 右半分を分割
        merge(A, left, mid, right);     // mergeで分割した配列を統合
    }
}

int main() {
    int n, S[MAX];
    cnt = 0;
    cin >> n;
    for (int i = 0; i < n; ++i) {
        cin >> S[i];
    }
    mergeSort(S, 0, n);

    for (int i = 0; i < n; ++i) {
        if (i) cout << " ";
        cout << S[i];
    }
    cout << endl;
    
    cout << cnt << endl;

    return 0;
}