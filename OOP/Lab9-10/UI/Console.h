#include "../Service/ServiceBilet.h"

class Console{
private:
    Service& service;
public:
    //CONSTRUCTOR DESTRUCTOR
    Console(Service& service);
    ~Console();
    //Console operations
    void showOptions();
    void inputComand(char& option);
    void console();
    void readInput(int &id,int &time, int &price);
    //UI operations
    void UiPayTicket();
    void UIaddParkTicket();
    void UIprintAllTikets();

};