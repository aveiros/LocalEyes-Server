package org.softmed.daos.strategies;


public class SearchLikeStrategy extends BaseQueryBuilder {

	String fieldName;
	String searchString;

	public SearchLikeStrategy(Class type, String fieldName, String searchString) {
		this.fieldName = fieldName;
		this.searchString = searchString;
	}

	public String getOwnQuery() throws Exception {

		String query = searchString;
		query = query.replace(" ", "%");

		query = " where " + fieldName + " like \'%" + query + "%\'";
		return query;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getSearchString() {
		return searchString;
	}
}
