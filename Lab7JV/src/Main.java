import java.util.ArrayList;
import java.util.Random;

public class Main {
    final static int amountOfParticles = 100;
    final static int maxIterations = 1000;


    public static void main(String[] args) {

        Random random = new Random();
        ArrayList<WalkParticle> particles = new ArrayList<>();
        int iteration = 0;
        for (int i = 0; i < amountOfParticles; i++) {
            particles.add(new WalkParticle());
        }
        WalkParticle[] newGeneration = new WalkParticle[amountOfParticles];
       boolean success = false;

       while (iteration<maxIterations && !success ) {

            ArrayList<WalkParticle> rulet = new ArrayList<>();
            for (int i = 0; i < particles.size() ; i++) {
                particles.get(i).calculateFitness();
                for (int j=0; j<particles.get(i).fitness;j++)
                    rulet.add(particles.get(i));
            }


            ArrayList<WalkParticle> temp = new ArrayList<>();
            for (int i = 0; i < amountOfParticles; i++) {
                temp.add(rulet.get(random.nextInt(rulet.size())));
            }
            particles = temp;

            for (int i = 0; i < newGeneration.length; i++) {
                WalkParticle parent1 = particles.get(random.nextInt(particles.size()));
                WalkParticle parent2 = particles.get(random.nextInt(particles.size()));
                while(parent1 == parent2){
                    parent2 = particles.get(random.nextInt(particles.size()));
                }
                newGeneration[i] = parent1.produceChildWith(parent2);
            }
            for (int i = 0; i < newGeneration.length/10 ; i++) {
                newGeneration[random.nextInt(newGeneration.length)].mutate();
            }

            particles.clear();
            for (WalkParticle child : newGeneration) {
                particles.add(child);
            }
           for (WalkParticle particle:particles) {
               particle.calculateFitness();
               if (particle.fitness>=9)
                   success = true;
           }

            ++iteration;
        }
        System.out.println("TOTAL PARTICLES: " + particles.size());
        System.out.println("ITERATIONS: " + iteration) ;
        int successParticles = 0;
        for (WalkParticle particle:particles) {
            particle.calculateFitness();
            if(particle.fitness >= 9) {
                successParticles++;
            }
        }
        System.out.println("SUCCESS PARTICLES: " + successParticles);
        for (WalkParticle particle:particles) {
            particle.calculateFitness();
            if(particle.fitness >= 9) {
                System.out.print(" " + particle.fitness + " ");
                particle.printPath();
            }
        }

    }


}
