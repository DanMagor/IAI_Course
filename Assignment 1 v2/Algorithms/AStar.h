//
// Created by DanMagor on 15.09.2017.
//

#ifndef ASSIGNMENT_1_V2_ASTAR_H
#define ASSIGNMENT_1_V2_ASTAR_H


#include <map>
#include <vector>
#include <unordered_map>
#include <queue>
#include <stack>
#include "../Environement/Graph.h"

typedef pair<int,int> point;
class AStar {
private:
    static stack<point> ConvertMapToStack(map<point, point>, point, point); //Restore the Path

    template<typename T, typename priority_t>
    struct PriorityQueue {  //Need for A* realization
        typedef pair<priority_t, T> PQElement;
        priority_queue<PQElement, vector<PQElement>,std::greater<PQElement>> elements;

        inline bool empty() const { return elements.empty(); }

        inline void put(T item, priority_t priority) {
            elements.emplace(priority, item);
        }

        inline T get() {
            T best_item = elements.top().second;
            elements.pop();
            return best_item;
        }
    };

public:
    static stack<point> FindPath(Graph<pair<int, int>> &, pair<int, int>, pair<int, int>);  //Find Path between start and gola
};


#endif //ASSIGNMENT_1_V2_ASTAR_H
