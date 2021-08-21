package tran.tuananh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tran.tuananh.model.File;

@Repository
public interface FileRepository extends JpaRepository<File, Integer> {

}
