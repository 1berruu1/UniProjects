
#include <iostream>
#include "problem.h"
using namespace std;

int palindrom(int n){
    int pal = 0;
    while(n != 0){
        pal = pal*10 + n%10;
        n = n/10;
    }
    return pal;
}
int readInput(){
    int n;
    cout << "choose an number: ";
    cin >> n;
    return n;
}

void printInput(int n){
    cout << "The palindrome of this number is: " << n << "\n";
}

void probl8(){
    int number = readInput();
    int pal = palindrom(number);
    printInput(pal);

}