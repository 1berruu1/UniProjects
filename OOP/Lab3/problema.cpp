#include "problema.h"
#include <iostream>
#include <unordered_set>
using namespace std;


void citire(int &n, int *&x)
{
    cout << "lungime lista: ";
    cin >> n;
    x = new int[n];
    cout << "numerele: ";
    for(int i=0;i < n; i++)
        cin >> x[i];
}

void afisare(int inp,int n, int *x)
{
    for(int i = inp; i < n;i++)
        cout << x[i] << " ";
    cout << "\n";
}

bool isprime(int n){
    if(n == 0 || n == 1) return false;
    for(int i = 2;i < n;i++)
        if(n % i == 0)
            return false;
    return true;
}

void s_prime(int &n,int *x){
    for(int i = 0; i < n;i++)
        if(isprime(x[i])){
            cout << x[i] << " ";
        }
    cout << "\n";
}

void distinct(int &n,int *x, int &m,int *sol)
{
    m=0;
    for(int i = 0;i < n;i++)
    {
        int j;
        for( j = 0; j < i; j++)
        {
            if(x[i] == x[j])
                break;
        }
        if(i == j)
            sol[m] = x[i], m++;
    }
}

void s_distinct(int &n, int *x)
{
    int *sol = new int[n];
    int m = 0;
    distinct(n,x,m,sol);
    for(int i = 0; i < m; i++)
        cout << sol[i] << " ";
    delete[] sol;
    cout << "\n";
}

void console()
{
    int *x, n, a;
    x = new int[100];
    while(true) {
        cout << "1.Citire\n2.Afisare\n3.Secventa prime\n4.Secventa distincte\n5.Exit\n";
        cout << "Input: ";
        cin >> a;
        if (a == 1)
            citire(n, x);
        else if (a == 2)
            afisare(0, n, x);
        else if (a == 3)
            s_prime(n, x);
        else if (a == 4)
            s_distinct(n,x);
        else if(a == 5)
        {
            if(x != NULL){
                delete[] x;
            }
            break;
        }
        else
            cout << "comanda neimplementata";
    }

}