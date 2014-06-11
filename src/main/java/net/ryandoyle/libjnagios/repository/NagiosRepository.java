package net.ryandoyle.libjnagios.repository;


import net.ryandoyle.libjnagios.domain.Host;
import net.ryandoyle.libjnagios.domain.UnknownHostException;

import java.io.IOException;
import java.util.List;

public interface NagiosRepository {

    public Host getHost(String hostName) throws IOException, UnknownHostException;
}
