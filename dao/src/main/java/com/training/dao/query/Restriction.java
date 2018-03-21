package com.training.dao.query;

public class Restriction {

    public static Expression eq(QueryProperty property, Object value) {

        return new PropertyExpression(property, value, QueryOperator.EQ);
    }

    public static Expression nq(QueryProperty property, Object value) {

        return new PropertyExpression(property, value, QueryOperator.NQ);
    }

    public static Expression lt(QueryProperty property, Object value) {

        return new PropertyExpression(property, value, QueryOperator.LT);
    }

    public static Expression gt(QueryProperty property, Object value) {

        return new PropertyExpression(property, value, QueryOperator.GT);
    }

    public static Expression and(Expression left, Expression right) {

        return new LogicalExpression(left, right, QueryOperator.AND);
    }

    public static Expression or(Expression left, Expression right) {

        return new LogicalExpression(left, right, QueryOperator.OR);
    }

    public static Expression like(QueryProperty property, Object value) {

        return new LikeExpression(property, value);
    }

    public static Expression isNull(QueryProperty property) {

        return new AssertionExpression(property, AssertionExpression.IS_NULL);
    }

    public static Expression isNotNull(QueryProperty property) {

        return new AssertionExpression(property, AssertionExpression.IS_NOT_NULL);
    }


    public static JunctionExpression conjunction() {

        return new JunctionExpression(QueryOperator.AND);
    }

    public static JunctionExpression disjunction() {

        return new JunctionExpression(QueryOperator.OR);
    }

    public static enum QueryOperator {
        AND(" AND "),
        OR(" OR "),
        EQ(" = "),
        NQ(" != "),
        LT(" < "),
        GT(" > "),
        LE(" <= "),
        GE(" >= "),
        LIKE(" LIKE ");

        private String operator;

        private QueryOperator(String operator) {
            this.operator = operator;
        }

        @Override
        public String toString() {
            return operator;
        }
    }
}
