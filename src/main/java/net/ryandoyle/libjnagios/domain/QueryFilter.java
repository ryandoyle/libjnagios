package net.ryandoyle.libjnagios.domain;


public class QueryFilter {

    public final String HOSTNAME_QUERY_KEY = "host";
    public StringBuilder queryString;

    public QueryFilter(){
       this.queryString = new StringBuilder();
    }

    public QueryFilter setHostName(String hostName){
        addQueryParameter(HOSTNAME_QUERY_KEY, hostName);
        return this;
    }

    @Override
    public String toString(){
        return queryString.toString();
    }

    private void addQueryParameter(String queryKey, String queryValue){
        queryString.append(queryKey + "=" +  queryValue + "&");
    }


}
