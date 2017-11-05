//
// Created by DanMagor on 15.09.2017.
//

#ifndef ASSIGNMENT_1_V2_BEAR_H
#define ASSIGNMENT_1_V2_BEAR_H


#include "Unit.h"
//Bear implementation. It's a child of class Unit
class Bear: public Unit {
private:
    bool CheckArea() override;
    vector<point> DetectionCells(); //return bear's detection cells
public:
    explicit Bear(const string &);
    void MakeAction() override;

};


#endif //ASSIGNMENT_1_V2_BEAR_H
