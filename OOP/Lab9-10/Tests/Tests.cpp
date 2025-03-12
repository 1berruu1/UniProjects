//#include "../Repository/Repo.h"
#include "Tests.h"
#include "../Service/ServiceBilet.h"
#include <fstream>
#include <cassert>
#include <iostream>
using namespace std;
void testConstrDestr(){
    Bilet b1;
    assert(b1.getID() == 0);
    assert(b1.getTime() == 0);
    assert(b1.getPrice() == 0);
    Bilet b2(1,1,1);
    assert(b2.getID() == 1);
    assert(b2.getTime() ==1);
    assert(b2.getPrice() == 1);
    Bilet b3(b2);
    assert(b3.getID() == 1);
    assert(b3.getTime() ==1);
    assert(b3.getPrice() == 1);
}
void testGetTime(){
    Bilet b1(1,1,1);
    assert(b1.getTime() == 1);
}

void testGetID(){
    Bilet b1(1,1,1);
    assert(b1.getID() == 1);
}

void testGetPrice(){
    Bilet b1(1,1,1);
    assert(b1.getPrice() == 1);
}

//void testGetSize(){
//    Bilet b1(1,1,1);
//    Bilet b2(1,1,1);
//    Bilet b3(1,1,1);
//    Repository repo;
//    repo.addBilet(b1);
//    repo.addBilet(b2);
//    repo.addBilet(b3);
//    assert(repo.getSize() == 3);
//}
//void testAdd(){
//    Bilet b1(1,1,1);
//    Repository repo;
//    repo.addBilet(b1);
//    assert(repo.getSize() == 1);
//}
void testFileRepo() {
    std::ofstream testFile("testFile.txt");
    Bilet b1(1, 1, 1);
    testFile << b1;
    testFile.close();

}

void testService(){
    FileRepo testrepo("testFile.txt");
    Service service(testrepo);
    service.addBilet(1,6,18);
    //Test getTicket
    vector<Bilet> bilet = service.getTicket(1);
    assert(bilet.size() == 1);
    assert(bilet[0].getID() == 1);
    assert(bilet[0].getTime() == 6);
    assert(bilet[0].getPrice() == 18);

    // Test checkID
    assert(service.checkID(1) == true);
    assert(service.checkID(2) == false);

    // Test deleteBilet
    service.deleteBilet(1);
    assert(service.getAll().size() == 0);
}

void testAll(){
    testConstrDestr();
    testGetTime();
    testGetID();
    testGetPrice();
//    testGetSize();
//    testAdd();
    testFileRepo();
    std::cout<< "Test done!"<< endl;
}
