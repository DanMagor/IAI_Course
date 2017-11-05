//
// Created by DanMagor on 15.09.2017.
//

#ifndef ASSIGNMENT_1_V2_ENVIRONMENT_H
#define ASSIGNMENT_1_V2_ENVIRONMENT_H


#include <map>
#include "../Units/Unit.h"
using namespace std;
class Unit;
typedef pair<int,int> point;
class Environment {
private:
    int size_ = 9;  //Width and Height of Environment
    Unit ***lattice_; //2-D matrix with pointers on Units. Need to visualization
    map<string, Unit*> units_; // All units in Environment
public:
    //Initialization
    Environment();
    explicit Environment(int);

    //Environment information
    int GetSize();
    Unit* GetUnitWithID(string); //return pointer on unit with specific ID
    void PrintCell(); //Visualization
    vector<point> WolfDetectionCells();  //return detection Wolf detection area
    vector<point> BearDetectionCells();//return detection Bear detection area
    bool IsWolfDetection(point);
    bool IsBearDetection(point);

    //Unit Management
    void AddUnit(Unit&); //add unit in units_
    void UpdateUnitsPositions(); //Check positions of all unit in environment and place them in matrix

    //Destructor
    ~Environment();
};


#endif //ASSIGNMENT_1_V2_ENVIRONMENT_H
