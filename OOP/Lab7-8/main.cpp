#include <iostream>
#include "Tests/Tests.h"
#include "UI/UI.h"


using namespace std;
int main() {
//    testAll();
    Repository repo;
    Service service(repo);
    UI userInterface(service);
    userInterface.console();

    return 0;
}
