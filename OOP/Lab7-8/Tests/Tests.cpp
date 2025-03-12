#include "Tests.h"
#include <cassert>
#include <iostream>
#include "../Service/Service.h"

using namespace std;
//Test Entity
void testConstructorsDestructor(){
    Expense e1;
    assert(e1.getDay() == 1);
    assert(e1.getSpentMoney() == 0);
    assert(e1.getType() == nullptr);
    assert(e1.getDescription() == nullptr);
    Expense e2(2,100,(char*)"Factura",(char*)"Digi");
    assert(e2.getDay() == 2);
    assert(e2.getSpentMoney() == 100);
    assert(e2.getType() == "Factura");
    assert(e2.getDescription() == "Digi");
    Expense e3(e2);
    assert(e3.getDay() == 2);
    assert(e3.getSpentMoney() == 100);
    assert(e3.getType() == "Factura");
    assert(e3.getDescription() == "Digi");
}

void testGetDay(){
    Expense e1(2,250,(char*)"Cheltuiala",(char*)"factura gaz");
    assert(e1.getDay() == 2);
}

void testGetMoneySpent(){
    Expense e1(2,250,(char*)"Cheltuiala",(char*)"factura gaz");
    assert(e1.getSpentMoney()==250);
}

void testGetType(){
    Expense e1(2,250,(char*)"Cheltuiala",(char*)"factura gaz");
    assert(e1.getType() == "Cheltuiala");
}

void testGetDesc(){
    Expense e1(2,250,(char*)"Cheltuiala",(char*)"factura gaz");
    assert(e1.getDescription() == "factura gaz");
}
//Test Repo
void testGetSize(){
    Expense testE1(5,100,(char*)"Cheltuiala",(char*)"mancare");
    Expense testE2(6,90,(char*)"Cheltuiala",(char*)"curent");
    Expense testE3(9,50,(char*)"Cheltuiala",(char*)"aprovizionari");
    Repository repo;
    repo.addExpense(testE1);
    repo.addExpense(testE2);
    repo.addExpense(testE3);
    assert(repo.getCurrentSize() == 3);
}
//Test Service
void testResize(){
    Expense testE1(15,100,(char*)"out",(char*)"mancare");
    Repository repo;
    Service service(repo);
    service.addElem(21,100,(char*)"in",(char*)"money");
    service.addElem(10,150,(char*)"out",(char*)"cheltuiala");
    service.addElem(15,100,(char*)"out",(char*)"mancare");
    service.addElem(9,12000,(char*)"in",(char*)"omegalul");
    service.addElem(31,300,(char*)"out",(char*)"jocuri");
    assert(service.getElem(5) == testE1);

}

void testServiceUpdate(){
    Expense testE1(15,100,(char*)"out",(char*)"mancare");
    Repository repo;
    Service service(repo);
    service.addElem(0,1300,(char*)"in",(char*)"buisness");
    service.updateElem(1,15,100,(char*)"out",(char*)"mancare");
    assert(service.getElem(1) == testE1);
}

void testServiceDelete(){
    Expense testE1(15,100,(char*)"out",(char*)"mancare");
    Expense testE2(10,1000,(char*)"in",(char*)"etc");
    Repository repo;
    Service service(repo);
    service.addElem(15,100,(char*)"out",(char*)"mancare");
    service.addElem(10,1000,(char*)"in",(char*)"etc");
    service.deleteElem(0);
    assert(service.getElem(0)==testE2);
}

void testServiceCreate(){
    Expense testE1(15,100,(char*)"out",(char*)"mancare");
    Repository repo;
    Service service(repo);
    service.addElem(15,100,(char*)"out",(char*)"mancare");
    assert(service.getElem(0)==testE1);
}


void testAll(){
    testGetDay();
    testGetMoneySpent();
    testGetType();
    testGetDesc();
    testGetSize();
    testConstructorsDestructor();
    testResize();
    testServiceUpdate();
    testServiceDelete();
    testServiceCreate();
}