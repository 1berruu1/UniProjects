#include "../Service/Service.h"
using namespace std;
#include <iostream>



class UI{
private:
    Service& service;
public:
    //CONSTRUCTOR DESTRUCTOR
    UI(Service& service);
    ~UI();
    //Console operations
    void runMenu();
    void inputComand(char& option);
    void console();
    void readInfo(int &day, int &sum, char* &type, char*& desc);
    //UI operations
    void UIaddExpense();
    void UIprintAll();
    void UIprintExpense();
    void UI_update();
    void UIdeleteExpense();
    void UIdeleteExpenseByDay();
    void UIdeleteExpenseByType();
    void UIfilterByType();
    void UIshowTotalByType();
    void undo();

};