package com.njusc.npm.metadata.router;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author jinzf
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	private List<String> dataSourceValus = new ArrayList<String>();

	@Override
	protected Object determineCurrentLookupKey() {
		return DynamicDataSourceHolder.getCurrentKey();
	}

	@Override
	public void setTargetDataSources(Map<Object, Object> targetDataSources) {
		super.setTargetDataSources(targetDataSources);
		if (targetDataSources != null) {
			for (Entry<Object, Object> entry : targetDataSources.entrySet()) {
				if (entry.getKey() != null && entry.getKey() instanceof String) {
					dataSourceValus.add((String) entry.getKey());
				}
			}
		}
	}

	public List<String> getDataSourceValues() {
		return dataSourceValus;
	}
}
