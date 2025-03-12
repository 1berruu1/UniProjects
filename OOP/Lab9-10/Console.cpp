#include "Console.h"
using namespace std;
#include <iostream>
#include "../Domain/Vali_and_Exept.h"
#include "../Domain/vali.h"


//CONSTRUCTOR DESTRUCTOR
Console::Console(Service &service) : service(service) {

}

Console::~Console() {

}
//OTHER OPERATIONS
void Console::showOptions() {
    cout << "1. Add a park ticket" << endl;
    cout << "2. Print all tickets" << endl;
    cout << "3. Pay a ticket" << endl;
    cout << "x. Exit" << endl;
}

void Console::inputComand(char &option) {
    cout <<"Choose an option: ";
    cin >> option;
    cout<< endl;
}

void Console::readInput(int &id, int &time, int &price) {
    cout << "Input the id: "<< endl;
    cin >> id;
    cout << "Choose the parking time: "<< endl;
    cout << "1. 1 hour - 5.0 lei" << endl;
    cout << "2. 3 hours - 12.0 lei" << endl;
    cout << "3. 6 hours 18.0 lei" << endl;
    cout << "4. 1 day 50.0 lei" << endl;
    int option;
    cin >> option;
    switch(option) {
        case 1:
            time = 1;
            price = 5;
            break;
        case 2:
            time = 3;
            price = 12;
            break;
        case 3:
            time = 6;
            price = 18;
            break;
        case 4:
            time = 24;
            price = 50;
            break;
        default:
            cout << "Invalid option! Defaulting to 1 hour." << endl;
            time = 1;
            break;
    }
    cout << "Input the price: "<< endl;
    cout << endl;
}

void Console::UIaddParkTicket() {
    int id, time, price;
    readInput(id, time, price);
    while(this->service.checkID(id)){
        cout << "The id is already taken! Please input another one: ";
        cin >> id;
    }
    cout << "Added ticket with id: " << id << " time: " << time << " price: " << price << endl;
    this->service.addBilet(time, id, price);
    cout << "The ticket was added!" << endl;
}

void Console::UIprintAllTikets() {
    cout <<"These are all the parking tickets: " << endl;
    vector<Bilet> all = service.getAll();
    for(auto& ticket : all){
        ticket.display();
    }
}

void Console::UiPayTicket() {
    ATM atm(100, 100, 100, 100, 100);
    int id, ammountPaid;
    cout << "Input the id of the ticket you want to pay: ";
    cin >> id;
    if(!service.checkID(id)){
        throw UIexceptions("The id does not exist!");
    }
    vector<Bilet> ticket = service.getTicket(id);
    if (ticket.empty()){
        throw UIexceptions("The ticket does not exist!");
    }
    cout << "The ticket costs: " << ticket[0].getPrice() << endl;
    cout << "Input the ammount you want to pay: ";
    cin >> ammountPaid;
    if(ammountPaid < ticket[0].getPrice()){
        throw UIexceptions("Not enough money to pay for the ticket");
    }
    try {
        service.payTicket(atm, ticket[0].getPrice(), ammountPaid);
    } catch (const ATMexceptions& e) {
        std::cerr << e.what() << std::endl;
    }
        service.deleteBilet(id);
    cout << "The ticket was paid!" << endl;
}

void Console::console() {
    try {
        Validator v;
        bool run = true;
        while (run) {
            showOptions();
            char option;
            inputComand(option);
            v.Validate_option(option,'1','3');
            switch (option) {
                case '1':
                    UIaddParkTicket();
                    break;
                case '2':
                    UIprintAllTikets();
                    break;
                case '3':
                    try {
                        UiPayTicket();
                    } catch (const UIexceptions &e) {
                        std::cerr << e.what() << std::endl;
                    }
                    break;
                case 'x':
                    run = false;
                    break;
            }
        }
    }
    catch (...){

    }

}
