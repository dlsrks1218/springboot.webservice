package com.croquette.webservice.springboot.web.dto;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트() {
        // given
        String name = "test";
        int amount = 1000;

        // when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        // then
        // assertThat() :테스트 검증 라이브러리 assertj의 검증 메소드
        // isEqualTo() : 검증하고 싶은 대상을 메소드 인자로 받음, 메소드 체이닝이 지원되어 메소드를 이어 사용 가능
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
