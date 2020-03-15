using System;
using System.Net;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Collections.Generic;
using System.Linq;
using Partner.Data;

namespace Partner.Service {

  // The service is stateful, as it is only a Proof of Concept.
  // Services should be stateless, this is for demonstration purpose only.
  [ServiceBehavior(InstanceContextMode = InstanceContextMode.Single)]
  public class MappingService : IMappingService
  {
    

    public float CalculateDistance(string from, string to)
    {
        //TODO
        Console.WriteLine("ok");
        return 1;
    }

    public float CalculateTime(string from, string to)
    {
        //TODO
      return 0;
    }

  }
}
