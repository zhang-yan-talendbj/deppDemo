
#include "stdlib.h"
#include "io.h"
#include "math.h"
#include "time.h"

#define OK 1
#define ERROR 0

#define TRUE 1
#define FALSE 0

#define MAXSIZE 20 /* 存储空间初始分配量 */

typedef int Status; /* Status是函数的类型,其值是函数结果状态代码，如OK等 */
typedef int ElemType; /* ElemType类型根据实际情况而定，这里假设为int */

typedef struct {
	ElemType data[MAXSIZE];
	int length;
} SqList;

Status InitList(SqList *L) {
	L->length = 0;
	return OK;
}
int main() {
	SqList L;
	Status i;
	i = InitList(&L);

	int j;
	for (j = 0; j < 5; j++) {
		listInsert(&L, 1, j);
	}

	ListTraverse(L);
	ElemType elem;
	i = ListDelete(&L, 1, &elem);
	visit(elem);
	getElem(&L, 1, &elem);
	visit(elem);
	printf("\n");
	SqList L2;
	i = InitList(&L2);
	for (j = 10; j > 0; j--)
		listInsert(&L2, 1, j);

	ListTraverse(L2);
	i = unionList(&L, L2);
	ListTraverse(L);
	return OK;
}
Status unionList(SqList *L, SqList Lb) {
	int lLength, lbLength;
	lLength = L->length;
	lbLength = Lb.length;
	int k;
	for (k = 1; k <= lbLength; k++) {
		ElemType e;
		getElem(Lb, k, &e);
		int tmp = LocateElem(*L, e);
		if (!tmp) {
			listInsert(L, ++lLength, e);
		}
	}
	return OK;
}

int LocateElem(SqList L, ElemType e) {
	if (L.length == 0)
		return ERROR;
	int k;
	for (k = 0; k < L.length; k++) {
		if (e == L.data[k])
			break;
	}
	if (k >= L.length)
		return 0;
	return k + 1;
}

Status ListDelete(SqList *L, int i, ElemType *e) {

	if (L->length == 0)
		return ERROR;
	if (i < 1 || i > L->length)
		return ERROR;
	if (i < L->length) {
		*e = L->data[i - 1];
		int k;
		for (k = i - 1; k < L->length; k++)
			L->data[k] = L->data[k + 1];
	}
	L->length--;
	return OK;
}
Status ListTraverse(SqList L) {
	int k;
	for (k = 0; k < L.length; k++) {
		visit(L.data[k]);
	}
	printf("\n");
	return OK;
}

Status getElem(SqList L, int i, ElemType *e) {

	if (L.length == 0 || i < 0 || i > L.length)
		return ERROR;
	*e = L.data[i - 1];
	return OK;
}
Status visit(ElemType c) {
	printf("%d ", c);
	return OK;
}
Status listInsert(SqList *L, int i, ElemType e) {
	int k;
	if (L->length == MAXSIZE)
		return ERROR;

	if (i < 1 || i > MAXSIZE) // i的位置从1开始
		return ERROR;

	if (i <= L->length) {
		for (k = L->length - 1; k >= i - 1; k--) {
			L->data[k + 1] = L->data[k];
		}
	}
	L->data[i - 1] = e; //数组从0开始存放数据
	L->length++;
	return OK;
}
