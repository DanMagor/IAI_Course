//
// Created by DanMagor on 15.09.2017.
//

#ifndef ASSIGNMENT_1_V2_CUTTER_H
#define ASSIGNMENT_1_V2_CUTTER_H


#include "Unit.h"
//Wood Cutter implementation. It's a child of class Unit
class Cutter: public Unit {
public:
    bool CheckArea() override;
    vector<point> DetectionCells(); //return cutter's detection cells
public:
    void MakeAction() override;
    explicit Cutter(const string &);

};


#endif //ASSIGNMENT_1_V2_CUTTER_H
