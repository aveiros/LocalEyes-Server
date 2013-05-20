package org.softmed.daos.strategies;


public interface IQueryBuilder {

	public IQueryBuilder getQueryBuilder();

	public void setQueryBuilder(IQueryBuilder queryBuilder);

	public String getQuery() throws Exception;

	public String getOwnQuery() throws Exception;

}
