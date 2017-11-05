using System;
using System.Collections;
using System.Collections.Generic;
using static ConsoleApplication.Particle;


namespace ConsoleApplication
{
    internal class Program
    {
        private const double Min =-5, Max = 5;
        private const double C1 = 2, C2 = 2, M = 1;
        private const int Dimension = 5, AmountOfParticles = 10000, AmountOfIterations = 100;

        public static void Main(string[] args)
        {
            var particles = new ArrayList();
            var random = new Random();
            var randomPoint = new double[Dimension];
            for (var i = 0; i < Dimension; i++)
                randomPoint[i] = random.NextDouble() * (Max - Min) + Min;
            var temp = new Particle(randomPoint);
            GlobalBest = temp.Pos;
            particles.Add(new Particle(randomPoint));

            for (var i = 0; i < AmountOfParticles - 1; i++)
            {
                randomPoint = new double[Dimension];
                for (var j = 0; j < Dimension; j++)
                {
                    randomPoint[j] = random.NextDouble() * (Max - Min) + Min;
                }
                temp = new Particle(randomPoint);

                if (temp.TakePosSum() < TakeGlobSum())
                {
                    GlobalBest = temp.Pos;
                }
                particles.Add(temp);
            }

            for (var i = 0; i < AmountOfIterations; i++)
            {
                foreach (Particle p in particles)
                {
                    for (var j = 0; j < Dimension; j++)
                    {
                        p.Speed[j] = M * p.Speed[j] + C1 * random.NextDouble() * (p.LocalBest[j] - p.Pos[j]) +
                                     C2 * random.NextDouble() * (GlobalBest[j] - p.Pos[j]);

                        p.Pos[j] = p.Pos[j] + p.Speed[j];
                        if (p.Pos[j] < Min) p.Pos[j] = Min;
                        if (p.Pos[j] > Max) p.Pos[j] = Max;
                    }

                    if (p.TakePosSum() < p.TakeLocSum())
                        p.LocalBest = p.Pos;
                    if (p.TakePosSum() < TakeGlobSum())
                    {
                        GlobalBest = p.Pos;
                    }
                }
            }
            Console.WriteLine(TakeGlobSum());
        }
    }
}