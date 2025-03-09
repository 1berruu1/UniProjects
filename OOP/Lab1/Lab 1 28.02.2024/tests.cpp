
#include <iostream>
#include "tests.h"
#include <cassert>
#include "problem.h"
using namespace std;

void test_pal(){
    assert(palindrom(123) == 321);
    assert(palindrom(223) == 322);
    assert(palindrom(500) == 5 );
    cout << "All tests are ok\n";
}