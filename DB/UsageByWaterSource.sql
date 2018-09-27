
/** Get sources **/
SELECT * FROM prod_we2db.w2_water_source_type;

/** Get WTP id for site-4 **/
SELECT * FROM prod_we2db.w2_wtp WHERE site_id=4;
/** Get WTP Day Total between date range 2018-01-09 to 2018-01-12**/
SELECT * FROM prod_we2db.w2_wtp_component_day_total WHERE wtp_id=1 AND (dt BETWEEN '2018-04-01 00:00:00' and '2018-04-30 23:59:59' );

/** Get Tanker id for site-4 **/
SELECT * FROM prod_we2db.w2_tankers where site_id=4;
/** Get Tanker Day Total between date range 2018-01-09 to 2018-01-12**/
SELECT * FROM prod_we2db.w2_tanker_day_total WHERE tanker_id=4 AND (dt BETWEEN '2018-04-01 00:00:00' and '2018-04-30 23:59:59' );

/** Get Municipal id for site-4 **/
SELECT * FROM prod_we2db.w2_municipal where site_id=4;
/** Get Municipal Day Total between date range 2018-01-09 to 2018-01-12**/
SELECT * FROM prod_we2db.w2_municipal_day_total WHERE municipal_id=1 AND (dt BETWEEN '2018-04-01 00:00:00' and '2018-04-30 23:59:59' );

/** Get Flush id for site-4 **/
SELECT * FROM prod_we2db.w2_flush where site_id=4;
/** Get Flush Day Total between date range 2018-01-09 to 2018-01-12**/
SELECT * FROM prod_we2db.w2_flush_day_total WHERE flush_id=1 AND (dt BETWEEN '2018-04-01 00:00:00' and '2018-04-30 23:59:59' );

/** Get Domestic id for site-4 **/
SELECT * FROM prod_we2db.w2_domestic where site_id=4;
/** Get Domestic Day Total between date range 2018-01-09 to 2018-01-12**/
SELECT * FROM prod_we2db.w2_domestic_day_total WHERE domestic_id=1 AND (dt BETWEEN '2018-04-01 00:00:00' and '2018-04-30 23:59:59' );

/** Ground water not available **/
/** Rain Water not available **/




