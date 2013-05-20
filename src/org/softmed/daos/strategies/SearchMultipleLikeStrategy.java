package org.softmed.daos.strategies;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SearchMultipleLikeStrategy extends BaseQueryBuilder {

	String searchString;
	private List<String> fieldNames = new ArrayList<String>();
	String mainName;

	String connectionQuery = "  where ";

	public SearchMultipleLikeStrategy(String searchString, String fieldNames) {
		setFieldNames(fieldNames);
		this.searchString = searchString;
	}

	public SearchMultipleLikeStrategy(String connectionQuery,
			String searchString, String mainName, String fieldNames) {
		this.connectionQuery = connectionQuery;
		this.searchString = searchString;
		this.mainName = mainName;

		setFieldNames(fieldNames);
	}

	private void setFieldNames(String fieldNames) {
		StringTokenizer tokenizer = new StringTokenizer(fieldNames, " ,");
		while (tokenizer.hasMoreTokens()) {
			String next = tokenizer.nextToken();
			this.fieldNames.add(next);
		}
	}

	public String getOwnQuery() throws Exception {

		String query = searchString;
		query = "\'%" + query.replace(" ", "%") + "%\'";

		String baseQuery = " "
				+ (connectionQuery == null ? "" : connectionQuery) + " ";

		boolean started = false;
		for (String fieldName : fieldNames) {
			if (!started) {
				baseQuery += (mainName == null ? "" : mainName + ".")
						+ fieldName + " like " + query;
				started = true;
			} else
				baseQuery += " or " + fieldName + " like " + query;
		}

		return baseQuery;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
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
