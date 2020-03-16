using System;
using System.Net;
using System.IO;
using System.Collections.Generic;
using Newtonsoft.Json.Linq;

namespace Partner.Service
{
    public class Utils
    {

        public static string responseFromServer = "Loading...";
        public static string APIKey = "Ee97SaJ62e4MWZcwbAeIFhtlGydaB1GR";

        public static float toRadians(float angleIn10thofaDegree)
        {
            // Angle in 10th 
            // of a degree 
            return (angleIn10thofaDegree * (float)Math.PI) / 180;
        }

        public static float calculateDistance(float lat1, float lng1, float lat2, float lng2)
        {
            lat1 = toRadians(lat1);
            lat2 = toRadians(lat2);
            lng1 = toRadians(lng1);
            lng2 = toRadians(lng2);

            // Haversine formula  
            float dlon = lng2 - lng1;
            float dlat = lat2 - lat1;
            double a = Math.Pow(Math.Sin(dlat / 2), 2) + Math.Cos(lat1) * Math.Cos(lat2) * Math.Pow(Math.Sin(dlon / 2), 2);

            double c = 2 * Math.Asin(Math.Sqrt(a));

            // Radius of earth in  
            // kilometers. Use 3956  
            // for miles 
            float r = 6371;
            // calculate the result 
            return ((float)c * r);
        }

        public static float calculateTime(float lat1, float lng1, float lat2, float lng2)
        {
            float distance = calculateDistance(lat1, lng1, lat2, lng2);
            return 20 * distance;
        }

        public static String getLatLngFromAddress(String address)
        {
            // Create a request for the URL.
            WebRequest request = WebRequest.Create(
            "https://www.mapquestapi.com/geocoding/v1/address?key=" + APIKey + "&outFormat=json&location=" + address);
            // If required by the server, set the credentials.
            // request.Credentials = CredentialCache.DefaultCredentials;
            // Get the response.
            WebResponse response = request.GetResponse();
            // Display the status.
            Console.WriteLine(((HttpWebResponse)response).StatusDescription);
            // Get the stream containing content returned by the server.
            Stream dataStream = response.GetResponseStream();
            // Open the stream using a StreamReader for easy access.
            StreamReader reader = new StreamReader(dataStream);
            // Read the content. string
            responseFromServer = reader.ReadToEnd();
            // Clean up the streams and the response.
            reader.Close();
            response.Close();
            return responseFromServer;
        }

        public static Dictionary<string, float> convertLatLng(String from, String to)
        {
            Dictionary<string, float> coordinates = new Dictionary<string, float>();
            float lat1 = 0;
            float lat2 = 0;
            float lng1 = 0;
            float lng2 = 0;
            getLatLngFromAddress(from);
            //Console.WriteLine(responseFromServer);
            JObject o1 = JObject.Parse(Utils.responseFromServer);
            foreach (JObject item in o1["results"])
            {
                foreach (JObject i in item["locations"])
                {
                    Console.WriteLine(i["latLng"]["lat"]);
                    Console.WriteLine(i["latLng"]["lng"]);
                    lat1 = float.Parse(i["latLng"]["lat"].ToString());
                    lng1 = float.Parse(i["latLng"]["lng"].ToString());
                }
            }
            getLatLngFromAddress(to);
            //Console.WriteLine(responseFromServer);
            JObject o2 = JObject.Parse(responseFromServer);
            foreach (JObject item in o2["results"])
            {
                foreach (JObject i in item["locations"])
                {
                    Console.WriteLine(i["latLng"]["lat"]);
                    Console.WriteLine(i["latLng"]["lng"]);
                    lat2 = float.Parse(i["latLng"]["lat"].ToString());
                    lng2 = float.Parse(i["latLng"]["lng"].ToString());
                }
            }

            coordinates["lat1"] = lat1;
            coordinates["lng1"] = lng1;
            coordinates["lat2"] = lat2;
            coordinates["lng2"] = lng2;

            return coordinates;
        }
    }
}
