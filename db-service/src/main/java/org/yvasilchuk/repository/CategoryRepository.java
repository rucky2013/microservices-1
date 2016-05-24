package org.yvasilchuk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yvasilchuk.domain.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
