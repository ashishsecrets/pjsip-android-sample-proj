
package com.example.freeswitchandroid.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Line {

    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("host")
    @Expose
    private String host;
    @SerializedName("domain")
    @Expose
    private String domain;
    @SerializedName("port")
    @Expose
    private String port;
    @SerializedName("protocol")
    @Expose
    private String protocol;
    @SerializedName("nonce")
    @Expose
    private String nonce;

    /**
     * No args constructor for use in serialization
     *
     */
    public Line() {
    }

    /**
     *
     * @param password
     * @param protocol
     * @param port
     * @param domain
     * @param host
     * @param nonce
     * @param username
     */
    public Line(String username, String password, String host, String domain, String port, String protocol, String nonce) {
        super();
        this.username = username;
        this.password = password;
        this.host = host;
        this.domain = domain;
        this.port = port;
        this.protocol = protocol;
        this.nonce = nonce;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

}