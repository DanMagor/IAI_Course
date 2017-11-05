#include <iostream>
#include "Environement/SimulationController.h"

//the start point of program
using namespace std;

int main() {

    cout << "Enter the desired number of tests: ";
    int n;
    cin >> n;
    cout << "Enter the time of delay for step(in micro sec) during demonstration." << endl;

    int astar_success=0, backtrack_success = 0;
    int astar_steps = 0, backtrack_steps = 0;
    cout << "WAIT, SIMULATING IS WORKING..." << endl;
    srand(time(NULL)); //for units random placement
    const clock_t begin_time = clock();

    for (int i = 0; i < n; i++) {
        vector<int> result = SimulationController::StartAStarSimulation(9, 0);  // Test n times A* with size 9 and delay 0
        if (result.at(0)) ++astar_success;
        astar_steps += result.at(1);
    }
    float astar_time = float(clock() - begin_time) / CLOCKS_PER_SEC / n;
    astar_steps = astar_steps/n;

    const clock_t begin_time1 = clock();
    for (int i = 0; i < n; i++) {
        vector<int> result = SimulationController::StartBacktrackingSimulation(9, 0); // Test n time Backtracking with size 9 and delay 0
        if (result.at(0))   ++backtrack_success;
        backtrack_steps += result.at(1);
    }

    float backtrack_time = float(clock() - begin_time1) / CLOCKS_PER_SEC / n;
    backtrack_steps = backtrack_steps/n;
    system("CLS");
    cout << "Average amount of time for A* algorithm: " << astar_time << " sec" << endl;
    cout << "Average amount of steps:"<<astar_steps<<" steps"<<endl;
    cout << "Wins: " << astar_success << "  Lose: " << n - astar_success << endl;
    cout << "Average amount of time for BackTracking algorithm: " << backtrack_time << " sec" << endl;
    cout << "Average amount of steps:"<<backtrack_steps<<" steps"<<endl;
    cout << "Wins: " << backtrack_success << "  Lose: " << n - backtrack_success << endl;
    system("PAUSE");

    return 0;


}