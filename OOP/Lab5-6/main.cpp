#include <iostream>
#include "Tests.h"
#include "Entity.h"
#include <stack>

using namespace std;

void print_menu();

int main() {
    Tests tests;
    tests.test_all();

    stack<Entity> entities;
    while(true)
    {
        print_menu();
        cout<<"Introdu comanda: ";
        int option;
        cin>>option;
        cout<<endl;
        if(option==6)
            return 0;
        if(option==1)
        {
            cout<<"Introdu latura: ";
            float l;
            cin>>l;
            cout<<endl<<"Introdu coordonata x: ";
            int x;
            cin>>x;
            cout<<endl<<"Introdu coordonata y: ";
            int y;
            cin>>y;
            cout<<endl;
            Entity new_entity(l, x, y);
            entities.push(new_entity);
        }
        if(option==2)
        {
            stack<Entity> stack_copy = entities;
            while(!stack_copy.empty())
            {
                cout<<"Patrat cu latura "<<stack_copy.top().getL()<<" ; aria: " << stack_copy.top().getArea()
                << " ; perimetrul: "<<stack_copy.top().getPerimeter()<<" pozitia "
                <<stack_copy.top().getX()<<","<<stack_copy.top().getY()<<endl;
                stack_copy.pop();
            }

            cout<<endl;
        }
        if(option==3)
        {
            if(!entities.empty())
            {
                Entity biggest_entity = entities.top().getBiggest(entities);
                cout<<"Cea mai mare entitate are latura " << biggest_entity.getL() << endl<<endl;
            }
        }
        if(option==4)
        {
            stack<Entity> stack_copy = entities;
            while(!stack_copy.empty())
            {
                if(stack_copy.top().inQuadrant1())
                {
                    cout<<"Patratul in "<<stack_copy.top().getX()<<","<<stack_copy.top().getY()<<" cu latura "<<stack_copy.top().getL()
                        <<" se afla in cadranul 1"<<endl;
                }
                stack_copy.pop();
            }

            cout<<endl;
        }
        if(option==5)
        {
            if(!entities.empty())
                cout<<"Lungime maxima: "<<entities.top().getMaxSequence(entities);
            cout<<endl<<endl;

        }
    }

    return 0;
}


void print_menu()
{
    cout<<"1. Introdu entitate noua\n";
    cout<<"2. Afiseaza informatiile despre toate entitatile\n";
    cout<<"3. Determina cea mai mare\n";
    cout<<"4. Identificare cadran 1\n";
    cout<<"5. Identificare secventa\n";
    cout<<"6. Iesire\n\n";
}