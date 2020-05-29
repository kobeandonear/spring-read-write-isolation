package com.gdw.database.isolation;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 2020/5/28 - 15:26 by guowenhao6
 * email：guowenhao6@jd.com
 * 不生产代码 做bug的搬运工
 *
 * @author guowenhao6
 */
public class DataSourceSelector extends AbstractRoutingDataSource {

    private String defaultDataSource;

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextThreadHolder.getDataSoureLocal();
    }

    public String getDefaultDataSource() {
        return defaultDataSource;
    }

    public void setDefaultDataSource(String defaultDataSource) {
        this.defaultDataSource = defaultDataSource;
    }
}
