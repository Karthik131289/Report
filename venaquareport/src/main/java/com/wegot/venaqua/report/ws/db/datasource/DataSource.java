package com.wegot.venaqua.report.ws.db.datasource;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "data-source")
public class DataSource {
    private String name;
    private String dialect;
    private String location;
    private String connectionDriver;
    private String url;
    private String username;
    private String password;
    private long inactivityTimeout;

    public DataSource() {
    }

    public DataSource(String name, String dialect, String location, String connectionDriver, String url, String username, String password, long inactivityTimeout) {
        this.name = name;
        this.dialect = dialect;
        this.location = location;
        this.connectionDriver = connectionDriver;
        this.url = url;
        this.username = username;
        this.password = password;
        this.inactivityTimeout = inactivityTimeout;
    }

    @XmlAttribute(name = "name", required = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlAttribute(name = "dialect", required = true)
    public String getDialect() {
        return dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

    @XmlAttribute(name = "location", required = true)
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @XmlAttribute(name = "connection-driver", required = true)
    public String getConnectionDriver() {
        return connectionDriver;
    }

    public void setConnectionDriver(String connectionDriver) {
        this.connectionDriver = connectionDriver;
    }

    @XmlAttribute(name = "url", required = true)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @XmlAttribute(name = "username", required = true)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @XmlAttribute(name = "password", required = true)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlAttribute(name = "inactivity-timeout", required = true)
    public long getInactivityTimeout() {
        return inactivityTimeout;
    }

    public long getInactivityTimeoutMS() {
        return inactivityTimeout * 1000;
    }

    public void setInactivityTimeout(long inactivityTimeout) {
        this.inactivityTimeout = inactivityTimeout;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DataSource{");
        sb.append("name=")    .append(name );
        sb.append(", dialect='") .append(dialect ).append('\'');
        sb.append(", location='").append(location).append('\'');
        sb.append(", connectionDriver='").append(connectionDriver).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", inactivityTimeout='").append(inactivityTimeout).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
