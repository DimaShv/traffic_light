package com.traffic_light.crossroad.log;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class CrossroadLogSpecification implements Specification<CrossroadLog> {
    private LocalDateTime startPeriod;
    private LocalDateTime endPeriod;
    private Boolean isTrafficOffender;

    @Override
    public Predicate toPredicate(Root<CrossroadLog> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicateList = new ArrayList<>();
        if (isTrafficOffender != null) {
            predicateList.add(cb.equal(root.get("isTrafficOffender"), isTrafficOffender));
        }
        if (startPeriod != null) {
            predicateList.add(cb.greaterThanOrEqualTo(root.get("time"), startPeriod));
        }
        if (endPeriod != null) {
            predicateList.add(cb.lessThanOrEqualTo(root.get("time"), endPeriod));
        }
        return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
    }
}
