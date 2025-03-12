#include "FileRepo.h"
#include <algorithm>




void FileRepo::addBilet( Bilet& bilet) {
    storeInFile(bilet);
}

vector<Bilet> FileRepo::getElem(int pos) {
    vector<Bilet> tickets = loadFromFile();
    vector<Bilet> result;
    for (const auto& ticket : tickets) {
        if (ticket.getID() == pos) {
            result.push_back(ticket);
            break;
        }
    }
    return result;
}

vector<Bilet> FileRepo::getRepo() {
    return loadFromFile();
}

bool FileRepo::checkID(int id) {
        vector<Bilet> tickets = loadFromFile();
        for (const auto& ticket : tickets) {
            if (ticket.getID() == id) {
                return true;
            }
        }
        return false;
}

void FileRepo::deleteBilet(int id) {
        vector<Bilet> tickets = loadFromFile();
        tickets.erase(std::remove_if(tickets.begin(), tickets.end(),[id](const Bilet& ticket) { return ticket.getID() == id; }),tickets.end());
        ofstream file(ParkingTickets);
        if (file.is_open()) {
            for (auto& ticket : tickets) {
                file << ticket;
            }
            file.close();
        }
}
void FileRepo::storeInFile(Bilet &tickets) {
    {
        ofstream file("ParkingTickets",ios::app);
        if (file.is_open()) {
            file << tickets;
            file.close();
        } else {
            cout << "Error opening file";
        }
    }
}

FileRepo::FileRepo(const string &parkingtickets) : ParkingTickets(parkingtickets){

}

vector<Bilet> FileRepo::loadFromFile() {
    vector<Bilet> tickets;
    ifstream file("ParkingTickets");
    Bilet ticket;
    while(file >> ticket){
        tickets.push_back(ticket);
    }
    return tickets;
}

