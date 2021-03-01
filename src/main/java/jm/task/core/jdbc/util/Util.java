package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;



public class Util {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        try {
            // Настройки hibernate
            Configuration configuration = new Configuration()
                    .setProperty( "hibernate.connection.driver_class",
                            "com.mysql.cj.jdbc.Driver" )
                    .setProperty( "hibernate.connection.url",
                            "jdbc:mysql://127.0.0.1:3306/mydbtest?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false" )
                    .setProperty( "hibernate.connection.username",
                            "bestuser" )
                    .setProperty( "hibernate.connection.password",
                            "bestuser" )
//                    .setProperty( "hibernate.connection.pool_size", "1" )
//                    .setProperty( "hibernate.connection.autocommit", "false" )
//                    .setProperty( "hibernate.cache.provider_class",
//                            "org.hibernate.cache.NoCacheProvider" )
//                    .setProperty( "hibernate.cache.use_second_level_cache",
//                            "false" )
//                    .setProperty( "hibernate.cache.use_query_cache", "false" )
                    .setProperty( "hibernate.dialect",
                            "org.hibernate.dialect.MySQLDialect" )
                    .setProperty( "hibernate.show_sql","true" )
                    .setProperty( "hibernate.current_session_context_class",
                            "thread" )
                    .setProperty("hibernate.hbm2ddl.auto", "update")

                    .addPackage( "ru.mysql.db" )
                    .addAnnotatedClass(User.class);

            StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(registryBuilder.build());
        } catch (Exception e) {
            System.out.println("Исключение! sessionFactory" + e);
        }
        return sessionFactory;
    }
}