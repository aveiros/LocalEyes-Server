package org.softmed.daos.strategies;

public abstract class BaseQueryBuilder implements IQueryBuilder {

	IQueryBuilder queryBuilder;

	public String getQuery() throws Exception {
		String query = getOwnQuery();
		if (queryBuilder == null)
			return query;

		return query + " " + queryBuilder.getQuery();
	}

	@Override
	public IQueryBuilder getQueryBuilder() {
		// TODO Auto-generated method stub
		return queryBuilder;
	}

	@Override
	public void setQueryBuilder(IQueryBuilder queryBuilder) {
		this.queryBuilder = queryBuilder;
	}

}
