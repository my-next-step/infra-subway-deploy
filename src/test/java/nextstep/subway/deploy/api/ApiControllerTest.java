package nextstep.subway.deploy.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test") // 테스트 전용 프로필
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApiControllerTest {

    @LocalServerPort
    private int port;

    @DisplayName("application-test.properties 값을 확인한다")
    @Test
    void properties() {
        String expected = "안녕하세요 : testuser";
        String response = given()
                .port(port) // 랜덤 포트
                .get("/")
                .then().extract().body().asString();

        assertThat(response).isEqualTo(expected);
    }
}