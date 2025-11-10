package com.app.webnest.api.privateapi.quizapi;

import com.app.webnest.domain.dto.ApiResponseDTO;
import com.app.webnest.domain.vo.QuizVO;
import com.app.webnest.service.quiz.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/*")
@RequiredArgsConstructor
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

}
