using System.Runtime.Serialization;

/**
 * example of serialization but not used in our project
 */

namespace Partner.Data { 
    [DataContract(Namespace = "http://partner/external/mapping/data/",
                  Name = "Location")]
    public class Location
    {
        [DataMember]
        public string address { get; set; }

        [DataMember]
        public float latitude { get; set; }

        [DataMember]
        public float longitude { get; set; }


    }

}
