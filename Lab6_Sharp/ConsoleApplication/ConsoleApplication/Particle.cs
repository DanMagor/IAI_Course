using System;
using System.Runtime.Remoting.Messaging;

namespace ConsoleApplication
{
    public class Particle
    {
        private double[] _pos;
        private double[] _localBest;
        private double[] _speed;
        private static double[] _globalBest;

        public Particle(double[] pos)
        {
            _pos = new double[pos.Length];
            _localBest = new double[pos.Length];
            _speed = new double[pos.Length];
            for (var i = 0; i < pos.Length; i++)
            {
                _pos[i] = pos[i];
                _localBest[i] = pos[i];
                _speed[i] = 0;
            }

            
        }


        public double[] Pos
        {
            get { return _pos; }
            set { _pos = value; }
        }

        public double[] LocalBest
        {
            get { return _localBest; }
            set
            {
                _localBest = new double[value.Length];
                for (var i = 0; i < value.Length; i++)
                {
                    _localBest[i] = value[i];
                }
            }
        }

        public double[] Speed
        {
            get { return _speed; }
            set { _speed = value; }
        }

        public static double[] GlobalBest
        {
            get { return _globalBest; }
            set
            {
                _globalBest = new double[value.Length];
                for (var i = 0; i < value.Length; i++)
                {
                    _globalBest[i] = value[i];
                }
            }
        }

        public double TakePosSum()
        {
            double result = 0;
            foreach (var po in _pos)
            {
                result += po * po;
            }
            return result;
        }

        public double TakeLocSum()
        {
            double result = 0;
            foreach (var lc in _localBest)
            {
                result += lc * lc;
            }
            return result;
        }

        public static double TakeGlobSum()
        {
            double result = 0;
            foreach (double gl in _globalBest)
            {
                result += gl * gl;
            }
            return result;
        }
    }
}