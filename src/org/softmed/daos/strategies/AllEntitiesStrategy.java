package org.softmed.daos.strategies;


public class AllEntitiesStrategy extends BaseQueryBuilder {

	Class type;

	public AllEntitiesStrategy(Class type) {
		this.type = type;
	}

	public Class getType() {
		return type;
	}

	public void setType(Class type) {
		this.type = type;
	}

	@Override
	public String getOwnQuery() throws Exception {
		return "from " + type.getSimpleName();
	}

}
