package br.com.fiapspring;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRunnerBean implements ApplicationRunner {

    public ApplicationRunnerBean() {
        super();
    }

    @Override
    public void run(ApplicationArguments arg0) throws Exception {
        System.out.println("INICIANDO ");
        org.h2.tools.Server server = org.h2.tools.Server.createTcpServer().start();
    }
}