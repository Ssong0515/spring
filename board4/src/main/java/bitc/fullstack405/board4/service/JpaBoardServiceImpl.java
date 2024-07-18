package bitc.fullstack405.board4.service;

import bitc.fullstack405.board4.entity.JpaBoardEntity;
import bitc.fullstack405.board4.repository.JpaBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JpaBoardServiceImpl implements JpaBoardService {

    @Autowired
    private JpaBoardRepository jpaBoardRepository;

    @Override
    public List<JpaBoardEntity> selectBoardList() throws Exception {
        return jpaBoardRepository.findAllByOrderByBoardIdxDesc();
    }

    @Override
    public JpaBoardEntity selectBoardDetail(int boardIdx) throws Exception {
//        Optional: 데이터베이스에서 데이터를 조회 시, 조회된 데이터가 null일 경우
//        이 null은 자바의 null과 의미가 조금 다름
//        데이터베이스의 null은 특정 컬럼에 데이터가 없다는 의미
//        자바의 null은 객체가 가리키는 주소(pointer)가 없다는 의미
//        데이터베이스의 null을 자바 소스코드에 그대로 적용 시, 원하지 않는 오류가 발생할 가능성이 높음
//        그래서 이러한 오류를 줄이기 위해 만들어진 타입이 Optional 타입임
//        Optional 타입을 통해서 데이터가 있는지 없는지 여부를 확인할 수 있음
        Optional<JpaBoardEntity> optBoard = jpaBoardRepository.findById(boardIdx);
        
//        조회된 데이터의 존재 유무를 알려줌
        if (optBoard.isPresent()) {
//            반환할 데이터 타입의 객체 생성
//            get() 메서드를 사용하여 Optional 타입의 객체에 저장된 데이터를 가져옴
            JpaBoardEntity board = optBoard.get();
//            현재 조회수 업데이트
            board.setHitCnt(board.getHitCnt() + 1);
//            수정된 데이터를 데이터베이스에 update 시킴
            jpaBoardRepository.save(board);
            
            return board;
        } else {
//            데이터가 없을 시 처리
            throw new NullPointerException();
        }
    }

    @Override
    public void saveBoard(JpaBoardEntity board) throws Exception {
//        JpaRepository에서 제공하는 save() 메서드를 사용하여 insert/update 기능을 구현
        jpaBoardRepository.save(board);
    }

    @Override
    public void deleteBoard(int boardIdx) throws Exception {
//        JpaRepository에서 제공하는 deleteById() 메서드를 사용하여 delete 기능을 구현
        jpaBoardRepository.deleteById(boardIdx);
    }
}