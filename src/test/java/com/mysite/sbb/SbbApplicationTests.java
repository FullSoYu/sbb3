package com.mysite.sbb;

import com.mysite.sbb.Answer.Answer;
import com.mysite.sbb.Answer.dao.AnswerRepository;
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

    @Autowired
    private AnswerRepository answerRepository;

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
    void findsubjectlike(){
        Optional<Question> oq = questionRepository.findBySubjectLike("%수정%");

        if(oq.isPresent()) {
            Question q = oq.get();
            System.out.println(q.getSubject());
            q.setSubject("수정된 스프링부트 모델 질문입니다.2");
            questionRepository.save(q);
            System.out.println("수정완료");
            System.out.println(q.getSubject());
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
    @Test
    void delete_question() {
        Optional<Question> oq = questionRepository.findById(1);
        System.out.println("삭제 전 전체 질문 갯수 " + questionRepository.count());
        assertTrue(oq.isPresent());
        Question q = oq.get();
        questionRepository.delete(q);
        System.out.println("삭제 후 전체 질문 갯수 " + questionRepository.count());

    }
    @Test
    void Create_question() {
     Question q = new Question();
     q.setSubject("새로 생성된 질문");
     q.setContent("새로 생성된 내용입니다.");
     q.setCreateDate(LocalDateTime.now());
     questionRepository.save(q);
    }
    @Test
    void Test_1() {
        System.out.println("============질문 목록 ===========");
        for(int i = 1; i <= questionRepository.count(); i++){
            Optional<Question> oq = questionRepository.findById(i);
            if(oq.isPresent()){
                Question q = oq.get();
                System.out.println("질문 : " + q.getSubject());
            }
        }
    }

    @Test
    void Size_check() {
        List<Question> oq = questionRepository.findAllBySubjectLike("%생성%");
        Question q1 = oq.get(0);
        q1.setSubject("수정 수정 질문");
        q1.setCreateDate(LocalDateTime.now());
        questionRepository.save(q1);

        Question q2 = oq.get(1);
        q2.setSubject("수정정러러러러러 질문");
        q2.setCreateDate(LocalDateTime.now());
        questionRepository.save(q2);
    }
    @Test
    void create_answer() {
        Optional<Question> oq = questionRepository.findById(1);
        assertTrue(oq.isPresent());
        Question q = oq.get();

        Answer answer = new Answer();
        answer.setQuestion(q);
        answer.setContent("새로 생성된 답변입니다.");
        answer.setCreateDate(LocalDateTime.now());
        answerRepository.save(answer);

    }
    @Test
    void create_answer2(){
        Optional<Question> oq = questionRepository.findById(2);
        assertTrue(oq.isPresent());
        Question q = oq.get();

        Answer a = new Answer();
        a.setContent("답변 ID도 자동 생성이 되나요?");
        a.setCreateDate(LocalDateTime.now());
        a.setQuestion(q);
        answerRepository.save(a);
    }

    @Test
    void check_answer() {
        Optional<Answer> oa = answerRepository.findById(2);
        assertTrue(oa.isPresent());
        Answer a = oa.get();
        assertEquals(2, a.getQuestion().getId());
        System.out.println("답변 테이블에");
    }



}
