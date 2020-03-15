using System;
using System.ServiceModel;
using System.ServiceModel.Web;
using System.Collections.Generic;
using Partner.Data;

namespace Partner.Service {

  	[ServiceContract]
  	public interface IMappingService
  	{

    	[OperationContract]
    	[WebInvoke( Method = "GET", UriTemplate = "mapping/distance/{from}/{to}",
        	        ResponseFormat = WebMessageFormat.Json)]
    	float CalculateDistance(string from, string to);

    	[OperationContract]
    	[WebInvoke( Method = "GET", UriTemplate = "mapping/time/{from}/{to}",
        	        ResponseFormat = WebMessageFormat.Json)]
    	float CalculateTime(string from, string to);

	}

}
