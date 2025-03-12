#include <cmath>
#include "ServiceBilet.h"
#include "../Domain/Vali_and_Exept.h"
//CONSTRUCTOR DESTRUCTOR
Service::Service(FileRepo &repo) : fileRepo(repo){

}


Service::Service(FileRepo givenRepo, FileRepo &repo) : fileRepo(repo){

}

Service::~Service() {

}





//OPERATIONS

void Service::deleteBilet(int id) {
    this->fileRepo.deleteBilet(id);
}

void Service::addBilet(int id, int time, int price) {
    Bilet new_parking(time, id, price);
    this->fileRepo.addBilet(new_parking);
}

vector<Bilet> Service::getAll() {
    return this->fileRepo.getRepo();
}

vector<Bilet> Service::getTicket(int id) {
    return this->fileRepo.getElem(id);
}


bool Service::checkID(int id) {
    vector < Bilet > allTickets = this->getAll();
    for (Bilet &ticket: allTickets) {
        if (ticket.getID() == id)
            return true;
        }
    return false;
}

void Service::payTicket(ATM &atm, int ticketPrice, int ammountPaid) {
    if (ammountPaid < ticketPrice) {
        throw ATMexceptions("Not enough money to pay for the ticket");
        return;
    }
    int change = ammountPaid - ticketPrice;
    int fiftyBills = change / 50;
    change = change % 50;
    int tenBills = change / 10;
    change = change % 10;
    int fiveBills = change / 5;
    change = change % 5;
    int oneBills = change;


    if(fiftyBills > atm.getFiftyDollars() || tenBills > atm.getTenDollars() || fiveBills > atm.getFiveDollars() || oneBills > atm.getOneDollar()){
        throw ATMexceptions("Not enough money in the ATM");
        return;
    }
    atm.setFiftyDollars(atm.getFiftyDollars() - fiftyBills);
    atm.setTenDollars(atm.getTenDollars() - tenBills);
    atm.setFiveDollars(atm.getFiveDollars() - fiveBills);
    atm.setOneDollar(atm.getOneDollar() - oneBills);

    cout << "Payment successful. Your change is: " << endl;
    if (fiftyBills > 0) cout << "50: " << fiftyBills << endl;
    if (tenBills > 0) cout << "10: " << tenBills << endl;
    if (fiveBills > 0) cout << "5: " << fiveBills << endl;
    if (oneBills > 0) cout << "1: " << oneBills << endl;
}



