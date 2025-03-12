
#include "Entity.h"

Entity::Entity() {
    this->l = 0;
    this->x = 0;
    this->y = 0;
}

Entity::Entity(float _l, int _x, int _y) {
    this->l = _l;
    this->x = _x;
    this->y = _y;
}

Entity::Entity(const Entity &s) {
    this->l = s.l;
    this->x = s.x;
    this->y = s.y;
}

Entity::~Entity() {

}

float Entity::getL() {
    return this->l;
}

void Entity::setL(float _l) {
    this->l = _l;
}

int Entity::getX() {
    return this->x;
}

void Entity::setX(int _x)
{
    this->x = _x;
}

int Entity::getY() {
    return this->y;
}

void Entity::setY(int _y)
{
    this->y = _y;
}

Entity& Entity::operator=(const Entity &s)
{
    this->l = s.l;
    this->x = s.x;
    this->y = s.y;
    return *this;
}

bool Entity::operator==(const Entity &s)
{
    return (this->l - s.l < 0.1 && this->x == s.x && this->y == s.y);
}

float Entity::getArea()
{
    return this->l * this->l;
}

float Entity::getPerimeter()
{
    return 4*this->l;
}

Entity Entity::getBiggest(stack<Entity> entities)
{
    float max_l = 0;
    Entity max_entity;
    while(!entities.empty())
    {
        if(entities.top().getL() > max_l)
            max_l = entities.top().getL(), max_entity = entities.top();
        entities.pop();
    }

    return max_entity;
}

bool Entity::inQuadrant1()
{
    if(this->x >=0 && this->y >= 0)
        return true;
    else return false;
}

int Entity::getMaxSequence(stack<Entity> entities)
{
    stack<Entity> reversed_stack;
    while(!entities.empty())
    {
        reversed_stack.push(entities.top());
        entities.pop();
    }
    int lmax=1, lc=1;
    Entity last_entity = reversed_stack.top();
    reversed_stack.pop();
    while(!reversed_stack.empty())
    {
        if(reversed_stack.top() == last_entity)
        {
            lc++;
            if(lc>lmax)
                lmax=lc;
        }
        else
        {
            if(lc>lmax)
                lmax=lc;
            lc=1;
        }
        last_entity = reversed_stack.top();
        reversed_stack.pop();

    }
    return lmax;
}