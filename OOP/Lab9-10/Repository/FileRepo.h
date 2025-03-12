#include <iostream>
#include <fstream>
#include <vector>
#include "../Domain/Entity.h"
using namespace std;

class FileRepo{
private:
    string ParkingTickets;
    string testFile;
public:
    FileRepo(const string& parkingtickets);
    void storeInFile(Bilet& tickets);
    vector<Bilet> loadFromFile();

    void addBilet(Bilet& bilet);
    vector<Bilet> getElem(int pos);
    vector<Bilet> getRepo();
    bool checkID(int id);
    void deleteBilet(int id);
};