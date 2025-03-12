#include "Repo.h"
using namespace std;
#include <iostream>

// CONSTRUCTOR DESTRUCTOR
Repository::Repository() {
    this->numberExpenses = 0;
    this->maxNumberExpenses = 4;
    this->Repo = new Expense[4];
}

Repository::Repository(Expense *Repo, int numExpense, int maxNumExpense) {
    this->numberExpenses = numExpense;
    this->maxNumberExpenses = maxNumExpense;
    this->Repo = new Expense[numExpense];
    for(int i = 0; i< numExpense; i++){
        this->Repo[i] = Repo[i];
    }
}

Repository::Repository(const Repository &repo) {
    this->numberExpenses = repo.numberExpenses;
    this->maxNumberExpenses = repo.numberExpenses;
    this->Repo = new Expense[repo.numberExpenses];
    for(int i = 0; i< repo.numberExpenses; i++){
        this->Repo[i] = Repo[i];
    }
}

Repository::~Repository(){
    if (this->Repo != nullptr)
    {
        delete[] this->Repo;
        this->Repo = nullptr;
    }
}


// GETTER SETTER

int Repository::getCurrentSize() const {
    return this->numberExpenses;
}

int Repository::getMaxSize() const{
    return this->maxNumberExpenses;
}

void Repository::setCurentSize(int size) {
    this->numberExpenses = size;
}

void Repository::setRepo(Expense *repo) {
    this->Repo = repo;
}

void Repository::setMaxsize(int max_size) {
    this->maxNumberExpenses = max_size;
}
// OTHER

Expense* Repository::getRepo() {
    return this->Repo;
}

Expense Repository::getExpense(int pos) {
    return this->Repo[pos];
}

void Repository::addExpense(Expense &expense) {
    this->Repo[this->numberExpenses++] = expense;
}


Repository& Repository::operator=(const Repository &repo) {
    this->numberExpenses = 0;
    this->maxNumberExpenses = repo.maxNumberExpenses;
    this->Repo = new Expense[this->maxNumberExpenses];
    for(int i=0;i<repo.numberExpenses;i++)
    {
        Repo[this->numberExpenses] = repo.Repo[i];
        this->numberExpenses++;
    }
    return *this;
}