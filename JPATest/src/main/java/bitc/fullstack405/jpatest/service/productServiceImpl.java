package bitc.fullstack405.jpatest.service;

import bitc.fullstack405.jpatest.data.entity.ProductEntity;
import bitc.fullstack405.jpatest.data.entity.ProviderEntity;
import bitc.fullstack405.jpatest.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class productServiceImpl implements productService {

    @Autowired
    private ProductRepository productRepo;

    @Override
    public void finds() throws Exception {
        System.out.println("\n ----- finds() 쿼리 메서드 실행 ------ \n");

        Optional<ProductEntity> prod1 = productRepo.findByNumber((long) 1);
        Optional<ProductEntity> prod2 = productRepo.findProductEntityByNumber((long) 2);
        Optional<List<ProductEntity>> prod3 = productRepo.findAllByName("백종원 도시락");
        ProductEntity prod4 = productRepo.queryByName("진짬뽕");

        if (prod1.isPresent()) {
            ProductEntity result = prod1.get();
            System.out.println("상품 번호 : " + result.getNumber());
            System.out.println("상품 이름 : " + result.getName());
            System.out.println("상품 가격 : " + result.getPrice());
            System.out.println("제고 수량 : " + result.getStock());
            System.out.println("등록 날짜 : " + result.getCreatedAt());
        } else {
            System.out.println("데이터 없음");
        }

        System.out.println("\n ----- finds() 쿼리 메서드 실행 완료 ------ \n");
    }

    @Override
    public void exists() throws Exception {
        System.out.println("\n ----- exists() 쿼리 메서드 실행 ------ \n");
        boolean isProductNumber = productRepo.existsByNumber((long) 200);
    }

    @Override
    public void count() throws Exception {
        System.out.println("\n ----- count() 쿼리 메서드 실행 ------ \n");
        int count = productRepo.countByName("삼각김밥");
    }

    @Override
    public void delete() throws Exception {
        System.out.println("\n ----- delete() 쿼리 메서드 실행 ------ \n");

//        productRepo.deleteByNumber((long) 1);
//        int count = productRepo.removeByName("백종원 도시락");
    }

    @Override
    public void limit() throws Exception {

    }

    @Override
    public void equals() throws Exception {

    }

    @Override
    public void isNot() throws Exception {

    }

    @Override
    public void isNull() throws Exception {

    }

    @Override
    public void isNotNull() throws Exception {

    }

    @Override
    public void and() throws Exception {
        System.out.println("\n ----- and() 쿼리 메서드 실행 ------ \n");

        ProductEntity prod1 = productRepo.findByNumberAndName((long) 5, "삼각김밥");
        ProductEntity prod2 = productRepo.findByNumberAndName((long) 9, "삼각김밥");
    }

    @Override
    public void or() throws Exception {
        System.out.println("\n ----- or() 쿼리 메서드 실행 ------ \n");

        List<ProductEntity> prod1 = productRepo.findByNumberOrName((long) 5, "새우탕면");
    }

    @Override
    public void between() throws Exception {

    }

    @Override
    public void like() throws Exception {

    }

    @Override
    public void oderBy() throws Exception {

    }

    @Override
    public void querySelectAll() throws Exception {
        System.out.println("\n ----- querySelectAll() 쿼리 메서드 실행 ------ \n");
        List<ProductEntity> result = productRepo.querySelectAll();
    }

    @Override
    public void querySelectName() throws Exception {
        System.out.println("\n ----- querySelectName() 쿼리 메서드 실행 ------ \n");

        ProductEntity prod = null;
        prod = productRepo.querySelectName();
        prod = productRepo.querySelectName1("칠성사이다", (long) 1300);
        prod = productRepo.querySelectName2("코카콜라제로", (long) 1400);
        prod = productRepo.querySelectName3("코카콜라제로", (long) 1400);

    }
}
