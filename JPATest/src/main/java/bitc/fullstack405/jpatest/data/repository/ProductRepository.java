package bitc.fullstack405.jpatest.data.repository;

import bitc.fullstack405.jpatest.data.entity.ProductEntity;
import org.aspectj.weaver.ast.Not;
import org.hibernate.sql.exec.spi.StandardEntityInstanceResolver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    // JpaRepository를 상속받았기 때문에 JpaRepository가 제공하는 기본 메소드와 쿼리 메소드를 생성하여 사용 가능

    // 쿼리 메서드: JpaRepository에서 제공하는 기본 메서드로는 원하는 데이터를 모두 컨트롤 할 수 없기 때문에
    // JpaRepository에서 제공하는 주제 키워드를 조합하여 원하는 메서드를 생성할 수 있음

    /* 쿼리 메서드 생성 방법
     리턴타입 + {주제 키워드 + 서술어}
     리터타입 키워드 대상entity By 칼럼
      를 조합하여 메서드를 선언 ({} 부분은 반복 사용 가능)
     ex) List<Person> findByLastnameAndEmail(String lastName, String email);
    */

     // <주제 키워드>
     // find ... By : select 명령을 수행하는 키워드
        // read ... By, get ... By, query ... By, search ... By, stream ... By 등이 추가로 존재

    Optional<ProductEntity> findProductEntityByNumber(Long number); // 기본 사용 방식
    Optional<ProductEntity> findByNumber(Long number); // entity를 이미 지정했기 때문에 생략 가능
    Optional<List<ProductEntity>> findAllByName(String name); // 이름을 기준으로 모든 데이터 가져오기

    ProductEntity queryByName(String name); // 다른 키워드로 데이터 가져오기

    // exists ... By : 특정 데이터가 존재하는지 여부를 확인하는 키워드, true/false 반환
    boolean existsByNumber(Long number); // 지정한 번호의 데이터가 있는지 확인

    // count .. By : select 명령을 수행 후 쿼리 결과로 나온 레코드의 수를 출력하는 키워드
    int countByName(String name);

    // delete ... By, remove ... By : delete 명령을 수행하는 키워드
        // delete ... By 는 리턴값 없음
        // remove ... By 는 삭제 수를 반환
    void deleteByNumber(Long number); // 지정한 번호를 기준으로 데이터 삭제, 반환값 없음
    int removeByName(String name); // 지정한 이름으로 데이터 삭제, 삭제된 데이터 수 반환

    // First<number> ... , Top<number> ... : select 명령을 수행한 후 조회된 결과를 제한하는 키워드
    // 주제 키워드와 By 사이에 입력함
    List<ProductEntity> findFirst5ByName(String name); // 조회된 결과 중 처음 5개만 출력;
    List<ProductEntity> findTop5ByName(String name); // 조회된 결과 중 숫자가 큰 것 5개 출력

    // Is: 조건 키워드, 값의 일치 여부를 조건으로 사용, Equals와 동일한 기능을 제공
    ProductEntity findByNumberIs(Long number);// 지정한 번호와 같은 데이터를 조회
    ProductEntity findByNumberEquals(Long number);// 지정한 번호와 같은 데이터를 조회

//    (Is)Not: 값의 불일치 조건을 사용하는 조건 키워드, Is 생략 가능
    ProductEntity findByNumberIsNot(Long number);// 지정한 번호와 같이 않은 데이터를 조회
    ProductEntity findByNumberNot(Long number) ;// 지정한 번호와 같지 않은 데이터를 조회

//    (Is)Null, (Is)NotNull : 값이 null인 것만 조회 0
//    List<ProductEntity> findByUpdatedAtNull(); // 수정된 데이터가 null인 것만 조회
//    List<ProductEntity> findByUpdatedAtIsNot();
//    List<ProductEntity> findByUpdatedAtNull();
//    List<ProductEntity> findByUpdatedAtNull();

//    (Is)True, (Is)False : Boolean 데이터 타입으로 지정된 커럼 값을 확인하는 키워드
//    And, Or: 조건을 추가하는 키워드
        // and 명령으로 조회 조건을 추가, 지정한 번호와 지정한 이름이 같은 데이터를 조회
        // or 명령으로 조회 조건을 추가, 지정한 번호나 지정한 이름이 같은 데이터를 조회

//    (Is)GreaterThan, (Is)LessThan, (Is)Between: 숫자나 dateTime 칼럼을 대상으로 비교 연산에
//    사용 할 수 있는 조건 키워드
//    경계값을 포함할 경우 Equal 키워드를 추가

    ProductEntity findByNumberAndName(long number, String name);

    List<ProductEntity> findByNumberOrName(long number, String name);

//    @Query : 복잡한 쿼리문은 쿼리 메서드로 생성하기 힘들기 때문에 @Query 라는 어노테이션을 제공
//    JPQL(JPA SQL) 문법을 사용하여 SQL 쿼리를 생성하여 복잡한 쿼리문을 실행할 수 있도록 도와줌
//    검색 조건에 '?순번' 형태로 데이터를 사용함
    
//    사용법:
//    @Query("select entity객체명 from entity클래스명 where 조건1, 조건2, ...")
//    반환타입 매서드명(매개변수1, 매개변수2, ...);
    
    @Query("select p from ProductEntity p")
    List<ProductEntity> querySelectAll();
    
    @Query("select p from ProductEntity p where p.name = '코카콜라'")
    ProductEntity querySelectName();
    
//    순번은 1번부터 시작하고, 매개변수의 순서대로 적용됨
    @Query("select p from ProductEntity p where p.name = ?1 and p.price = ?2")
    ProductEntity querySelectName1(String name, Long price);

//    메서드의 매개변수를 쿼리문에 적용할 경우 ':매개변수명' 형식으로 입력하여 매칭
//    ':매개변수명' 방식은 메서드의 매개변수 앞에 @Param("컬럼명") 어노테이션을 붙혀야 함
//    ':매개변수명' 방식은 매개변수의 순서와 상관없음
    @Query("select p from ProductEntity p where p.name = :name and p.price = :price")
    ProductEntity querySelectName2(@Param("name") String name, @Param("price") Long price);
    
    @Query("select p from ProductEntity p where p.name = :name and p.price = :price")
    ProductEntity querySelectName3(String name, Long price);
}

