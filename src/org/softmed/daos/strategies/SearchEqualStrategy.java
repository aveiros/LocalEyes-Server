package org.softmed.daos.strategies;


public class SearchEqualStrategy extends BaseQueryBuilder {

	String fieldName;
	String searchString;
	String mainName;
	String connectionQuery = "  where ";

	public SearchEqualStrategy(String fieldName, String searchString) {
		this.fieldName = fieldName;
		this.searchString = searchString;
	}

	public SearchEqualStrategy(String connectionQuery, String fieldName,
			String mainName, String searchString) {
		this.connectionQuery = connectionQuery;
		this.fieldName = fieldName;
		this.searchString = searchString;
		this.mainName = mainName;
	}

	public String getOwnQuery() throws Exception {
		String query = " " + (connectionQuery == null ? "" : connectionQuery)
				+ " " + (mainName == null ? "" : mainName + ".") + fieldName
				+ "=" + searchString + "";
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

	public String getMainName() {
		return mainName;
	}

	public void setMainName(String mainName) {
		this.mainName = mainName;
	}

}
