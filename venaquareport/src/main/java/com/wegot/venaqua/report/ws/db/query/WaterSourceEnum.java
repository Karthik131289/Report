package com.wegot.venaqua.report.ws.db.query;

public enum WaterSourceEnum {
    WTP( "WTP", 3, "SELECT t1.id, t1.wtp_id, t1.day_total, t1.dt FROM w2_wtp_component_day_total t1 JOIN (SELECT DISTINCT (wtp_id) FROM w2_wtp WHERE site_id=?) t2 ON t1.wtp_id=t2.wtp_id AND (t1.dt>=? AND t1.dt<? )"),
    TANKER("Tanker", 3, "SELECT t1.id, t1.tanker_id, t1.day_total, t1.dt FROM w2_tanker_day_total t1 JOIN (SELECT DISTINCT (id) FROM w2_tankers WHERE w2_tankers.site_id=?) t2 ON t1.tanker_id=t2.id AND (t1.dt>=? AND t1.dt<? )"),
    GROUND("Ground", 3, "SELECT t1.id, t1.bwell_id, t1.agg_total, t1.dt FROM w2_bwell_day_total t1 JOIN (SELECT DISTINCT (id) FROM w2_borewells WHERE w2_borewells.site_id=?) t2 ON t1.bwell_id=t2.id AND (t1.dt>=? AND t1.dt<? )"),
    MUNICIPAL("Municipal", 3, "SELECT t1.id, t1.municipal_id, t1.day_total, t1.dt FROM w2_municipal_day_total t1 JOIN (SELECT DISTINCT (id) FROM w2_municipal WHERE w2_municipal.site_id=?) t2 ON t1.municipal_id=t2.id AND (t1.dt>=? AND t1.dt<? )"),
    RAINWATER("Rain Water", 3, ""),
    DOMESTIC("Domestic", 3, "SELECT t1.id, t1.domestic_id, t1.day_total, t1.dt FROM w2_domestic_day_total t1 JOIN (SELECT DISTINCT (id) FROM w2_domestic WHERE w2_domestic.site_id=?) t2 ON t1.domestic_id=t2.id AND (t1.dt>=? AND t1.dt<? )"),
    FLUSH("Flush", 3, "SELECT t1.id, t1.flush_id, t1.day_total, t1.dt FROM w2_flush_day_total t1 JOIN (SELECT DISTINCT (id) FROM w2_flush WHERE w2_flush.site_id=?) t2 ON t1.flush_id=t2.id AND (t1.dt>=? AND t1.dt<? )")
    ;

    WaterSourceEnum(String dbName, int usageColumn, String usageQry) {
        this.dbName = dbName;
        this.usageColumn = usageColumn;
        this.usageQry = usageQry;
    }

    public String getDbName() {
        return dbName;
    }

    public int getUsageColumn() {
        return usageColumn;
    }

    public String getUsageQry() {
        return usageQry;
    }

    private String dbName;
    private int usageColumn;
    private String usageQry;
}
