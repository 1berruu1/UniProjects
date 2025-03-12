#include <iostream>
#include "Tests/Tests.h"
#include "UI/Console.h"
#include "Domain/Vali_and_Exept.h"

int main() {
    testAll();
    FileRepo fileRepo("ParkingTickets");
    Service service(fileRepo);
    Console console(service);
    try {
        console.console();
    } catch (UIexceptions &e) {
        cout << e.what();
    }
    return 0;
}
