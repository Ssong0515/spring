package bitc.fullstack405.awsweb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// @Builder : 빌더 패턴을 사용 할 수 있도록 도와주는 어노테이션
@Builder
@Getter

// @AllArgsConstructor : 모든 클래스 필드에 대한 매개변수를 생성자로 자동 생성 해 주는 어노테이션
@AllArgsConstructor
// @NoArgsConstructor : 기본 생성자(빈 매개변수)를 자동으로 생성하는 어노테이션
@NoArgsConstructor
public class PhoneBookDTO {

    private String name;
    private String phone;
    private String email;

}
