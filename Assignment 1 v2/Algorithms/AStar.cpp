//
// Created by DanMagor on 15.09.2017.
//

#include "AStar.h"

stack<point> AStar::FindPath(Graph<pair<int, int>> &graph, pair<int, int> start, pair<int, int> goal) {
    PriorityQueue<point,double> priorityQueue; //Priority queue for next cell
    priorityQueue.put(start,0);  //place first cell in path
    map<point,double> cell_cost;
    map<point,point> came_from;
    came_from[start] = start;  //Save the path
    cell_cost[start] = 0; //Cost for cells
    bool isFindPath = false;
    while(!priorityQueue.empty()){
        point current = priorityQueue.get();        //Deafult A* algorithm
        if (current == goal){
            isFindPath = true;
            break;
        }
        for(auto next: graph.GetNeighbors(current)){
            double edge_cost = graph.GetEdgeWeight(current,next);  //Need in case of Bear cells detected
            double new_cost = cell_cost[current] + 1 + edge_cost;
            if (cell_cost.find(next) == cell_cost.end() || new_cost < cell_cost[next]){
                cell_cost[next] = new_cost;
                priorityQueue.put(next,new_cost);
                came_from[next] = current;
            }
        }
    }
    if (isFindPath) return ConvertMapToStack(came_from,start,goal); //Restore the path
    else{
        stack<point> result; //Empty path. Lose case
        return result;
    }

}

stack<point> AStar::ConvertMapToStack(map<point, point> came_from, point start, point goal) {
    point current = goal;
    stack<point> result;
    result.push(goal);
    while(came_from[current]!=start){
        result.push(current);
        current = came_from[current];
    }
    result.push(current);
    return result;
}
