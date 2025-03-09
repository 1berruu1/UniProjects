
#include <iostream>
#include "problema.h"
using namespace std;





void citire(int &n,int x[100]){
    cout << "Lungimea listei: ";
    cin >> n;
    cout << "Numere: ";
    for(int i = 0;i<n;i++)
        cin >> x[i];
}

void afisare(int &n,int x[100]){
    for(int i = 0;i<n; i++)
        cout << x[i] << " ";
}

bool prim(int x) {
    if ( x == 1 || x == 0) return false;
    for (int i = 2; i < x; i++){
        if(x % i == 0)
            return false;
    }
    return true;
}

void s_prime(int &n, int x[100]){
    for(int i = 1 ; i < n; i++){
        if(prim(x[i])){
            cout << x[i] << " ";
        }
    }
}



void s_distincte(int &n, int x[100], int &m, int sol[100]) {
    m=0;
    for(int i = 0; i < n; i++) {
        int j;
        for (j = 0; j < i; j++)
            if (x[i] == x[j])
                break;
        if (i == j)
            sol[m]=x[i], m++;
    }
}

void console(int &n, int x[100]){
    while(true){
        cout << "\n1.Citire\n2.Afisare\n3.Secventa prime\n4.Secventa distincte\n5.Exit\n Input = ";
        int a;
        cin >> a;
        if(a==1)
            citire(n, x);
        else if(a==2)
            afisare(n, x);
        else if(a==3)
            s_prime(n,x);
        else if(a==4)
        {
            int sol[100] = {0};
            int m = 0;
            s_distincte(n,x,m,sol);
            for(int i=0;i<m;i++)
                cout<<sol[i]<<" ";
        }

        else if(a==5)
            break;
        else cout << "Comanda gresita";


    }
}