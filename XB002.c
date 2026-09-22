#define _CRT_SECURE_NO_WARNINGS
#include <stdio.h>
#include <iostream>
#include <string>
using namespace std;

int main() {
    string name, tag;
    int notes;
    cin >> name >> notes >> tag;
    cout << name << " has " << notes << " notes and follows " << tag << "." << endl;
    return 0;
}