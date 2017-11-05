//
// Created by DanMagor on 15.09.2017.
//

#ifndef ASSIGNMENT_1_V2_WOLF_H
#define ASSIGNMENT_1_V2_WOLF_H


#include "Unit.h"
//Wolf implementation. It's a child of class Unit
class Wolf: public Unit {
private:
    bool CheckArea() override;
    vector<point> DetectionCells(); //return wolf's detection cells

public:
    explicit Wolf(const string &);
    void MakeAction() override;

};


#endif //ASSIGNMENT_1_V2_WOLF_H
