package com.wegot.venaqua.report.ws.db.query;

import java.sql.Connection;

public class SiteUsageByWaterSourceQuery {
    private Connection connection;

    public SiteUsageByWaterSourceQuery(Connection connection) {
        this.connection = connection;
    }

    public void execute(String siteName, String fromDate, String toDate) {
        final String SITE_ID_QUERY = "SELECT site_id FROM w2_site_qa1 WHERE site_name=" + siteName + ";";
        final String WATER_SOURCE_QUERY = "SELECT * FROM w2_water_source_type;";

        Integer siteId = null;

        final String WTP_ID_QUERY = "SELECT * FROM w2_wtp WHERE site_id=" + siteId + ";";
        Integer wtpId = null;
        final String WTP_USAGE_QUERY = "SELECT * FROM w2_wtp_component_day_total WHERE wtp_id=" + wtpId +
                " AND (dt BETWEEN '" + fromDate + "' and '" + toDate + "' );";

        final String TANKERS_ID_QUERY = "SELECT * FROM w2_tankers WHERE site_id=" + siteId + ";";
        Integer tankerId = null;
        final String TANKERS_USAGE_QUERY = "SELECT * FROM w2_tanker_day_total WHERE tanker_id=" + tankerId +
                " AND (dt BETWEEN '" + fromDate + "' and '" + toDate + "' );";

        final String MUNICPAL_ID_QUERY = "SELECT * FROM w2_municipal WHERE site_id=" + siteId + ";";
        Integer municipalId = null;
        final String MUNICPAL_USAGE_QUERY = "SELECT * FROM w2_municipal_day_total WHERE municipal_id=" + municipalId +
                " AND (dt BETWEEN '" + fromDate + "' and '" + toDate + "' );";

        final String FLUSH_ID_QUERY = "SELECT * FROM w2_flush WHERE site_id=" + siteId + ";";
        Integer flushId = null;
        final String FLUSH_USAGE_QUERY = "SELECT * FROM w2_flush_day_total WHERE flush_id=" + flushId +
                " AND (dt BETWEEN '" + fromDate + "' and '" + toDate + "' );";

        final String DOMESTIC_ID_QUERY = "SELECT * FROM w2_domestic WHERE site_id=" + siteId + ";";
        Integer domesticId = null;
        final String DOMESTIC_USAGE_QUERY = "SELECT * FROM w2_domestic_day_total WHERE domestic_id=" + domesticId +
                " AND (dt BETWEEN '" + fromDate + "' and '" + toDate + "' );";

        final String BOREWELL_ID_QUERY = "SELECT * FROM w2_borewells WHERE site_id=" + siteId + ";";
        Integer borewellId = null;
        final String BOREWELL_USAGE_QUERY = "SELECT * FROM w2_bwell_day_total WHERE bwell_id=" + borewellId +
                " AND (dt BETWEEN '" + fromDate + "' and '" + toDate + "' );";

        final String RAIN_WATER_ID_QUERY = "SELECT * FROM w2_wtp WHERE site_id=" + siteId + ";";
        Integer rainWaterId = null;
        final String RAIN_WATER_USAGE_QUERY = "SELECT * FROM w2_water_source_type;";


    }
}
