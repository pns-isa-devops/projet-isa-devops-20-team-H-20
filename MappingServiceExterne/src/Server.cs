using System;

using System.ServiceModel;
using System.ServiceModel.Web;

using Partner.Service;

public class Server
{
  private string Port = "9090";
  

  // Web Server used to host services
  private WebServiceHost Host;

  /**
   * Start the web server on the given port, and host the expected service
   */
  public void start() {
    Console.WriteLine("Starting a WCF self-hosted .Net server... ");
    string url = "http://localhost" + ":" + Port;

    WebHttpBinding b = new WebHttpBinding();
    Host = new WebServiceHost(typeof(MappingService), new Uri (url));

    // Adding the service to the host
    Host.AddServiceEndpoint(typeof(IMappingService), b, "");

    // Staring the Host server
    Host.Open();
    Console.WriteLine("\nListening to " + "localhost" + ":" + Port + "\n");

    interactive();

  }

  /**
   * Stop the already started web server
   */
  private void stop() {
    Host.Close ();
    Console.WriteLine("Server shutdown complete!");
  }

  
  /**
   * Start the server in interactive mode, waiting for Return to
   */
  private void interactive() {
    Console.WriteLine("Hit Return to shutdown the server.");
    Console.ReadLine();
    stop();
  }
 

  /**
   * Main method, called with `mono server.exe`
   */
  public static void Main(string[] args)
  {
    var server = new Server();
    server.start();
  }

}
