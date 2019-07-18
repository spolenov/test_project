package com.century.test_project_spolenov.repository.goods;

import com.century.test_project_spolenov.model.goods.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsRepository extends JpaRepository<Goods, Integer> {
}
