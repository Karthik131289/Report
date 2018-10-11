/** Get site_id based on site_name **/
SELECT site_id FROM prod_we2db.w2_site_qa1 WHERE site_name='Ahad Euphoria';

/** Get sources **/
SELECT * FROM prod_we2db.w2_water_source_type;

/** Get WTP id for site-4 **/
SELECT * FROM prod_we2db.w2_wtp WHERE site_id=4;
/** Get WTP Day Total between date range 2018-01-09 to 2018-01-12**/
SELECT * FROM prod_we2db.w2_wtp_component_day_total WHERE wtp_id=1 AND (dt BETWEEN '2018-04-01 00:00:00' and '2018-04-30 23:59:59' );

SELECT t1.id, t1.wtp_id, t1.day_total, t1.dt FROM w2_wtp_component_day_total t1 JOIN (SELECT DISTINCT (wtp_id) FROM w2_wtp WHERE site_id=4) t2 ON t1.wtp_id=t2.wtp_id AND (t1.dt BETWEEN '2018-04-01 00:00:00' and '2018-04-30 23:59:59' );
/******************************************************/

/** Get Tanker id for site-4 **/
SELECT * FROM prod_we2db.w2_tankers where site_id=4;
/** Get Tanker Day Total between date range 2018-01-09 to 2018-01-12**/
SELECT * FROM prod_we2db.w2_tanker_day_total WHERE tanker_id=4 AND (dt BETWEEN '2018-04-01 00:00:00' and '2018-04-30 23:59:59' );

SELECT t1.id, t1.tanker_id, t1.day_total, t1.dt FROM w2_tanker_day_total t1 JOIN (SELECT DISTINCT (id) FROM w2_tankers WHERE w2_tankers.site_id=4) t2 ON t1.tanker_id=t2.id AND (t1.dt BETWEEN '2018-04-01 00:00:00' and '2018-04-30 23:59:59' );
/******************************************************/

/** Get Municipal id for site-4 **/
SELECT * FROM prod_we2db.w2_municipal where site_id=4;
/** Get Municipal Day Total between date range 2018-01-09 to 2018-01-12**/
SELECT * FROM prod_we2db.w2_municipal_day_total WHERE municipal_id=1 AND (dt BETWEEN '2018-04-01 00:00:00' and '2018-04-30 23:59:59' );

SELECT t1.id, t1.municipal_id, t1.day_total, t1.dt FROM w2_municipal_day_total t1 JOIN (SELECT DISTINCT (id) FROM w2_municipal WHERE w2_municipal.site_id=4) t2 ON t1.municipal_id=t2.id AND (t1.dt BETWEEN '2018-04-01 00:00:00' and '2018-04-30 23:59:59' );
/******************************************************/

/** Get Flush id for site-4 **/
SELECT * FROM prod_we2db.w2_flush where site_id=4;
/** Get Flush Day Total between date range 2018-01-09 to 2018-01-12**/
SELECT * FROM prod_we2db.w2_flush_day_total WHERE flush_id=1 AND (dt BETWEEN '2018-04-01 00:00:00' and '2018-04-30 23:59:59' );

SELECT t1.id, t1.flush_id, t1.day_total, t1.dt FROM w2_flush_day_total t1 JOIN (SELECT DISTINCT (id) FROM w2_flush WHERE w2_flush.site_id=4) t2 ON t1.flush_id=t2.id AND (t1.dt BETWEEN '2018-04-01 00:00:00' and '2018-04-30 23:59:59' );
/******************************************************/

/** Get Domestic id for site-4 **/
SELECT * FROM prod_we2db.w2_domestic where site_id=4;
/** Get Domestic Day Total between date range 2018-01-09 to 2018-01-12**/
SELECT * FROM prod_we2db.w2_domestic_day_total WHERE domestic_id=1 AND (dt BETWEEN '2018-04-01 00:00:00' and '2018-04-30 23:59:59' );

SELECT t1.id, t1.domestic_id, t1.day_total, t1.dt FROM w2_domestic_day_total t1 JOIN (SELECT DISTINCT (id) FROM w2_domestic WHERE w2_domestic.site_id=4) t2 ON t1.domestic_id=t2.id AND (t1.dt BETWEEN '2018-04-01 00:00:00' and '2018-04-30 23:59:59' );
/******************************************************/

/** Ground water - Get BoreWell id for site-4**/
SELECT * FROM prod_we2db.w2_borewells where site_id=4;
/** Get BoreWell Day Total between date range 2018-01-09 to 2018-01-12**/
SELECT * FROM prod_we2db.w2_bwell_day_total WHERE bwell_id=1 AND (dt BETWEEN '2018-04-01 00:00:00' and '2018-04-30 23:59:59' );

SELECT t1.id, t1.bwell_id, t1.agg_total, t1.dt FROM w2_bwell_day_total t1 JOIN (SELECT DISTINCT (id) FROM w2_borewells WHERE w2_borewells.site_id=4) t2 ON t1.bwell_id=t2.id AND (t1.dt BETWEEN '2018-04-01 00:00:00' and '2018-04-30 23:59:59' );
/******************************************************/

/** Rain Water not available **/


/** Get Block Ids and names for site-4 **/
SELECT * FROM prod_we2db.w2_block WHERE site_id=4;
/** Get House Ids and names based on Block Id **/
SELECT * FROM prod_we2db.w2_apart_master WHERE block_id=7;
SELECT id, cust_name, block_id FROM prod_we2db.w2_apart_master WHERE block_id=16;
/** Get House's day consumption based on house id for a date range **/
SELECT * FROM prod_we2db.w2_apart_day_total WHERE apart_id=20 AND (dt BETWEEN '2018-04-01 00:00:00' and '2018-04-30 23:59:59' );


SELECT * FROM w2_apart_day_total t4 JOIN (SELECT * FROM w2_apart_master t1 JOIN (SELECT * FROM w2_block WHERE w2_block.site_id=4) t2 ON t1.block_id=t2.id) t3  ON t4.apart_id=t3.id AND (t4.dt BETWEEN '2018-04-01 00:00:00' and '2018-04-30 23:59:59' );








SELECT * FROM prod_we2db.w2_pumps WHERE site_id=4;

SELECT * FROM prod_we2db.w2_pump_status_log where pump_id = 6 and (dt BETWEEN '2018-09-01 00:01:00' and '2018-09-02 00:01:00');

select * from w2_bwell_day_total where bwell_id =6;

 

