package com.app.webnest.api.privateapi;

import com.app.webnest.domain.dto.ApiResponseDTO;
import com.app.webnest.domain.dto.QuizResponseDTO;
import com.app.webnest.domain.vo.QuizVO;
import com.app.webnest.exception.QuizException;
import com.app.webnest.service.QuizService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/*")
@RequiredArgsConstructor
@Slf4j
public class QuizApi {

    private final QuizService quizService;

    @PostMapping("/quiz")
    public ResponseEntity<ApiResponseDTO<Map<String, Object>>> getQuizList(@RequestBody(required = false) Map<String,Object> params) {
        // 쿼리에 넘길 Map 구성

        if (params == null) params = new HashMap<>();
//        전체 문제 수
        // 안전한 파싱 및 기본값
        String quizLanguage = params.get("quizLanguage") == null ? null : String.valueOf(params.get("quizLanguage"));
        String quizDifficult = params.get("quizDifficult") == null ? null : String.valueOf(params.get("quizDifficult"));
        String keyword = params.get("keyword") == null ? null : String.valueOf(params.get("keyword"));

        int page = 1;
        if (params.get("page") != null) {
            try {
                page = Integer.parseInt(String.valueOf(params.get("page"))); // 들어오는 현재페이지번호 ex) Object타입의 "1" String으로 형변환후 Integer로 다시 형변환해서 처리
                if (page < 1) page = 1;
            } catch (NumberFormatException ignored) {}
        }

//                 화면에서 받아올 값
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("quizLanguage", quizLanguage);
        filters.put("quizDifficult", quizDifficult);
        filters.put("keyword", keyword);
        filters.put("page", page); // 매퍼에서 page로 OFFSET 계산

        List<QuizVO> findQuizList = quizService.quizDirection(filters); // service에서 매퍼 호출
        if (findQuizList == null) findQuizList = new ArrayList<>();
        Long quizTotalCount = quizService.quizCount(filters);
        Map<String,Object> data = new HashMap<>();
        data.put("findQuizList", findQuizList);
        data.put("quizTotalCount", quizTotalCount);

        return ResponseEntity.ok(ApiResponseDTO.of("문제리스트 불러오기", data));

    };

    @GetMapping("/workspace/quiz/{id}")
    public ResponseEntity<ApiResponseDTO<QuizVO>> getQuizById(@PathVariable("id") Long id) {
        QuizVO findQuiz = quizService.findQuizById(id);
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponseDTO.of("문제상세조회",  findQuiz));
    }

    @PostMapping("/quiz/all")
    public ResponseEntity<ApiResponseDTO<List<QuizVO>>> getAllQuizList() {
        List<QuizVO>quizList = quizService.quizList();
        return ResponseEntity.status(HttpStatus.OK).body(ApiResponseDTO.of("전체문제조회", quizList));
    }

    @PostMapping("/quiz/expectation")
    public ResponseEntity<ApiResponseDTO> getQuizExpectation(@RequestBody QuizResponseDTO  quizResponseDTO) {
        Long id = quizResponseDTO.getQuizId();
        String code = quizResponseDTO.getCode();
        String language = quizResponseDTO.getLanguage();
        String defaultExpectation = quizService.findQuizExpectationById(id);

        log.info("코드실행: id={}, language={}, code={}", id, language, code);
        if(quizResponseDTO.getQuizId() == null){
            throw new QuizException("잘못된 요청입니다.");
        }
        if(!language.equals(quizService.findQuizById(id).getQuizLanguage())){
            throw new QuizException("잘못된 언어입니다.");
        }
//        js코드는 자바에서 JS코드 실행시켜주는 메서드 실행시켜서 그 결과값을 data로 보내고
//        리액트에 output으로 반환해서 콘솔창에 출력시켜줌
//        java코드는 여기서 실행시켜서 콘솔 결과값을 data로 보내고 동일?
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName(language);

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponseDTO.of("실행 성공", true));
    }
}
