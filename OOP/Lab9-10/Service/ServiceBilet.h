#include "../Repository/FileRepo.h"

class Service{
private:
//    Repository& repo;
    FileRepo& fileRepo;
public:
    // CONSTRUCTOR DESTRUCTOR
    Service(FileRepo  &fileRepo1);
    Service(FileRepo givenRepo, FileRepo &repo);
    ~Service();


    //OPERATIONS
//    void resize(Repository& repo);
    int ReturnCurentSize();

    vector<Bilet> getAll();
    vector<Bilet> getTicket(int id);
    bool checkID(int id);
    void addBilet(int id,int time,int price);
    void payTicket(ATM& atm, int ticketPrice,int ammountPaid);
    void deleteBilet(int id);
    void testService();
};