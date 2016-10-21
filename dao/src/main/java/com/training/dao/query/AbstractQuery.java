package com.training.dao.query;

import com.training.dao.model.Employee;
import com.training.dao.model.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yangkai on 16/10/9.
 */
public class AbstractQuery<T extends Query<?,?>, U extends Entity> implements Query<T, U>{

    protected String statement;
    protected Expression conditions;
    protected List<String> groupBy = new ArrayList<>();
    protected List<String> orderBy = new ArrayList<>();


    public T from(String statement) {

        this.statement = statement;
        return (T) this;
    }


    public T where(Expression conditions) {

        if (null == this.conditions) {
            this.conditions = conditions;
        } else {
            and(conditions);
        }
        return (T) this;
    }


    public T and(Expression conditions) {

        if (null == this.conditions) {
            throw new RuntimeException();
        } else {
            this.conditions = Restriction.and(this.conditions, conditions);
            return (T) this;
        }
    }


    public T or(Expression conditions) {

        if (null == this.conditions) {
            // TODO: error handling.
            throw new RuntimeException();
        } else {
            this.conditions = Restriction.or(this.conditions, conditions);
            return (T) this;
        }
    }


    public MybatisQuery<E> groupBy(QueryProperty property) {

        groupBy.add(property.getName());
        return this;
    }


    public MybatisQuery<E> asc(QueryProperty property) {

        orderBy.add(property.getName() + "ASC");
        return this;
    }


    public MybatisQuery<E> desc(QueryProperty property) {

        orderBy.add(property.getName() + "DESC");
        return this;
    }


    public MybatisQuery<E> limit(int startIndex, int pageLength) {

        setStartIndex(startIndex);
        setPageLength(pageLength);
        return this;
    }


    public Map<String, Object> getConditionMap() {

        Map<String, Object> conditionMap = new HashMap<>();
        String conditions = buildConditionClause();
        conditionMap.put("conditions", conditions);
        String groupBy = buildGroupByClause();
        conditionMap.put("groupBy", groupBy);
        String orderBy = buildOrderByClause();
        conditionMap.put("orderBy", orderBy);
        String limitBy = buildLimitClause();
        conditionMap.put("limit", limitBy);

        return conditionMap;
    }

    private String buildConditionClause() {

        return conditions.interpret();
    }

    private String buildGroupByClause() {

        if (groupBy.size() > 0) {
            StringBuilder buf = new StringBuilder();
            buf.append(GROUP_BY);

            for (String property : groupBy) {
                buf.append(property);
                buf.append(SystemConstants.COMMA);
            }
            buf.delete(buf.length() - 1, buf.length());
            return buf.toString();
        } else {
            return "";
        }
    }

    private String buildOrderByClause() {

        if (orderBy.size() > 0) {
            StringBuilder buf = new StringBuilder();
            buf.append(ORDER_BY);

            for (String sort : orderBy) {
                buf.append(sort);
                buf.append(SystemConstants.COMMA);
            }
            buf.delete(buf.length() - 1, buf.length());
            return buf.toString();
        } else {
            return "";
        }
    }

    private String buildLimitClause() {

        return LIMIT + getStartIndex() + SystemConstants.COMMA + getPageLength();
    }


    public String getStatement() {

        return statement;
    }

    public Expression getConditions() {

        return conditions;
    }

    public List<String> getGroupBy() {

        return groupBy;
    }


    public T asc() {
        return null;
    }

    public T desc() {
        return null;
    }

    public long count() {
        return 0;
    }

    public U singleResult() {
        return null;
    }

    public List<U> list() {
        return null;
    }

    public List<U> listPage(int offset, int limit) {
        return null;
    }
}
