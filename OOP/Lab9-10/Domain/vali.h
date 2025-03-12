#ifndef LAB_9_10_24_04_2024_VALI_H
#define LAB_9_10_24_04_2024_VALI_H
#include <string>
#include "Vali_and_Exept.h"
using namespace std;
class Validator{
private:
    string message;
public:
    void Validate_option(char opt, char min, char max);
};

void Validator::Validate_option(char opt, char min, char max) {
    if(opt < min && max){
        throw UIexceptions("optiune invalida");
    }
}
#endif //LAB_9_10_24_04_2024_VALI_H

