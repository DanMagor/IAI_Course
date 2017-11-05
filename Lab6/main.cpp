#include <iostream>
#include <ctime>
#include <vector>

using namespace std;
double c1 = 2, c2 = 2, m = 1;
double global_best_pos;
class Particle{
public:
    double pos_;
    double local_best;
    double speed_;
public:
        Particle(double pos){
        pos_ = pos;
        local_best = pos;

        speed_  = 0;
    }

};
int main() {

    srand(time(NULL));

    vector<Particle> particles;
    double randomPoint = (double)rand()/RAND_MAX;
    randomPoint = 1 + randomPoint*(4);
    Particle temp(randomPoint);
    global_best_pos = temp.pos_;
    particles.emplace_back(Particle(randomPoint));

    for (int i = 0; i<10; i++){
        double randomPoint = (double)rand()/RAND_MAX;
        randomPoint = 1 + randomPoint*(4);
        Particle temp(randomPoint);
        if (temp.pos_*temp.pos_<global_best_pos*global_best_pos) global_best_pos = temp.pos_;
        particles.emplace_back(Particle(randomPoint));
    }
    for(int i= 0; i<100; i++) {
        for (auto p:particles) {
            p.speed_ = m * p.speed_ + c1 * ((double) rand() / RAND_MAX) * (p.local_best - p.pos_) +
                       c2 * ((double) rand() / RAND_MAX) * (global_best_pos - p.pos_);
            p.pos_ = p.pos_ + p.speed_;
            if (p.pos_*p.pos_ < global_best_pos*global_best_pos) global_best_pos = p.pos_;
        }
    }
    cout<<global_best_pos;
	system("PAUSE");

}