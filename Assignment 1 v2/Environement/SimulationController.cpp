//
// Created by DanMagor on 15.09.2017.
//

#include <synchapi.h>
#include "SimulationController.h"
#include "../Units/Granny.h"
#include "../Units/Wolf.h"
#include "../Units/Bear.h"
#include "../Units/Cutter.h"

vector<int> SimulationController::StartAStarSimulation(int size, int delay) {
    //Create and initialize environment
    Environment environment(size);
    RedHood redHood("RedHood");
    Granny granny("Granny");
    Wolf wolf("Wolf");
    Bear bear("Bear");
    Cutter cutter("Cutter");
    redHood.SetEnvironment(environment);
    wolf.SetEnvironment(environment);
    bear.SetEnvironment(environment);
    cutter.SetEnvironment(environment);


    //Add Units to Environment
    environment.AddUnit(redHood);
    environment.AddUnit(granny);
    environment.AddUnit(wolf);
    environment.AddUnit(bear);
    environment.AddUnit(cutter);

    //Place Unit
    PlaceUnitsRandomly(environment);
    environment.UpdateUnitsPositions();

    redHood.SetGoal(granny.GetPosition());
    vector<int> statistic; //time and amount of step for simulating
    int steps = 0;
    //Simulation:
    while (redHood.GetPosition() != granny.GetPosition() && redHood.IsLife()) {
        if (delay != 0) {
            environment.PrintCell();
            Sleep(delay);
            system("CLS");
        }
        redHood.MakeAction();  //Each agent do some action
        wolf.MakeAction();
        bear.MakeAction();
        cutter.MakeAction();
        environment.UpdateUnitsPositions();
        ++steps;

    }
    statistic.push_back(redHood.IsLife());
    statistic.push_back(steps);
    return statistic;

}


void SimulationController::PlaceUnitsRandomly(Environment &environment) {
    typedef pair<int, int> point;
    int size = environment.GetSize();
    int amount_of_cells = environment.GetSize() * environment.GetSize();
    map<int, point> cells;  //Cells available for placement
    for (int i = 0; i < amount_of_cells; i++) {
        int y = i / size;
        int x = i % size;
        cells[i] = point(y, x);
    }

    //Set RedHood Position
    Unit *unit = environment.GetUnitWithID("RedHood");
    int random_index = 0;
    auto iter = cells.begin();
    advance(iter, random_index);
    int key = iter->first;
    point p = iter->second;
    unit->SetPosition(p);
    cells.erase(key);
    //Also need to exclude neighbors cells
    cells.erase(key + 1);
    cells.erase(key + size);
    cells.erase(key + size + 1);

    //Set Wolf Position
    unit = environment.GetUnitWithID("Wolf");
    random_index = rand() % cells.size();
    iter = cells.begin();
    advance(iter, random_index); //Take random cell from list of available cells
    key = iter->first;
    p = iter->second;
    unit->SetPosition(p);
    cells.erase(key); //exclude wolf position cell

    //Set Bear Position
    unit = environment.GetUnitWithID("Bear");
    random_index = rand() % cells.size();
    iter = cells.begin();
    advance(iter, random_index);
    key = iter->first;
    p = iter->second;
    unit->SetPosition(p);  //Set random position
    cells.erase(key); //exclude bear position cell


    // Delete bear and wolf range from available cells. We don't want to place granny in wold or bear detection
    //Bear:
    cells.erase(key - size);
    cells.erase(key + size);
    if (key % size != 0) {
        cells.erase(key - 1);
        cells.erase(key - size - 1);
        cells.erase(key + size - 1);
    }
    if (key % environment.GetSize() != size - 1) {
        cells.erase(key + 1);
        cells.erase(key + size + 1);
        cells.erase(key - size + 1);
    }
    //Wolf:
    unit = environment.GetUnitWithID("Wolf");
    key = unit->GetPosition().first * size + unit->GetPosition().second;
    cells.erase(key - size);
    cells.erase(key + size); // top and down cells
    if (key % size != 0) cells.erase(key - 1);
    if (key % size != size - 1) cells.erase(key + 1); //left and right



    //Set Granny Position
    unit = environment.GetUnitWithID("Granny");
    random_index = rand() % cells.size();
    iter = cells.begin();
    advance(iter, random_index);
    key = iter->first;
    p = iter->second;
    unit->SetPosition(p);
    cells.erase(key);  //exclude granny position cell

    //Set Cutter Position
    unit = environment.GetUnitWithID("Cutter");
    random_index = rand() % cells.size();
    iter = cells.begin();
    advance(iter, random_index);
    key = iter->first;
    p = iter->second;
    unit->SetPosition(p);
    cells.erase(key); //exclude cutter position cell (for some theoretical future placement)
}

vector<int> SimulationController::StartBacktrackingSimulation(int size, int delay) {
    //Create and initialize environment and unit
    Environment environment(size);
    RedHood redHood("RedHood");
    Granny granny("Granny");
    Wolf wolf("Wolf");
    Bear bear("Bear");
    Cutter cutter("Cutter");
    redHood.SetEnvironment(environment);
    wolf.SetEnvironment(environment);
    bear.SetEnvironment(environment);
    cutter.SetEnvironment(environment);

    //Add Units to Environment
    environment.AddUnit(redHood);
    environment.AddUnit(granny);
    environment.AddUnit(wolf);
    environment.AddUnit(bear);
    environment.AddUnit(cutter);

    //Place Units
    PlaceUnitsRandomly(environment);
    environment.UpdateUnitsPositions();

    redHood.SetGoal(granny.GetPosition());

    vector<int> statistic; //time and amount of step for simulating
    int steps = 0;
    //Simulation:
    while (redHood.GetPosition() != granny.GetPosition() && redHood.IsLife()) {
        if (delay != 0) {
            environment.PrintCell();
            Sleep(delay);
            system("CLS");
        }
        redHood.MakeActionBacktracking();    //Each agent do some action
        wolf.MakeAction();
        bear.MakeAction();
        cutter.MakeAction();
        environment.UpdateUnitsPositions();
        ++steps;

    }
    statistic.push_back(redHood.IsLife());
    statistic.push_back(steps);
    return statistic;
}
