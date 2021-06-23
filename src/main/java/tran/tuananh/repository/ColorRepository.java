package tran.tuananh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tran.tuananh.model.Color;

@Repository
public interface ColorRepository extends JpaRepository<Color, Integer> {

}
