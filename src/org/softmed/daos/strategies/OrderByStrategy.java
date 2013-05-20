package org.softmed.daos.strategies;

public class OrderByStrategy extends BaseQueryBuilder {

	String field;
	boolean asc;

	public OrderByStrategy(String field, boolean asc) {
		this.field = field;
		this.asc = asc;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public boolean isAsc() {
		return asc;
	}

	public void setAsc(boolean asc) {
		this.asc = asc;
	}

	@Override
	public String getOwnQuery() throws Exception {
		String query = " order by " + field;
		if (asc)
			query += " asc";
		else
			query += " desc";

		return query;
	}

}
