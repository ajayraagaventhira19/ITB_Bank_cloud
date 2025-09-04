# Use official Tomcat image with JDK 11
FROM tomcat:9.0-jdk11

# Remove default Tomcat webapps
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy your locally built WAR into Tomcat
COPY target/ITBBank.war /usr/local/tomcat/webapps/ROOT.war

# Expose Tomcat port
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
