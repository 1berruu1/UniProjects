#include "../Domain/Entities.h"


class Repository{
private:
    Expense* Repo;
    int numberExpenses;
    int maxNumberExpenses;
public:
    // CONSTRUCTOR DESTRUCTOR
    Repository();
    Repository(Expense*,int numExpense, int maxNumExpense);
    Repository(const Repository& repo);
    ~Repository();
    //OTHER
    int getMaxSize() const;
    int getCurrentSize() const;

    void setCurentSize(int size);
    void setRepo(Expense *repo);
    void setMaxsize(int max_size);

    Repository& operator=(const Repository &repo);

    void addExpense(Expense& expense);
    Expense getExpense(int pos);
    Expense* getRepo();
};