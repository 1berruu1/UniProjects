#include <vector>
#include "Repo.h"
using namespace std;
Repository::Repository() {
    this->currentSize = 0;
    this->maxSize = 0;
    this->repo = vector<Bilet>(5);
}

Repository::Repository(int size, int maxSize, Bilet* repo) {
    this->currentSize = size;
    this->maxSize = maxSize;
    this->repo = vector<Bilet>(size);
    for(int i =0;i< size; i++)
        this->repo[i] = repo[i];
}

Repository::Repository(const Repository &repo) {
    this->currentSize = repo.currentSize;
    this->maxSize = repo.maxSize;
    this->repo = vector<Bilet>(repo.currentSize);
    for(int i=0;i<repo.currentSize;i++)
        this->repo[i] = repo.repo[i];
}

Repository::~Repository() {

}

//GETTER SETTER
int Repository::getSize() {
    return this->currentSize;
}

int Repository::getMaxSize() {
    return this->maxSize;
}

void Repository::setRepo(vector<Bilet> newRepo) {
    this->repo = newRepo;
}

void Repository::setSize(int size) {
    this->currentSize = size;
}

void Repository::setMaxSize(int maxSize) {
    this->maxSize = maxSize;
}
//OTHER OPERATIONS

void Repository::addBilet(Bilet &bilet) {
    this->repo[this->currentSize] = bilet;
    this->currentSize++;
}

vector<Bilet> Repository::getElem(int pos) {
    vector<Bilet> result;
    for (int i = 0; i < this->currentSize; i++) {
        if (this->repo[i].getID() == pos) {
            result.push_back(this->repo[i]);
            break;
        }
    }
    return result;
}

vector<Bilet> Repository::getRepo() {
    return this->repo;
}