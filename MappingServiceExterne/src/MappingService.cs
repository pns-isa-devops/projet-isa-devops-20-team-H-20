using System;
using System.Net;
using System.IO;
using System.Collections.Generic;
using System.ServiceModel;

namespace Partner.Service {

  [ServiceBehavior(InstanceContextMode = InstanceContextMode.Single)]
  public class MappingService : IMappingService
  {
        public Dictionary<string, float> coordinates;


        public float CalculateDistance(string from, string to)
        {
            coordinates = Utils.convertLatLng(from, to);
            return Utils.calculateDistance(coordinates["lat1"], coordinates["lng1"], coordinates["lat2"], coordinates["lng2"]);
        }

        public float CalculateTime(string from, string to)
        {
            coordinates = Utils.convertLatLng(from, to);
            return Utils.calculateTime(coordinates["lat1"], coordinates["lng1"], coordinates["lat2"], coordinates["lng2"]);
        }

    }
}
