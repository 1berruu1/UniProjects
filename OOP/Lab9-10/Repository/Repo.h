#include "../Domain/Entity.h"
#include <vector>
class Repository{
private:
    vector<Bilet> repo;
    int currentSize;
    int maxSize;
public:
    //CONSTRUCTOR DESTRUCTOR
    Repository();
    Repository(int size, int maxSize, Bilet* repo);
    Repository(const Repository& repo);
    ~Repository();
    //GETTER SETTER
    int getSize();
    int getMaxSize();

    void setSize(int size);
    void setMaxSize(int maxSize);
    void setRepo(vector<Bilet> newRepo);
    //OTHER OPERATIONS
    void addBilet(Bilet& bilet);
    vector<Bilet> getElem(int pos);
    vector<Bilet> getRepo();
};
