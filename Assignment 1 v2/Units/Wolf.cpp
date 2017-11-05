//
// Created by DanMagor on 15.09.2017.
//


#include "Wolf.h"
#include <algorithm>
Wolf::Wolf(const string &ID) : Unit(ID){
    symbol_ = 'W'; //symbol for matrix
}

void Wolf::MakeAction() {
    if (CheckArea())
        environment_->GetUnitWithID("RedHood")->Die(); //If there is RedHood, RedHood will die.

}

bool Wolf::CheckArea() {
    point c = environment_->GetUnitWithID("RedHood")->GetPosition();
    vector<point> detection = DetectionCells();
    auto result = find(detection.begin(),detection.end(),c);
    return result != detection.end();
}  //Is there RedHood in detection area? True if there is she

vector<point> Wolf::DetectionCells() { //return wolf's detection cells
    vector<point> cells;
    int y = GetPosition().first, x = GetPosition().second;
    cells.emplace_back(y,x);
    cells.emplace_back(y+1,x);
    cells.emplace_back(y-1,x);
    cells.emplace_back(y,x+1);
    cells.emplace_back(y,x-1);
    return cells;
}


