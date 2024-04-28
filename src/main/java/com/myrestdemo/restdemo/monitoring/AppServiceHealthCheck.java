package com.myrestdemo.restdemo.monitoring;

import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

@Controller
public class AppServiceHealthCheck implements HealthIndicator {

    @Autowired
    private Environment env;

    @Override
    public Health health() {
        try {
            if (isServiceUp()) {
                return Health.up().withDetail("App Service", "is running").build();
            }
        } catch (IOException ex) {
            // TODO log this exception
            ex.printStackTrace();
        }
        return Health.down().withDetail("App Service", "is DOWN").build();
    }

    private boolean isServiceUp() throws IOException {
        String address = env.getProperty("appService.address");
        String port = env.getProperty("appService.port");

        if (StringUtils.isBlank(address) && StringUtils.isNotBlank(port)) {
            return false;
        }
        System.out.println("Address: ".concat(address).concat(", Port: ".concat(port)));
        return isAddressReachable(address, Integer.parseInt(port), 3000);
    }

    private boolean isAddressReachable(String address, int port, int timeout) throws IOException {
        Socket isSocket = new Socket();
        boolean isConnect = false;
        try {
            isSocket.connect(new InetSocketAddress(address, port), timeout);
            isConnect = true;
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            isSocket.close();
        }
        return isConnect;
    }
}
