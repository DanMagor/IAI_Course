//
// Created by DanMagor on 15.09.2017.
//

#ifndef ASSIGNMENT_1_V2_GRAPH_H
#define ASSIGNMENT_1_V2_GRAPH_H


#include <vector>
#include <map>


//Graph implementation. It is used for A* and backtracking algorithms. Support Templates.

using namespace std;


template<typename V, typename E=double>
class Graph {


    struct vertex {
        typedef pair<E, vertex *> edge;
        vector<edge> edges;
        V name;

        vertex(V s) : name(s) {}
    };  //struct for single Vertex.

private:
    typedef map<V, vertex *> vmap;
    vmap vertices;
public:
    void AddVertex(V v) {
        typename vmap::iterator itr = vertices.find(v);

        if (itr == vertices.end()) {
            vertex *temp;
            temp = new vertex(v);
            vertices[v] = temp;
        }
    }  //Add vertex in graph without any edge

    void AddEdge(const V from, const V to, E weight = 0.0) {  //add edge from vertex 'from' to vertex 'to' with weight
        vertex *f = (vertices.find(from)->second);
        vertex *t = (vertices.find(to)->second);
        pair<E, vertex *> edge = make_pair(weight, t);
        f->edges.push_back(edge);
    } //Add directed edge from vertex 'from' to
                                                                    // vertex 'to' with weight.
                                                                    // Default value for weight is 0
    vmap GetVertices() {
        return vertices;
    }  //return vmap of vertices

    vector<V> GetNeighbors(pair<int, int> v) {  //return Neighbors of vertex V
        vector<V> neighbors;
        if (vertices.find(v)==vertices.end()) return neighbors;
        for (typename Graph::vertex::edge t:vertices.find(v)->second->edges) {
            neighbors.push_back(t.second->name);
        }
        return neighbors;
    } //return vector of neighbors some current vertex

    void DeleteVertex(V deleted_vertex) {
        for (auto v: vertices) {
            int i = 0;
            for (auto e: v.second->edges) {
                if (e.second->name == deleted_vertex) {
                    v.second->edges.erase(v.second->edges.begin() + i);
                }
                i++;
            }
        }
        vertices.erase(deleted_vertex);
    }  //delete vertex and all edge from it. Also delete all edges that point ob that vertex

    double GetEdgeWeight(pair<int, int> v1, pair<int, int> v2) {
        if (vertices.find(v1) != vertices.end()) {
            for (auto e: vertices[v1]->edges) {
                if (e.second->name == v2) {
                    return e.first;
                }
            }
        }
    } // return weight of edge between vertex v1 and v2

    void SetEdgeWeight(V v1, V v2, E weight) {
        if (vertices.find(v1) != vertices.end()) {
            for (auto &e: vertices[v1]->edges) {
                if (e.second->name == v2) {
                    e.first = weight;
                }
            }
        }

    }  //set weight for edge between vertex v1 and v2

    bool ContainsVertex(V v) {
        return !(vertices.find(v) == vertices.end());
    }  // Is this graph contains vertex v? True if contains


};

#endif //ASSIGNMENT_1_V2_GRAPH_H
