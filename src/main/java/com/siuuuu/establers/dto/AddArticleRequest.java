package com.siuuuu.establers.dto;

import com.siuuuu.establers.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddArticleRequest {
    private String title;
    private String content;

    // DTO를 엔티티로 만들어주는 메서드
    public Article toEntity(){
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}


