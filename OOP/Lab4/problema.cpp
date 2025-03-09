#include "problema.h"
#include <iostream>
using namespace std;


NumarComplex::NumarComplex(){
    cout << "Consturctor implicit" << "\n";
    this -> real =0;
    this -> imag =0;
}

NumarComplex::NumarComplex(double r){
    cout << "Constructor cu parametru real: " << "\n";
    this -> real = r;
    this -> imag = 0;

NumarComplex::NumarComplex()
}
