package net.ryandoyle.libjnagios.repository;


import net.ryandoyle.libjnagios.domain.Host;
import net.ryandoyle.libjnagios.domain.NoHostsFoundException;
import net.ryandoyle.libjnagios.domain.QueryFilter;

import java.io.IOException;
import java.util.List;

public interface NagiosRepository {

    public List<Host> getHosts() throws IOException, NoHostsFoundException;

    public List<Host> getHosts(QueryFilter queryFilter) throws IOException, NoHostsFoundException;
}
