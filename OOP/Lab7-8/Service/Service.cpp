#include "Service.h"
#include <iostream>
#include <cstring>
using namespace std;

//CONSTRUCTOR DESTRUCTOR
Service::Service(Repository &repo) : Repo(repo) {
    this->Repo.getCurrentSize();
    this->Repo.getMaxSize();
}

Service::Service(Repository givenRepo, Repository &repo) : Repo(repo) {
    this->Repo.setCurentSize(givenRepo.getCurrentSize());
    this->Repo.setMaxsize(givenRepo.getMaxSize());

}

Service::Service(const Repository &repo1, Repository &repo2) : Repo(repo2) {

}


Service::~Service() {
}

Service &Service::operator=(const Service &s) {
    this->Repo = s.Repo;
    return *this;
}

// OPERATIONS
void Service::resize(Repository &repo) {
    int temp = repo.getCurrentSize() * 2;
    auto* newList = new Expense[temp];
    Expense* expense = repo.getRepo();
    for(int i =0;i < repo.getMaxSize(); i++)
        newList[i] = expense[i];

    repo.setMaxsize(temp);
    if(expense != nullptr){
        delete[] expense;
        expense = nullptr;
    }
    repo.setRepo(newList);
}


void Service::addElem(int day, int sum, char *type, char *desc) {
    if(this->Repo.getCurrentSize() == this->Repo.getMaxSize())
        resize(this->Repo);
    Expense new_expense(day, sum, type, desc);
    Repo.addExpense(new_expense);
}

Expense* Service::getAll() {
    return this->Repo.getRepo();
}

Expense Service::getElem(int elem) {
    return this->Repo.getExpense(elem);
}

int Service::noExpenses() {
    return this->Repo.getCurrentSize();
}
void Service::updateElem(int id, int newDay,int newPrice, char *newType, char *newDesc) {
    Expense updatedExpense = Expense(newDay,newPrice,newType,newDesc);
    Expense* expenses = getAll();
    expenses[id] =updatedExpense;
    this->Repo.setRepo(expenses);
    history.push(Repo);
}

void Service::deleteElem(int id) {
    Expense* expense = this->Repo.getRepo();
    int size = this->Repo.getCurrentSize() - 1;
    for(int i = id; i < size; i++)
        expense[i] = expense[i + 1];
    this->Repo.setRepo(expense);
    this->Repo.setCurentSize(size);
    history.push(Repo);
}

void Service::deleteElemByDay(int day) {
    Expense *expenses = this->Repo.getRepo();
    int size = this->Repo.getCurrentSize();

    int newSize = 0;
    for(int i = 0;i< size;i++){
        if(expenses[i].getDay() != day){
            expenses[newSize] = expenses[i];
            newSize++;
        }

    }
    this->Repo.setCurentSize(newSize);
    history.push(Repo);
}

void Service::deleteElemByType(const char *type) {
    Expense *expenses = this->Repo.getRepo();
    int size = this->Repo.getCurrentSize();

    int newSize = 0;
    for (int i = 0; i < size; ++i) {
        if (strcmp(expenses[i].getType(), type) != 0) {
            expenses[newSize] = expenses[i];
            newSize++;
        }
    }
    this->Repo.setCurentSize(newSize);
    history.push(Repo);
}

void Service::filterByType(char *type) {
    auto* filteredExpenses = new Expense[noExpenses()];
    Expense* allExpenses = getAll();
    int count = 0;
    for(int i = 0; i < noExpenses(); i++)
        if(strcmp(allExpenses[i].getType(),type) == 0)
            filteredExpenses[count++] = allExpenses[i];
    this->Repo.setRepo(filteredExpenses);
    this->Repo.setCurentSize(count);
    history.push(Repo);
}

int Service::getTotalByType(char *type) {
    Expense* expense = this->Repo.getRepo();
    int size = this->Repo.getCurrentSize();
    int totalAmount = 0;

    for(int i=0; i <  size;i++){
        if(strcmp(expense[i].getType(),type) == 0){
            totalAmount += expense[i].getSpentMoney();
        }
    }
    return totalAmount;
}

void Service::undo() {
    if(history.empty())
        return;
    history.pop();
    if(history.empty())
    {
        Repo = Repository();
        return;
    }

    Repo = history.top();
}

void Service::addToHistory() {
//    Repository TempRepo = Repo;
    //history.push(TempRepo);
}