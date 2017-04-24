package com.github.chuanzh.orm;

public class AndCondition {
	public String column = null;
	public ConditionOperator operator;
	public String value = null;
  
	public AndCondition(String column, ConditionOperator operator, String value) {
		this.column = column;
		this.operator = operator;
		this.value = value;
	}
}
