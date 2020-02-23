package pl.questionMenager.transformer.database;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PropertiesHibernateConfig {
    private String driver;
    private String url;
    private String user;
    private String pass;
    private String show_sql;
    private String dialect;
    private String hbm2ddl_auto;

}
