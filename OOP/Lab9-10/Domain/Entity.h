#include <iostream>
#include<istream>
using namespace std;

class Bilet{
private:
    int id;
    float price;
    int timp;
public:
    //CONSTRUCTORI DESCRTUCOR
    Bilet();
    Bilet(int id,int time,int price);
    Bilet(const Bilet& b);
    ~Bilet();
    //GETTER SETTER
    int getID() const;
    float getPrice() const;
    int getTime() const;

    void setID(int id);
    void setPrice(float price);
    void setTime(int time);

    // OTHER OPERATIONS
    Bilet& operator=(const Bilet& bilet);
    void display();
    friend std::ostream& operator<<(std::ostream& os, Bilet& bilet);
    friend std::istream& operator>>(std::istream& is, Bilet& bilet);
};

class ATM{
private:
    float pointFiveCoins;
    float oneDollar;
    float fiveDollars;
    float tenDollars;
    float fiftyDollars;
public:
    ATM(float pFive, float one, float five, float ten, float fifty);
    //getter setter
    float getPointFiveCoins();
    float getOneDollar();
    float getFiveDollars();
    float getTenDollars();
    float getFiftyDollars();

    void setPointFiveCoins(float pointFiveCoins);
    void setOneDollar(float oneDollar);
    void setFiveDollars(float fiveDollars);
    void setTenDollars(float tenDollars);
    void setFiftyDollars(float fiftyDollars);

};