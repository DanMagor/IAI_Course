//
// Created by DanMagor on 15.09.2017.
//

#ifndef ASSIGNMENT_1_V2_REDHOOD_H
#define ASSIGNMENT_1_V2_REDHOOD_H


#include <stack>
#include "Unit.h"
#include "../Environement/Environment.h"
#include "../Environement/Graph.h"

class RedHood: public Unit {
private:
    //Properties:
    Graph<point> graph_environment_;
    point goal_;
    stack<point> path_;
    vector<point> WolfDetectionCells();  //Detection cells of wolf for RedHood
    vector<point> BearDetectionCells(); //Detection cells of bear for RedHood
    void MakeGraphFromEnvironment();  //Make graph from known environment
    bool CheckArea() override;

public:
    //Initialization
    explicit RedHood(string);
    RedHood(string,point);
    RedHood(string,int,int);
    //Behaviour
    void MakeAction() override; //A* Make action
    void MakeActionBacktracking(); //Backtracking MakeAction
    void SetEnvironment(Environment &) override; //SetEnvironment for communication
    void SetGoal(point); //Where is RedHood want to go?


};


#endif //ASSIGNMENT_1_V2_REDHOOD_H
