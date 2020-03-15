using System.Runtime.Serialization;
using System;

namespace Partner.Data {

  [DataContract(Namespace = "http://partner/external/mapping/data/",
                Name = "Location")]
  public class Location
  {
    [DataMember]
    public string address { get; set; }

    [DataMember]
    public string longitude { get; set; }

    [DataMember]
    public double latitude { get; set; }
  }

}
