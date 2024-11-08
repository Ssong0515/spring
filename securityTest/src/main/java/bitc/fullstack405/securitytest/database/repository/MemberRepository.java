package bitc.fullstack405.securitytest.database.repository;

import bitc.fullstack405.securitytest.database.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUserId(String userId);

}
