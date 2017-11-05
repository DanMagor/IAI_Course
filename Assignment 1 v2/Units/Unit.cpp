//
// Created by DanMagor on 15.09.2017.
//

#include "Unit.h"

Unit::Unit(string ID) {
    ID_ = ID;
    position_ = point(0,0);
}

Unit::Unit(string ID, point position) {
    ID_ = ID;
    position_ = position;
}

Unit::Unit(string ID, int y , int x) {
    ID_ = ID;
    position_ = point(y,x);
}


string Unit::GetID() {
    return ID_;
}

char Unit::GetSymbol() {
    return symbol_;
}

point Unit::GetPosition() {
    return position_;
}

int Unit::GetLife() {
    return life_;
}

bool Unit::IsLife() {
    return is_life;
}

void Unit::SetPosition(point position) {
    position_ = position;
}

void Unit::SetPosition(int y , int x) {
    position_ = point(y,x);
}

void Unit::SetLife(int life) {
    life_ = life;
    if (life_<=0) Die();
}

void Unit::Die() {
    is_life = false;
    life_ = 0;
}

void Unit::SetEnvironment(Environment &environment) {
    environment_ = &environment;
}



