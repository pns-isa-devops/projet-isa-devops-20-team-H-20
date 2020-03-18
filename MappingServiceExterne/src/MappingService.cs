using System;
using System.Collections.Generic;
using System.ServiceModel;

namespace Partner.Service {

  [ServiceBehavior(InstanceContextMode = InstanceContextMode.Single)]
  public class MappingService : IMappingService
  {
        public Dictionary<string, float> coordinates;


        public float CalculateDistance(string from, string to)
        {
            Console.WriteLine("Calculate distance in km from " + from + " to " + to);
            return getRandom(0, 20);
        }

        public float CalculateTime(string from, string to)
        {
            Console.WriteLine("Calculate time in min from " + from + " to " + to);
            return getRandom(0, 45);
        }

        private float getRandom(int min, int max)
        {
            Random rand = new Random();
            double val = rand.NextDouble() + rand.Next(min, max);
            return (float) val;
        }

    }
}
