package com.siuuuu.establers.service;

import com.siuuuu.establers.domain.Article;
import com.siuuuu.establers.dto.AddArticleRequest;
import com.siuuuu.establers.dto.ArticleResponse;
import com.siuuuu.establers.dto.UpdateArticleRequest;
import com.siuuuu.establers.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor    // final, notnull이 붙은 필드 생성자 추가
@Service // bean
public class BlogService {
    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request){
        return blogRepository.save(request.toEntity());
    }

    public List<Article> findAll(){
        return blogRepository.findAll();
    }

    public Article findById(long id){
        return blogRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("not found : " + id));
    }

    public void delete(long id){
        blogRepository.deleteById(id);
    }

    @Transactional
    public Article update(long id, UpdateArticleRequest  request){
        Article article = blogRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("NOT found : " + id));

        article.update(request.getTitle(), request.getContent());
        return article;
    }
}
