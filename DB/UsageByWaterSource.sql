/** Get site_id based on site_name **/
SELECT site_id FROM w2_site_qa1 WHERE site_name='Ahad Euphoria';

/** Get sources **/
SELECT * FROM w2_water_source_type;

/** Get WTP id for site-4 **/
SELECT * FROM w2_wtp WHERE site_id=4;
/** Get WTP Day Total between date range 2018-01-09 to 2018-01-12**/
SELECT * FROM w2_wtp_component_day_total WHERE wtp_id=1 AND (dt BETWEEN '2018-04-01 00:00:00' and '2018-04-02 23:59:59' );

SELECT t1.id, t1.wtp_id, t1.day_total, t1.dt FROM w2_wtp_component_day_total t1 JOIN (SELECT DISTINCT (wtp_id) FROM w2_wtp WHERE site_id=4) t2 ON t1.wtp_id=t2.wtp_id AND (t1.dt BETWEEN '2018-04-01 00:00:00' and '2018-04-02 23:59:59' );
/******************************************************/

/** Get Tanker id for site-4 **/
SELECT * FROM w2_tankers where site_id=4;
/** Get Tanker Day Total between date range 2018-01-09 to 2018-01-12**/
SELECT * FROM w2_tanker_day_total WHERE tanker_id=4 AND (dt BETWEEN '2018-04-01 00:00:00' and '2018-04-30 23:59:59' );

SELECT t1.id, t1.tanker_id, t1.day_total, t1.dt FROM w2_tanker_day_total t1 JOIN (SELECT DISTINCT (id) FROM w2_tankers WHERE w2_tankers.site_id=4) t2 ON t1.tanker_id=t2.id AND (t1.dt BETWEEN '2018-04-01 00:00:00' and '2018-04-10 23:59:59' );
/******************************************************/

/** Get Municipal id for site-4 **/
SELECT * FROM w2_municipal where site_id=4;
/** Get Municipal Day Total between date range 2018-01-09 to 2018-01-12**/
SELECT * FROM w2_municipal_day_total WHERE municipal_id=1 AND (dt BETWEEN '2018-04-01 00:00:00' and '2018-04-30 23:59:59' );

SELECT t1.id, t1.municipal_id, t1.day_total, t1.dt FROM w2_municipal_day_total t1 JOIN (SELECT DISTINCT (id) FROM w2_municipal WHERE w2_municipal.site_id=4) t2 ON t1.municipal_id=t2.id AND (t1.dt BETWEEN '2018-04-01 00:00:00' and '2018-04-30 23:59:59' );
/******************************************************/

/** Get Flush id for site-4 **/
SELECT * FROM w2_flush where site_id=4;
/** Get Flush Day Total between date range 2018-01-09 to 2018-01-12**/
SELECT * FROM w2_flush_day_total WHERE flush_id=1 AND (dt BETWEEN '2018-04-01 00:00:00' and '2018-04-30 23:59:59' );

SELECT t1.id, t1.flush_id, t1.day_total, t1.dt FROM w2_flush_day_total t1 JOIN (SELECT DISTINCT (id) FROM w2_flush WHERE w2_flush.site_id=4) t2 ON t1.flush_id=t2.id AND (t1.dt BETWEEN '2018-04-01 00:00:00' and '2018-04-30 23:59:59' );
/******************************************************/

/** Get Domestic id for site-4 **/
SELECT * FROM w2_domestic where site_id=4;
/** Get Domestic Day Total between date range 2018-01-09 to 2018-01-12**/
SELECT * FROM w2_domestic_day_total WHERE domestic_id=1 AND (dt BETWEEN '2018-04-01 00:00:00' and '2018-04-30 23:59:59' );

SELECT t1.id, t1.domestic_id, t1.day_total, t1.dt FROM w2_domestic_day_total t1 JOIN (SELECT DISTINCT (id) FROM w2_domestic WHERE w2_domestic.site_id=4) t2 ON t1.domestic_id=t2.id AND (t1.dt BETWEEN '2018-04-01 00:00:00' and '2018-04-30 23:59:59' );
/******************************************************/

/** Ground water - Get BoreWell id for site-4**/
SELECT * FROM w2_borewells where site_id=4;
/** Get BoreWell Day Total between date range 2018-01-09 to 2018-01-12**/
SELECT * FROM w2_bwell_day_total WHERE bwell_id=1 AND (dt BETWEEN '2018-04-01 00:00:00' and '2018-04-30 23:59:59' );

SELECT t1.id, t1.bwell_id, t1.agg_total, t1.dt FROM w2_bwell_day_total t1 JOIN (SELECT DISTINCT (id) FROM w2_borewells WHERE w2_borewells.site_id=4) t2 ON t1.bwell_id=t2.id AND (t1.dt BETWEEN '2018-04-01 00:00:00' and '2018-04-30 23:59:59' );
/******************************************************/

/** Rain Water not available **/


/** Get Block Ids and names for site-4 **/
SELECT * FROM w2_block WHERE site_id=4;
/** Get House Ids and names based on Block Id **/
SELECT * FROM w2_apart_master WHERE block_id=7;
SELECT id, cust_name, block_id FROM w2_apart_master WHERE block_id=16;
/** Get House's day consumption based on house id for a date range **/
SELECT * FROM w2_apart_day_total WHERE apart_id=20 AND (dt BETWEEN '2018-04-01 00:00:00' and '2018-04-30 23:59:59' );


SELECT * FROM w2_apart_day_total t4 JOIN (SELECT * FROM w2_apart_master t1 JOIN (SELECT * FROM w2_block WHERE w2_block.site_id=4) t2 ON t1.block_id=t2.id) t3  ON t4.apart_id=t3.id AND (t4.dt BETWEEN '2018-04-01 00:00:00' and '2018-04-30 23:59:59' );

/** returns all records **/
SELECT t1.id, t1.block_name, t1.site_id, t2.id as house_id, t2.cust_name, t3.id as usageId, t3.apart_id, t3.agg_total, t3.dt from w2_block t1 inner join w2_apart_master t2 on t1.site_id=4 and t2.block_id=t1.id INNER JOIN w2_apart_day_total t3 on t3.apart_id=t2.id and (t3.dt BETWEEN '2018-04-01 00:00:00' and '2018-04-30 23:59:59' );
/** returns total usage for a house without sub query **/
SELECT t1.site_id, t1.id, t1.block_name, t2.cust_name, t3.apart_id, sum(t3.agg_total) as totalUsage from w2_block t1 inner join w2_apart_master t2 on t1.site_id=4 and t2.block_id=t1.id INNER JOIN w2_apart_day_total t3 on t3.apart_id=t2.id and (t3.dt BETWEEN '2018-04-01 00:00:00' and '2018-04-30 23:59:59' ) group by t3.apart_id;
/** returns total usage for a house with sub query **/
SELECT t1.block_name, t1.site_id, t2.cust_name, t3.apart_id, t3.total from w2_block t1 inner join w2_apart_master t2 on t1.site_id=4 and t2.block_id=t1.id INNER JOIN (select apart_id, sum(agg_total) as total from w2_apart_day_total where dt BETWEEN '2018-04-01 00:00:00' and '2018-04-30 23:59:59' group by apart_id) t3 on t3.apart_id=t2.id;
/** Test **/
SELECT t1.site_id, t1.id, t1.block_name, t2.cust_name, t3.apart_id, t3.agg_total from w2_block t1 inner join w2_apart_master t2 on t1.site_id=4 and t2.block_id=t1.id INNER JOIN w2_apart_day_total t3 on t3.apart_id=t2.id and (t3.dt BETWEEN '2018-04-01 00:00:00' and '2018-04-30 23:59:59' ) order by t3.apart_id;
SELECT t1.site_id, t1.block_name, t2.cust_name, t3.apart_id, t3.total from w2_block t1 inner join w2_apart_master t2 on t1.site_id=4 and t2.block_id=t1.id INNER JOIN (select apart_id, sum(agg_total) as total from w2_apart_day_total where dt>='2018-04-01 00:00:00' and dt<'2018-04-30 23:59:59' group by apart_id) t3 on t3.apart_id=t2.id;


/****** GANTT CHART ******/

SELECT id, cust_name, pump_id, block_id, site_id  FROM w2_pumps WHERE site_id=4;

SELECT id, pump_id, state, cumulative, site_id, dt FROM w2_pump_status_log where site_id=4 AND pump_id = 4 AND (dt BETWEEN '2018-08-01 00:00:00' and '2018-08-01 23:59:59');
SELECT pump_id, state, cumulative, site_id, dt FROM w2_pump_status_log where site_id=4 AND (dt BETWEEN '2018-08-01 00:00:00' and '2018-08-01 23:59:59');

SELECT * from w2_pumps t1 INNER JOIN (SELECT * from w2_pump_status_log where site_id=4 AND (dt BETWEEN '2018-09-01 00:00:00' and '2018-09-01 23:59:59')) t2 on t1.site_id=4 and t1.id = t2.pump_id;

// working one
SELECT t1.id, t1.pump_id, t1.cust_name, t1.block_id, t2.site_id, t2.state, t2.cumulative, t2.dt from w2_pumps t1 INNER JOIN ( SELECT *  FROM w2_pump_status_log WHERE site_id=4 and (dt BETWEEN '2018-09-01 00:00:00' and '2018-09-01 23:59:59')) t2 on t1.id = t2.pump_id and t1.site_id=4;

SELECT t1.id, t1.pump_id, t1.cust_name, t2.state, t2.site_id, t2.cumulative, t2.dt from w2_pumps t1 INNER JOIN ( SELECT *  FROM w2_pump_status_log WHERE site_id=4 and (dt BETWEEN '2018-09-01 00:00:00' and '2018-09-01 23:59:59') GROUP BY pump_id) t2 on t1.id = t2.pump_id and t1.site_id=4;

select * from w2_bwell_day_total where bwell_id =6;


/****** Water Map ******/
select id, apart_id, agg_total, dt from w2_apart_day_total where apart_id=4 and (dt>='2018-04-01 00:00:00' and dt<'2018-04-30 23:59:59');









