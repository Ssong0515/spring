package bitc.fullstack405.awsweb.service;

import bitc.fullstack405.awsweb.dto.PhoneBookDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PhoneBookService {

    public List<PhoneBookDTO> getPhoneBookList() throws Exception {
        PhoneBookDTO phone1 = PhoneBookDTO.builder()
                .name("karina")
                .phone("010-1234-5678")
                .email("karina@naver.com")
                .build();

        PhoneBookDTO phone2 = PhoneBookDTO.builder() // new PhoneBookDTO
                .name("winter")
                .phone("010-1234-5789")
                .email("winter@naver.com")
                .build();

        List<PhoneBookDTO> phonebookList = new ArrayList<>();
        phonebookList.add(phone1);
        phonebookList.add(phone2);

        return phonebookList;
    }

}
