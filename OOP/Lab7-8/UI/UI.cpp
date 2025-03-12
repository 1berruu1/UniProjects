#include "UI.h"
//CONSTRUCTOR DESTRUCTOR
using namespace std;
UI::UI(Service &service) : service(service){

}

UI::~UI() {

}
//OTHER OPERATIONS
void UI::runMenu() {
    cout << "1.Adauga cheltuiala" << endl;
    cout << "2.Afiseaza toate cheltuielile" << endl;
    cout << "3.Afiseaza cheltuiala" << endl;
    cout << "4.Update cheltuiala" << endl;
    cout << "5.Delete cheltuiala" << endl;
    cout << "6.Delete cheltuieli dupa zi" << endl;
    cout << "7.Delete dupa tip(in/out)" << endl;
    cout << "8.Filter dupa tip(in/out)" << endl;
    cout << "9.Total dupa tip(in/out)" << endl;
    cout << "m.Reprint menu" << endl;
    cout << "x.Exit" << endl;
}

void UI::readInfo(int &day, int &sum, char* &type, char* &desc) {
    cout << "Introdu ziua cheltuielii: ";
    cin >> day;
    cout << endl;
    cout << "Introdu suma cheltuielii: ";
    cin >> sum;
    cout << endl;
    cout << "Introdu tipul cheltuielii(in/out): ";
    cin >> type;
    cout << endl;
    cout << "Introdu descriere pentru cheltuiala: ";
    cin >> desc;
    cout << endl;
}

void UI::UIaddExpense() {
    int day, money_spent;
    char* type = new char[3];
    char* desc = new char[10];
    readInfo(day, money_spent, type, desc);

    if (day < 1 || day > 31) {
        cout << "Ziua trebuie sa fie intre 1 si 31!" << endl;
        return;
    }
    this->service.addElem(day, money_spent, type, desc);
    cout << "Cheltuiala a fost adaugata!" << endl;

}

void UI::inputComand(char& option) {
    runMenu();
    cout << "Alege o optiune: ";
    cin >> option;
    cout << endl;
}

void UI::UIprintAll() {
    cout << "Astrea sunt toate cheltuielile: " << endl;
    int noExpense = service.noExpenses();
    Expense* expenses = service.getAll();
    for(int i  =0; i < noExpense; i++)
        cout << i << ". " << expenses[i] << endl;
}

void UI::UIprintExpense() {
    cout << "Ce cheltuiala vrei sa vezi: " << endl;
    int expenseID;
    cin >> expenseID;
    Expense theExpense = this->service.getElem(expenseID);
    cout << theExpense << endl;
}

void UI::UIdeleteExpense() {
    int id;
    UIprintAll();
    cout<<"Care dintre urmatoarele cheltieli doriti sa o stergeti: ";
    cin >> id;
    this->service.deleteElem(id);
}

void UI::UI_update() {
    int day,money_spent,id;
    char* type = new char[25];
    char* desc = new char[10];
    UIprintAll();
    cout << "Alege o cheltuiala care vrei sa o editezi: ";
    cin >> id;
    readInfo(day,money_spent,type,desc);
    this->service.updateElem(id,day,money_spent,type,desc);
}
// TODO Cand sterge iara da simboluri
void UI::UIdeleteExpenseByDay() {
    int day;
    cout << "Introdu ziua care vrei sa o stergi: ";
    cin >> day;
    this->service.deleteElemByDay(day);
    cout << "Cheltuielile din ziua" << " " << day << " " << "au fost sterse" << endl;
}
//TODO iara nu se repeta meniul
void UI::UIdeleteExpenseByType() {
        char type[25];
        std::cout << "Alege tipul de cheltuia care vrei sa fie sters('in' sau 'out'): ";
        std::cin >> type;

        this->service.deleteElemByType(type);
}

void UI::UIfilterByType() {
    char* type = new char[25];
    cout <<"Alege tipul de cheltuiala care vrei sa fie filtrata('in' sau 'out'): ";
    cin>> type;

    this->service.filterByType(type);
}

void UI::UIshowTotalByType() {
    char type[10];
    cout <<"Introdu tipul la care vrei sa fie caltulat totalul('in' sau 'out'): ";
    cin >> type;
    int totalAmount = this->service.getTotalByType(type);
    cout << "Totalul este la tipul " << type << ": " << totalAmount << endl;
}

void UI::undo() {
    cout<<"Undoing ultima actiune" << endl;
    this->service.undo();
    cout<<"Undo complete"<<endl;
    this->UIprintAll();
}

//CONSOLE
void UI::console() {
    bool run = true;
    while(run)
    {
        char option;
        inputComand(option);
        switch (option) {

            case '1': {
                UIaddExpense();
                break;
            }
            case '2': {
                UIprintAll();
                break;
            }
            case '3':{
                UIprintExpense();
                break;
            }
            case '4': {
                UI_update();
                break;
            }
            case '5': {
                UIdeleteExpense();
                break;
            }
            case '6':{
                UIdeleteExpenseByDay();
                break;
            }
            case '7':{
                UIdeleteExpenseByType();
                break;
            }
            case '8':{
                UIfilterByType();
                break;
            }
            case '9':{
                UIshowTotalByType();
                break;
            }
            case 'z':{
                undo();
                break;
            }
            case 'm':{
                runMenu();
                break;
            }
            case 'x': {
                run = false;
                break;
            }
        }
    }
}