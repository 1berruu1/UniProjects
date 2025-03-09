#include "tests.h"
#include "problema.h"
#include <cassert>

void testPrime()
{
    assert(isprime(4) == false);
    assert(isprime(13) == true);
}

void testDistinct()
{
    int n=4;
    int x[100]={1,2,2,3,3};
    int m=0;
    int res[100]={0};
    distinct(n,x,m,res);

    assert(res[0] == 1);
    assert(res[1] == 2);
    assert(res[2] == 3);
}