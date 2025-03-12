#include <iostream>
using namespace std;

class Expense{
private:
    int day;
    int spent_money;
    char* type;
    char* description;
public:
    // Constructor & Destructor
    Expense();
    Expense(int day,int spent_money,char* type,char* desc);
    Expense(const Expense &expense);
    ~Expense();
    //Other
    int getDay();
    int getSpentMoney();
    char* getType() const;
    char* getDescription() const;

    void setDay(int givenDay);
    void setSpentMoney(int givenSpentMoney);
    void setType(char *givenType);
    void setDescription(char *givenDesc);

    Expense& operator=(const Expense &expense);
    bool operator==(const Expense &expense);
    friend ostream& operator<<(ostream& os, Expense& expense);
};