package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@DisplayName("질문 기능 관련 테스트")
public class QuestionTest {
    public Question Q1;
    public Question Q2;

    @BeforeEach
    void setUp() {
        Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");
    }

    @Test
    @DisplayName("질문 삭제시 로그인 사용자와 질문한 사람이 같을 경우 예외를 던지지 않는다.")
    void delete_LoginUserEqualToQuestionUser_NotThrowException() {
        assertThatNoException()
                .isThrownBy(() -> Q1.delete(NsUserTest.JAVAJIGI));

        assertThatNoException()
                .isThrownBy(() -> Q2.delete(NsUserTest.SANJIGI));
    }

    @Test
    @DisplayName("질문 삭제시 로그인 사용자와 질문한 사람이 다를 경우 예외를 던진다.")
    void delete_LoginUserNotEqualToQuestionUser_ThrowException() {
        assertThatThrownBy(() -> Q1.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);

        assertThatThrownBy(() -> Q2.delete(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("삭제 가능할 경우에 isDelete 값을 true로 변경한다.")
    void delete_DeleteQuestion_ChangeIsDeleteToTrue() {
        Q1.delete(NsUserTest.JAVAJIGI);
        Q2.delete(NsUserTest.SANJIGI);

        assertThat(Q1.isDeleted()).isTrue();
        assertThat(Q2.isDeleted()).isTrue();
    }

}
