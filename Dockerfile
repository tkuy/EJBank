FROM glassfish

RUN curl http://repo1.maven.org/maven2/mysql/mysql-connector-java/5.1.34/mysql-connector-java-5.1.34.jar -o glassfish/lib/mysql-connector-java-5.1.34.jar

COPY Server/wait-for-it.sh wait-for-it.sh
RUN chmod +x wait-for-it.sh

COPY Server/domain.xml glassfish/domains/domain1/config/domain.xml

RUN rm -r glassfish/domains/domain1/autodeploy/*
COPY EJBank-ear/target/EJBank.ear glassfish/domains/domain1/autodeploy/EJBank.ear

EXPOSE 8080

CMD asadmin start-domain --verbose
