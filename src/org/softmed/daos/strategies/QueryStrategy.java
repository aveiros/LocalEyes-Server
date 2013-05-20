package org.softmed.daos.strategies;

public class QueryStrategy extends BaseQueryBuilder {

	String query;

	public QueryStrategy(String query) {
		this.query = query;
	}

	@Override
	public String getOwnQuery() throws Exception {
		return query;
	}

	public String getQueryString() {
		return query;
	}

	public void setQueryString(String query) {
		this.query = query;
	}

}
