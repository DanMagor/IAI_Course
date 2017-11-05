cmake_minimum_required(VERSION 3.8)
project(Assignment_1_v2)

set(CMAKE_CXX_STANDARD 11)

set(SOURCE_FILES main.cpp Units/Unit.cpp Units/Unit.h Units/RedHood.cpp Units/RedHood.h Environement/Environment.cpp Environement/Environment.h Environement/SimulationController.cpp Environement/SimulationController.h Environement/Graph.cpp Environement/Graph.h Algorithms/AStar.cpp Algorithms/AStar.h Units/Granny.cpp Units/Granny.h Units/Wolf.cpp Units/Wolf.h Units/Bear.cpp Units/Bear.h Units/Cutter.cpp Units/Cutter.h Algorithms/Backtracking.cpp Algorithms/Backtracking.h)
add_executable(Assignment_1_v2 ${SOURCE_FILES})