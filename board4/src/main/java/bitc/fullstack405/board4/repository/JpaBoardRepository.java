package bitc.fullstack405.board4.repository;

import bitc.fullstack405.board4.entity.JpaBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Repository: JPA에서 데이터베이스에 명령어를 전달하는 클래스
// Mybatis의 Mapper 인터페이스 _xml 파일과 동일한 역할을 함

// Repository -> CrudRespsitory -> PagingAndSortingRepository -> JpaReposityr의 상속간계를 가지고 있음
// 사용자는 JpaRepository를 상속받아 사용함
// JpaRepository 상속 시, 컬렉션 타입으로 첫번째 매개변수에 사용할 entity 클래스명, 두번째 매개변수로 해당 entity 클래스에서 @Id로 설정한 필드의 데이터 타입을 입력

// JpaRepository에서 기본 명령어 제공
// count() : sql의 count() 명령과 같음
// save() : sql이 insert, update 명령과 동일함
// saveAll() : 여러개의 데이터를 동시에 save() 하는 기능
// findById : sql의 select 명령과 동일한 기능, 기본키를 통해서 검색
// findAll() : sql의 select 명령과 동일한 기능, 모든 데이터 가져오기
// deleteById() : sql의 delete 명령과 동일한 기능, 기본키를 통해서 삭제
// deleteAll() : sql의 delete 명령과 동일한 기능, 전체 삭제
public interface JpaBoardRepository extends JpaRepository<JpaBoardEntity, Integer> {
    
//    쿼리 메서드: JpaRepository에서 지원하는 기본 쿼리 메서드를 가지고는
//    원하는 모든 기능을 사용할 수 없기 때문에
//    특정 키워드를 사용하여 사용자 정의 메서드를 생성하는 방식의 메서드
//    쿼리 메서드를 사용하여 필요한 명령을 전달
    List<JpaBoardEntity> findAllByOrderByBoardIdxDesc();
    
//    @Query : JpaRepository에서 제공하는 기본 명령어 및 쿼리 메서드로 만들기 힘든
//    복잡한 쿼리문의 경우 JPQL 문법을 통해서 SQL 쿼리문을 직접 생성하여
//    사용할 수 있도록 해주는 어노테이션


}
