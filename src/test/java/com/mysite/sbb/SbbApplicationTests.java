package com.mysite.sbb;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.dao.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SbbApplicationTests {

    @Autowired
    private QuestionRepository questionRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void testJpa() {
        Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가요?");
        q1.setContent("sbb에 대해 알고 싶습니다.");
        q1.setCreateDate(LocalDateTime.now());
        questionRepository.save(q1);

        Question q2 = new Question();
        q2.setSubject("스프링부트 모델 질문입니다.");
        q2.setContent("id는 자동으로 생성되나요?");
        q2.setCreateDate(LocalDateTime.now());
        questionRepository.save(q2);
    }

    @Test
    void testJpa2() {
        List<Question> all = this.questionRepository.findAll();
        assertEquals(10, all.size());

        Question q = all.get(0);
        assertEquals("sbb가 무엇인가요?", q.getSubject());
    }


    @Test
    void findbyID() {
        Optional<Question> oq = questionRepository.findById(1);
        if (oq.isPresent()) {
            Question q = oq.get();
            assertEquals("sbb가 무엇인가요?", q.getSubject());
            System.out.println("subject : " + q.getSubject());
        }
    }

    @Test
    void findByID2() {
        Optional<Question> oq = questionRepository.findById(2);
        if (oq.isPresent()) {
            Question q = oq.get();

            System.out.println("subject : " + q.getSubject());
            System.out.println("content : " + q.getContent());

        }
    }

    @Test
    void findBySubejct() {
        // Subject가 같은 내용의 데이터가 있으면 조회가 불가능
        Question q = questionRepository.findBySubject("sbb가 무엇인가요?");
        assertEquals(1, q.getId());
    }

    @Test
    void findAllBySubject() {
        List<Question> questions = questionRepository.findAllBySubject("sbb가 무엇인가요?");
        assertEquals(3, questions.size());
        System.out.println("sbb가 무엇인가요 subject 갯수 : " + questions.size());
    }

    @Test
    void findBySubjectAndContent() {
        Question q = questionRepository.findBySubjectAndContent("sbb가 무엇인가요?", "sbb에 대해 알고 싶습니다.");
        assertEquals(1, q.getId());
    }

    @Test
    void findBySubjectOrContent() {
        Question q = questionRepository.findBySubjectOrContent("sbb가 무엇인가요?", "sbb인가요??");

        System.out.println("?? : " + q.getSubject());
    }

    @Test
    void findBySubjectLike() {
        List<Question> q = questionRepository.findBySubjectLike("%무엇%");

        if (q.isEmpty()) {
            System.out.println("없음");
        } else {
            Question q2 = q.get(0);

            System.out.println(q2.getId());

        }

    }

    @Test
    void EdditJpa() {
        Optional<Question> oq = questionRepository.findById(1);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        q.setSubject("수정된 제목");
        q.setContent("수정된 내용");
        questionRepository.save(q);

    }

    @Test
    void findedditsubjectAndContent() {
        Question q = questionRepository.findBySubjectAndContent("수정된 제목", "수정된 내용");
        if (q != null) {
            q.setSubject("수정된 제목 확인");
            q.setContent("수정된 내용 확인");
            questionRepository.save(q);
            System.out.println("수정 성공");

        } else {
            System.out.println("수정 실패");
        }

    }

//    @Test
//    void findbyedditcontent() {
//        List <Question> oq = questionRepository.findAllByContentLike("%내용%");
//
//        Question q = oq.
//
//
//        if(q != null) {
//            q.setContent("수정된 내용 입니다다다다다다다다.");
//            questionRepository.save(q);
//            System.out.println("수정이 완료 되었습니다.");
//        }
//        else {
//            System.out.printf("수정 실패");
//        }


//    }


}
