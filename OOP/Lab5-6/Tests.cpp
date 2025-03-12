
#include "Tests.h"
#include <cassert>

void Tests::test_all() {
    test_class();
}

void Tests::test_class() {
    Entity s1;
    Entity s2(5, 1, 1);
    Entity s3(s2);
    Entity s4(s3);

    s4.setL(10);
    assert(s1.getL() == 0);
    assert(s2.getL() == 5);
    assert(s3.getL() == 5);
    assert(s4.getL() == 10);
    assert(s4.getArea() == 100);
    assert(s4.getPerimeter() == 40);

    stack<Entity> entities;
    entities.push(s1);
    entities.push(s2);
    entities.push(s3);
    entities.push(s4);
    Entity biggest = entities.top().getBiggest(entities);
    assert(biggest.getL() == 10);
    assert(s2.inQuadrant1() == true);

    Entity e1(10,0,0);
    Entity e2(10,0,0);
    Entity e3(12,0,0);
    Entity e4(15,0,0);
    stack<Entity> st;
    st.push(e1);
    st.push(e2);
    st.push(e3);
    st.push(e4);
    assert(entities.top().getMaxSequence(entities) == 2);
}

