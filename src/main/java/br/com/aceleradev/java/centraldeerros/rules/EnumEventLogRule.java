package br.com.aceleradev.java.centraldeerros.rules;

import br.com.aceleradev.java.centraldeerros.model.EventLog;
import br.com.aceleradev.java.centraldeerros.model.Level;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Predicate;

public class EnumEventLogRule implements Specification<EventLog> {
    private String columnName;
    private String columnValue;

    public EnumEventLogRule(String name, String value) {
        this.columnName = name;
        this.columnValue = value;
    }

    @Override
    public Predicate toPredicate(Root<EventLog> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (this.columnValue == null) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        }
            return criteriaBuilder.equal(root.get(this.columnName), Level.find(this.columnValue));
        }
}
