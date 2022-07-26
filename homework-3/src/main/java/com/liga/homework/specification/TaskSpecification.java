package com.liga.homework.specification;

import com.liga.homework.SearchCriteria;
import com.liga.homework.model.Task;
import com.liga.homework.model.User;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskSpecification implements Specification<Task> {

  private final String STATUS_FIELD = "status";
  private SearchCriteria searchCriteria;
  //private String criteria;

  @Nullable
  @Override
  public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> query,
                               CriteriaBuilder builder) {
    List<Predicate> predicates = new ArrayList<>();

    predicates.add(builder.equal(root.get("user"), searchCriteria.getUser()));

    if(searchCriteria.getStatusOfTask()!=null){
      predicates.add(builder.equal(root.get(STATUS_FIELD), searchCriteria.getStatusOfTask()));
    }
    if (searchCriteria.getDateFrom() != null) {
      predicates.add(builder.greaterThanOrEqualTo(root.get("date"), searchCriteria.getDateFrom()));
    }
    if (searchCriteria.getDateTo() != null) {
     predicates.add(builder.lessThanOrEqualTo(root.get("date"), searchCriteria.getDateTo()));
    }
    return builder.and(predicates.toArray(new Predicate[0]));
    }

  }

