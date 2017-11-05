//
// Created by DanMagor on 16.09.2017.
//

#ifndef ASSIGNMENT_1_V2_BACKTRACKING_H
#define ASSIGNMENT_1_V2_BACKTRACKING_H


#include <stack>

#include "../Environement/Graph.h"
#include "../Environement/Environment.h"
#include <vector>
#include <algorithm>

class Backtracking {
private:
    static bool Solve(Graph<point>&,Environment*,point);
public:
    static stack<point> FindPath(Graph<point>&,Environment*,point,point);

};



#endif //ASSIGNMENT_1_V2_BACKTRACKING_H
