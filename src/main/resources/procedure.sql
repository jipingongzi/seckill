DELIMITER $$
CREATE PROCEDURE `seckill`.`execute_seckill`
	(in v_id VARCHAR(36),in v_kill_product_id VARCHAR(36),in v_mobile BIGINT,in v_kill_time TIMESTAMP,out r_result int)
	BEGIN
		DECLARE insert_count int DEFAULT 0;
    START TRANSACTION;
    INSERT IGNORE INTO kill_item(id,kill_product_id,mobile) values(v_id,v_kill_product_id,v_mobile);
    SELECT ROW_COUNT() INTO insert_count;
		IF(insert_count = 0) THEN
			ROLLBACK;
			SET r_result = -1;
		ELSEIF(insert_count < 0) THEN
			ROLLBACK;
			SET r_result = -2;
		ELSE
			UPDATE kill_product SET number = number - 1
			WHERE id = v_kill_product_id AND number >= 1 AND end_time > v_kill_time AND start_time < v_kill_time;
			SELECT ROW_COUNT() INTO insert_count;
			IF(insert_count = 0) THEN
				ROLLBACK;
				SET r_result = 0;
			ELSEIF(insert_count < 0) THEN
				ROLLBACK;
				SET r_result = -2;
			ELSE
				COMMIT;
				SET r_result = 1;
			END IF;
		END IF;
	END;
$$
DELIMITER ;
