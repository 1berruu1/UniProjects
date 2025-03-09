#include <iostream>
#include "problema.h"
#include "tests.h"
using namespace std;


// This program is a console application that reads a list of integers and performs operations on it.
// adding numbers to a sequence, displaying the sequence,
// displaying the prime numbers in the sequence, and displaying the distinct numbers in the sequence.
// the array for the sequence is alocated staticaly

int main() {
    int n, x[100];
    test_distincte();
    test_prime();
    console(n,x);
    return 0;
}
 
