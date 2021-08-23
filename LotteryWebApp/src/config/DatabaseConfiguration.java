package config;

public class DatabaseConfiguration {
    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String USER = "user";
    public static final String PASS = "password";

    // URLs to connect to database depending on your development approach
    // (NOTE: please change to option 1 when submitting)

    // 1. use this when running everything in Docker using docker-compose
    public static final String DB_URL = "jdbc:mysql://db:3306/lottery";

    // 2. use this when running tomcat server locally on your machine and mysql database server in Docker
//    public static final String DB_URL = "jdbc:mysql://localhost:33333/lottery";

    // 3. use this when running tomcat and mysql database servers on your machine
    //public static final String DB_URL = "jdbc:mysql://localhost:3306/lottery";

}
