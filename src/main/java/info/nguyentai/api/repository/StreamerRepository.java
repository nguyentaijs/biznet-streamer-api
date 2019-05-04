package info.nguyentai.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import info.nguyentai.api.entity.Stream;

@Repository
public interface StreamerRepository extends JpaRepository<Stream, String> {

}
