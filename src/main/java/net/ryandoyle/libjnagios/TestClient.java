package net.ryandoyle.libjnagios;


import net.ryandoyle.libjnagios.domain.Host;
import net.ryandoyle.libjnagios.domain.Service;


public class TestClient {

    public static void main(String[] args ) throws Exception {
      NagiosClient nagiosClient = new NagiosClient("http://localhost:8088/cgi-bin/nagios3/", "nagiosadmin", "nagiosadmin");
        Host host = nagiosClient.getHost("localhost");
        System.out.println(host.getName());

        for (Service service : host.getServices()){
            System.out.println(service.getStatusInformation());
        }
    }

}
