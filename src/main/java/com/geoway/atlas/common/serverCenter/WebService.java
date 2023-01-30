package com.geoway.atlas.common.serverCenter;

/**
 * zookeeper节点存储的服务对象
 */
public class WebService implements java.io.Serializable {
    public String ip;
    public String host;
    public Double cpu;

    public WebService(String ip, String host, double cpu, String scale, double memorySize, double totalPercentage, double usedPercentage,double usablePercentage) {
        this.ip = ip;
        this.host = host;
        this.cpu = cpu;
        this.scale = scale;
        this.memorySize = memorySize;
        this.totalPercentage = totalPercentage;
        this.usedPercentage = usedPercentage;
        this.usablePercentage = usablePercentage;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String scale;

    public double memorySize;

    public double getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(double memorySize) {
        this.memorySize = memorySize;
    }

    public double getTotalPercentage() {
        return totalPercentage;
    }

    public void setTotalPercentage(double totalPercentage) {
        this.totalPercentage = totalPercentage;
    }

    public double getUsedPercentage() {
        return usedPercentage;
    }

    public void setUsedPercentage(double usedPercentage) {
        this.usedPercentage = usedPercentage;
    }

    public double getUsablePercentage() {
        return usablePercentage;
    }

    public void setUsablePercentage(double usablePercentage) {
        this.usablePercentage = usablePercentage;
    }

    public double totalPercentage;

    public double usedPercentage;

    public double usablePercentage;


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Double getCpu() {
        return cpu;
    }

    public void setCpu(Double cpu) {
        this.cpu = cpu;
    }




    @Override
    public String toString() {
        return "WebService{" +
                "Ip='" + ip + '\'' +
                ", Host='" + host + '\'' +
                ", Cpu=" + cpu +
                ", MemorySize=" + memorySize +
                ", Scale=" + scale + '\'' +
                ", TotalPercentage=" + totalPercentage +
                ", UsedPercentage=" + usedPercentage +
                ", UsablePercentage=" + usablePercentage +
                '}';
    }
}

