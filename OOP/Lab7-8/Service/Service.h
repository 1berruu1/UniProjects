#include "../Repository/Repo.h"
#include <stack>

class Service{
private:
    Repository& Repo;
    stack<Repository> history;
public:
// CONSTRUCTOR DESTRUCTOR
    Service(Repository  &repo);
    Service(Repository givenRepo, Repository &repo);
    Service(const Repository &repo1, Repository &repo2);
    Service& operator=(const Service& s);
    ~Service();

    //OPERATIONS
    void resize(Repository& repo);
    int noExpenses();

    Expense* getAll();
    Expense getElem(int elem);
    void addElem(int day,int sum,char* type,char* desc);
    void updateElem(int id, int newDay,int newPrice,char* newType,char* newDesc);
    void deleteElem(int id);
    void deleteElemByDay(int day);
    void deleteElemByType(const char* type);
    void filterByType(char* type);
    int getTotalByType(char* type);
    void undo();
    void addToHistory();
};