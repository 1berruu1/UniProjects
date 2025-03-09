
#include "tests.h"
#include "problema.h"
#include <cassert>
#include <iostream>
using namespace std;

void test_distincte()
{
    int n=5;
    int a[100]={0,1,2,2,3,3};
    int m=0;
    int res[100]={0};
    s_distincte(n,a,m,res);

    assert(res[0] == 0);
    assert(res[1] == 2);
    assert(res[2] == 3);
}

void test_prime()
{
    assert(prim(3) == true );
    assert(prim(6) == false);
}