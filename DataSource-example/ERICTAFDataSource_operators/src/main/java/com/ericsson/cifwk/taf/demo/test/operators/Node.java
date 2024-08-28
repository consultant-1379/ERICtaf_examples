package com.ericsson.cifwk.taf.demo.test.operators;

/**
 * Represents a Node in the system.
 */
public class Node {

    private String name;
    private String ipAddress;
    private String meContextModelVersion;
    private String cppVersion;
    private String cppNamespace;
    private String erbsVersion;
    private String erbsNamespace;
    private String erbsConnectivityInfoModelVersion;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(final String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getCppVersion() {
        return cppVersion;
    }

    public void setCppVersion(final String cppVersion) {
        this.cppVersion = cppVersion;
    }

    public String getCppNamespace() {
        return cppNamespace;
    }

    public void setCppNamespace(final String cppNamespace) {
        this.cppNamespace = cppNamespace;
    }

    public String getErbsVersion() {
        return erbsVersion;
    }

    public void setErbsVersion(final String erbsVersion) {
        this.erbsVersion = erbsVersion;
    }

    public String getErbsNamespace() {
        return erbsNamespace;
    }

    public void setErbsNamespace(final String erbsNamespace) {
        this.erbsNamespace = erbsNamespace;
    }

    public String getErbsConnectivityInfoModelVersion() {
        return erbsConnectivityInfoModelVersion;
    }

    public void setErbsConnectivityInfoModelVersion(String erbsConnectivityInfoModelVersion) {
        this.erbsConnectivityInfoModelVersion = erbsConnectivityInfoModelVersion;
    }

    public String getMeContextModelVersion() {
        return meContextModelVersion;
    }

    public void setMeContextModelVersion(String meContextModelVersion) {
        this.meContextModelVersion = meContextModelVersion;
    }
}
