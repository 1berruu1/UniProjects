
#include "Entity.h"

// CONSTRUCTOR DESTRUCTOR
Bilet::Bilet() {
    this->id = 0;
    this->timp = 0;
    this->price = 0;

}

Bilet::Bilet(int id, int time, int price) {
    this->id = id;
    this->timp = time;
    this->price = price;
}

Bilet::Bilet(const Bilet &b) {
    this->id = b.id;
    this->timp = b.timp;
    this->price = b.price;
}

Bilet::~Bilet(){
}

//GETTER SETTER

int Bilet::getID() const {
    return this->id;
}

float Bilet::getPrice() const{
    return this->price;
}

int Bilet::getTime() const{
    return this->timp;
}

void Bilet::setID(int id) {
    this->id = id;
}

void Bilet::setPrice(float price) {
    this->price = price;
}

void Bilet::setTime(int time) {
    this->timp = time;
}

// OTHER OPERATIONS
Bilet &Bilet::operator=(const Bilet &bilet) {
    this->setID(bilet.id);
    this->setPrice(bilet.price);
    this->setTime(bilet.timp);
    return *this;
}


istream &operator>>(istream &is, Bilet &bilet) {
    int id, time;
    float price;
    if (is >> id >> time >> price) {
        bilet.setID(id);
        bilet.setTime(time);
        bilet.setPrice(price);
    }
    return is;
}

ostream &operator<<(ostream &os, Bilet &bilet) {
    os << bilet.getID() << " " << bilet.getTime() << " " << bilet.getPrice() << endl;
    return os;
}

void Bilet::display() {
    cout << "ID: " << getID() << " time in hours: " << getTime() << " price: " << getPrice() << endl;
}

//
//ATM
//

ATM::ATM(float pFive, float one, float five, float ten, float fifty) {
    this->pointFiveCoins = pFive;
    this->oneDollar = one;
    this->fiveDollars = five;
    this->tenDollars = ten;
    this->fiftyDollars = fifty;
}

float ATM::getPointFiveCoins() {
    return this->pointFiveCoins;
}

float ATM::getOneDollar() {
    return this->oneDollar;
}

float ATM::getFiveDollars() {
    return this->fiveDollars;
}

float ATM::getTenDollars() {
    return this->tenDollars;
}

float ATM::getFiftyDollars() {
    return this->fiftyDollars;
}

void ATM::setPointFiveCoins(float pointFiveCoins) {
    this->pointFiveCoins = pointFiveCoins;
}

void ATM::setOneDollar(float oneDollar) {
    this->oneDollar = oneDollar;
}

void ATM::setFiveDollars(float fiveDollars) {
    this->fiveDollars = fiveDollars;
}

void ATM::setTenDollars(float tenDollars) {
    this->tenDollars = tenDollars;
}

void ATM::setFiftyDollars(float fiftyDollars) {
    this->fiftyDollars = fiftyDollars;
}

