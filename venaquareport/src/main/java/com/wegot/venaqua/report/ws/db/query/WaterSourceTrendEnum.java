package com.wegot.venaqua.report.ws.db.query;

public enum WaterSourceTrendEnum {
    WTP("WTP", 1, 2, "SELECT sum(t1.day_total) as dayTotal, t1.dt FROM w2_wtp_component_day_total t1 JOIN (SELECT DISTINCT (wtp_id) FROM w2_wtp WHERE site_id=?) t2 ON t1.wtp_id=t2.wtp_id AND (t1.dt >= ? and t1.dt< ? ) group by t1.dt order by t1.dt asc;"),
    TANKER("Tanker", 1, 2, "SELECT sum(t1.day_total) as dayTotal, t1.dt FROM w2_tanker_day_total t1 JOIN (SELECT DISTINCT (id) FROM w2_tankers WHERE w2_tankers.site_id=?) t2 ON t1.tanker_id=t2.id AND (t1.dt >= ? and t1.dt< ? ) group by t1.dt order by t1.dt asc;"),
    GROUND("Ground", 1, 2, "SELECT sum(t1.agg_total) as dayTotal, t1.dt FROM w2_bwell_day_total t1 JOIN (SELECT DISTINCT (id) FROM w2_borewells WHERE w2_borewells.site_id=?) t2 ON t1.bwell_id=t2.id AND (t1.dt >= ? and t1.dt< ? ) group by t1.dt order by t1.dt asc;"),
    MUNICIPAL("Municipal", 1, 2, "SELECT sum(t1.day_total) as dayTotal, t1.dt FROM w2_municipal_day_total t1 JOIN (SELECT DISTINCT (id) FROM w2_municipal WHERE w2_municipal.site_id=?) t2 ON t1.municipal_id=t2.id AND (t1.dt >= ? and t1.dt< ? ) group by t1.dt order by t1.dt asc;"),
    RAINWATER("Rain Water", 1, 2, ""),
    DOMESTIC("Domestic", 1, 2, "SELECT sum(t1.day_total) as dayTotal, t1.dt FROM w2_domestic_day_total t1 JOIN (SELECT DISTINCT (id) FROM w2_domestic WHERE w2_domestic.site_id=?) t2 ON t1.domestic_id=t2.id AND (t1.dt >= ? and t1.dt< ? ) group by t1.dt order by t1.dt asc;"),
    FLUSH("Flush", 1, 2, "SELECT sum(t1.day_total) as dayTotal, t1.dt FROM w2_flush_day_total t1 JOIN (SELECT DISTINCT (id) FROM w2_flush WHERE w2_flush.site_id=?) t2 ON t1.flush_id=t2.id AND (t1.dt >= ? and t1.dt< ? ) group by t1.dt order by t1.dt asc;");

    WaterSourceTrendEnum(String dbName, int usageColumn, int dateColumn, String query) {
        this.dbName = dbName;
        this.usageColumn = usageColumn;
        this.dateColumn = dateColumn;
        this.query = query;
    }

    public String getDbName() {
        return dbName;
    }

    public int getUsageColumn() {
        return usageColumn;
    }

    public int getDateColumn() {
        return dateColumn;
    }

    public String getQuery() {
        return query;
    }



    private String dbName;
    private int usageColumn;
    private int dateColumn;
    private String query;
}
