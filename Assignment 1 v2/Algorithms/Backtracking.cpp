//
// Created by DanMagor on 16.09.2017.
//

#include "Backtracking.h"
point goal_;
map<point, int> marked;
stack<point> path;

stack<point> Backtracking::FindPath(Graph<point> &graph, Environment *environment, point start, point goal) {
    goal_ = goal;
    marked = map<point,int>(); //Tabu cells
    path = stack<point>(); // out final path
    //Try to find path and convert ot
    if(Solve(graph,environment,start)) {
        stack<point> result;
        result.push(goal);
        while(!path.empty()) {

            point &temp = path.top();
            result.push(temp);
            path.pop();
        }
        return result;
    }
    //Path empty. Lose case
    return stack<point>();

}

bool Backtracking::Solve(Graph<point> &graph, Environment *environment, point from) {
    marked[from] = 1;
    //Recursive implementation
    for(auto v:graph.GetNeighbors(from)){
        if (marked[v] != 0) continue; //we already visited it
        path.push(v);
        if (goal_==v)
            return true; //path was found
        marked[v] = 1; //mark that we visited it
        if (environment->IsWolfDetection(v) || environment->IsBearDetection(v)){ //Check available cell
            path.pop();
        }else
        if (Solve(graph,environment,v)) return true; //Find solution for that neighbor
    }
    //wrong way:
    return false;
}
