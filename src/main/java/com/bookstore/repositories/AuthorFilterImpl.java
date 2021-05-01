package com.bookstore.repositories;

import org.springframework.stereotype.Component;
import com.bookstore.Models.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Component
public class AuthorFilterImpl implements AuthorFilter {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Author> findAuthorsByBookName(String bookName) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Author> cq = cb.createQuery(Author.class);

        Root<Author> author = cq.from(Author.class);
        List<Predicate> predicates = new ArrayList<>();

        if (bookName != null) {
            predicates.add(cb.equal(author.get("book").get("bookName"), bookName));
        }
        cq.orderBy(cb.asc(author.get("lastName")));
        cq.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(cq).getResultList();
    }

    @Override
    public List<Author> findAuthorsByFirstNameAndLastName(String fName, String lName) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Author> cq = cb.createQuery(Author.class);

        Root<Author> author = cq.from(Author.class);
        List<Predicate> predicates = new ArrayList<>();

        if (fName != null) {
            predicates.add(cb.equal(author.get("firstName"), fName));
        }
        if (lName != null) {
            predicates.add(cb.equal(author.get("lastName"), lName));
        }
        cq.orderBy(cb.asc(author.get("book").get("bookName")));
        cq.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(cq).getResultList();
    }

}
