
#ifndef LAB_9_10_24_04_2024_VALI_AND_EXCEPT_H
#define LAB_9_10_24_04_2024_VALI_AND_EXCEPT_H
#include <exception>
#include <string>
using namespace std;




class UIexceptions{
private:
    string message;
public:
    UIexceptions(const string& message): message{message} {}
    const char* what() const noexcept {
        return message.c_str();
    }
};


class ATMexceptions{
private:
    string message;
public:
    ATMexceptions(const string& message): message{message} {}
    const char* what() const noexcept {
        return message.c_str();
    }
};
#endif //LAB_9_10_24_04_2024_VALI_AND_EXCEPT_H

