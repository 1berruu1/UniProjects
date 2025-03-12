#include "Entities.h"
#include <iostream>
#include <cstring>
using namespace std;

//CONSTRUCTOR & DESTRUCTOR
Expense::Expense() {
    this->day=1;
    this->spent_money=0;
    this->type = nullptr;
    this-> description = nullptr;
}

Expense::Expense(int days,int sp_money,char* types,char* desc) {
    this->day = days;
    this->spent_money = sp_money;
    this->type = types;
    this->description =desc;
}

Expense::Expense(const Expense &expense) {
    this->day = expense.day;
    this->spent_money = expense.spent_money;
    this->type = expense.type;
    this->description = expense.description;
}

Expense::~Expense() {
    if(this->type != nullptr){
        delete[] this->type;
        this->type = nullptr;
    }
    if(this->description != nullptr){
        delete[] this->description;
        this->description = nullptr;
    }
}

//GETTER & SETTER

int Expense::getDay(){
    return this->day;
}

int Expense::getSpentMoney(){
    return this->spent_money;
}

char* Expense::getType() const{
    return this->type;
}

char* Expense::getDescription() const{
    return this->description;
}

void Expense::setDay(int givenDay){
    this->day = givenDay;
}
void Expense::setSpentMoney(int givenSpentMoney){
    this->spent_money = givenSpentMoney;
}
void Expense::setType(char *givenType){
    this->type =new char[strlen(givenType) + 1];
    strcpy_s(this->type, strlen(givenType) + 1, givenType);

}

void Expense::setDescription(char *givenDesc){
    this->description =new char[strlen(givenDesc) + 1];
    strcpy_s(this->description, strlen(givenDesc) + 1, givenDesc);
}

Expense& Expense::operator=(const Expense &expense){
    this->setDay(expense.day);
    this->setSpentMoney(expense.spent_money);
    this->type = nullptr;
    this->setType(expense.getType());
    this->description = nullptr;
    this->setDescription(expense.getDescription());
    return *this;
}

bool Expense::operator==(const Expense &expense) {
    if(this->day == expense.day)
        if(this->spent_money == expense.spent_money)
            if(this->type, expense.type == 0)
                if(this->description, expense.description == 0)
                    return true;
    return false;
}

ostream& operator<<(ostream& os, Expense& expense){
    os << "Cheltuiala este pe data: " << expense.getDay() << endl
    << "Cu suma de: " << expense.getSpentMoney() << endl
    << "De tipul: " << expense.getType() << endl
    << "Cu descrierea: " << expense.getDescription() << endl;
    return os;
}
