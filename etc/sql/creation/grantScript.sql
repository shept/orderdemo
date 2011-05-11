SET search_path = s1, pg_catalog;

GRANT ALL ON 
	address,
	customer,
	item,
	"order",
	orderItem,
	orderNumber,
	unit,
	vatrate
TO postgres;

GRANT SELECT, INSERT, UPDATE, DELETE ON 
	address,
	customer,
	item,
	"order",
	orderItem,
	orderNumber,
	unit,
	vatrate
TO sheptorder_group;

GRANT SELECT, UPDATE ON 
	address_id_seq,
	customer_id_seq,
	item_id_seq,
	order_id_seq,
	orderItem_id_seq,
	orderNumber_id_seq,
	unit_id_seq,
	vatrate_id_seq
TO sheptorder_group; 
