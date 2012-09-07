#include "stdio.h"
#include "string.h"
#include "ctype.h"
#include "stdlib.h"
#include "io.h"
#include "math.h"
#include "time.h"

#define OK 1
#define ERROR 0
#define TRUE 1
#define FALSE 0

#define MAXSIZE 20 /* 存储空间初始分配量 */

typedef int Status;/* Status是函数的类型,其值是函数结果状态代码，如OK等 */
typedef int ElemType;/* ElemType类型根据实际情况而定，这里假设为int */

typedef struct Node {
	ElemType data;
	struct Node *next;
} Node;
typedef struct Node *LinkList; /* 定义LinkList */
Status ListLength(LinkList L) {
	int i = 0;
	LinkList p = L->next;
	while (p) {
		i++;
		p = p->next;
	}
	return i;
}
Status ListTraverse(LinkList L) {
	LinkList p = L->next;
	while (p) {
		visit(p->data);
		p = p->next;
	}
	return OK;
}
Status visit(ElemType e) {
	printf("%d ", e);
	return OK;
}
Status ListInsert(LinkList *L, int i, ElemType e) {
	LinkList p, s;
	p = *L;
	int j = 1; //插入位置从1开始计算
	while (p && j < i) {
		p = p->next;
		j++;
	}
	if (!p || j > i) {
		printf("j= %d;", j);
		return ERROR;
	}
	s = (LinkList) malloc(sizeof(Node));
	s->data = e;
	printf("%d ", s->data);
	s->next = p->next;
	p->next = s;
	return OK;
}
Status InitList(LinkList *L) //*L L为指向LinkList的指针
{
	printf("指向LinkList的指针:%d\n", L);
	*L = (LinkList) malloc(sizeof(Node)); /* 产生头结点,并使L指向此头结点 */
	printf("线情表的头指针%d\n", *L);
	if (!(*L)) /* 存储分配失败 */
		return ERROR;
	(*L)->next = NULL; /* 指针域为空 */

	return OK;
}

int ListEmpty(LinkList L) {
	if (L->next)
		return FALSE;
	else
		return TRUE;
}
Status ClearList(LinkList *L) {
	LinkList p, q;
	p = (*L)->next;
	while (p) {
		q = p->next;
		free(p);
		p = q;
	}
	(*L)->next = NULL;
	return OK;
}
Status GetElem(LinkList L, int i, ElemType *e) {
	LinkList p = L;
	int j = 0;
	while (p && j < i) {
		p = p->next;
		j++;
	}
	if (!p || j < i)
		return ERROR;
	*e = p->data;
	return OK;
}
int LocateElem(LinkList L, ElemType e) {
	int i = 0;
	LinkList p = L->next;
	while (p) {
		i++;
		if (p->data == e)
			return i;
		p = p->next;
	}
	return 0;
}
Status ListDelete(LinkList *L, int i, ElemType *e) {
	LinkList p = *L;
	int j = 1;
	while (p->next && j < i) {
		p = p->next;
		j++;
	}
	if (!(p->next) || j > i)
		return ERROR;

	LinkList q;
	q = p->next; //要删除X元素,一般要定位到X元素的前一个元素
	p->next = q->next; //去除要删除的元素,然后把指针指向要删除元素的下个元素
	*e = q->data;
	free(q);
	return OK;
}

Status CreateListHead(LinkList *L, int n) {
	LinkList p;
	int i;
	srand(time(0));
	*L = (LinkList) malloc(sizeof(Node));
	(*L)->next = NULL;
	for (i = 0; i < n; i++) {
		p = (LinkList) malloc(sizeof(Node));
		p->data = rand() % 100 + 1;
		p->next = (*L)->next;
		(*L)->next = p;
	}
	return OK;
}
Status CreateListTail(LinkList *L, int n) {
	LinkList p, r;
	int i;
	srand(time(0));
	*L = (LinkList) malloc(sizeof(Node));
	r = *L;
	for (i = 0; i < n; i++) {
		p = (LinkList) malloc(sizeof(Node));
		p->data = rand() % 100 + 1;
		r->next = p;
		r = p;
	}
	r->next = NULL;
	return OK;
}
int main() {
	LinkList L; //L是指针,为头指针
	ElemType e;
	Status i;
	int j, k;
	i = InitList(&L); //参数是&L是指向L的指针
	printf("初始化L后：ListLength(L)=%d\n", ListLength(L));
	for (j = 1; j <= 5; j++) {
		i = ListInsert(&L, 1, j);
	}
	printf("在L的表头依次插入1～5后：L.data=");
	ListTraverse(L);

	printf("ListLength(L)=%d \n", ListLength(L));
	i = ListEmpty(L);
	printf("L是否空：i=%d(1:是 0:否)\n", i);

	i = ClearList(&L);
	printf("清空L后：ListLength(L)=%d\n", ListLength(L));
	i = ListEmpty(L);
	printf("L是否空：i=%d(1:是 0:否)\n", i);

	for (j = 1; j <= 10; j++)
		ListInsert(&L, j, j);
	printf("在L的表尾依次插入1～10后：L.data=");
	ListTraverse(L);

	printf("ListLength(L)=%d \n", ListLength(L));

	ListInsert(&L, 1, 0);
	printf("在L的表头插入0后：L.data=");
	ListTraverse(L);
	printf("ListLength(L)=%d \n", ListLength(L));

	GetElem(L, 5, &e);
	printf("第5个元素的值为：%d\n", e);
	for (j = 3; j <= 4; j++) {
		k = LocateElem(L, j);
		if (k)
			printf("第%d个元素的值为%d\n", k, j);
		else
			printf("没有值为%d的元素\n", j);
	}

	k = ListLength(L); /* k为表长 */
	for (j = k + 1; j >= k; j--) {
		i = ListDelete(&L, j, &e); /* 删除第j个数据 */
		if (i == ERROR)
			printf("删除第%d个数据失败\n", j);
		else
			printf("删除第%d个的元素值为：%d\n", j, e);
	}
	printf("依次输出L的元素：");
	ListTraverse(L);

	j = 5;
	ListDelete(&L, j, &e); /* 删除第5个数据 */
	printf("删除第%d个的元素值为：%d\n", j, e);

	printf("依次输出L的元素：");
	ListTraverse(L);

	i = ClearList(&L);
	printf("\n清空L后：ListLength(L)=%d\n", ListLength(L));
	CreateListHead(&L, 20);
	printf("整体创建L的元素(头插法)：");
	ListTraverse(L);

	i = ClearList(&L);
	printf("\n清空L后：ListLength(L)=%d\n", ListLength(L));
	CreateListTail(&L, 20);
	printf("整体创建L的元素(尾插法)：");
	ListTraverse(L);

	return 0;
}
