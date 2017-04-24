package com.github.chuanzh.orm;

import java.util.List;

public class OrCondition {
	public List<String> column = null;
	public List<ConditionOperator> operator;
	public List<String> value = null;

	public OrCondition(List<String> column, List<ConditionOperator> operator,
			List<String> value) {
		this.column = column;
		this.operator = operator;
		this.value = value;
	}
}