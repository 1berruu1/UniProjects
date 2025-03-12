
#ifndef LAB4OOP_ENTITY_H
#define LAB4OOP_ENTITY_H
#include <stack>
using namespace std;

class Entity {
private:
    float l;
    int x;
    int y;
public:
    Entity();
    Entity(float _l, int _x, int _y);
    Entity(const Entity &s);
    Entity& operator=(const Entity &s);

    ~Entity();

    bool operator==(const Entity &s);

    void setL(float _l);
    float getL();

    void setX(int _x);
    int getX();
    void setY(int _y);
    int getY();

    float getArea();
    float getPerimeter();

    Entity getBiggest(stack<Entity> entities);
    bool inQuadrant1();
    int getMaxSequence(stack<Entity> entities);
};


#endif //LAB4OOP_ENTITY_H
